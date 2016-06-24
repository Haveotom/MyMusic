package com.jingjiang.baidumusic.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


/**
 * Created by dllo on 16/6/21.
 * 基类Activity
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initView();
        initData();

    }

    /**
     * 加载布局的抽象方法
     *
     * @return
     */
    protected abstract int getLayout();

    /**
     * 加载组件的抽象方法
     *
     * @return
     */
    protected abstract void initView();

    /**
     * 加载数据的抽象方法
     *
     * @return
     */
    protected abstract void initData();

    /**
     * 这个方法使组件实例化不需要转型
     *
     * @param id
     * @param <T>
     * @return
     */
    protected <T extends View> T bindView(int id) {
        return (T) findViewById(id);
    }


}
