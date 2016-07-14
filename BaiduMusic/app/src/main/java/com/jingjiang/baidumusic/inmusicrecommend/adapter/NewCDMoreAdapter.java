package com.jingjiang.baidumusic.inmusicrecommend.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.inmusicrecommend.data.NewCDData;
import com.squareup.picasso.Picasso;


/**
 * Created by dllo on 16/7/5.
 */
public class NewCDMoreAdapter extends BaseAdapter {
    private Context context;
    private NewCDData data;

    public void setData(NewCDData data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public NewCDMoreAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return data != null ? data.getPlaze_album_list().getRM().getAlbum_list().getList().size() : 0;
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
        NewCDViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_recommend_newcd_more, parent, false);
            holder = new NewCDViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (NewCDViewHolder) convertView.getTag();
        }
        holder.titleTv.setText(data.getPlaze_album_list().getRM().getAlbum_list().getList().get(position).getTitle());
        holder.nameTv.setText(data.getPlaze_album_list().getRM().getAlbum_list().getList().get(position).getAuthor());
        Picasso.with(context).load(data.getPlaze_album_list().getRM().getAlbum_list().getList().get(position).getPic_radio())
                .error(R.mipmap.default_newalbum)
                .into(holder.pictureIv);

        return convertView;
    }

    class NewCDViewHolder {
        ImageView pictureIv;
        TextView titleTv, nameTv;

        public NewCDViewHolder(View view) {
            pictureIv = (ImageView) view.findViewById(R.id.item_recommend_newcd_more_picture_iv);
            titleTv = (TextView) view.findViewById(R.id.item_recommend_newcd_more_title_tv);
            nameTv = (TextView) view.findViewById(R.id.item_recommend_newcd_more_name_tv);

        }
    }
}
