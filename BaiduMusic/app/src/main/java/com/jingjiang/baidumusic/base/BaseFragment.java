package com.jingjiang.baidumusic.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dllo on 16/6/21.
 * 基类fragment
 */
public abstract class BaseFragment extends Fragment {
    //创建的Context对象
    private Context context;

    /**
     * Context 从依附的Activity上获取context对象
     *
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        context = context;
    }

    /**
     * 初始化视图
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(initLayout(), container, false);
    }

    /**
     * 加载组件
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    /**
     * 加载数据
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 加载布局的方法
     *
     * @return
     */
    protected abstract int initLayout();

    /**
     * 加载组件的抽象方法
     */
    protected abstract void initView();

    /**
     * 加载数据的方法
     */
    protected abstract void initData();

    /**
     * 使组件实例化的时候不需要转型
     *
     * @param id
     * @param <T>
     * @return 这样使用这个方法的时候是不需要转型的
     */
    protected <T extends View> T bindView(int id) {
        return (T) getView().findViewById(id);
    }
}
