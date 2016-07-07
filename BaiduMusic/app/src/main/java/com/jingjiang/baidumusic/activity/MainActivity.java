package com.jingjiang.baidumusic.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.base.BaseActivity;
import com.jingjiang.baidumusic.widget.eventbus.PlaySongEvent;
import com.jingjiang.baidumusic.inmusiclibrary.fragment.RadioFragment;
import com.jingjiang.baidumusic.inmusiclibrary.fragment.RankListDetailFragment;
import com.jingjiang.baidumusic.inmusiclibrary.fragment.RankListFragment;
import com.jingjiang.baidumusic.inmusiclibrary.fragment.RecommendFragment;
import com.jingjiang.baidumusic.inmusiclibrary.fragment.SongMenuDetailFragment;
import com.jingjiang.baidumusic.inmusiclibrary.fragment.SongMenuFragment;
import com.jingjiang.baidumusic.inmusicrecommend.fragment.AllSingerFragment;
import com.jingjiang.baidumusic.inmusicrecommend.fragment.AllSingerMoreFragment;
import com.jingjiang.baidumusic.inmusicrecommend.fragment.HappyMoreFragment;
import com.jingjiang.baidumusic.inmusicrecommend.fragment.NewCDMoreFragment;
import com.jingjiang.baidumusic.widget.service.PlaySongService;
import com.jingjiang.baidumusic.showsong.ShowSongActivity;
import com.jingjiang.baidumusic.widget.myinterface.OnFragmentSkipListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import it.sephiroth.android.library.picasso.Picasso;


/**
 * Created by dllo on 16/6/25.
 */
