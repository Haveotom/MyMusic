package com.jingjiang.baidumusic.inmusicradio.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.jingjiang.baidumusic.inmusicradio.fragment.RadioMoreFragment;

/**
 * Created by dllo on 16/7/6.
 */
public class RadioMoreAdapter extends FragmentStatePagerAdapter {
    private String[] titles = {"语言", "主题", "活动", "曲风", "年代", "心情"};

    public RadioMoreAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return RadioMoreFragment.getFragment(position);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
