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
public class HotMVAdapter extends BaseAdapter {
    private Context context;
    private RecommendData data;

    public HotMVAdapter(Context context) {
        this.context = context;
    }

    public void setData(RecommendData data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data != null ? data.getResult().getMix_5().getResult().size() : 0;
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
        HotMVViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_recommend_newcd, parent, false);
            holder = new HotMVViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (HotMVViewHolder) convertView.getTag();
        }
        holder.titleTv.setText(data.getResult().getMix_5().getResult().get(position).getTitle());
        holder.nameTv.setText(data.getResult().getMix_5().getResult().get(position).getAuthor());
        Picasso.with(context).load(data.getResult().getMix_5().getResult().get(position).getPic()).resize(230, 230)
                .into(holder.iconIv);
        return convertView;
    }

    class HotMVViewHolder {
        ImageView iconIv;
        TextView titleTv, nameTv;

        public HotMVViewHolder(View view) {
            iconIv = (ImageView) view.findViewById(R.id.item_recommend_newcd_icon_iv);
            titleTv = (TextView) view.findViewById(R.id.item_recommend_newcd_title_tv);
            nameTv = (TextView) view.findViewById(R.id.item_recommend_newcd_name_tv);

        }
    }
}
