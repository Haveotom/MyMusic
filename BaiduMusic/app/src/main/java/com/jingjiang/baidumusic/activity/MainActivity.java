package com.jingjiang.baidumusic.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ScrollView;

import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.adapter.MainAdapter;
import com.jingjiang.baidumusic.base.BaseActivity;
import com.jingjiang.baidumusic.fragment.KSongFragment;
import com.jingjiang.baidumusic.fragment.LiveFragment;
import com.jingjiang.baidumusic.fragment.MyMusicFragment;
import com.jingjiang.baidumusic.fragment.MusicLibraryFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private MainAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> fragments;
    private KSongFragment kSongFragment = new KSongFragment();

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        //初始化
        tabLayout = bindView(R.id.activity_main_tablayout);
        viewPager = bindView(R.id.activity_main_viewpager);
        adapter = new MainAdapter(getSupportFragmentManager());
        initFragments();
        adapter.setFragments(fragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void initFragments() {
        fragments = new ArrayList<>();
        fragments.add(new MyMusicFragment());
        fragments.add(new MusicLibraryFragment());
        fragments.add(new KSongFragment());
        fragments.add(new LiveFragment());
    }

    @Override
    protected void initData() {
//        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                //当滑动时
//                switch (position) {
//                    case 2:
//                        kSongFragment.initScrollTo();
//                        break;
//                }
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });

    }
}
