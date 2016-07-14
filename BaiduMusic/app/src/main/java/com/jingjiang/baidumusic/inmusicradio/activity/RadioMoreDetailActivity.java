package com.jingjiang.baidumusic.inmusicradio.activity;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.base.BaseActivity;
import com.jingjiang.baidumusic.base.MyApplication;
import com.jingjiang.baidumusic.inmusicradio.adapter.RadioMoreDetailAdapter;
import com.jingjiang.baidumusic.inmusicradio.data.RadioMoreDetailData;
import com.jingjiang.baidumusic.widget.eventbus.StringEvent;
import com.jingjiang.baidumusic.widget.single.VolleySingle;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/7/6.
 */
public class RadioMoreDetailActivity extends BaseActivity implements View.OnClickListener {
    private TextView titleTv;
    private ViewPager viewPager;
    private RadioMoreDetailAdapter adapter;
    private RadioMoreDetailData detailData;


    @Override
    protected int getLayout() {
        return R.layout.activity_music_radio_more_detail;
    }

    @Override
    protected void initView() {
        viewPager = bindView(R.id.activity_music_radio_more_detail_viewpager);
        bindView(R.id.activity_music_radio_more_detail_return_iv).setOnClickListener(this);
        adapter = new RadioMoreDetailAdapter(MyApplication.context);
        viewPager.setAdapter(adapter);
        bindView(R.id.activity_music_radio_more_detail_title_tv).setOnClickListener(this);
        titleTv = bindView(R.id.activity_music_radio_more_detail_title_tv);
        String name = getIntent().getStringExtra("scene_name");
        titleTv.setText(name);
        //设置viewpager对多一次缓存4页
        viewPager.setOffscreenPageLimit(4);

    }

    @Override
    protected void initData() {

        String id = getIntent().getStringExtra("scene_id");
        Log.d("RadioMoreDetailActivity", id);
        String left = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.song.getSmartSongList&page_no=1&page_size=50&scene_id=";
        String right = "&item_id=0&version=5.2.5&from=ios&channel=appstore";
        String url = left + id + right;
        VolleySingle.getInstance()._addRequest(url,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }, new Response.Listener<RadioMoreDetailData>() {
                    @Override
                    public void onResponse(RadioMoreDetailData response) {
                        detailData = response;
                        adapter.setSonglistBeanList(detailData.getResult().getSonglist());
                    }
                }, RadioMoreDetailData.class);
        initViewPager();


    }

    private void initViewPager() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                adapter.setPos(position);
                //将歌曲的songId发给服务
                String songId = detailData.getResult().getSonglist().get(position).getSong_id();
                EventBus.getDefault().post(new StringEvent(songId));
                Log.d("RadioMoreDetailActivity", songId);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_music_radio_more_detail_viewpager:
                break;
            case R.id.activity_music_radio_more_detail_title_tv:
                finish();
                break;
            case R.id.activity_music_radio_more_detail_return_iv:
                finish();
                break;
        }
    }
}
