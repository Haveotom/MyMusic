package com.jingjiang.baidumusic.fragment;

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
import com.jingjiang.baidumusic.widget.myinterface.OnClickSomeListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/6/21.
 */
public class MusicLibraryFragment extends BaseFragment implements OnClickSomeListener {
    private List<Fragment> fragments;
    private MusicLibraryAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private RecommendFragment recommendFragment;

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
        recommendFragment = new RecommendFragment();
        fragments = new ArrayList<>();
        fragments.add(recommendFragment);
        fragments.add(new RankListFragment());
        fragments.add(new SongMenuFragment());
        fragments.add(new RadioFragment());
        fragments.add(new MVFragment());
        recommendFragment.setOnclickSomeListener(this);
    }

    @Override
    protected void initData() {

    }


    @Override
    public void onClickSome() {
        viewPager.setCurrentItem(2);
    }
}
