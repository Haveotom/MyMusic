package com.jingjiang.baidumusic.inmymusic.fragment;

import android.view.View;

import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.base.BaseFragment;

/**
 * Created by dllo on 16/7/11.
 */
public class MyKSongFragment extends BaseFragment {
    @Override
    protected int initLayout() {
        return R.layout.mymusic_f_myksong;
    }

    @Override
    protected void initView() {
        bindView(R.id.mymusic_f_myksong_return_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

    }

    @Override
    protected void initData() {

    }
}
