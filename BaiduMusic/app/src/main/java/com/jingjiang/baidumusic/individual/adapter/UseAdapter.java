package com.jingjiang.baidumusic.individual.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.individual.bean.UseData;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/6/29.
 */
public class UseAdapter extends BaseAdapter {
    private Context context;
    private UseData data;

    public UseAdapter(Context context) {
        this.context = context;
    }

    public void setData(UseData data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data != null ? data.getRecommend().size() : 0;
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
        UseViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_individual_use, parent, false);
            holder = new UseViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (UseViewHolder) convertView.getTag();
        }
        holder.nameTv.setText(data.getRecommend().get(position).getTitle());
        holder.contentTv.setText(data.getRecommend().get(position).getDesc());
        Picasso.with(context).load(data.getRecommend().get(position).getPic()).resize(80, 80).into(holder.iconIv);
        return convertView;
    }

    class UseViewHolder {
        ImageView iconIv, downloadIv;
        TextView nameTv, contentTv;

        public UseViewHolder(View view) {
            iconIv = (ImageView) view.findViewById(R.id.item_individual_use_icon_iv);
            nameTv = (TextView) view.findViewById(R.id.item_individual_use_name_tv);
            contentTv = (TextView) view.findViewById(R.id.item_individual_use_content_tv);

        }
    }
}
