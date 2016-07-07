package com.jingjiang.baidumusic.inmusicrecommend.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.base.BaseFragment;
import com.jingjiang.baidumusic.inmusicrecommend.adapter.AllSingerMoreAdapter;
import com.jingjiang.baidumusic.inmusicrecommend.data.AllSingerMoreData;
import com.jingjiang.baidumusic.widget.single.VolleySingle;

/**
 * Created by dllo on 16/7/5.
 */
public class AllSingerMoreFragment extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private GridView gridView;
    private AllSingerMoreAdapter adapter;


    @Override
    protected int initLayout() {
        return R.layout.music_f_recommend_allsonger_more;
    }

    @Override
    protected void initView() {
        bindView(R.id.music_recommend_happy_more_fanhui_ll).setOnClickListener(this);
        gridView = bindView(R.id.music_recommend_happy_more_gridview);
        adapter = new AllSingerMoreAdapter(getContext());
       gridView.setOnItemClickListener(this);

    }

    @Override
    protected void initData() {
        VolleySingle.getInstance()._addRequest("http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.0.1&channel=xiaomi&operator=2&method=baidu.ting.artist.getList&format=json&offset=0&limit=48&order=1&area=0&sex=0",
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }, new Response.Listener<AllSingerMoreData>() {
                    @Override
                    public void onResponse(AllSingerMoreData response) {
                        adapter.setData(response);


                    }
                }, AllSingerMoreData.class);
        gridView.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.music_recommend_happy_more_fanhui_ll:
                getFragmentManager().popBackStack();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
