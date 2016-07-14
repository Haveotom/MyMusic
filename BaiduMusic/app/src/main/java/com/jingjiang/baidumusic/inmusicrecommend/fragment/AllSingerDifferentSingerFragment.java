package com.jingjiang.baidumusic.inmusicrecommend.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.base.BaseFragment;
import com.jingjiang.baidumusic.inmusicrecommend.adapter.AllSingerDifferentSingerAdapter;
import com.jingjiang.baidumusic.inmusicrecommend.data.AllSingerDifferentSingerData;
import com.jingjiang.baidumusic.widget.othertool.UrlTool;
import com.jingjiang.baidumusic.widget.single.VolleySingle;

import java.net.URL;
import java.util.List;

/**
 * Created by dllo on 16/7/13.
 */
public class AllSingerDifferentSingerFragment extends BaseFragment {
    private ListView listView;
    private AllSingerDifferentSingerAdapter adapter;
    private List<AllSingerDifferentSingerData.ArtistBean> beanList;

    @Override
    protected int initLayout() {
        return R.layout.recommend_allsinger_different_singer;
    }

    @Override
    protected void initView() {
        adapter = new AllSingerDifferentSingerAdapter(getContext());
        listView = bindView(R.id.music_allsinger_different_listview);
        bindView(R.id.music_allsinger_different_fanhui_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

    }

    @Override
    protected void initData() {
        VolleySingle.getInstance()._addRequest(UrlTool.ALLSINGER_CHINA_MAN,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }, new Response.Listener<AllSingerDifferentSingerData>() {
                    @Override
                    public void onResponse(AllSingerDifferentSingerData response) {
                        adapter.setData(response);
//                        Log.d("Allfragment", beanList.get(0).getName());

                    }
                }, AllSingerDifferentSingerData.class);
        listView.setAdapter(adapter);

    }
}
