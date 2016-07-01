package com.jingjiang.baidumusic.inmusiclibrary.adapterrecommend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.inmusiclibrary.bean.RecommendData;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/6/25.
 */
public class ClassifyAdapter extends BaseAdapter {
    private Context context;
    private RecommendData data;

    public ClassifyAdapter(Context context) {
        this.context = context;
    }

    public void setData(RecommendData data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data != null ? data.getResult().getEntry().getResult().size() : 0;
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
        ClassifyViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_recommend_songclassify, parent, false);
            holder = new ClassifyViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ClassifyViewHolder) convertView.getTag();
        }
        holder.titleTv.setText(data.getResult().getEntry().getResult().get(position).getTitle());
        Picasso.with(context).load(data.getResult().getEntry().getResult().get(position).getIcon()).into(holder.iconIv);
        holder.dayTv.setText(data.getResult().getEntry().getResult().get(position).getDay());


        return convertView;
    }

    class ClassifyViewHolder {
        TextView dayTv, titleTv;
        ImageView iconIv;

        public ClassifyViewHolder(View view) {
            iconIv = (ImageView) view.findViewById(R.id.item_recommend_songclassify_icon_iv);
            dayTv = (TextView) view.findViewById(R.id.item_recommend_songclassify_day_tv);
            titleTv = (TextView) view.findViewById(R.id.item_recommend_songclassify_title_tv);

        }
    }
}
