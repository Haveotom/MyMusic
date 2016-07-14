package com.jingjiang.baidumusic.inmusiclibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.inmusiclibrary.bean.SongMenuData;
import com.squareup.picasso.Picasso;


/**
 * Created by dllo on 16/6/22.
 */
public class SongMenuAdapter extends BaseAdapter {
    private Context context;
    private SongMenuData songMenuData;

    public SongMenuAdapter(Context context) {
        this.context = context;
    }

    public void setSongMenuData(SongMenuData songMenuData) {
        this.songMenuData = songMenuData;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return songMenuData != null ? songMenuData.getContent().size() : 0;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_music_songmenu, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.titleTv.setText(songMenuData.getContent().get(position).getTitle());
        holder.littleTitleTv.setText(songMenuData.getContent().get(position).getTag());
        holder.numberTv.setText(songMenuData.getContent().get(position).getListenum());
        Picasso.with(context).load(songMenuData.getContent().get(position).getPic_300()).
                resize(330, 330).error(R.mipmap.icon_error).into(holder.iconIv);
        return convertView;
    }

    class ViewHolder {
        ImageView iconIv;
        TextView numberTv, titleTv, littleTitleTv;

        public ViewHolder(View itemView) {
            iconIv = (ImageView) itemView.findViewById(R.id.item_songmenu_icon_iv);
            numberTv = (TextView) itemView.findViewById(R.id.item_songmenu_number_tv);
            titleTv = (TextView) itemView.findViewById(R.id.item_songmenu_title_tv);
            littleTitleTv = (TextView) itemView.findViewById(R.id.item_songmenu_little_title_tv);

        }
    }
}
