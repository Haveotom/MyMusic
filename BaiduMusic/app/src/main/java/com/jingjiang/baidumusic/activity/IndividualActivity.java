package com.jingjiang.baidumusic.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.base.BaseActivity;
import com.jingjiang.baidumusic.individual.activity.ActiveActivity;
import com.jingjiang.baidumusic.individual.activity.LoginActivity;
import com.jingjiang.baidumusic.individual.activity.SetActivity;
import com.jingjiang.baidumusic.individual.activity.UseActivtity;

/**
 * Created by dllo on 16/6/24.
 */
public class IndividualActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout setLl, activeLl, useLl;
    private TextView loginTv;


    @Override
    protected int getLayout() {
        return R.layout.activity_individual;
    }

    @Override
    protected void initView() {
        setLl = bindView(R.id.activity_individual_set_ll);
        activeLl = bindView(R.id.activity_individual_activity_ll);
        useLl = bindView(R.id.activity_individual_use_ll);
        loginTv = bindView(R.id.activity_individual_login_tv);
        bindView(R.id.activity_individual_fanhui_ll).setOnClickListener(this);
        loginTv.setOnClickListener(this);
        setLl.setOnClickListener(this);
        activeLl.setOnClickListener(this);
        useLl.setOnClickListener(this);


    }

    @Override
    protected void initData() {
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_individual_fanhui_ll:
                finish();
                break;
            case R.id.activity_individual_login_tv:
                startActivity(new Intent(this, LoginActivity.class));
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                break;
            case R.id.activity_individual_set_ll:
                startActivity(new Intent(this, SetActivity.class));
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

                break;
            case R.id.activity_individual_activity_ll:
                startActivity(new Intent(this, ActiveActivity.class));
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

                break;
            case R.id.activity_individual_use_ll:
                startActivity(new Intent(this, UseActivtity.class));
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

                break;

        }
    }
}
