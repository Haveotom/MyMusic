package com.jingjiang.baidumusic.fragment;

import android.widget.ListView;
import android.widget.ScrollView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.adapter.KSongAdapter;
import com.jingjiang.baidumusic.base.BaseFragment;
import com.jingjiang.baidumusic.bean.KSongAllSingData;
import com.jingjiang.baidumusic.widget.NoScrollListView;
import com.jingjiang.baidumusic.widget.Url;
import com.jingjiang.baidumusic.widget.VolleySingle;

/**
 * Created by dllo on 16/6/21.
 */
public class KSongFragment extends BaseFragment {
    private NoScrollListView listView;
    private KSongAdapter adapter;
    ScrollView scrollView;

    @Override
    protected int initLayout() {
        return R.layout.fragment_ksong;
    }

    @Override
    protected void initView() {
        listView = bindView(R.id.kson_allsing_listview);
        adapter = new KSongAdapter(getContext());
        scrollView = bindView(R.id.kson_scrollview);
        scrollView.scrollTo(0, 0);
        //显示在0,0位置

    }


    @Override
    protected void initData() {
        VolleySingle.getInstance()._addRequest(Url.KSONG_ALLSING,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }, new Response.Listener<KSongAllSingData>() {
                    @Override
                    public void onResponse(KSongAllSingData response) {
                        adapter.setData(response);

                    }
                }, KSongAllSingData.class);
        listView.setAdapter(adapter);
    }
}
