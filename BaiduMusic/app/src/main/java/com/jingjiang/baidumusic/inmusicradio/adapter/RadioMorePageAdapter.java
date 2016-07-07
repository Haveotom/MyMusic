package com.jingjiang.baidumusic.inmusicradio.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.inmusicradio.data.RadioMoreData;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/7/6.
 */
public class RadioMorePageAdapter extends BaseAdapter {
    private Context context;
    private RadioMoreData data;

    public RadioMorePageAdapter(Context context) {
        this.context = context;
    }

    public void setData(RadioMoreData data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data != null ? data.getResult().size() : 0;
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
        RadioMoreViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_music_radio_more, parent, false);
            holder = new RadioMoreViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (RadioMoreViewHolder) convertView.getTag();
        }
        holder.nameTv.setText(data.getResult().get(position).getScene_name());
        Picasso.with(context).load(data.getResult().get(position).getIcon_android()).error(R.mipmap.zzz)
                .resize(160,160).into(holder.imageViewIv);
        return convertView;
    }

    class RadioMoreViewHolder {
        ImageView imageViewIv;
        TextView nameTv;

        public RadioMoreViewHolder(View view) {
            imageViewIv = (ImageView) view.findViewById(R.id.item_music_radio_more_picture_iv);
            nameTv = (TextView) view.findViewById(R.id.item_music_radio_more_name_tv);
        }
    }
}
