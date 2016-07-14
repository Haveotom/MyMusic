package com.jingjiang.baidumusic.inmymusic.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.base.BaseFragment;
import com.jingjiang.baidumusic.individual.activity.LoginActivity;
import com.jingjiang.baidumusic.inmymusic.data.SingleSongData;
import com.jingjiang.baidumusic.inmymusic.adapter.ILikeAdapter;
import com.jingjiang.baidumusic.widget.eventbus.FinishILikeEvent;
import com.jingjiang.baidumusic.widget.eventbus.StringEvent;
import com.jingjiang.baidumusic.widget.single.SingleLiteOrm;
import com.litesuits.orm.LiteOrm;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by dllo on 16/7/7.
 */
public class ILkeFragment extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ListView listView;
    private LiteOrm liteOrm;
    private RelativeLayout loginRl;
    private LinearLayout loginTrueLl, returnLl;
    private TextView nameTv;
    private ILikeAdapter iLikeAdapter;
    private BmobUser bmobUser;
    private BmobQuery<SingleSongData> query;
    private List<SingleSongData> singleSongDatas;
    private String songId;


    @Override
    protected int initLayout() {
        return R.layout.mymusic_f_ilike;
    }

    public ILkeFragment() {
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initView() {
        iLikeAdapter = new ILikeAdapter(getContext());
        returnLl = bindView(R.id.mymusic_f_like_return_ll);
        returnLl.setOnClickListener(this);
        bindView(R.id.mymusic_f_like_login_tv).setOnClickListener(this);
        loginRl = bindView(R.id.activity_individual_login_state_rl);
        listView = bindView(R.id.mymusic_f_like_listview);
        loginTrueLl = bindView(R.id.mymusic_f_like_login_ll);
        nameTv = bindView(R.id.mymusic_f_like_name_tv);

        bmobUser = BmobUser.getCurrentUser(getContext());
        if (bmobUser != null) {
            loginRl.setVisibility(View.GONE);
            loginTrueLl.setVisibility(View.VISIBLE);
            nameTv.setText(bmobUser.getUsername());
        }

        //对每条设置监听
        listView.setOnItemClickListener(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getFinish(FinishILikeEvent event) {
        returnLl.callOnClick();
        int call = event.getFinish();
        Log.d("ILkeFragment", "call:" + call);

    }

    @Override
    protected void initData() {
        //对有账号时的查询获取
        query = new BmobQuery<SingleSongData>();
        query.addWhereEqualTo("userName", nameTv.getText());
        query.setLimit(50);
        SingleSongData userData = BmobUser.getCurrentUser(getContext(), SingleSongData.class);
        if (null != userData) {
            query.findObjects(getContext(), new FindListener<SingleSongData>() {
                @Override
                public void onSuccess(List<SingleSongData> list) {
                    //将list的数据给adapter
                    iLikeAdapter.setSongList(list);
                    //初始化
                    singleSongDatas = new ArrayList<SingleSongData>();
                    //将list的值赋给singleSongDatas
                    singleSongDatas = list;
                }

                @Override
                public void onError(int i, String s) {

                }
            });
        } else {
            //从本地获取歌曲
            singleSongDatas = SingleLiteOrm.getSingleLiteOrm().getLiteOrm().query(SingleSongData.class);
            iLikeAdapter.setSongList(singleSongDatas);

        }
        listView.setAdapter(iLikeAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mymusic_f_like_return_ll:
                getFragmentManager().popBackStack();
                break;
            case R.id.mymusic_f_like_login_tv:
                startActivity(new Intent(getContext(), LoginActivity.class));
                break;
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //将songid  发给服务
        EventBus.getDefault().post(new StringEvent(singleSongDatas.get(position).getSongId()));
        Log.d("ILkeFragment", singleSongDatas.get(position).getSongId());

    }

    @Override
    public void onDestroyOptionsMenu() {
        EventBus.getDefault().unregister(this);
        super.onDestroyOptionsMenu();
    }
}
