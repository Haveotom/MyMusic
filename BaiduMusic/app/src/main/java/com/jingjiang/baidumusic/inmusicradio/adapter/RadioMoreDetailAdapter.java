package com.jingjiang.baidumusic.inmusicradio.adapter;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.inmusicradio.data.RadioMoreDetailData;
import com.jingjiang.baidumusic.widget.service.PlaySongService;
import com.jingjiang.baidumusic.widget.single.SingleQueue;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by dllo on 16/7/6.
 */
public class RadioMoreDetailAdapter extends PagerAdapter {
    private Context context;
    private RadioMoreDetailData.ResultBean.SonglistBean data;
    private List<View> views;
    private List<RadioMoreDetailData.ResultBean.SonglistBean> songlistBeanList;
    private PlaySongService.MyBinder myBinder;
    private ServiceConnection connection;
    private int pos;
    private ImageView playIv;
    private ImageView pauseIv;

    public void setPos(int pos) {
        this.pos = pos;
    }

    public RadioMoreDetailAdapter(Context context) {
        this.context = context;
        //连接服务
        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                myBinder = (PlaySongService.MyBinder) service;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        Intent intent = new Intent(context, PlaySongService.class);
        context.bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    public void setSonglistBeanList(List<RadioMoreDetailData.ResultBean.SonglistBean> songlistBeanList) {
        this.songlistBeanList = songlistBeanList;
        views = new ArrayList<>();
        for (RadioMoreDetailData.ResultBean.SonglistBean songlistBean : songlistBeanList) {
            views.add(null);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return songlistBeanList != null ? songlistBeanList.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    //添加页卡
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        data = songlistBeanList.get(position);
        View view = LayoutInflater.from(context).inflate(R.layout.item_music_radio_detail,null);
        TextView titleTv, nameTv;
        final ImageView iconTv;
        titleTv = (TextView) view.findViewById(R.id.item_music_radio_more_detail_title_tv);
        nameTv = (TextView) view.findViewById(R.id.item_music_radio_more_detail_name_tv);
        iconTv = (ImageView) view.findViewById(R.id.item_music_radio_more_detail_image_iv);
        playIv = (ImageView) view.findViewById(R.id.item_music_radio_more_detail_play_iv);
        pauseIv = (ImageView) view.findViewById(R.id.item_music_radio_more_detail_pause_iv);
        //设置数据
        titleTv.setText(data.getTitle());
        nameTv.setText(data.getAuthor());
        if (iconTv != null) {
            SingleQueue.getSingleQueue(context).getImageLoader().get(data.getPic_huge(),
                    ImageLoader.getImageListener(iconTv, R.mipmap.default_album_playing, R.mipmap.default_album_playing));
        }
        //点击
        playIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myBinder.pause();//点击play的时候就暂停
                playIv.setVisibility(View.GONE);
                pauseIv.setVisibility(View.VISIBLE);



            }
        });
        pauseIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myBinder.start();//点击pause就开始播放
                playIv.setVisibility(View.VISIBLE);
                pauseIv.setVisibility(View.GONE);
            }
        });
        container.addView(view);
        views.set(position, view);
        return view;
    }


  //删除页卡
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //销毁指定位置的pager回收内存
        if (container.getChildAt(position) == object) {
            container.removeViewAt(position);
            views.set(position, null);
        }
    }



}
