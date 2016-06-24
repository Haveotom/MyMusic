package com.jingjiang.baidumusic.fragment;

import android.widget.ScrollView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.adapter.LiveAdapter;
import com.jingjiang.baidumusic.base.BaseFragment;
import com.jingjiang.baidumusic.bean.LiveHotData;
import com.jingjiang.baidumusic.widget.MyGridView;
import com.jingjiang.baidumusic.widget.Url;
import com.jingjiang.baidumusic.widget.VolleySingle;

/**
 * Created by dllo on 16/6/21.
 */
public class LiveFragment extends BaseFragment {
    private LiveAdapter adapter;
    private MyGridView gridView;
    private ScrollView scrollView;

    @Override
    protected int initLayout() {
        return R.layout.fragment_live;
    }

    @Override
    protected void initView() {
        gridView = bindView(R.id.live_gridview);
        adapter = new LiveAdapter(getContext());
        scrollView = bindView(R.id.live_scrollView);
        scrollView.scrollTo(0,0);

    }

    @Override
    protected void initData() {
        VolleySingle.getInstance()._addRequest(Url.LIVE_HOT,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }, new Response.Listener<LiveHotData>() {
                    @Override
                    public void onResponse(LiveHotData response) {
                        adapter.setData(response);

                    }
                }, LiveHotData.class);
        gridView.setAdapter(adapter);

    }
}
