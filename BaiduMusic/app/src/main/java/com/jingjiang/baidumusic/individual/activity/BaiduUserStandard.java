package com.jingjiang.baidumusic.individual.activity;

import android.view.View;

import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.base.BaseActivity;

/**
 * Created by dllo on 16/7/9.
 */
public class BaiduUserStandard extends BaseActivity {
    @Override
    protected int getLayout() {
        return R.layout.activity_baidu_user_standard;
    }

    @Override
    protected void initView() {
        bindView(R.id.activity_baidu_user_return_iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void initData() {

    }
}
