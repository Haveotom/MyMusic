package com.jingjiang.baidumusic.fragment;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.base.BaseFragment;
import com.jingjiang.baidumusic.inmymusic.data.SingleSongData;
import com.jingjiang.baidumusic.widget.eventbus.SizeEvent;
import com.jingjiang.baidumusic.widget.eventbus.ToCallCountEvent;
import com.jingjiang.baidumusic.widget.myinterface.OnFragmentSkipListener;
import com.jingjiang.baidumusic.widget.single.SingleLiteOrm;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by dllo on 16/6/21.
 */
public class MyMusicFragment extends BaseFragment implements View.OnClickListener {
    private RelativeLayout locationRl, recentRl, downloadRl, iLikeRl, newSonglistRl;
    private int[] ids = {R.id.mymusic_bendi_rl, R.id.mymusic_recent_play_rl, R.id.mymusic_download_rl,
            R.id.mymusic_ilike_rl, R.id.mymusic_new_songlist_rl, R.id.mymusic_myksong_rl};
    private OnFragmentSkipListener skipListener;
    private TextView countTv, downloadCountTv;
    private SingleSongData userName;
    private int downloadCount;

    public MyMusicFragment() {
        EventBus.getDefault().register(this);
        String call = "call";
        EventBus.getDefault().post(new ToCallCountEvent(call));

    }

    public void setSkipListener(OnFragmentSkipListener skipListener) {
        this.skipListener = skipListener;
    }

    @Override
    public void onAttach(Context context) {
        skipListener = (OnFragmentSkipListener) context;
        super.onAttach(context);
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_mymusic;

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getDownloadCount(SizeEvent event) {
        downloadCount = event.getSize();

    }

    @Override
    protected void initView() {
        countTv = bindView(R.id.mymusic_singlesong_count_tv);
        downloadCountTv = bindView(R.id.mymusic_myksong_download_count_tv);
        downloadCountTv.setText(downloadCount + "首");
        for (int i = 0; i < ids.length; i++) {
            bindView(ids[i]).setOnClickListener(this);
        }
        userName = BmobUser.getCurrentUser(getContext(), SingleSongData.class);//判断
        if (userName != null) {
            BmobQuery<SingleSongData> dataBmobQuery = new BmobQuery<>();
            dataBmobQuery.addWhereEqualTo("userName", BmobUser.getCurrentUser(getContext()).getUsername().toString());
            dataBmobQuery.findObjects(getContext(), new FindListener<SingleSongData>() {
                @Override
                public void onSuccess(List<SingleSongData> list) {
                    Log.d("MyMusicFragment", "list.size():" + list.size());
                    countTv.setText(String.valueOf(list.size()) + "首");

                }

                @Override
                public void onError(int i, String s) {

                }
            });
        } else {
            //查询本地的数据库
            List list = SingleLiteOrm.getSingleLiteOrm().getLiteOrm().query(SingleSongData.class);
            countTv.setText(list.size() + "");
        }

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mymusic_ilike_rl:
                if (skipListener != null) {
                    skipListener.toSkipFragment(7);
                }

                break;
            case R.id.mymusic_bendi_rl:
                if (skipListener != null) {
                    skipListener.toSkipFragment(8);
                }
                break;
            case R.id.mymusic_recent_play_rl:
                if (skipListener != null) {
                    skipListener.toSkipFragment(9);
                }
                break;
            case R.id.mymusic_download_rl:
                if (skipListener != null) {
                    skipListener.toSkipFragment(10);
                }
                break;
            case R.id.mymusic_myksong_rl:
                if (skipListener != null) {
                    skipListener.toSkipFragment(11);
                }
                break;
        }
    }


    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
