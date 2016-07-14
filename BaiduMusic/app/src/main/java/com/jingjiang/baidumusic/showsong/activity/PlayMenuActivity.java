package com.jingjiang.baidumusic.showsong.activity;

import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;

import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.base.BaseActivity;
import com.jingjiang.baidumusic.showsong.adapter.PlayMenuAdapter;

/**
 * Created by dllo on 16/7/12.
 */
public class PlayMenuActivity extends BaseActivity {
    private ListView listView;
    private PlayMenuAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_playmenu;
    }

    @Override
    protected void initView() {
        listView = bindView(R.id.activity_playmenu_listview);
        adapter = new PlayMenuAdapter();

        bindView(R.id.activity_playmenu_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void initData() {

    }
}
