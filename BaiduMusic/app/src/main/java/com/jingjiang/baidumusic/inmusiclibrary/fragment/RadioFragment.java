package com.jingjiang.baidumusic.inmusiclibrary.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.base.BaseFragment;
import com.jingjiang.baidumusic.inmusicradio.activity.RadioMoreActivity;
import com.jingjiang.baidumusic.widget.myinterface.OnFragmentSkipListener;

/**
 * Created by dllo on 16/6/21.
 */
public class RadioFragment extends BaseFragment implements View.OnClickListener {
    private RelativeLayout redHeartRl;
    private OnFragmentSkipListener listener;

    public void setListener(OnFragmentSkipListener listener) {
        this.listener = listener;

    }

    /*注意注意  一定要依附  否则Listener报空指针*/

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (OnFragmentSkipListener) context;

    }

    @Override
    protected int initLayout() {
        return R.layout.music_f_radio;
    }

    @Override
    protected void initView() {
        redHeartRl = bindView(R.id.music_radio_redheart_rl);
        redHeartRl.setOnClickListener(this);
        bindView(R.id.music_radio_more_ll).setOnClickListener(this);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.music_radio_more_ll:
                startActivity(new Intent(getContext(), RadioMoreActivity.class));
                break;
        }

    }
}
