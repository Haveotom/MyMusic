package com.jingjiang.baidumusic.inmusicrecommend.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.inmusicrecommend.data.AllSingerMoreData;
import com.squareup.picasso.Picasso;


/**
 * Created by dllo on 16/7/5.
 */
public class AllSingerMoreAdapter extends BaseAdapter {
    private Context context;
    private AllSingerMoreData data;

    public AllSingerMoreAdapter(Context context) {
        this.context = context;
    }

    public void setData(AllSingerMoreData data) {
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
        AllSingerMoreViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_recommend_allsinger_more, parent, false);
            holder = new AllSingerMoreViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (AllSingerMoreViewHolder) convertView.getTag();
        }
        holder.nameTv.setText(data.getArtist().get(position).getName());

        Picasso.with(context).load(data.getArtist().get(position).getAvatar_big()).error(R.mipmap.default_newalbum).into(holder.iconIv);

        return convertView;
    }

    class AllSingerMoreViewHolder {
        TextView nameTv;
        ImageView iconIv;

        public AllSingerMoreViewHolder(View view) {
            nameTv = (TextView) view.findViewById(R.id.item_recommend_allsinger_more_name_iv);
            iconIv = (ImageView) view.findViewById(R.id.item_recommend_allsinger_more_icon_iv);

        }
    }
}
