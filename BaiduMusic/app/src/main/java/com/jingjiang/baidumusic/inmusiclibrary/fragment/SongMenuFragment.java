package com.jingjiang.baidumusic.inmusiclibrary.fragment;

import android.view.View;
import android.widget.GridView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.base.BaseFragment;
import com.jingjiang.baidumusic.inmusiclibrary.adapter.SongMenuAdapter;
import com.jingjiang.baidumusic.inmusiclibrary.bean.SongMenuData;
import com.jingjiang.baidumusic.widget.MyGridView;
import com.jingjiang.baidumusic.widget.Url;
import com.jingjiang.baidumusic.widget.VolleySingle;

/**
 * Created by dllo on 16/6/21.
 */
public class SongMenuFragment extends BaseFragment {
    private GridView gridView;
    private SongMenuAdapter songMenuAdapter;

    @Override
    protected int initLayout() {
        return R.layout.music_f_songmenu;
    }

    @Override
    protected void initView() {
        gridView = bindView(R.id.music_songmenu_gridview);
        songMenuAdapter = new SongMenuAdapter(getContext());
        gridView.setOverScrollMode(View.OVER_SCROLL_NEVER);


    }

    @Override
    protected void initData() {
        VolleySingle.getInstance()._addRequest(Url.MUSICLIBRARY_SONGMENU,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }, new Response.Listener<SongMenuData>() {
                    @Override
                    public void onResponse(SongMenuData response) {
                        songMenuAdapter.setSongMenuData(response);

                    }
                }, SongMenuData.class);
        gridView.setAdapter(songMenuAdapter);

    }
}
