package com.jingjiang.baidumusic.inmusiclibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.inmusiclibrary.bean.RankListDetailData;
import com.jingjiang.baidumusic.widget.single.SingleQueue;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/7/1.
 */
public class RankListDetailAdapter extends BaseAdapter {
    private Context context;
    private RankListDetailData detailData;

    public void setDetailData(RankListDetailData detailData) {
        this.detailData = detailData;
        notifyDataSetChanged();
    }

    public RankListDetailAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return detailData != null ? detailData.getSong_list().size() : 0;
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
        RankListDetailViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_music_ranklist_detail, parent, false);
            holder = new RankListDetailViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (RankListDetailViewHolder) convertView.getTag();
        }
        holder.titleTv.setText(detailData.getSong_list().get(position).getTitle());
        holder.nameTv.setText(detailData.getSong_list().get(position).getAuthor());
        if (position == 0) {
            holder.rankIv.setImageResource(R.mipmap.img_king_mask01);
        } else if (position == 1) {
            holder.rankIv.setImageResource(R.mipmap.img_king_mask02);
        } else if (position == 2) {
            holder.rankIv.setImageResource(R.mipmap.img_king_mask03);
        } else {
            holder.rankIv.setImageResource(R.mipmap.img_king_mask1);
        }
        if (Integer.valueOf(detailData.getSong_list().get(position).getRank()) < 10) {
            holder.numTv.setText("0" + detailData.getSong_list().get(position).getRank());
        }

        holder.numTv.setText(detailData.getSong_list().get(position).getRank());

        SingleQueue.getSingleQueue(context).getImageLoader().get(
                detailData.getSong_list().get(position).getPic_small(),
                ImageLoader.getImageListener(holder.iconIv,R.mipmap.view_loading,R.mipmap.view_loading));


        return convertView;
    }

    class RankListDetailViewHolder {
        TextView titleTv, nameTv, numTv;
        ImageView iconIv, rankIv;

        public RankListDetailViewHolder(View view) {
            titleTv = (TextView) view.findViewById(R.id.item_music_ranklist_detail_title_tv);
            nameTv = (TextView) view.findViewById(R.id.item_music_ranklist_detail_name_tv);
            iconIv = (ImageView) view.findViewById(R.id.item_music_ranklist_detail_icon_iv);
            numTv = (TextView) view.findViewById(R.id.item_music_ranklist_detail_num_tv);
            rankIv = (ImageView) view.findViewById(R.id.item_music_ranklist_detail_sanjiao_iv);


        }
    }
}
