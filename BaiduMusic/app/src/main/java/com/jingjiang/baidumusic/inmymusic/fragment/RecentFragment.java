package com.jingjiang.baidumusic.inmymusic.fragment;

import android.view.View;
import android.widget.ListView;

import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.base.BaseFragment;

/**
 * Created by dllo on 16/7/11.
 */
public class RecentFragment extends BaseFragment {

    private ListView listView;

    @Override
    protected int initLayout() {
        return R.layout.mymusic_f_recent;
    }

    @Override
    protected void initView() {
        bindView(R.id.mymusic_f_recent_return_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        listView = bindView(R.id.mymusic_f_recent_listview);

    }

    @Override
    protected void initData() {

    }
}
