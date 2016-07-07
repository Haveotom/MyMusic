package com.jingjiang.baidumusic.showsong.fragment;

import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.base.BaseFragment;
import com.jingjiang.baidumusic.widget.eventbus.PlaySongEvent;
import com.jingjiang.baidumusic.widget.single.SingleQueue;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/7/2.
 */
public class PictureFragment extends BaseFragment {
    private ImageView pictureIv;
    private String picture;

    @Override
    protected int initLayout() {
        return R.layout.showsong_f_picture;
    }

    public PictureFragment() {

        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getImage(PlaySongEvent event) {
        picture = event.getData().getSonginfo().getPic_huge();
    }

    @Override
    protected void initView() {
        pictureIv = bindView(R.id.showsong_f_picture_big_icon_iv);
        SingleQueue.getSingleQueue(getContext()).getImageLoader()
                .get(picture, ImageLoader.getImageListener(pictureIv, R.mipmap.default_album_playing, R.mipmap.default_album_playing));
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
