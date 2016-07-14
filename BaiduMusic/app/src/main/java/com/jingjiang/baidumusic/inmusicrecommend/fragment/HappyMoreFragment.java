package com.jingjiang.baidumusic.inmusicrecommend.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.base.BaseFragment;
import com.jingjiang.baidumusic.inmusicrecommend.activity.BecomeAuchorActivity;
import com.jingjiang.baidumusic.inmusicrecommend.data.HappyMoreData;
import com.jingjiang.baidumusic.widget.single.VolleySingle;
import com.squareup.picasso.Picasso;


/**
 * Created by dllo on 16/7/5.
 */
public class HappyMoreFragment extends BaseFragment implements View.OnClickListener {
    private TextView titleTv;
    private HappyMoreAdapter adapter;
    private GridView gridView;


    @Override
    protected int initLayout() {
        return R.layout.music_f_recommend_happy_more;
    }

    @Override
    protected void initView() {
        titleTv = bindView(R.id.music_recommend_happy_more_bar_title_tv);
        titleTv.setText("乐播节目");
        gridView = bindView(R.id.music_recommend_happy_more_gridview);
        adapter = new HappyMoreAdapter(getContext());
        bindView(R.id.music_recommend_happy_more_fanhui_ll).setOnClickListener(this);//返回
        bindView(R.id.music_recommend_happy_more_zhubo_ll).setOnClickListener(this);//我要当主播

    }

    @Override
    protected void initData() {
        VolleySingle.getInstance()._addRequest("http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.0.1&channel=xiaomi&operator=2&method=baidu.ting.lebo.getChannelTag&format=json&page_no=1&page_size=1000",
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }, new Response.Listener<HappyMoreData>() {
                    @Override
                    public void onResponse(HappyMoreData response) {
                        adapter.setData(response);
                    }
                }, HappyMoreData.class);
        gridView.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.music_recommend_happy_more_fanhui_ll:
                getFragmentManager().popBackStack();
                break;
            case R.id.music_recommend_happy_more_zhubo_ll:
                startActivity(new Intent(getContext(), BecomeAuchorActivity.class));


                break;

        }


    }


    //adapter
    private class HappyMoreAdapter extends BaseAdapter {
        private Context context;
        private HappyMoreData data;

        public void setData(HappyMoreData data) {
            this.data = data;
            notifyDataSetChanged();
        }

        public HappyMoreAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return data != null ? data.getResult().getTaglist().size() : 0;
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
            HappyMoreViewHolder holder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_recommend_allsinger_more, parent, false);
                holder = new HappyMoreViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (HappyMoreViewHolder) convertView.getTag();
            }
            holder.nameTv.setText(data.getResult().getTaglist().get(position).getTag_name());
            Picasso.with(context).load(data.getResult().getTaglist().get(position).getTag_pic()).error(R.mipmap.default_newalbum)
                    .into(holder.iconIv);
            return convertView;
        }

        class HappyMoreViewHolder {
            TextView nameTv;
            ImageView iconIv;

            public HappyMoreViewHolder(View view) {
                nameTv = (TextView) view.findViewById(R.id.item_recommend_allsinger_more_name_iv);
                iconIv = (ImageView) view.findViewById(R.id.item_recommend_allsinger_more_icon_iv);

            }
        }
    }
}
