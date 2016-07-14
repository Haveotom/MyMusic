package com.jingjiang.baidumusic.inmymusic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.inmymusic.data.SingleSongData;

import java.util.List;

/**
 * Created by dllo on 16/7/7.
 */
public class ILikeAdapter extends BaseAdapter {
    private Context context;
    private List<SingleSongData> songList;

    public ILikeAdapter(Context context) {
        this.context = context;
    }


    public void setSongList(List<SingleSongData> songList) {
        this.songList = songList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return songList != null ? songList.size() : 0;
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

        ILikeViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_music_ilike, parent, false);
            holder = new ILikeViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ILikeViewHolder) convertView.getTag();
        }
        holder.titleTv.setText(songList.get(position).getTitle());
        holder.nameTV.setText(songList.get(position).getAuthor());
        return convertView;
    }

    class ILikeViewHolder {
        TextView titleTv, nameTV;
        ImageView moreIv;

        public ILikeViewHolder(View view) {
            titleTv = (TextView) view.findViewById(R.id.item_music_ilike_title_tv);
            nameTV = (TextView) view.findViewById(R.id.item_music_ilike_name_tv);
            moreIv = (ImageView) view.findViewById(R.id.item_music_ilike_more_iv);


        }
    }
}
