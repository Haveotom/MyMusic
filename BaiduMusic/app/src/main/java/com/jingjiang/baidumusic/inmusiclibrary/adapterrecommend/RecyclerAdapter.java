package com.jingjiang.baidumusic.inmusiclibrary.adapterrecommend;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jingjiang.baidumusic.inmusiclibrary.bean.RecommendData;

import java.util.ArrayList;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/6/25.
 */
public class RecyclerAdapter extends PagerAdapter {
    private Context context;
    private ArrayList<String> iconUrls;

    public RecyclerAdapter(Context context) {
        this.context = context;
    }

    public void setIconUrls(ArrayList<String> iconUrls) {
        this.iconUrls = iconUrls;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return iconUrls != null ? Integer.MAX_VALUE : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        Picasso.with(context).load(iconUrls.get(position % iconUrls.size())).into(imageView);
        imageView.setPadding(10,0,0,0);
        /**
         * 当图片少的时候不会触发destroyItem
         * 这个时候去向container中addView会报错
         * 手动捕捉异常
         */
        try {
            container.addView(imageView);

        } catch (IllegalStateException e) {
            //先移除
            container.removeView(imageView);
            //再添加
            container.addView(imageView);
        }
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (container.getChildAt(position % iconUrls.size()) == object) {
            container.removeViewAt(position % iconUrls.size());
        }
    }
}
