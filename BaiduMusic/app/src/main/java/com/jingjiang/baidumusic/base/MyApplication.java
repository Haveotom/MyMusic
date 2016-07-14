package com.jingjiang.baidumusic.base;

import android.app.Application;
import android.content.Context;

import cn.bmob.v3.Bmob;

//import cn.bmob.v3.Bmob;

/**
 * Created by dllo on 16/6/21.
 * 我们自己注册的application
 * 在使用的时候 还需要在清单文件中进行注册
 */
public class MyApplication extends Application {
    //注意是public
    public static Context context;
    //Application创建的原因是我们需要一个属于自己的大"环境",即Context
    //保证自己的App拥有单独的一个context对象


    //第一个声明周期中我们对context赋值
    @Override
    public void onCreate() {
        super.onCreate();
        //有两种初始化的方法
        /*       第一种      */
        //this代表当前的环境
        context = this;//赋值为this,context便有值
        //后面的是注册时的Application ID
        Bmob.initialize(this, "040416333fbace4cbf32b6c78bf547d5");

    }

    //对外提供一个方法,这个方法就是让别的类获取自己的context
    public static Context getContext() {
        return context;
    }
}
