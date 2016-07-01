package com.jingjiang.baidumusic.inmusiclibrary.fragment;

import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.base.BaseFragment;
import com.jingjiang.baidumusic.inmusiclibrary.adapter.MVAdapter;
import com.jingjiang.baidumusic.inmusiclibrary.bean.MVData;
import com.jingjiang.baidumusic.widget.PullToRefreshView;
import com.jingjiang.baidumusic.widget.UrlTool;
import com.jingjiang.baidumusic.widget.VolleySingle;


/**
 * Created by dllo on 16/6/21.
 */
public class MVFragment extends BaseFragment implements View.OnClickListener, PullToRefreshView.OnFooterRefreshListener {
    private MVAdapter mvAdapter;
    private GridView gridView;
    private TextView newest, hotest;
    private PullToRefreshView refreshView;
    private int i = 1, j = 1;
    private MVData data, hotData;


    @Override
    protected int initLayout() {
        return R.layout.music_f_mv;
    }

    @Override
    protected void initView() {
        refreshView = bindView(R.id.music_f_mv_refresh);//必须写在gridview之前
        gridView = bindView(R.id.music_mv_gridview);
        mvAdapter = new MVAdapter(getContext());
        gridView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        newest = bindView(R.id.music_mv_newest_tv);
        hotest = bindView(R.id.music_mv_hotest);
        newest.setOnClickListener(this);
        hotest.setOnClickListener(this);
        refreshView.setEnablePullTorefresh(false);


    }

    @Override
    protected void initData() {

        VolleySingle.getInstance()._addRequest(UrlTool.MUSICLIBRARY_MV_NEW,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }, new Response.Listener<MVData>() {
                    @Override
                    public void onResponse(MVData response) {
                        data = response;
                        mvAdapter.setMvData(data);
                    }
                }, MVData.class);
        gridView.setAdapter(mvAdapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.music_mv_newest_tv:
                initData();
                newest.setTextColor(getResources().getColor(R.color.blue));
                hotest.setTextColor(getResources().getColor(R.color.black_normal));
                refreshView.setOnFooterRefreshListener(new PullToRefreshView.OnFooterRefreshListener() {
                    @Override
                    public void onFooterRefresh(PullToRefreshView view) {
                        i = i + 1;
                        final String mvUrls = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.0.1&channel=xiaomi&operator=2&provider=11%2C12&method=baidu.ting.mv.searchMV&format=json&order=1&page_num=";
                        refreshView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                VolleySingle.getInstance()._addRequest(mvUrls + String.valueOf(i),
                                        new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                refreshView.onFooterRefreshComplete();

                                            }
                                        }, new Response.Listener<MVData>() {
                                            @Override
                                            public void onResponse(MVData response) {
                                                for (int i1 = 0; i1 <= response.getResult().getMv_list().size() - 1; i1++) {
                                                    data.getResult().getMv_list().add(response.getResult().getMv_list().get(i1));
                                                }
                                                mvAdapter.setMvData(data);

                                            }
                                        }, MVData.class);

                                refreshView.onFooterRefreshComplete();

                            }
                        }, 1000);
                    }

                });
                break;
            case R.id.music_mv_hotest:
                hotest.setTextColor(getResources().getColor(R.color.blue));
                newest.setTextColor(getResources().getColor(R.color.black_normal));
                VolleySingle.getInstance()._addRequest(UrlTool.MUSICLIBRARY_MV_HOT, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }, new Response.Listener<MVData>() {
                    @Override
                    public void onResponse(MVData response) {
                        hotData = response;
                        mvAdapter.setMvData(hotData);
                        gridView.setAdapter(mvAdapter);

                    }
                }, MVData.class);
                refreshView.setOnFooterRefreshListener(this);


                break;
        }
    }

    @Override
    public void onFooterRefresh(PullToRefreshView view) {
        j = j + 1;
        final String hotUrls = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.0.1&channel=xiaomi&operator=2&provider=11%2C12&method=baidu.ting.mv.searchMV&format=json&order=0&page_num=";
        refreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                VolleySingle.getInstance()._addRequest(hotUrls + String.valueOf(j),
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }, new Response.Listener<MVData>() {
                            @Override
                            public void onResponse(MVData hotResponse) {
                                for (int i1 = 0; i1 <= hotResponse.getResult().getMv_list().size() - 1; i1++) {
                                    hotData.getResult().getMv_list().add(hotResponse.getResult().getMv_list().get(i1));
                                }
                                mvAdapter.setMvData(hotResponse);
                                refreshView.onFooterRefreshComplete();

                            }
                        }, MVData.class);

            }
        }, 1000);
    }
}