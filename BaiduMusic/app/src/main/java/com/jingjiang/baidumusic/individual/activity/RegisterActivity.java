package com.jingjiang.baidumusic.individual.activity;

import android.content.Intent;
import android.graphics.Paint;
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
import com.jingjiang.baidumusic.widget.eventbus.InforFinishEvent;

import org.greenrobot.eventbus.EventBus;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by dllo on 16/7/9.
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private ImageView returnIv, eyesIv;
    private EditText phoneEt, passwordEt;
    private Button registerBtn;
    private TextView agreeTv;

    @Override
    protected int getLayout() {
        return R.layout.activity_individual_register;
    }

    @Override
    protected void initView() {
        bindView(R.id.activity_register_return_iv).setOnClickListener(this);
        phoneEt = bindView(R.id.activity_register_phone_et);
        passwordEt = bindView(R.id.activity_register_password_et);
        registerBtn = bindView(R.id.activity_register_register_btn);
        agreeTv = bindView(R.id.activity_register_user_agree_tv);
        phoneEt.setOnClickListener(this);
        passwordEt.setOnClickListener(this);
        agreeTv.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
        agreeTv.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//下划线
        agreeTv.getPaint().setAntiAlias(true);//抗锯齿

    }

    @Override
    protected void initData() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_register_register_btn:
                toRegister();
                break;
            case R.id.activity_register_return_iv:
                finish();
                break;
            case R.id.activity_register_user_agree_tv:
                startActivity(new Intent(this, BaiduUserStandard.class));
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

                break;

        }
    }


    private void toRegister() {
        String phone, password;
        phone = phoneEt.getText().toString();
        password = passwordEt.getText().toString();
        if (phone.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();

        } else {
            BmobUser bmobUser = new BmobUser();
            bmobUser.setUsername(phone);
            bmobUser.setPassword(password);
            bmobUser.signUp(this, new SaveListener() {
                @Override
                public void onSuccess() {
                    Toast.makeText(RegisterActivity.this, "注册成功-请享受您的音乐之旅吧!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MyApplication.context, IndividualActivity.class));
                    int a = 1;
                    EventBus.getDefault().post(new InforFinishEvent(a));
                    finish();
                }

                @Override
                public void onFailure(int i, String s) {
                    Toast.makeText(RegisterActivity.this, "注册失败,请继续尝试...", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
