package com.jingjiang.baidumusic.inmusicradio.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.inmusicradio.data.RadioMoreDetailData;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/7/6.
 */
public class RadioMoreDetailAdapter extends PagerAdapter {
    private Context context;
    private RadioMoreDetailData detailData;

    public RadioMoreDetailAdapter(Context context) {
        this.context = context;
    }

    public void setDetailData(RadioMoreDetailData detailData) {
        this.detailData = detailData;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return detailData != null ? Integer.MAX_VALUE : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_music_radio_detail, container);
        TextView titleTv, nameTv;
        ImageView iconTv;
        titleTv = (TextView) view.findViewById(R.id.item_music_radio_more_detail_title_tv);
        nameTv = (TextView) view.findViewById(R.id.item_music_radio_more_detail_name_tv);
        iconTv = (ImageView) view.findViewById(R.id.item_music_radio_more_detail_image_iv);

        titleTv.setText(detailData.getResult().getSonglist().get(position).getTitle());
        nameTv.setText(detailData.getResult().getSonglist().get(position).getAuthor());
        Picasso.with(context).load(detailData.getResult().getSonglist().get(position).getPic_big())
                .error(R.mipmap.default_newalbum).into(iconTv);
        Log.d("RadioMoreDetailAdapter", detailData.getResult().getSonglist().get(position).getPic_huge());
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (container.getChildAt(position) == object) {
            //销毁指定位置的pager回收内存
            container.removeViewAt(position);
        }
    }
}
