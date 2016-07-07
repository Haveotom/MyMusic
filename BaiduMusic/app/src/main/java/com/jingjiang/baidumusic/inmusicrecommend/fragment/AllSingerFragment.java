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
        }
    }
}
