package com.jingjiang.baidumusic.showsong.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import java.util.List;

/**
 * Created by dllo on 16/7/2.
 */
public class ShowSongAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
        notifyDataSetChanged();
    }

    public ShowSongAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public int getCount() {
        return fragments != null ? fragments.size() : 0;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);

    }

}
