package com.jingjiang.baidumusic.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.bean.KSongAllSingData;

/**
 * Created by dllo on 16/6/21.
 */
public class KSongAdapter extends BaseAdapter {
    private Context context;
    private KSongAllSingData data;

    public KSongAdapter(Context context) {
        this.context = context;
    }

    public void setData(KSongAllSingData data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data != null ? data.getResult().getItems().size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        KSongViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_ksong_allsing, parent, false);
            holder = new KSongViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (KSongViewHolder) convertView.getTag();
        }
        holder.titleTv.setText(data.getResult().getItems().get(position).getArtist_name() + " - " +
                data.getResult().getItems().get(position).getSong_title());
        holder.numberTv.setText(data.getResult().getItems().get(position).getPlay_num() + "人唱过");

        return convertView;
    }

    class KSongViewHolder {
        TextView titleTv, numberTv;
        ImageView singIv;

        public KSongViewHolder(View itemView) {
            titleTv = (TextView) itemView.findViewById(R.id.item_ksong_title_tv);
            numberTv = (TextView) itemView.findViewById(R.id.item_ksong_number_tv);
            singIv = (ImageView) itemView.findViewById(R.id.item_ksong_sing_iv);

        }
    }
}
