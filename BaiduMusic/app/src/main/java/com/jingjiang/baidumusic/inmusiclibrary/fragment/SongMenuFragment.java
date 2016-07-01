package com.jingjiang.baidumusic.inmusiclibrary.fragment;

import android.view.View;
import android.widget.GridView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.base.BaseFragment;
import com.jingjiang.baidumusic.inmusiclibrary.adapter.SongMenuAdapter;
import com.jingjiang.baidumusic.inmusiclibrary.bean.SongMenuData;
import com.jingjiang.baidumusic.widget.PullToRefreshView;
import com.jingjiang.baidumusic.widget.UrlTool;
import com.jingjiang.baidumusic.widget.VolleySingle;

/**
 * Created by dllo on 16/6/21.
 */
public class SongMenuFragment extends BaseFragment implements PullToRefreshView.OnFooterRefreshListener {
    private GridView gridView;
    private SongMenuAdapter songMenuAdapter;
    private PullToRefreshView pullToRefreshView;
    private SongMenuData data;
    private int i = 1;
    private String refreshUrl = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.0.1&channel=xiaomi&operator=2&method=baidu.ting.diy.gedan&page_size=40&page_no=";


    @Override
    protected int initLayout() {
        return R.layout.music_f_songmenu;
    }

    @Override
    protected void initView() {
        pullToRefreshView = bindView(R.id.music_f_songmenu_refresh);//必须写在gridview绑定之前
        gridView = bindView(R.id.music_songmenu_gridview);
        songMenuAdapter = new SongMenuAdapter(getContext());
        gridView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        pullToRefreshView.setOnFooterRefreshListener(this);
        pullToRefreshView.setEnablePullTorefresh(false);//下拉属性,是否可下拉刷新,false 不可下拉

    }

    @Override
    protected void initData() {
        VolleySingle.getInstance()._addRequest(UrlTool.MUSICLIBRARY_SONGMENU,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }, new Response.Listener<SongMenuData>() {
                    @Override
                    public void onResponse(SongMenuData response) {
                        data = response;
                        songMenuAdapter.setSongMenuData(data);

                    }
                }, SongMenuData.class);
        gridView.setAdapter(songMenuAdapter);

    }

    @Override
    public void onFooterRefresh(PullToRefreshView view) {
        //刷新的接口
        //http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.0.1&channel=xiaomi&operator=2&method=baidu.ting.diy.gedan&page_size=30&page_no=4
        i = i + 1;

        pullToRefreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                VolleySingle.getInstance()._addRequest(refreshUrl + String.valueOf(i),
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }, new Response.Listener<SongMenuData>() {
                            @Override
                            public void onResponse(SongMenuData response) {
                                for (int i = 0; i < response.getContent().size() - 1; i++) {
                                    data.getContent().add(response.getContent().get(i));

                                }
                                songMenuAdapter.setSongMenuData(data);
                                pullToRefreshView.onFooterRefreshComplete();

                            }
                        }, SongMenuData.class);

            }
        }, 1000);


    }
}
