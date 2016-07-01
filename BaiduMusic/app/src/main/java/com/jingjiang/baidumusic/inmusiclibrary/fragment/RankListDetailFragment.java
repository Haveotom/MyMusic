package com.jingjiang.baidumusic.inmusiclibrary.fragment;

import android.view.View;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.base.BaseFragment;
import com.jingjiang.baidumusic.eventbusclass.IntegerEvent;
import com.jingjiang.baidumusic.inmusiclibrary.adapter.RankListDetailAdapter;
import com.jingjiang.baidumusic.inmusiclibrary.bean.RankListDetailData;
import com.jingjiang.baidumusic.widget.VolleySingle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by dllo on 16/6/29.
 */
public class RankListDetailFragment extends BaseFragment {
    private ListView listView;
    private RankListDetailAdapter rankListDetailAdapter;
    private int type;


    @Override
    protected int initLayout() {
        return R.layout.detail_f_music_ranklist;
    }

    public RankListDetailFragment() {
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)//加上该注解  证明该方法是EventBus调用的
    public void getType(IntegerEvent event) {
        //我们需要所有值都在这个StringEvent里面
        type = event.getAnInt();

    }

    @Override
    protected void initView() {
        listView = bindView(R.id.detail_music_ranklist_listview);
        rankListDetailAdapter = new RankListDetailAdapter(getContext());

        bindView(R.id.detail_music_ranklist_return_iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();

            }
        });


    }

    @Override
    protected void initData() {
        String url = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.0.1&channel=xiaomi&operator=2&method=baidu.ting.billboard.billList&format=json&type=%id&offset=0&size=100&fields=song_id%2Ctitle%2Cauthor%2Calbum_title%2Cpic_big%2Cpic_small%2Chavehigh%2Call_rate%2Ccharge%2Chas_mv_mobile%2Clearn%2Csong_source%2Ckorean_bb_song";
        url.replace("%id", String.valueOf(type));
        VolleySingle.getInstance()._addRequest(url,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }, new Response.Listener<RankListDetailData>() {
                    @Override
                    public void onResponse(RankListDetailData response) {
                        rankListDetailAdapter.setDetailData(response);

                    }
                }, RankListDetailData.class);
        listView.setAdapter(rankListDetailAdapter);

    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