public class MainActivity extends BaseActivity implements OnFragmentSkipListener, View.OnClickListener {
    private RankListFragment rankListFragment;
    private SongMenuFragment songMenuFragment;
    private RecommendFragment recommendFragment;
    private AllSingerFragment allSongerFragment;
    private RadioFragment radioFragment;
    private ImageView iconIv, songMenuIv, playIv, pauseIv, nextIv;
    private TextView titleTv, nameTv;
    private ServiceConnection connection;
    private PlaySongService.MyBinder myBinder;
    private PlaySongService playSongService;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }


    @Override
    protected void initView() {
        iconIv = bindView(R.id.play_music_bar_icon_iv);//图片
        songMenuIv = bindView(R.id.play_music_bar_songmenu_iv);//歌单
        playIv = bindView(R.id.play_music_bar_play_iv);//播放
        pauseIv = bindView(R.id.play_music_bar_pause_iv);//暂停
        nextIv = bindView(R.id.play_music_bar_next_iv);//下一首
        titleTv = bindView(R.id.play_music_bar_title_iv);//歌名
        nameTv = bindView(R.id.play_music_bar_name_iv);//人名
        songMenuIv.setOnClickListener(this);
        playIv.setOnClickListener(this);
        pauseIv.setOnClickListener(this);
        nextIv.setOnClickListener(this);
        playSongService = new PlaySongService();
        bindView(R.id.play_music_bar_layout_rl).setOnClickListener(this);//播放小栏的点击
        //注册eventbus
        EventBus.getDefault().register(this);
        //绑定服务
        Intent serviceIntent = new Intent(this, PlaySongService.class);
        startService(serviceIntent);
        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {//Ibinder是Service里返回回来的 return binder;
                //当绑定成功后,对Activity里的BInder对象赋值
                //让他和Service里的那个Binder对象是一个实体
                //这样,我们操作Activity里的Binder,就相当于操作了
                //Service里的Binder,就实现了Service和Activity
                //之间的通信
                myBinder = (PlaySongService.MyBinder) service;//连接一起,便和服务中的binder是同一个了

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                //当服务由于非正常原因,与Activity失去连接
                //则会调用该方法
                myBinder = null;//置空

            }
        };
        //绑定服务
        //接收三个参数,Intent用来指明和哪个服务绑定
        //Connection对象用来帮助我们绑定服务
        //flag:通常都填写 BIND_AUTO_CREATE:即在绑定时
        //     如果服务未创建,就先创建服务,再绑定上
        bindService(serviceIntent, connection, BIND_AUTO_CREATE);
        initPlaySong();

    }

    /**
     * eventbus  接收传过来的播放条的信息
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEverySong(PlaySongEvent event) {

        titleTv.setText(event.getData().getSonginfo().getTitle());
        nameTv.setText(event.getData().getSonginfo().getAuthor());
        Picasso.with(this).load(event.getData().getSonginfo().getPic_small()).
                error(R.mipmap.img_minibar_default).into(iconIv);
        pauseIv.setVisibility(View.VISIBLE);
        playIv.setVisibility(View.GONE);
    }

    private void initPlaySong() {

    }
    //http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.song.getSmartSongList&page_no=1&page_size=50&scene_id=42&item_id=0&version=5.2.5&from=ios&channel=appstore

    @Override
    protected void initData() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_main_framelayout, new MainFragment()).commit();
        initReplaceFragment();

    }

    private void initReplaceFragment() {
        songMenuFragment = new SongMenuFragment();
        songMenuFragment.setSongMenuListener(this);
        rankListFragment = new RankListFragment();
        rankListFragment.setFragmentSkipListener(this);
        recommendFragment = new RecommendFragment();
        recommendFragment.setRecommendSkipListener(this);

        allSongerFragment = new AllSingerFragment();
        allSongerFragment.setAllSongerListener(this);

    }

    @Override
    public void toSkipFragment(int witch) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction().setCustomAnimations(R.anim.activity_in, R.anim.activity_out).addToBackStack(null);
        switch (witch) {
            case 1:
                transaction.replace(R.id.activity_main_framelayout, new RankListDetailFragment()).commit();
                break;
            case 2:
                transaction.replace(R.id.activity_main_framelayout, new SongMenuDetailFragment()).commit();
                break;
            case 3:
                transaction.replace(R.id.activity_main_framelayout, new AllSingerFragment()).commit();
                break;
            case 4:
                transaction.replace(R.id.activity_main_framelayout, new AllSingerMoreFragment()).commit();
                break;
            case 5:
                transaction.replace(R.id.activity_main_framelayout, new NewCDMoreFragment()).commit();
                break;
            case 6:
                transaction.replace(R.id.activity_main_framelayout, new HappyMoreFragment()).commit();
                break;

        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play_music_bar_play_iv://播放
                pauseIv.setVisibility(View.VISIBLE);
                playIv.setVisibility(View.GONE);
                playSongService.mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        playSongService.mediaPlayer.stop();
                    }
                });

                break;
            case R.id.play_music_bar_pause_iv:
                playIv.setVisibility(View.VISIBLE);
                pauseIv.setVisibility(View.GONE);
                break;
            case R.id.play_music_bar_songmenu_iv://歌单
                break;
            case R.id.play_music_bar_next_iv://下一首
                break;
            case R.id.play_music_bar_layout_rl://展开播放界面
                Intent intent = new Intent(this, ShowSongActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_up, R.anim.activity_no_active);

                break;
        }

    }

    @Override
    protected void onDestroy() {
        //在Activity的onDestroy里
        //当Activity与Service进行绑定后
        //如果该Activity destroy了,那么
        //服务就会自动的 断掉绑定
        //这个时候如果该服务没有任何客户端执行过
        //startService()方法,这个服务就会destroy
        //但是  在某些版本中,虽然Activity Destroy了
        //会正常的解绑服务,但是有可能会有错误日志的产生
        //所以,我们通常会在activity的onDestroy生命周期中
        //手动的调用一次unBindService()
        unbindService(connection);
        EventBus.getDefault().unregister(this);//取消注册
        super.onDestroy();
    }

}
