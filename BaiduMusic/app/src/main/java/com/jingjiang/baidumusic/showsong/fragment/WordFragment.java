package com.jingjiang.baidumusic.showsong.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.base.BaseFragment;
import com.jingjiang.baidumusic.widget.eventbus.SeekBarTimeEvent;
import com.jingjiang.baidumusic.widget.view.LyricView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by dllo on 16/7/2.
 */
public class WordFragment extends BaseFragment {
    private LyricView lyricView;

    @Override
    protected int initLayout() {
        return R.layout.showsong_f_word;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        lyricView = bindView(R.id.showsong_f_word_lyric_lv);

    }

    public void loadLrc(String lrc) {
        lyricView.loadLrc(lrc);
    }

    public void onUpdate(int progress) {
        if (lyricView.hasLrc()) {
            //当前时间  更新进度
            lyricView.updateTime(progress);

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getLyric(SeekBarTimeEvent event) {
        //得到歌词
        loadLrc(event.getLyric());
        //刷新歌词进度
        onUpdate((int) event.getCurrentTime());

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
