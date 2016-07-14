package com.jingjiang.baidumusic.widget.single;

import android.content.Context;

import com.jingjiang.baidumusic.base.MyApplication;
import com.litesuits.orm.LiteOrm;

/**
 * Created by dllo on 16/7/9.
 * 懒汉式
 */
public class SingleLiteOrm {
    private static SingleLiteOrm singleLiteOrm = new SingleLiteOrm();
    private LiteOrm liteOrm;
    private Context context;

    public SingleLiteOrm() {
        context = MyApplication.getContext();
    }

    public static SingleLiteOrm getSingleLiteOrm() {
        return singleLiteOrm;
    }

    public LiteOrm getLiteOrm() {
        if (liteOrm == null) {
            liteOrm = LiteOrm.newCascadeInstance(context, "localName.db");
        }
        return liteOrm;
    }
}
