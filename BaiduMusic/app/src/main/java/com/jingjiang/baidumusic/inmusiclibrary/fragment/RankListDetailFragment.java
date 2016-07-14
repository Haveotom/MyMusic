package com.jingjiang.baidumusic.inmusiclibrary.fragment;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.base.BaseFragment;
import com.jingjiang.baidumusic.widget.eventbus.IntegerEvent;
import com.jingjiang.baidumusic.widget.eventbus.SendSongInforEvent;
import com.jingjiang.baidumusic.widget.eventbus.StringEvent;
import com.jingjiang.baidumusic.inmusiclibrary.adapter.RankListDetailAdapter;
import com.jingjiang.baidumusic.inmusiclibrary.bean.RankListDetailData;
import com.jingjiang.baidumusic.widget.eventbus.TitleEvent;
import com.jingjiang.baidumusic.widget.myinterface.OnClickMoreListener;
import com.jingjiang.baidumusic.widget.single.DownloadSingle;
import com.jingjiang.baidumusic.widget.single.VolleySingle;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;


/**
 * Created by dllo on 16/6/29.
 */
public class RankListDetailFragment extends BaseFragment implements AdapterView.OnItemClickListener, View.OnClickListener {
    private ListView listView;
    private RankListDetailAdapter rankListDetailAdapter;
    private RankListDetailData detailData;
    private int type;
    private ImageView iconIv;
    private TextView dateTv, titleTv, countTv, pwTitleTv;
    ;
    private PopupWindow popupWindow, sharePw;
    private String titleName = "";
    private int postion = 0;
    private View view;//PopupWindow的view

    @Override
    protected int initLayout() {
        return R.layout.detail_f_music_ranklist;
    }

    public RankListDetailFragment() {
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)//加上该注解  证明该方法是EventBus调用的
    public void getType(IntegerEvent event) {
        //我们需要所有值都在这个IntegerEvent里面
        type = event.getAnInt();

    }


