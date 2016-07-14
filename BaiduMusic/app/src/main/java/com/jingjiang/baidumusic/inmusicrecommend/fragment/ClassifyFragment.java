package com.jingjiang.baidumusic.inmusicrecommend.fragment;

import android.view.View;

import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.base.BaseFragment;

/**
 * Created by dllo on 16/7/8.
 */
public class ClassifyFragment extends BaseFragment implements View.OnClickListener {
    @Override
    protected int initLayout() {
        return R.layout.recommend_f_classify;
    }

    @Override
    protected void initView() {
        bindView(R.id.music_recommend_classify_fanhui_ll).setOnClickListener(this);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.music_recommend_classify_fanhui_ll:
                getFragmentManager().popBackStack();
                break;
        }
    }
}
