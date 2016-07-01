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
 * Created by dllo on 16/6/28.
 */
public class HotSellAdapter extends BaseAdapter {
    private Context context;
    private RecommendData data;

    public HotSellAdapter(Context context) {
        this.context = context;
    }

    public void setData(RecommendData data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data != null ? data.getResult().getMix_22().getResult().size() : 0;
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
        HotSellViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_recommend_newcd, parent, false);
            holder = new HotSellViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (HotSellViewHolder) convertView.getTag();
        }
        holder.titleTv.setText(data.getResult().getMix_22().getResult().get(position).getTitle());
        holder.nameTv.setText(data.getResult().getMix_22().getResult().get(position).getDesc());
        Picasso.with(context).load(data.getResult().getMix_22().getResult().get(position).getPic()).resize(230, 230)
                .into(holder.iconTv);
        return convertView;
    }

    class HotSellViewHolder {
        TextView titleTv, nameTv;
        ImageView iconTv;

        public HotSellViewHolder(View view) {
            titleTv = (TextView) view.findViewById(R.id.item_recommend_newcd_title_tv);
            nameTv = (TextView) view.findViewById(R.id.item_recommend_newcd_name_tv);
            iconTv = (ImageView) view.findViewById(R.id.item_recommend_newcd_icon_iv);

        }
    }
}
