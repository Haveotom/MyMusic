package com.jingjiang.baidumusic.inmusiclibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.inmusiclibrary.bean.SongMenuDetailData;

/**
 * Created by dllo on 16/7/2.
 */
public class SongMenuDetailAdapter extends BaseAdapter {
    private Context context;
    private SongMenuDetailData data;

    public SongMenuDetailAdapter(Context context) {
        this.context = context;
    }

    public void setData(SongMenuDetailData data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data != null ? data.getContent().size() : 0;
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
        SongMenuDetailViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_music_ranklist_detail, parent, false);
            holder = new SongMenuDetailViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (SongMenuDetailViewHolder) convertView.getTag();
        }
        holder.titleTv.setText(data.getContent().get(position).getTitle());
        holder.nameTv.setText(data.getContent().get(position).getAuthor());
        holder.picturePartRl.setVisibility(View.GONE);

        return convertView;
    }

    class SongMenuDetailViewHolder {
        TextView titleTv, nameTv;
        RelativeLayout picturePartRl;

        public SongMenuDetailViewHolder(View view) {
            titleTv = (TextView) view.findViewById(R.id.item_music_ranklist_detail_title_tv);
            nameTv = (TextView) view.findViewById(R.id.item_music_ranklist_detail_name_tv);
            picturePartRl = (RelativeLayout) view.findViewById(R.id.item_music_ranklist_detail_rl);

        }
    }
}