    @Override
    protected void initView() {
        Log.d("RankListDetailFragment", "initview" + titleName);
        listView = bindView(R.id.detail_music_ranklist_listview);
        iconIv = bindView(R.id.detail_music_ranklist_icon_iv);
        dateTv = bindView(R.id.detail_music_ranklist_date_tv);
        titleTv = bindView(R.id.detail_music_songmenu_title_tv);
        countTv = bindView(R.id.detail_music_songmenu_count_tv);
        rankListDetailAdapter = new RankListDetailAdapter(getContext());

        bindView(R.id.detail_music_songmenu_return_iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        initPopupWindow();//popupwindow的方法
        listView.setOnItemClickListener(this);
        pwTitleTv = (TextView) view.findViewById(R.id.detail_ranklist_pw_title_tv);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getTitleNsme(TitleEvent event) {
        titleName = event.getTitle();
        Log.d("RankListDetailFragment", "shou" + titleName);
        pwTitleTv.setText(titleName);

    }

    private void initPopupWindow() {


        int[] ids = {R.id.detail_ranklist_pw_like_iv, R.id.detail_ranklist_download_iv,
                R.id.detail_ranklist_share_iv, R.id.detail_ranklist_add_iv};
        //显示更多的PopupWindow点击
//        TextView pwTitleTv ;
        rankListDetailAdapter.setClickMoreListener(new OnClickMoreListener() {
            @Override
            public void onClickMore(String title, int pos) {
                postion = pos;
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                    backgroundAlpha(1);//让背景还原
                } else {
                    popupWindow.showAtLocation(listView, Gravity.BOTTOM, 0, 0);
//                    popupWindow.isShowing();
                    backgroundAlpha(0.5f);
                }
                Log.d("RankListDetailFragment", title);

            }
        });
        //Android 已经提供DisplayMetircs 类可以很方便的获取分辨率。
        WindowManager windowManager = getActivity().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);//将当前窗口的一些信息放在DisplayMetrics类中，
        //popupwindow的显示
        view = LayoutInflater.from(getContext()).inflate(R.layout.detail_f_music_ranklist_popupwindow, null);
        for (int i = 0; i < ids.length; i++) {
            view.findViewById(ids[i]).setOnClickListener(this);
        }

        popupWindow = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT, displayMetrics.heightPixels / 4 * 1);
        popupWindow.setContentView(view);
        popupWindow.setFocusable(true);//可点击
        popupWindow.setAnimationStyle(R.style.AnimPopupWindpwBottom);
        //添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int height = view.findViewById(R.id.detail_ranklist_pw_ll).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        popupWindow.dismiss();
                        backgroundAlpha(1);//让背景还原
                    }
                }
                return true;
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.detail_ranklist_pw_like_iv:
                Toast.makeText(getContext(), "去播放主页收藏啦~", Toast.LENGTH_SHORT).show();
                break;
            case R.id.detail_ranklist_download_iv:
                Toast.makeText(getContext(), "已经去下载", Toast.LENGTH_SHORT).show();
                DownloadSingle.getSingleDownload().Download(detailData.getSong_list().get(postion).getSong_id());
                Toast.makeText(getContext(), "下载完成", Toast.LENGTH_SHORT).show();
                EventBus.getDefault().post(new SendSongInforEvent(detailData.getSong_list().get(postion).getTitle(),
                        detailData.getSong_list().get(postion).getSong_id()));//传歌名和id去当地进行判断
                break;
            case R.id.detail_ranklist_share_iv:
                initShare();

                break;
            case R.id.detail_ranklist_add_iv:
                Toast.makeText(getContext(), "不给添加的", Toast.LENGTH_SHORT).show();
                break;
        }
        popupWindow.dismiss();
        backgroundAlpha(1);

    }

    private void initShare() {
        ShareSDK.initSDK(getContext());
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(getString(R.string.share));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");
        // 启动分享GUI
        oks.show(getContext());

    }

    /**
     * 设置添加屏幕的透明度
     *
     * @param alpha
     */
    public void backgroundAlpha(float alpha) {
        WindowManager.LayoutParams layoutParams = getActivity().getWindow().getAttributes();
        layoutParams.alpha = alpha;
        getActivity().getWindow().setAttributes(layoutParams);//设置属性

    }

    //http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.0.1&channel=xiaomi&operator=2&method=baidu.ting.billboard.billList&format=json&type=2&offset=0&size=100&fields=song_id%2Ctitle%2Cauthor%2Calbum_title%2Cpic_big%2Cpic_small%2Chavehigh%2Call_rate%2Ccharge%2Chas_mv_mobile%2Clearn%2Csong_source%2Ckorean_bb_song


    @Override
    protected void initData() {
        String urlLeft = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.0.1&channel=xiaomi&operator=2&method=baidu.ting.billboard.billList&format=json&type=";
        String urlRight = "&offset=0&size=100&fields=song_id%2Ctitle%2Cauthor%2Calbum_title%2Cpic_big%2Cpic_small%2Chavehigh%2Call_rate%2Ccharge%2Chas_mv_mobile%2Clearn%2Csong_source%2Ckorean_bb_song";
        String url = urlLeft + String.valueOf(type) + urlRight;
        VolleySingle.getInstance()._addRequest(url,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }, new Response.Listener<RankListDetailData>() {
                    @Override
                    public void onResponse(RankListDetailData response) {
                        detailData = response;
                        rankListDetailAdapter.setDetailData(detailData);
                        Picasso.with(getContext()).load(detailData.getBillboard().getPic_s210()).error(R.mipmap.icon_error)
                                .into(iconIv);
                        titleTv.setText(detailData.getBillboard().getName());
                        dateTv.setText("更新日期 : " + detailData.getBillboard().getUpdate_date());
                        countTv.setText("共" + detailData.getSong_list().size() + "首歌");
                    }
                }, RankListDetailData.class);
        listView.setAdapter(rankListDetailAdapter);

    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        EventBus.getDefault().post(new StringEvent(detailData.getSong_list().get(position).getSong_id()));


    }


}
