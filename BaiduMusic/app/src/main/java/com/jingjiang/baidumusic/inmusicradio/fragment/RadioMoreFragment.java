package com.jingjiang.baidumusic.inmusicradio.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.base.BaseFragment;
import com.jingjiang.baidumusic.inmusicradio.activity.RadioMoreDetailActivity;
import com.jingjiang.baidumusic.inmusicradio.adapter.RadioMorePageAdapter;
import com.jingjiang.baidumusic.inmusicradio.data.RadioMoreData;
import com.jingjiang.baidumusic.widget.UrlTool;
import com.jingjiang.baidumusic.widget.single.VolleySingle;

/**
 * Created by dllo on 16/7/6.
 */
public class RadioMoreFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private String urls[] = {UrlTool.Radio_LANGRAGE, UrlTool.Radio_TOPIC, UrlTool.Radio_ACTIVE,
            UrlTool.Radio_STYLE, UrlTool.Radio_TIME, UrlTool.Radio_FEELING};
    private static final String POS = "";

    private RadioMorePageAdapter adapter;
    private GridView gridView;
    private RadioMoreData data;

    //写一个生成指定Fragment的方法
    public static RadioMoreFragment getFragment(int position) {
        RadioMoreFragment radioMoreFragment = new RadioMoreFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(POS, position);
        radioMoreFragment.setArguments(bundle);
        return radioMoreFragment;
    }


    @Override
    protected int initLayout() {
        return R.layout.music_f_radio_more;
    }

    @Override
    protected void initView() {
        adapter = new RadioMorePageAdapter(getContext());
        gridView = bindView(R.id.music_f_radio_more_gridview);
        gridView.setOverScrollMode(View.OVER_SCROLL_NEVER);//不要阴影
        gridView.setOnItemClickListener(this);
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        int pos = bundle.getInt(POS);
        VolleySingle.getInstance()._addRequest(urls[pos],
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }, new Response.Listener<RadioMoreData>() {
                    @Override
                    public void onResponse(RadioMoreData response) {
                        data = response;
                        adapter.setData(data);
                    }
                }, RadioMoreData.class);
        gridView.setAdapter(adapter);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getContext(), RadioMoreDetailActivity.class);
        intent.putExtra("scene_id", data.getResult().get(position).getScene_id());
        intent.putExtra("scene_name", data.getResult().get(position).getScene_name());
        startActivity(intent);
    }
}
