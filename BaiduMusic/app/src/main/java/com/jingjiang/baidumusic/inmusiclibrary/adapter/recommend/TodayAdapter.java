package com.jingjiang.baidumusic.inmusiclibrary.adapter.recommend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.inmusiclibrary.bean.RecommendData;
import com.squareup.picasso.Picasso;


/**
 * Created by dllo on 16/6/28.
 */
public class TodayAdapter extends BaseAdapter {
    private Context context;
    private RecommendData data;

    public TodayAdapter(Context context) {
        this.context = context;
    }

    public void setData(RecommendData data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data != null ? 3 : 0;
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
        TodayViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_recommend_today, parent, false);
            holder = new TodayViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (TodayViewHolder) convertView.getTag();
        }
        if (data.getResult().getRecsong().getResult().get(position).getVersions() != "") {
            holder.titleTv.setText(data.getResult().getRecsong().getResult().get(position).getTitle() + "(" +
                    data.getResult().getRecsong().getResult().get(position).getVersions() + ")");
        } else {
            holder.titleTv.setText(data.getResult().getRecsong().getResult().get(position).getTitle());
        }
        holder.nameTv.setText(data.getResult().getRecsong().getResult().get(position).getAuthor());
        Picasso.with(context).load(data.getResult().getRecsong().getResult().get(position).getPic_premium()).into(holder.iconIv);
        return convertView;
    }

    class TodayViewHolder {
        TextView titleTv, nameTv;
        ImageView iconIv;

        public TodayViewHolder(View view) {
            titleTv = (TextView) view.findViewById(R.id.item_recommend_today_title_tv);
            nameTv = (TextView) view.findViewById(R.id.item_recommend_today_name_tv);
            iconIv = (ImageView) view.findViewById(R.id.item_recommend_today_icon_iv);

        }
    }
}
