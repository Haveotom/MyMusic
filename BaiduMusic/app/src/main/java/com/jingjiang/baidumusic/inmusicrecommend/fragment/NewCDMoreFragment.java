package com.jingjiang.baidumusic.inmusicrecommend.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.base.BaseFragment;
import com.jingjiang.baidumusic.inmusicrecommend.adapter.NewCDMoreAdapter;
import com.jingjiang.baidumusic.inmusicrecommend.data.NewCDData;
import com.jingjiang.baidumusic.widget.single.VolleySingle;

/**
 * Created by dllo on 16/7/5.
 */
public class NewCDMoreFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private GridView gridView;
    private NewCDMoreAdapter newCDMoreAdapter;


    @Override
    protected int initLayout() {
        return R.layout.music_f_recommend_newcd_more;
    }

    @Override
    protected void initView() {
        gridView = bindView(R.id.music_recommend_newcd_gidview);
        bindView(R.id.music_recommend_newcd_more_fanhui_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        newCDMoreAdapter = new NewCDMoreAdapter(getContext());

    }

    @Override
    protected void initData() {
        VolleySingle.getInstance()._addRequest("http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.0.1&channel=xiaomi&operator=2&method=baidu.ting.plaza.getRecommendAlbum&format=json&offset=0&limit=50 ",
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }, new Response.Listener<NewCDData>() {
                    @Override
                    public void onResponse(NewCDData response) {
                        newCDMoreAdapter.setData(response);

                    }
                }, NewCDData.class);
        gridView.setAdapter(newCDMoreAdapter);

        gridView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
