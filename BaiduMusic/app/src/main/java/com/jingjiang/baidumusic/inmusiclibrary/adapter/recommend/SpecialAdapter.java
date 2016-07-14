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
public class SpecialAdapter extends BaseAdapter {
    private Context context;
    private RecommendData data;

    public SpecialAdapter(Context context) {
        this.context = context;
    }

    public void setData(RecommendData data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data != null ? data.getResult().getMod_7().getResult().size() : 0;
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
        SpecialViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_recommend_today, parent, false);
            holder = new SpecialViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (SpecialViewHolder) convertView.getTag();

        }
        holder.rightIv.setVisibility(View.VISIBLE);
        holder.playIv.setVisibility(View.GONE);
        holder.moreIv.setVisibility(View.GONE);
        holder.titleTv.setText(data.getResult().getMod_7().getResult().get(position).getTitle());
        holder.nameTv.setText(data.getResult().getMod_7().getResult().get(position).getDesc());
        Picasso.with(context).load(data.getResult().getMod_7().getResult().get(position).getPic())
                .resize(230, 230).into(holder.iconIv);

        return convertView;
    }

    class SpecialViewHolder {
        ImageView iconIv, rightIv, playIv, moreIv;
        TextView titleTv, nameTv;

        public SpecialViewHolder(View view) {
            iconIv = (ImageView) view.findViewById(R.id.item_recommend_today_icon_iv);
            titleTv = (TextView) view.findViewById(R.id.item_recommend_today_title_tv);
            nameTv = (TextView) view.findViewById(R.id.item_recommend_today_name_tv);
            rightIv = (ImageView) view.findViewById(R.id.item_recommend_today_right_iv);
            playIv = (ImageView) view.findViewById(R.id.item_recommend_today_play_iv);
            moreIv = (ImageView) view.findViewById(R.id.item_recommend_today_more_iv);

        }
    }
}
