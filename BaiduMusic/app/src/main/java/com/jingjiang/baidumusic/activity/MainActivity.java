package com.jingjiang.baidumusic.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.base.BaseActivity;
import com.jingjiang.baidumusic.inmusiclibrary.fragment.RadioFragment;
import com.jingjiang.baidumusic.inmusiclibrary.fragment.RankListDetailFragment;
import com.jingjiang.baidumusic.inmusiclibrary.fragment.RankListFragment;
import com.jingjiang.baidumusic.widget.OnFragmentSkipListener;


/**
 * Created by dllo on 16/6/25.
 */
public class MainActivity extends BaseActivity implements OnFragmentSkipListener {
    private RankListFragment rankListFragment;
    private RadioFragment radioFragment;


    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }


    @Override
    protected void initData() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_main_framelayout, new MainFragment()).commit();
        initRankList();
    }


    private void initRankList() {
        rankListFragment = new RankListFragment();
        rankListFragment.setFragmentSkipListener(this);

    }

    @Override
    public void toSkipFragment() {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.activity_in, R.anim.activity_out)
                .replace(R.id.activity_main_framelayout, new RankListDetailFragment())
                .addToBackStack(null)
                .commit();
    }


}
