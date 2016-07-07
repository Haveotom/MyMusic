package com.jingjiang.baidumusic.fragment;

import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.adapter.SearchAdapter;
import com.jingjiang.baidumusic.base.BaseFragment;
import com.jingjiang.baidumusic.bean.SearchBean;
import com.jingjiang.baidumusic.bean.SearchContentData;
import com.jingjiang.baidumusic.widget.myinterface.OnDrawerListener;
import com.jingjiang.baidumusic.widget.UrlTool;
import com.jingjiang.baidumusic.widget.single.VolleySingle;

/**
 * Created by dllo on 16/6/25.
 */
public class SearchFragment extends BaseFragment implements View.OnClickListener {
    private ImageView returnIv, searchIv, delateIv;
    private EditText contentEt;
    private OnDrawerListener onDrawerListener;
    private SearchAdapter searchAdapter;
    private GridView gridView;

    @Override
    protected int initLayout() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initView() {
        returnIv = bindView(R.id.fragment_main_fanhui_iv);
        searchIv = bindView(R.id.fragment_main_to_search_iv);
        contentEt = bindView(R.id.fragment_main_content_et);
        gridView = bindView(R.id.fragment_search_choice_gridview);
        delateIv = bindView(R.id.fragment_main_delate_iv);
        returnIv.setOnClickListener(this);
        searchIv.setOnClickListener(this);
        contentEt.setOnClickListener(this);
        delateIv.setOnClickListener(this);
        searchAdapter = new SearchAdapter(getContext());


    }


    public void setOnDrawerListener(OnDrawerListener onDrawerListener) {
        this.onDrawerListener = onDrawerListener;
    }

    @Override
    protected void initData() {
        VolleySingle.getInstance()._addRequest(UrlTool.SEARCH_WORD,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }, new Response.Listener<SearchBean>() {
                    @Override
                    public void onResponse(SearchBean response) {
                        searchAdapter.setBean(response);

                    }
                }, SearchBean.class);
        gridView.setAdapter(searchAdapter);

    }

    private void initSearch() {
        String url = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.search.merge&query=%id%page_size=50&page_no=1&type=-1&format=json&from=ios&version=5.2.5&from=ios&channel=appstore";
        url.replace("%id%", contentEt.getText());
        VolleySingle.getInstance()._addRequest(url,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }, new Response.Listener<SearchContentData>() {
                    @Override
                    public void onResponse(SearchContentData response) {

                    }
                }, SearchContentData.class);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_main_fanhui_iv:
                onDrawerListener.closeDrawer();
                break;
            case R.id.fragment_main_content_et:
                if (contentEt.getText() != null) {
                    delateIv.setVisibility(View.VISIBLE);

                } else {
                    delateIv.setVisibility(View.GONE);
                }

                break;
            case R.id.fragment_main_delate_iv:
                contentEt.setText("");
                delateIv.setVisibility(View.GONE);
                break;
            case R.id.fragment_main_to_search_iv:
                initSearch();
                break;
        }

    }

}
