package com.jingjiang.baidumusic.individual.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.activity.IndividualActivity;
import com.jingjiang.baidumusic.base.BaseActivity;
import com.jingjiang.baidumusic.base.MyApplication;
import com.jingjiang.baidumusic.widget.eventbus.FinishILikeEvent;
import com.jingjiang.baidumusic.widget.eventbus.InforFinishEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

//import cn.bmob.v3.Bmob;
//import cn.bmob.v3.BmobUser;
//import cn.bmob.v3.listener.SaveListener;

/**
 * Created by dllo on 16/6/29.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private ImageView returnIv, eyesIv;
    private TextView registerTv;
    private EditText phoneEt, passwordEt;
    private Button loginBtn;

    //在oncreate中初始化Bmob功能
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //有两种初始化的方法
        EventBus.getDefault().register(this);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getFinish(InforFinishEvent event) {
        int a = event.getType();
        finish();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_individual_login;
    }

    @Override
    protected void initView() {
        bindView(R.id.activity_login_return_iv).setOnClickListener(this);
        bindView(R.id.activity_login_password_eyes).setOnClickListener(this);
        phoneEt = bindView(R.id.activity_login_phone_et);
        passwordEt = bindView(R.id.activity_login_password_et);
        loginBtn = bindView(R.id.activity_login_login_btn);
        registerTv = bindView(R.id.activity_login_to_register_tv);
        registerTv.setOnClickListener(this);
        phoneEt.setOnClickListener(this);
        passwordEt.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
        registerTv.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//下划线
        registerTv.getPaint().setAntiAlias(true);//抗锯齿

    }

    @Override
    protected void initData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_login_password_eyes:
                break;
            case R.id.activity_login_login_btn:
                toLogin();
                break;
            case R.id.activity_login_to_register_tv:
                startActivity(new Intent(this, RegisterActivity.class));
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                break;
            case R.id.activity_login_return_iv:
                int finish = 1;
                EventBus.getDefault().post(new FinishILikeEvent(finish));
                finish();
                break;

        }
    }

    private void toLogin() {
        String phone, password;
        phone = phoneEt.getText().toString();
        password = passwordEt.getText().toString();
        final BmobUser bmobUser = new BmobUser();
        bmobUser.setUsername(phone);
        bmobUser.setPassword(password);
        bmobUser.login(this, new SaveListener() {
            @Override
            public void onSuccess() {//好好听歌
                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MyApplication.context, IndividualActivity.class));
                int a = 1;
                EventBus.getDefault().post(new InforFinishEvent(a));
                finish();
            }

            @Override
            public void onFailure(int i, String s) {

                Toast.makeText(LoginActivity.this, "登录失败,账号或密码错误.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
