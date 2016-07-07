package com.jingjiang.baidumusic.individual.activity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.base.BaseActivity;
import com.jingjiang.baidumusic.individual.bean.UseData;
import com.jingjiang.baidumusic.individual.adapter.UseAdapter;
import com.jingjiang.baidumusic.widget.single.VolleySingle;

/**
 * Created by dllo on 16/6/29.
 */
public class UseActivtity extends BaseActivity {
    private ListView listView;
    private LinearLayout returnLl;
    private UseAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_individual_use;
    }

    @Override
    protected void initView() {
        listView = bindView(R.id.activity_individual_use_listview);
        adapter = new UseAdapter(this);

        bindView(R.id.activity_individual_use_return_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void initData() {
        VolleySingle.getInstance()._addRequest("http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.0.1&channel=xiaomi&operator=2&method=baidu.ting.plaza.recommend&qd=xiaomi",
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }, new Response.Listener<UseData>() {
                    @Override
                    public void onResponse(UseData response) {
                        adapter.setData(response);

                    }
                }, UseData.class);
        listView.setAdapter(adapter);
    }
}
