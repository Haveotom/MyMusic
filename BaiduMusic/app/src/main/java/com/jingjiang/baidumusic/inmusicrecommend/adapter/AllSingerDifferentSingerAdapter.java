package com.jingjiang.baidumusic.inmusicrecommend.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.base.BaseFragment;
import com.jingjiang.baidumusic.base.BaseUpdateAdapter;
import com.jingjiang.baidumusic.base.MyApplication;
import com.jingjiang.baidumusic.inmusicrecommend.data.AllSingerDifferentSingerData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/7/13.
 */
public class AllSingerDifferentSingerAdapter extends BaseAdapter {

    private Context context;
    private AllSingerDifferentSingerData data;

    public AllSingerDifferentSingerAdapter(Context context) {
        this.context = context;
    }

    public void setData(AllSingerDifferentSingerData data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data != null ? data.getArtist().size() : 0;
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
        AllSingerDifferentViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_all_singer_different, parent, false);
            holder = new AllSingerDifferentViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (AllSingerDifferentViewHolder) convertView.getTag();
        }
        holder.nameTv.setText(data.getArtist().get(position).getName());
        Picasso.with(context).load(data.getArtist().get(position).getAvatar_middle()).into(holder.iconIv);
        return convertView;
    }

    class AllSingerDifferentViewHolder {
        TextView nameTv;
        ImageView iconIv;

        public AllSingerDifferentViewHolder(View view) {
            nameTv = (TextView) view.findViewById(R.id.item_all_singer_different_name_iv);
            iconIv = (ImageView) view.findViewById(R.id.item_all_singer_different_icon_iv);
        }
    }
}
