package com.jingjiang.baidumusic.inmusicrecommend.fragment;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.base.BaseFragment;
import com.jingjiang.baidumusic.widget.myinterface.OnFragmentSkipListener;

/**
 * Created by dllo on 16/7/5.
 */
public class AllSingerFragment extends BaseFragment implements View.OnClickListener {
    private TextView moreTv;
    private OnFragmentSkipListener onFragmentSkipListener;
    private LinearLayout returnLl;
    private int[] ids = {R.id.music_recommend_allsinger_more_china_man_ll, R.id.music_recommend_allsinger_more_china_women_ll, R.id.music_recommend_allsinger_more_china_club_ll,
            R.id.music_recommend_allsinger_more_europe_man_ll, R.id.music_recommend_allsinger_more_europe_women_ll, R.id.music_recommend_allsinger_more_europe_club_ll,
            R.id.music_recommend_allsinger_more_korea_man_ll, R.id.music_recommend_allsinger_more_korea_women_ll, R.id.music_recommend_allsinger_more_korea_club_ll,
            R.id.music_recommend_allsinger_more_japan_man_ll, R.id.music_recommend_allsinger_more_japan_women_ll, R.id.music_recommend_allsinger_more_japan_club_ll,
            R.id.music_recommend_allsinger_more_other_ll,};


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onFragmentSkipListener = (OnFragmentSkipListener) context;
    }

    public void setAllSongerListener(OnFragmentSkipListener onFragmentSkipListener) {
        this.onFragmentSkipListener = onFragmentSkipListener;
    }

    @Override
    protected int initLayout() {
        return R.layout.music_f_recommend_allsonger;
    }

    @Override
    protected void initView() {
        moreTv = bindView(R.id.music_recommend_allsinger_more_tv);
        returnLl = bindView(R.id.music_recommend_allsinger_fanhui_ll);
        returnLl.setOnClickListener(this);
        moreTv.setOnClickListener(this);
        for (int i = 0; i < ids.length; i++) {
            bindView(ids[i]).setOnClickListener(this);
        }


    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.music_recommend_allsinger_more_tv:
                if (onFragmentSkipListener != null) {
                    onFragmentSkipListener.toSkipFragment(4);
                }
                break;
            case R.id.music_recommend_allsinger_fanhui_ll:
                getFragmentManager().popBackStack();
                break;
            case R.id.music_recommend_allsinger_more_china_man_ll:
                if (onFragmentSkipListener != null) {
                    onFragmentSkipListener.toSkipFragment(12);//打开歌手地区歌手详情
                }
                break;
        }
    }
}
