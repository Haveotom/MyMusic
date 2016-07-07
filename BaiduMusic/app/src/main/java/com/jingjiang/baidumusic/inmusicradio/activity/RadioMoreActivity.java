package com.jingjiang.baidumusic.inmusicradio.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.base.BaseActivity;
import com.jingjiang.baidumusic.inmusicradio.adapter.RadioMoreAdapter;

/**
 * Created by dllo on 16/7/6.
 */
public class RadioMoreActivity extends BaseActivity implements View.OnClickListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageView closeIv;
    private RadioMoreAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_music_radio_more;
    }

    @Override
    protected void initView() {
        adapter = new RadioMoreAdapter(getSupportFragmentManager());
        tabLayout = bindView(R.id.activity_music_radio_rablayout);
        viewPager = bindView(R.id.activity_music_radio_viewpager);
        closeIv = bindView(R.id.activity_music_radio_close);
        closeIv.setOnClickListener(this);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_music_radio_close:
                finish();
                break;
        }
    }
}
