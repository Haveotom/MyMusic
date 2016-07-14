package com.jingjiang.baidumusic.inmusicrecommend.activity;

import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.base.BaseActivity;

/**
 * Created by dllo on 16/7/5.
 */
public class BecomeAuchorActivity extends BaseActivity implements View.OnClickListener {
    private WebView webView;

    @Override
    protected int getLayout() {
        return R.layout.activity_become_auchor;
    }

    @Override
    protected void initView() {
        //返回
        bindView(R.id.music_recommend_become_auchor_fanhui_ll).setOnClickListener(this);
        //刷新
        bindView(R.id.music_recommend_become_auchor_refresh_tv).setOnClickListener(this);
        webView = bindView(R.id.music_recommend_become_auchor_webview);

    }

    @Override
    protected void initData() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
        webSettings.setAllowFileAccess(true);
        webView.loadUrl("http://music.baidu.com/cms/webview/lebo/dj/intro.html?operator=2&musicch=xiaomi&fr=android&ver=5.8.0.1");


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.music_recommend_become_auchor_fanhui_ll:
                finish();
                break;
            case R.id.music_recommend_become_auchor_refresh_tv:
                initData();
                break;
        }

    }
}
