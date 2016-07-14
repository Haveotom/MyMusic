package com.jingjiang.baidumusic.inmusiclibrary.fragment;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.base.BaseFragment;
import com.jingjiang.baidumusic.widget.eventbus.SongMenuDetailEvent;
import com.jingjiang.baidumusic.widget.eventbus.StringEvent;
import com.jingjiang.baidumusic.inmusiclibrary.adapter.SongMenuDetailAdapter;
import com.jingjiang.baidumusic.inmusiclibrary.bean.SongMenuDetailData;
import com.jingjiang.baidumusic.widget.single.VolleySingle;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * Created by dllo on 16/7/2.
 * http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.diy.gedanInfo&from=ios&listid=5717&version=5.2.3&from=ios&channel=appstore
 */
public class SongMenuDetailFragment extends BaseFragment implements View.OnClickListener {
    private ListView listView;
    private String listId;
    private SongMenuDetailAdapter songMenuDetailAdapter;
    private TextView titleTv, littleTv, listenCountTv, likeCountTv, headerTv, songCountTv;
    private ImageView iconIv, returnIv;
    private SongMenuDetailData data;//用它的形式

    public SongMenuDetailFragment() {
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getlistId(SongMenuDetailEvent event) {
        listId = event.getString();
    }

    @Override
    protected int initLayout() {
        return R.layout.detail_f_music_songmenu;
    }

    @Override
    protected void initView() {
        songMenuDetailAdapter = new SongMenuDetailAdapter(getContext());
        listView = bindView(R.id.detail_music_songmenu_listview);
        titleTv = bindView(R.id.detail_music_songmenu_big_title_tv);
        littleTv = bindView(R.id.detail_music_songmenu_little_word_tv);
        listenCountTv = bindView(R.id.detail_music_songmenu_listen_count_tv);
        likeCountTv = bindView(R.id.detail_music_songmenu_like_count_tv);
        titleTv = bindView(R.id.detail_music_songmenu_big_title_tv);
        songCountTv = bindView(R.id.detail_music_songmenu_count_tv);
        iconIv = bindView(R.id.detail_music_songmenu_icon_iv);
        returnIv = bindView(R.id.detail_music_songmenu_return_iv);
        returnIv.setOnClickListener(this);
        headerTv =bindView(R.id.songmenu_detail_header_tv);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EventBus.getDefault().post(new StringEvent(data.getContent().get(position).getSong_id()));

            }
        });
    }

    @Override
    protected void initData() {
        String urlLeft = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.diy.gedanInfo&from=ios&listid=";
        String urlRight = "&version=5.2.3&from=ios&channel=appstore";
        String url = urlLeft + String.valueOf(listId) + urlRight;
        VolleySingle.getInstance()._addRequest(url,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }, new Response.Listener<SongMenuDetailData>() {
                    @Override
                    public void onResponse(SongMenuDetailData response) {
                        data = response;
                        titleTv.setText(data.getTitle());
                        littleTv.setText(data.getTag());
                        listenCountTv.setText(data.getListenum());
                        likeCountTv.setText(data.getCollectnum());
                        songCountTv.setText("共" + data.getContent().size() + "首歌");
                        Picasso.with(getContext()).load(data.getPic_w700()).resize(700,400).into(iconIv);
                        songMenuDetailAdapter.setData(data);
                        headerTv.setText(data.getDesc());
                    }
                }, SongMenuDetailData.class);
        listView.setAdapter(songMenuDetailAdapter);

    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.detail_music_songmenu_return_iv://返回
                getFragmentManager().popBackStack();
                break;
        }
    }
}
