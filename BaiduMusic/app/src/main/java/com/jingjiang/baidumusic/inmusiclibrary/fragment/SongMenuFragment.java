package com.jingjiang.baidumusic.inmusiclibrary.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.base.BaseFragment;
import com.jingjiang.baidumusic.widget.eventbus.SongMenuDetailEvent;
import com.jingjiang.baidumusic.inmusiclibrary.adapter.SongMenuAdapter;
import com.jingjiang.baidumusic.inmusiclibrary.bean.SongMenuData;
import com.jingjiang.baidumusic.widget.myinterface.OnFragmentSkipListener;
import com.jingjiang.baidumusic.widget.view.PullToRefreshView;
import com.jingjiang.baidumusic.widget.othertool.UrlTool;
import com.jingjiang.baidumusic.widget.single.VolleySingle;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by dllo on 16/6/21.
 */
public class SongMenuFragment extends BaseFragment implements PullToRefreshView.OnFooterRefreshListener, AdapterView.OnItemClickListener, View.OnClickListener {
    private GridView gridView;
    private SongMenuAdapter songMenuAdapter;
    private PullToRefreshView pullToRefreshView;
    private SongMenuData data;
    private int i = 1;
    private String refreshUrl = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.0.1&channel=xiaomi&operator=2&method=baidu.ting.diy.gedan&page_size=40&page_no=";
    private OnFragmentSkipListener songMenuListener;
    public PopupWindow allPopWindow;
    private LinearLayout allLl;

    public void setSongMenuListener(OnFragmentSkipListener songMenuListener) {
        this.songMenuListener = songMenuListener;
    }

    @Override
    protected int initLayout() {
        return R.layout.music_f_songmenu;
    }

    @Override
    public void onAttach(Context context) {
        songMenuListener = (OnFragmentSkipListener) context;
        super.onAttach(context);
    }

    @Override
    protected void initView() {
        pullToRefreshView = bindView(R.id.music_f_songmenu_refresh);//必须写在gridview绑定之前
        gridView = bindView(R.id.music_songmenu_gridview);
        songMenuAdapter = new SongMenuAdapter(getContext());
        gridView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        pullToRefreshView.setOnFooterRefreshListener(this);
        pullToRefreshView.setEnablePullTorefresh(false);//下拉属性,是否可下拉刷新,false 不可下拉
        allLl = bindView(R.id.music_f_songmenu_all_songmenu_ll);
        allLl.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        VolleySingle.getInstance()._addRequest(UrlTool.MUSICLIBRARY_SONGMENU,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }, new Response.Listener<SongMenuData>() {
                    @Override
                    public void onResponse(SongMenuData response) {
                        data = response;
                        songMenuAdapter.setSongMenuData(data);

                    }
                }, SongMenuData.class);
        gridView.setAdapter(songMenuAdapter);
        gridView.setOnItemClickListener(this);

        //popupWindow
        initPopup();

    }

    private void initPopup() {
        //显示PopupWindow分为三步
        //首先new出一个PopupWindow
        //通常在构造方法中对啊的宽高进行设置
        allPopWindow = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final View view = LayoutInflater.from(getContext()).inflate(R.layout.music_f_songmenu_all_popupwindow, null);
        allPopWindow.setContentView(view);
        allPopWindow.setFocusable(true);//可点击
        allPopWindow.setAnimationStyle(R.style.AnimPopupWindpwBottom);

        //添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int height = view.findViewById(R.id.music_songmenu_pw_ll).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        allPopWindow.dismiss();
                    }
                }
                return true;
            }
        });
    }

    @Override
    public void onFooterRefresh(PullToRefreshView view) {
        //刷新的接口
        //http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.0.1&channel=xiaomi&operator=2&method=baidu.ting.diy.gedan&page_size=30&page_no=4
        i = i + 1;

        pullToRefreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                VolleySingle.getInstance()._addRequest(refreshUrl + String.valueOf(i),
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }, new Response.Listener<SongMenuData>() {
                            @Override
                            public void onResponse(SongMenuData response) {
                                for (int i = 0; i < response.getContent().size() - 1; i++) {
                                    data.getContent().add(response.getContent().get(i));

                                }
                                songMenuAdapter.setSongMenuData(data);
                                pullToRefreshView.onFooterRefreshComplete();

                            }
                        }, SongMenuData.class);

            }
        }, 1000);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (songMenuListener != null) {
            songMenuListener.toSkipFragment(2);
            EventBus.getDefault().post(new SongMenuDetailEvent(data.getContent().get(position).getListid()));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.music_f_songmenu_all_songmenu_ll:
                if (allPopWindow.isShowing()) {
                    allPopWindow.dismiss();
                } else {
                    allPopWindow.showAsDropDown(allLl);
                }

                break;
        }
    }
}
