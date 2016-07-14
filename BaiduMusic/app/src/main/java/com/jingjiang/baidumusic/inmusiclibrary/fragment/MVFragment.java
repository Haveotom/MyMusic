package com.jingjiang.baidumusic.inmusiclibrary.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.base.BaseFragment;
import com.jingjiang.baidumusic.inmusiclibrary.activity.MVDetailActivity;
import com.jingjiang.baidumusic.inmusiclibrary.adapter.MVAdapter;
import com.jingjiang.baidumusic.inmusiclibrary.bean.MVData;
import com.jingjiang.baidumusic.inmusiclibrary.bean.MVDetailData;
import com.jingjiang.baidumusic.widget.view.PullToRefreshView;
import com.jingjiang.baidumusic.widget.othertool.UrlTool;
import com.jingjiang.baidumusic.widget.single.VolleySingle;


/**
 * Created by dllo on 16/6/21.
 */
public class MVFragment extends BaseFragment implements View.OnClickListener {
    private MVAdapter mvAdapter;
    private GridView gridView;
    private TextView newest, hotest;
    private PullToRefreshView refreshView;
    private int i = 1, j = 1;
    private MVData data, hotData;
    private LinearLayout all;


    @Override
    protected int initLayout() {
        return R.layout.music_f_mv;
    }

    @Override
    protected void initView() {
        all = bindView(R.id.music_mv_all_ll);
        refreshView = bindView(R.id.music_f_mv_refresh);//必须写在gridview之前
        gridView = bindView(R.id.music_mv_gridview);
        mvAdapter = new MVAdapter(getContext());
        gridView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        newest = bindView(R.id.music_mv_newest_tv);
        hotest = bindView(R.id.music_mv_hotest);
        newest.setOnClickListener(this);
        hotest.setOnClickListener(this);
        refreshView.setEnablePullTorefresh(false);
        newest.performClick();//自动点击最新
        all.setOnClickListener(this);


    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.music_mv_newest_tv:
                initData();//调用
                newest.setTextColor(getResources().getColor(R.color.blue));
                hotest.setTextColor(getResources().getColor(R.color.black_normal));
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
                initNewestRefresh();//刷新
                initNewestClick();//点击
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
                initHotRefresh();//刷新
                initHotClick();//点击
                break;

            //全部
            case R.id.music_mv_all_ll:

                break;
        }
    }

    private void initNewestRefresh() {
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

    }


    private void initHotRefresh() {
        refreshView.setOnFooterRefreshListener(new PullToRefreshView.OnFooterRefreshListener() {
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
                                        mvAdapter.setMvData(hotData);
                                        refreshView.onFooterRefreshComplete();

                                    }
                                }, MVData.class);

                    }
                }, 1000);

            }
        });

    }

    private void initNewestClick() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String leftUrl = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.0.1&channel=xiaomi&operator=2&provider=11%2C12&method=baidu.ting.mv.playMV&format=json&mv_id=";
                String rightUrl = "&song_id=&definition=0";
                String url = leftUrl + data.getResult().getMv_list().get(position).getMv_id() + rightUrl;
                VolleySingle.getInstance()._addRequest(url, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }, new Response.Listener<MVDetailData>() {
                    @Override
                    public void onResponse(MVDetailData response) {
                        Intent intent = new Intent(getContext(), MVDetailActivity.class);
                        String num = response.getResult().getVideo_info().getSourcepath().substring(31);
                        String mvUrl = "http://www.yinyuetai.com/mv/video-url/" + num;
                        String name = response.getResult().getMv_info().getTitle() + "";
                        intent.putExtra("MV", mvUrl);
                        intent.putExtra("NAME", name);
                        startActivity(intent);
                    }
                }, MVDetailData.class);

            }
        });

    }

    private void initHotClick() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                String leftUrl = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.0.1&channel=xiaomi&operator=2&provider=11%2C12&method=baidu.ting.mv.playMV&format=json&mv_id=";
                String rightUrl = "&song_id=&definition=0";
                String url = leftUrl + hotData.getResult().getMv_list().get(position).getMv_id() + rightUrl;
                VolleySingle.getInstance()._addRequest(url, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }, new Response.Listener<MVDetailData>() {
                    @Override
                    public void onResponse(MVDetailData response) {
                        Intent intent = new Intent(getContext(), MVDetailActivity.class);
                        String num = response.getResult().getVideo_info().getSourcepath().substring(31);
                        String mvUrl = "http://www.yinyuetai.com/mv/video-url/" + num;
                        String song = response.getResult().getMv_info().getTitle();
                        intent.putExtra("MV", mvUrl);
                        intent.putExtra("NAME", song);
                        startActivity(intent);

                    }
                }, MVDetailData.class);
            }
        });
    }


}
