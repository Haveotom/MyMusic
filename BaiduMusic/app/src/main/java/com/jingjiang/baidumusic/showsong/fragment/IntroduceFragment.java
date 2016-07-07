package com.jingjiang.baidumusic.showsong.fragment;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.base.BaseFragment;
import com.jingjiang.baidumusic.widget.eventbus.PlaySongEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/7/2.
 */
public class IntroduceFragment extends BaseFragment {
    private TextView nameTv, albumTv;
    private ImageView pictureIv;
    private String name, album,picture;

    @Override
    protected int initLayout() {
        return R.layout.showsong_f_introduce;
    }

    public IntroduceFragment() {
//        int aInt = 2;
//        EventBus.getDefault().post(new IntroduceEvent(aInt));
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getInformation(PlaySongEvent event) {
        Log.d("IntroduceFragment", event.getData().getSonginfo().getTitle());
        name = event.getData().getSonginfo().getAuthor();
        album = event.getData().getSonginfo().getAlbum_title();
        picture = event.getData().getSonginfo().getPic_big();

    }

    @Override
    protected void initView() {
        nameTv = bindView(R.id.showsong_f_introduce_name_tv);
        albumTv = bindView(R.id.showsong_f_introduce_album_tv);
        pictureIv = bindView(R.id.showsong_f_introduce_icon_iv);
        nameTv.setText("歌手 : "+name);
        albumTv.setText("专辑 : "+album);
        Picasso.with(getContext()).load(picture).error(R.mipmap.zzz).into(pictureIv);

    }


    @Override
    protected void initData() {

    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
