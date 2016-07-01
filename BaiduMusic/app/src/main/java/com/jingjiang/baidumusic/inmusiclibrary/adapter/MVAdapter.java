package com.jingjiang.baidumusic.inmusiclibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.inmusiclibrary.bean.MVData;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/6/23.
 */
public class MVAdapter extends BaseAdapter {
    private MVData mvData;
    private Context context;

    public MVAdapter(Context context) {
        this.context = context;
    }

    public void setMvData(MVData mvData) {
        this.mvData = mvData;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mvData != null ? mvData.getResult().getMv_list().size() : 0;
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
        MVViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_music_mv, parent, false);
            holder = new MVViewHolder(convertView);
            convertView.setTag(holder);

        } else {
            holder = (MVViewHolder) convertView.getTag();
        }
        holder.titleTv.setText(mvData.getResult().getMv_list().get(position).getTitle());
        holder.nameTv.setText(mvData.getResult().getMv_list().get(position).getArtist());
        Picasso.with(context).load(mvData.getResult().getMv_list().get(position).getThumbnail2())
                .error(R.mipmap.default_mv).resize(320,180).into(holder.iconIv);
        return convertView;
    }

    class MVViewHolder {
        ImageView iconIv;
        TextView titleTv, nameTv;

        public MVViewHolder(View itemView) {
            iconIv = (ImageView) itemView.findViewById(R.id.item_mv_icon_iv);
            titleTv = (TextView) itemView.findViewById(R.id.item_mv_title_tv);
            nameTv = (TextView) itemView.findViewById(R.id.item_mv_name_tv);

        }
    }
}
