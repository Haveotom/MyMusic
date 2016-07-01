package com.jingjiang.baidumusic.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.ImageView;

import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.adapter.MainAdapter;
import com.jingjiang.baidumusic.base.BaseFragment;
import com.jingjiang.baidumusic.fragment.KSongFragment;
import com.jingjiang.baidumusic.fragment.LiveFragment;
import com.jingjiang.baidumusic.fragment.MyMusicFragment;
import com.jingjiang.baidumusic.fragment.MusicLibraryFragment;
import com.jingjiang.baidumusic.fragment.SearchFragment;
import com.jingjiang.baidumusic.widget.OnDrawerListener;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends BaseFragment implements View.OnClickListener, OnDrawerListener {
    private MainAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> fragments;
    private KSongFragment kSongFragment;
    private LiveFragment liveFragment;
    private ImageView individualIv, searchIv;
    private DrawerLayout searchDrawer;//抽屉
    private SearchFragment searchFragment;


    @Override
    protected int initLayout() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView() {
        //初始化
        tabLayout = bindView(R.id.activity_main_tablayout);
        viewPager = bindView(R.id.activity_main_viewpager);
        individualIv = bindView(R.id.activity_main_singer);
        individualIv.setOnClickListener(this);
        adapter = new MainAdapter(getChildFragmentManager());
        initFragments();
        adapter.setFragments(fragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        searchIv = bindView(R.id.activity_main_search);
        searchIv.setOnClickListener(this);
        searchDrawer = bindView(R.id.activity_main_drawer);
        searchDrawer.setOnClickListener(this);
        //关闭滑动模式
        searchDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        initDrawerFragment();

    }

    //在抽屉中套一个fragment,替换占位布局
    private void initDrawerFragment() {
        searchFragment = new SearchFragment();
        //关闭
        searchFragment.setOnDrawerListener(this);
        getChildFragmentManager().beginTransaction().
                add(R.id.fragment_main_framelayout, searchFragment).commit();

    }

    private void initFragments() {
        kSongFragment = new KSongFragment();
        liveFragment = new LiveFragment();
        fragments = new ArrayList<>();
        fragments.add(new MyMusicFragment());
        fragments.add(new MusicLibraryFragment());
        fragments.add(kSongFragment);
        fragments.add(liveFragment);
        kSongFragment.setOnDrawerListener(this);
    }

    @Override
    protected void initData() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 2) {
                    kSongFragment.setScroll();
                }
                if (position == 3) {
                    liveFragment.setScroll();

                }
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 2) {
                    kSongFragment.setScroll();
                }
                if (position == 3) {
                    liveFragment.setScroll();

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == 2) {
                    kSongFragment.setScroll();
                }
                if (state == 3) {
                    liveFragment.setScroll();

                }

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_main_singer:
                startActivity(new Intent(getContext(), IndividualActivity.class));
                getActivity().overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                break;
            case R.id.activity_main_search:
                initData();
                searchDrawer.openDrawer(GravityCompat.END);//打开抽屉
                searchDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);//开启手势滑动
                break;
        }
    }


    @Override
    public void closeDrawer() {
        if (searchDrawer.isDrawerOpen(GravityCompat.END)) {
            searchDrawer.closeDrawer(GravityCompat.END);
            initData();
        }
    }

    @Override
    public void openDrawer() {
        searchDrawer.openDrawer(GravityCompat.END);
    }

}