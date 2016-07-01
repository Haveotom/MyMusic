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
public class TheSongMenuAdapter extends BaseAdapter {
    private Context context;
    private RecommendData data;

    public TheSongMenuAdapter(Context context) {
        this.context = context;
    }

    public void setData(RecommendData data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data != null ? data.getResult().getDiy().getResult().size() : 0;
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
        SongMenuViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_recommend_songmenu, parent, false);
            holder = new SongMenuViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (SongMenuViewHolder) convertView.getTag();
        }
        holder.titleTv.setText(data.getResult().getDiy().getResult().get(position).getTitle());
        holder.numberTv.setText(data.getResult().getDiy().getResult().get(position).getListenum());
        Picasso.with(context).load(data.getResult().getDiy().getResult().get(position).getPic())
                .resize(230,230).into(holder.iconIv);
        return convertView;
    }

    class SongMenuViewHolder {
        TextView titleTv, numberTv;
        ImageView iconIv;

        public SongMenuViewHolder(View view) {
            titleTv = (TextView) view.findViewById(R.id.item_recommend_newcd_title_tv);
            numberTv = (TextView) view.findViewById(R.id.item_recommend_songmenu_number_tv);
            iconIv = (ImageView) view.findViewById(R.id.item_recommend_newcd_icon_iv);


        }
    }
}
