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
import com.jingjiang.baidumusic.widget.Url;
import com.jingjiang.baidumusic.widget.VolleySingle;


/**
 * Created by dllo on 16/6/21.
 */
public class MVFragment extends BaseFragment implements View.OnClickListener {
    private MVAdapter mvAdapter;
    private GridView gridView;
    private TextView newest, hotest;


    @Override
    protected int initLayout() {
        return R.layout.music_f_mv;
    }

    @Override
    protected void initView() {
        gridView = bindView(R.id.music_mv_gridview);
        mvAdapter = new MVAdapter(getContext());
        gridView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        newest = bindView(R.id.music_mv_newest_tv);
        hotest = bindView(R.id.music_mv_hotest);
        newest.setOnClickListener(this);
        hotest.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        VolleySingle.getInstance()._addRequest(Url.MUSICLIBRARY_MV_NEW,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }, new Response.Listener<MVData>() {
                    @Override
                    public void onResponse(MVData response) {
                        mvAdapter.setMvData(response);
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
                break;
            case R.id.music_mv_hotest:
                hotest.setTextColor(getResources().getColor(R.color.blue));
                newest.setTextColor(getResources().getColor(R.color.black_normal));
                VolleySingle.getInstance()._addRequest(Url.MUSICLIBRARY_MV_HOT, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }, new Response.Listener<MVData>() {
                    @Override
                    public void onResponse(MVData response) {
                        mvAdapter.setMvData(response);

                    }
                }, MVData.class);
                gridView.setAdapter(mvAdapter);

                break;
        }
    }
}
