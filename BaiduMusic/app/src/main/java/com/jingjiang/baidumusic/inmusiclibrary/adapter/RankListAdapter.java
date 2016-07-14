package com.jingjiang.baidumusic.inmusiclibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.inmusiclibrary.bean.RankListData;
import com.squareup.picasso.Picasso;


/**
 * Created by dllo on 16/6/22.
 */
public class RankListAdapter extends BaseAdapter {
    private Context context;
    private RankListData rankListData;

    public RankListAdapter(Context context) {
        this.context = context;
    }

    public void setRankListData(RankListData rankListData) {
        this.rankListData = rankListData;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return rankListData != null ? rankListData.getContent().size() : 0;
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
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_music_ranklist, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.titleTv.setText(rankListData.getContent().get(position).getName());

        holder.firstTv.setText(rankListData.getContent().get(position).getContent().get(0).getTitle() + " - " +
                rankListData.getContent().get(position).getContent().get(1).getAuthor());
        holder.secondTv.setText(rankListData.getContent().get(position).getContent().get(1).getTitle() + " - " +
                rankListData.getContent().get(position).getContent().get(1).getAuthor());
        holder.thirdTv.setText(rankListData.getContent().get(position).getContent().get(2).getTitle() + " - " +
                rankListData.getContent().get(position).getContent().get(2).getAuthor());
        Picasso.with(context).load(rankListData.getContent().get(position).getPic_s192()).resize(180, 180)
                .into(holder.iconIv);


        return convertView;
    }

    class ViewHolder {
        ImageView iconIv;
        TextView titleTv, firstTv, secondTv, thirdTv;

        public ViewHolder(View itemView) {
            iconIv = (ImageView) itemView.findViewById(R.id.item_ranklist_icon_iv);
            titleTv = (TextView) itemView.findViewById(R.id.item_ranklist_title_tv);
            firstTv = (TextView) itemView.findViewById(R.id.item_ranklist_first_tv);
            secondTv = (TextView) itemView.findViewById(R.id.item_ranklist_second_tv);
            thirdTv = (TextView) itemView.findViewById(R.id.item_ranklist_third_tv);


        }
    }
}
