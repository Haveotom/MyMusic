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
import com.jingjiang.baidumusic.widget.single.VolleySingle;

/**
 * Created by dllo on 16/7/6.
 */
public class RadioMoreDetailActivity extends BaseActivity implements View.OnClickListener {
    private TextView titleTv;
    private ViewPager viewPager;
    private RadioMoreDetailAdapter adapter;


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
                        adapter.setDetailData(response);
                    }
                }, RadioMoreDetailData.class);

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
