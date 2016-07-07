package com.jingjiang.baidumusic.inmusiclibrary.fragment;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.base.BaseFragment;
import com.jingjiang.baidumusic.widget.eventbus.IntegerEvent;
import com.jingjiang.baidumusic.widget.eventbus.StringEvent;
import com.jingjiang.baidumusic.inmusiclibrary.adapter.RankListDetailAdapter;
import com.jingjiang.baidumusic.inmusiclibrary.bean.RankListDetailData;
import com.jingjiang.baidumusic.widget.single.VolleySingle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/6/29.
 */
public class RankListDetailFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private ListView listView;
    private RankListDetailAdapter rankListDetailAdapter;
    private RankListDetailData detailData;
    private int type;
    private ImageView iconIv;
    private TextView dateTv, titleTv, countTv;

    private RelativeLayout headRl;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private CoordinatorLayout coordinatorLayout;


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

        listView.setOnItemClickListener(this);
//        initBar();

    }

//    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//    private void initBar() {
//        AppBarLayout appBarLayout = bindView(R.id.detail_music_ranklist_app_bar);
//        Toolbar toolbar = bindView(R.id.detail_music_ranklist_tool_bar);
//        getActivity().setActionBar(toolbar);
//        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getActivity().onBackPressed();
//            }
//        });
//        headRl = bindView(R.id.detail_music_ranklist_head_rl);
//        coordinatorLayout = bindView(R.id.detail_music_ranklist_cl);
//        //使用CollapsingToolbarLayout必须把title设置到CollapsingToolbarLayout上，设置到Toolbar上则不会显示
//        collapsingToolbarLayout = bindView(R.id.collapsing_toolbar_layout);
//        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
//            @Override
//            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                if (verticalOffset <= -headRl.getHeight() / 2) {
//                    collapsingToolbarLayout.setTitle("涩郎");
//                } else {
//                    collapsingToolbarLayout.setTitle(" ");
//                }
//            }
//        });
//
//
//        //tablayout和viewpager建立联系为什么不用下面这个方法呢？自己去研究一下，可能收获更多
//        //toolbar_tab.setupWithViewPager(main_vp_container);
//        loadBlurAndSetStatusBar();
//
////        ImageView head_iv = bindView(R.id.head_iv);
////        Glide.with(this).load(R.mipmap.bg).bitmapTransform(new RoundedCornersTransformation(getActivity(),
////                90, 0)).into(head_iv);
//
//    }
//
//    private void loadBlurAndSetStatusBar() {
//        StatusBarUtil.setTranslucent(getActivity(), StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA);
//        Glide.with(this).load(R.mipmap.icon_error).bitmapTransform(new BlurTransformation(getActivity(), 100))
//                .into(new SimpleTarget<GlideDrawable>() {
//                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
//                    @Override
//                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super
//                            GlideDrawable> glideAnimation) {
//                        headRl.setBackground(resource);
//                        coordinatorLayout.setBackground(resource);
//                    }
//                });
//
//        Glide.with(this).load(R.mipmap.icon_error).bitmapTransform(new BlurTransformation(getActivity(), 100))
//                .into(new SimpleTarget<GlideDrawable>() {
//                    @Override
//                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super
//                            GlideDrawable> glideAnimation) {
//                        collapsingToolbarLayout.setContentScrim(resource);
//                    }
//                });
//
//    }
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
