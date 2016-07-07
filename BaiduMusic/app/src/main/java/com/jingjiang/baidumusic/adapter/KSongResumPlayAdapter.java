package com.jingjiang.baidumusic.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jingjiang.baidumusic.widget.myinterface.OnViewPagerClickListener;

import java.util.ArrayList;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/6/24.
 */
public class KSongResumPlayAdapter extends PagerAdapter {
    private ArrayList<String> imgUrls;
    private Context context;
    private OnViewPagerClickListener listener;

    public void setListener(OnViewPagerClickListener listener) {
        this.listener = listener;
    }

    public KSongResumPlayAdapter(Context context) {
        this.context = context;
    }

    public void setImgUrls(ArrayList<String> imgUrls) {
        this.imgUrls = imgUrls;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        //设置无上限的数量,可以无限的滑动
        return imgUrls != null ? Integer.MAX_VALUE : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        //取出指定位置的ImageView
        ImageView imageView = new ImageView(context);
        Picasso.with(context).load(imgUrls.get(position % imgUrls.size())).into(imageView);
        /**
         * 当图片少的时候不会触发destroyItem
         * 这个时候去向container中addView会报错
         * 手动捕捉异常
         */
        try {
            container.addView(imageView);
        } catch (IllegalStateException e) {
            //从container中移除ImageView
            container.removeView(imageView);
            //再次添加
            container.addView(imageView);
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v, position);
            }
        });


        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (container.getChildAt(position % imgUrls.size()) == object) {
            //销毁指定位置的ImageView回收内存
            container.removeViewAt(position % imgUrls.size());
        }
    }

}
