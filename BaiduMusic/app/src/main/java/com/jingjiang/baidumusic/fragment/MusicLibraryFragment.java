package com.jingjiang.baidumusic.fragment;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.adapter.MusicLibraryAdapter;
import com.jingjiang.baidumusic.base.BaseFragment;
import com.jingjiang.baidumusic.inmusiclibrary.fragment.MVFragment;
import com.jingjiang.baidumusic.inmusiclibrary.fragment.RadioFragment;
import com.jingjiang.baidumusic.inmusiclibrary.fragment.RankListFragment;
import com.jingjiang.baidumusic.inmusiclibrary.fragment.RecommendFragment;
import com.jingjiang.baidumusic.inmusiclibrary.fragment.SongMenuFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/6/21.
 */
public class MusicLibraryFragment extends BaseFragment {
    private List<Fragment> fragments;
    private MusicLibraryAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected int initLayout() {
        return R.layout.fragment_musiclibrary;
    }

    @Override
    protected void initView() {
        tabLayout = bindView(R.id.fragment_musiclibrary_tablayout);
        viewPager = bindView(R.id.fragment_musiclibrary_viewpager);
        adapter = new MusicLibraryAdapter(getChildFragmentManager());//必须用child的管理者
        initFragments();
        adapter.setFragments(fragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void initFragments() {
        fragments = new ArrayList<>();
        fragments.add(new RecommendFragment());
        fragments.add(new RankListFragment());
        fragments.add(new SongMenuFragment());
        fragments.add(new RadioFragment());
        fragments.add(new MVFragment());
    }

    @Override
    protected void initData() {

    }
}
