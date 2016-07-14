package com.jingjiang.baidumusic.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.base.BaseActivity;
import com.jingjiang.baidumusic.base.MyApplication;
import com.jingjiang.baidumusic.individual.activity.ActiveActivity;
import com.jingjiang.baidumusic.individual.activity.LoginActivity;
import com.jingjiang.baidumusic.individual.activity.SetActivity;
import com.jingjiang.baidumusic.individual.activity.UseActivtity;
import com.jingjiang.baidumusic.widget.eventbus.InforFinishEvent;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cn.bmob.v3.BmobUser;

/**
 * Created by dllo on 16/6/24.
 */
public class IndividualActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout setLl, activeLl, useLl, loginLl;
    private TextView loginTv, nameTv, closeLoginTv;
    private RelativeLayout loginNoRl;


    @Override
    protected int getLayout() {
        return R.layout.activity_individual;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);

        bindView(R.id.activity_individual_fanhui_ll).setOnClickListener(this);
        setLl = bindView(R.id.activity_individual_set_ll);
        activeLl = bindView(R.id.activity_individual_activity_ll);
        useLl = bindView(R.id.activity_individual_use_ll);
        loginTv = bindView(R.id.activity_individual_login_tv);
        loginLl = bindView(R.id.activity_individual_login_yes_ll);
        loginNoRl = bindView(R.id.activity_individual_login_no_rl);
        nameTv = bindView(R.id.activity_individual_login_name_tv);
        closeLoginTv = bindView(R.id.activity_individual_close_login_tv);
        closeLoginTv.setOnClickListener(this);
        loginTv.setOnClickListener(this);
        setLl.setOnClickListener(this);
        activeLl.setOnClickListener(this);
        useLl.setOnClickListener(this);

        //登录状态
        BmobUser bmobUser = BmobUser.getCurrentUser(this);
        if (bmobUser == null) {
            loginLl.setVisibility(View.GONE);
            loginNoRl.setVisibility(View.VISIBLE);
        } else {
            loginNoRl.setVisibility(View.GONE);
            loginLl.setVisibility(View.VISIBLE);
            nameTv.setText(bmobUser.getUsername());

        }

    }


    //关闭该Activity
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getFinish(InforFinishEvent event) {
        int a = event.getType();
        finish();
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
                anim();
                break;
            case R.id.activity_individual_set_ll:
                startActivity(new Intent(this, SetActivity.class));
                anim();
                break;
            case R.id.activity_individual_activity_ll:
                startActivity(new Intent(this, ActiveActivity.class));
                anim();
                break;
            case R.id.activity_individual_use_ll:
                startActivity(new Intent(this, UseActivtity.class));
                anim();
                break;
            case R.id.activity_individual_close_login_tv:
                //点击出现dialog
                initExitDialog();
                break;
        }
    }

    private void anim() {
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

    }

    private void initExitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("账号切换");
        builder.setMessage("切换其他账号登录,将清除之前账号下的缓存歌曲,是否确认切换?");
        builder.setNegativeButton("确认退出", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                BmobUser.logOut(MyApplication.context);   //清除缓存用户对象

                loginLl.setVisibility(View.GONE);
                loginNoRl.setVisibility(View.VISIBLE);

            }
        }).setNeutralButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();

    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
