package com.jingjiang.baidumusic.inmusiclibrary.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.base.BaseActivity;
import com.jingjiang.baidumusic.widget.view.CommonVideoView;

/**
 * Created by dllo on 16/7/4.
 */
public class MVDetailActivity extends BaseActivity {
    private CommonVideoView videoView;
    private ImageView returnIv;
    private TextView songTv;

    @Override

    protected int getLayout() {
        return R.layout.detail_f_music_mv;
    }

    @Override
    protected void initView() {
        videoView = bindView(R.id.detail_music_mv_videoview);
        songTv = bindView(R.id.detail_f_music_mv_song_tv);
        returnIv = bindView(R.id.detail_f_music_mv_return_iv);
        returnIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        String url = intent.getStringExtra("MV");
        String name = intent.getStringExtra("NAME");
        Log.d("MVDetailActivity", url);
        Log.d("MVDetailActivity", name + "");
        songTv.setText(name);
        videoView.start(url);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {//结构,外形
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {//横向
            videoView.setFullScreen();
        } else {
            videoView.setNormalScreen();
        }

    }
}
