package com.jingjiang.baidumusic.showsong.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.base.BaseActivity;
import com.jingjiang.baidumusic.widget.eventbus.PauseEvent;
import com.jingjiang.baidumusic.widget.eventbus.PlaySongEvent;
import com.jingjiang.baidumusic.widget.eventbus.SeekBarTimeEvent;
import com.jingjiang.baidumusic.widget.eventbus.TypeEvent;
import com.jingjiang.baidumusic.widget.service.PlaySongService;
import com.jingjiang.baidumusic.showsong.adapter.ShowSongAdapter;
import com.jingjiang.baidumusic.showsong.fragment.IntroduceFragment;
import com.jingjiang.baidumusic.showsong.fragment.PictureFragment;
import com.jingjiang.baidumusic.showsong.fragment.WordFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/7/2.
 */
public class ShowSongActivity extends BaseActivity implements View.OnClickListener {
    private int[] ids = {R.id.activity_showsong_return_iv, R.id.activity_showsong_word_iv, R.id.activity_showsong_last_iv, R.id.activity_showsong_next_iv, R.id.activity_showsong_play_iv, R.id.activity_showsong_pause_iv,
            R.id.activity_showsong_menu_iv, R.id.activity_showsong_order_iv, R.id.activity_showsong_title_tv, R.id.activity_showsong_author_tv, R.id.activity_showsong_start_time_tv, R.id.activity_showsong_end_time_tv};
    private ShowSongAdapter showSongAdapter;
    private List<Fragment> fragments;
    private ViewPager viewPager;
    private ImageView returnIv, wordIv, lastIv, nextIv, playIv, menuIv, orderIv, pauseIv;
    private TextView titleTv, authorTv, startTv, endTv;
    private PlaySongService playSongService;
    private ServiceConnection connection;
    private PlaySongService.MyBinder myBinder;
    private SeekBar seekBar;
    private DateFormat dateFormat;
    //是否正在拖动
    private boolean isSeeking = false;
    private MediaPlayer mediaPlayer;
    private SharedPreferences sp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在oncreate 的时候  通知服务  去拿东西
        String type = "type";
        EventBus.getDefault().post(new TypeEvent(type));
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_showsong;
    }

    @Override
    protected void initView() {
        for (int i = 0; i < ids.length; i++) {
            bindView(ids[i]).setOnClickListener(this);
        }
        showSongAdapter = new ShowSongAdapter(getSupportFragmentManager());
        initFragment();
        viewPager = bindView(R.id.activity_showsong_viewpager);
        seekBar = bindView(R.id.activity_showsong_seekbar);
        titleTv = bindView(R.id.activity_showsong_title_tv);
        authorTv = bindView(R.id.activity_showsong_author_tv);
        startTv = bindView(R.id.activity_showsong_start_time_tv);
        endTv = bindView(R.id.activity_showsong_end_time_tv);
        playIv = bindView(R.id.activity_showsong_play_iv);
        pauseIv = bindView(R.id.activity_showsong_pause_iv);
//        playIv.callOnClick();
//        pauseIv.callOnClick();


        showSongAdapter.setFragments(fragments);
        viewPager.setAdapter(showSongAdapter);
        viewPager.setCurrentItem(1);
        wordIv = bindView(R.id.activity_showsong_word_iv);
        initViewPager();
        playSongService = new PlaySongService();

        //绑定服务
        Intent serviceIntent = new Intent(this, PlaySongService.class);
        startService(serviceIntent);
        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                //Ibinder是Service里返回回来的 return binder;
                //当绑定成功后,对Activity里的BInder对象赋值
                //让他和Service里的那个Binder对象是一个实体
                //这样,我们操作Activity里的Binder,就相当于操作了
                //Service里的Binder,就实现了Service和Activity
                //之间的通信
                myBinder = (PlaySongService.MyBinder) service;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                //当服务由于非正常原因,与Activity失去连接
                //则会调用该方法
                myBinder = null;
            }
        };

        //绑定服务
        //接收三个参数,Intent用来指明和哪个服务绑定
        //Connection对象用来帮助我们绑定服务
        //flag:通常都填写 BIND_AUTO_CREATE:即在绑定时
        //     如果服务未创建,就先创建服务,再绑定上
        bindService(serviceIntent, connection, BIND_AUTO_CREATE);
//        //注册EventBus
        EventBus.getDefault().register(this);

        mediaPlayer = new MediaPlayer();

        //seekbar
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser == true) {
                    //发送广播,,让服务接收,,来控制歌曲进度
                    Intent intent = new Intent("SeekBar_Control");
                    intent.putExtra("seekBar", progress);
                    sendBroadcast(intent);
                    mediaPlayer.seekTo(progress);
                }

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //开始拖动
                isSeeking = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //当拖动结束
                int progress = seekBar.getProgress();
                //让歌曲定位到某个位置

            }
        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getAuthorInformation(PlaySongEvent event) {

        titleTv.setText(event.getData().getSonginfo().getTitle());
        authorTv.setText(event.getData().getSonginfo().getAuthor());
    }

    //格式化时间
    public String formatTime(long time) {
        if (dateFormat == null) {
            dateFormat = new SimpleDateFormat("mm:ss");
        }
        return dateFormat.format(time);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getSeekBarInformation(SeekBarTimeEvent event) {
        seekBar.setMax((int) event.getAllTime());
        seekBar.setProgress((int) event.getCurrentTime());
        startTv.setText(formatTime(event.getCurrentTime()));
        endTv.setText(formatTime(event.getAllTime()));
    }


    @Override
    protected void initData() {
        //传值
        Intent intent = new Intent();
        //设置传播的键值对:
        intent.setAction(ACCESSIBILITY_SERVICE);
        //发送广播
        sendBroadcast(intent);
    }

    private void initViewPager() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (viewPager.getCurrentItem() == 2) {
                    wordIv.setImageResource(R.mipmap.bt_playpage_button_pic_normal);
                } else {
                    wordIv.setImageResource(R.mipmap.bt_playpage_button_lyric_normal);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_showsong_return_iv://返回
                finish();
                break;
            case R.id.activity_showsong_word_iv:
                if (viewPager.getCurrentItem() == 0 || viewPager.getCurrentItem() == 1) {
                    viewPager.setCurrentItem(2);
                } else {
                    viewPager.setCurrentItem(1);
                }
                break;
            case R.id.activity_showsong_play_iv:
                playIv.setVisibility(View.GONE);
                pauseIv.setVisibility(View.VISIBLE);
                myBinder.pause();
                EventBus.getDefault().post(new PauseEvent(1));
                break;
            case R.id.activity_showsong_pause_iv:
                playIv.setVisibility(View.VISIBLE);
                pauseIv.setVisibility(View.GONE);
                myBinder.start();
                break;
            case R.id.activity_showsong_order_iv:
                break;
            case R.id.activity_showsong_last_iv:
                break;
            case R.id.activity_showsong_next_iv:
                break;
            case R.id.activity_showsong_menu_iv:
                startActivity(new Intent(this, PlayMenuActivity.class));

                break;
        }

    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new IntroduceFragment());
        fragments.add(new PictureFragment());
        fragments.add(new WordFragment());

    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        unbindService(connection);
        super.onDestroy();
    }
}