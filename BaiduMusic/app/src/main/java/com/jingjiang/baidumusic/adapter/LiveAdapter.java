package com.jingjiang.baidumusic.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.bean.LiveHotData;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/6/21.
 */
public class LiveAdapter extends BaseAdapter {
    private Context context;
    private LiveHotData data;

    public LiveAdapter(Context context) {
        this.context = context;
    }

    public void setData(LiveHotData data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data != null ? data.getData().getData().size() : 0;
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
        LiveViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_live_hotlive, parent, false);
            holder = new LiveViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (LiveViewHolder) convertView.getTag();
        }
        holder.nameTv.setText(data.getData().getData().get(position).getNickname());
        holder.numberTv.setText(data.getData().getData().get(position).getUsercount() + "");
        Picasso.with(context).load(data.getData().getData().get(position).getLiveimg())
                .resize(330, 240).
                error(R.mipmap.zzz).into(holder.pictureIv);
        return convertView;
    }

    class LiveViewHolder {
        TextView numberTv, nameTv;
        ImageView pictureIv;

        public LiveViewHolder(View itemView) {
            nameTv = (TextView) itemView.findViewById(R.id.item_live_name_tv);
            numberTv = (TextView) itemView.findViewById(R.id.item_live_number_tv);
            pictureIv = (ImageView) itemView.findViewById(R.id.item_live_picture_iv);

        }
    }
}
