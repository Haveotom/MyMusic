package com.jingjiang.baidumusic.inmusiclibrary.fragment;

import android.util.Log;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.base.BaseFragment;
import com.jingjiang.baidumusic.inmusiclibrary.adapter.RankListAdapter;
import com.jingjiang.baidumusic.inmusiclibrary.bean.RankListData;
import com.jingjiang.baidumusic.widget.Url;
import com.jingjiang.baidumusic.widget.VolleySingle;

/**
 * Created by dllo on 16/6/21.
 */
public class RankListFragment extends BaseFragment {
    private RankListAdapter rankListAdapter;
    private ListView listView;

    @Override
    protected int initLayout() {
        return R.layout.music_f_ranklist;
    }

    @Override
    protected void initView() {
        rankListAdapter = new RankListAdapter(getContext());
        listView = bindView(R.id.music_ranklist_listview);
    }

    @Override
    protected void initData() {
        VolleySingle.getInstance()._addRequest(
                Url.MUSICLIBRARY_RANKLIST,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }, new Response.Listener<RankListData>() {
                    @Override
                    public void onResponse(RankListData response) {
                        rankListAdapter.setRankListData(response);
                        Log.d("RankListFragment", response.getContent().get(2).getContent().get(1).getTitle());

                    }
                }, RankListData.class);
        listView.setAdapter(rankListAdapter);
        //612 GET http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.0.1&channel=xiaomi&operator=2&provider=11%2C12&method=baidu.ting.mv.searchMV&format=json&order=1&page_num=4&page_size=20&query=%E5%85%A8%E9%83%A8

    }
}
