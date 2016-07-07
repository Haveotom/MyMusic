package com.jingjiang.baidumusic.inmusiclibrary.fragment;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.base.BaseFragment;
import com.jingjiang.baidumusic.widget.eventbus.IntegerEvent;
import com.jingjiang.baidumusic.inmusiclibrary.adapter.RankListAdapter;
import com.jingjiang.baidumusic.inmusiclibrary.bean.RankListData;
import com.jingjiang.baidumusic.widget.myinterface.OnFragmentSkipListener;
import com.jingjiang.baidumusic.widget.UrlTool;
import com.jingjiang.baidumusic.widget.single.VolleySingle;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by dllo on 16/6/21.
 */
public class RankListFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private RankListAdapter rankListAdapter;
    private ListView listView;
    private OnFragmentSkipListener fragmentSkipListener;
    private RankListData data;


    public void setFragmentSkipListener(OnFragmentSkipListener fragmentSkipListener) {
        this.fragmentSkipListener = fragmentSkipListener;
    }

    @Override
    protected int initLayout() {
        return R.layout.music_f_ranklist;
    }
    /*注意注意   必须要依附  否则空指针*/

    /**
     * fragment与Activity发生关联
     *
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        //将context转型为接口,赋值给接口对象
        fragmentSkipListener = (OnFragmentSkipListener) context;
        super.onAttach(context);
    }


    @Override
    protected void initView() {
        rankListAdapter = new RankListAdapter(getContext());
        listView = bindView(R.id.music_ranklist_listview);
        listView.setOnItemClickListener(this);
    }

    @Override
    protected void initData() {
        VolleySingle.getInstance()._addRequest(
                UrlTool.MUSICLIBRARY_RANKLIST,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }, new Response.Listener<RankListData>() {
                    @Override
                    public void onResponse(RankListData response) {
                        data = response;
                        rankListAdapter.setRankListData(data);

                    }
                }, RankListData.class);
        listView.setAdapter(rankListAdapter);

    }
    //热歌榜
    //http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.0.1&channel=xiaomi&operator=2&method=baidu.ting.billboard.billList&format=json&type=2&offset=0&size=100&fields=song_id%2Ctitle%2Cauthor%2Calbum_title%2Cpic_big%2Cpic_small%2Chavehigh%2Call_rate%2Ccharge%2Chas_mv_mobile%2Clearn%2Csong_source%2Ckorean_bb_song
    //欧美金曲榜
    //http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.0.1&channel=xiaomi&operator=2&method=baidu.ting.billboard.billList&format=json&type=21&offset=0&size=100&fields=song_id%2Ctitle%2Cauthor%2Calbum_title%2Cpic_big%2Cpic_small%2Chavehigh%2Call_rate%2Ccharge%2Chas_mv_mobile%2Clearn%2Csong_source%2Ckorean_bb_song
    //http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.0.1&channel=xiaomi&operator=2&method=baidu.ting.billboard.kingDetail&format=json&type=100&offset=0&size=50&fields=song_id%2Ctitle%2Cauthor%2Calbum_title%2Cpic_big%2Cpic_small%2Chavehigh%2Call_rate%2Ccharge%2Chas_mv_mobile%2Clearn%2Csong_source%2Ckorean_bb_song
    //http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.0.1&channel=xiaomi&operator=2&method=baidu.ting.billboard.billList&format=json&type=%id&offset=0&size=100&fields=song_id%2Ctitle%2Cauthor%2Calbum_title%2Cpic_big%2Cpic_small%2Chavehigh%2Call_rate%2Ccharge%2Chas_mv_mobile%2Clearn%2Csong_source%2Ckorean_bb_song
    //再详情
    //http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.0.1&channel=xiaomi&operator=2&method=baidu.ting.song.getInfos&format=json&songid=266784254&ts=1467291319311&e=05SCiLJDVJBMLXKC7S42zfhR864eq%2B2uReBS3rgt%2FDtezkRoBttET1Ek6poaGuHE&nw=2&ucf=1&res=1&l2p=0&lpb=&usup=1&lebo=0
    //http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.0.1&channel=xiaomi&operator=2&method=baidu.ting.song.getInfos&format=json&songid=266942077&ts=1467291320501&e=0ERct8f00Ef8b%2FP1nt6Rkl4rBfTrMq%2F0hWmcjA9I9jvKsRg%2FY89DMRZZVc%2BNWW5F&nw=2&ucf=1&res=1&l2p=0&lpb=&usup=1&lebo=0

    //歌单
    /* %id */
    //http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.0.1&channel=xiaomi&operator=2&method=baidu.ting.diy.gedanInfo&format=json&listid=%id&isUGC=0
    //http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.0.1&channel=xiaomi&operator=2&method=baidu.ting.diy.gedanInfo&format=json&listid=6705&isUGC=0
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (fragmentSkipListener != null) {
            fragmentSkipListener.toSkipFragment(1);
            EventBus.getDefault().post(new IntegerEvent(data.getContent().get(position).getType()));


        }


    }
}
