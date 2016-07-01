package com.jingjiang.baidumusic.inmusiclibrary.fragment;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.base.BaseFragment;
import com.jingjiang.baidumusic.inmusiclibrary.adapterrecommend.ClassifyAdapter;
import com.jingjiang.baidumusic.inmusiclibrary.adapterrecommend.HappyAdapter;
import com.jingjiang.baidumusic.inmusiclibrary.adapterrecommend.HotMVAdapter;
import com.jingjiang.baidumusic.inmusiclibrary.adapterrecommend.HotSellAdapter;
import com.jingjiang.baidumusic.inmusiclibrary.adapterrecommend.NewCDAdapter;
import com.jingjiang.baidumusic.inmusiclibrary.adapterrecommend.RecyclerAdapter;
import com.jingjiang.baidumusic.inmusiclibrary.adapterrecommend.SpecialAdapter;
import com.jingjiang.baidumusic.inmusiclibrary.adapterrecommend.TAdapter;
import com.jingjiang.baidumusic.inmusiclibrary.adapterrecommend.TheSongMenuAdapter;
import com.jingjiang.baidumusic.inmusiclibrary.adapterrecommend.TodayAdapter;
import com.jingjiang.baidumusic.inmusiclibrary.bean.RecommendData;
import com.jingjiang.baidumusic.widget.MyGridView;
import com.jingjiang.baidumusic.widget.NoScrollListView;
import com.jingjiang.baidumusic.widget.UrlTool;
import com.jingjiang.baidumusic.widget.VolleySingle;

import java.util.ArrayList;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/6/21.
 */
public class RecommendFragment extends BaseFragment {
    private RecyclerAdapter recyclerAdapter;
    private RecommendData recommendData;
    private ViewPager viewPager;
    private ViewGroup viewGroup;
    private Handler handler;
    private ArrayList<String> iconUrls;
    private int sleepTick;
    private boolean threadAlive = true, userTouch = false;
    private ImageView[] imageNums;
    private MyGridView classifyGridView, songMenuGridView, newCDGridView, hotSellGridView,
            tGridView, hotMVGridView, happyGridView;
    private NoScrollListView todayListView, specialListView;

    private ImageView songMenuIv, newCDIv, hotSellIv, videoIv, todayIv,
            tIv, hotMVIv, happyIv, specialIv;
    //场景电台
    private ImageView videoOneIv, videoTwoIv, videoThreeIv, videoFourIv;
    private TextView videoOneTv, videoTwoTv, videoThreeTv, videoFourTv;

    //分类
    private ClassifyAdapter classifyAdapter;
    private TheSongMenuAdapter theSongMenuAdapter;
    private NewCDAdapter newCDAdapter;
    private HotSellAdapter hotSellAdapter;
    private TodayAdapter todayAdapter;
    private TAdapter tAdapter;
    private HotMVAdapter hotMVAdapter;
    private HappyAdapter happyAdapter;
    private SpecialAdapter specialAdapter;

    @Override
    protected int initLayout() {
        return R.layout.music_f_recommend;
    }

    @Override
    protected void initView() {
        recyclerAdapter = new RecyclerAdapter(getContext());
        viewPager = bindView(R.id.music_recommend_viewpager);
        viewGroup = bindView(R.id.music_recommend_point_ll);
        //轮播图方法
        initRecycler();
        //分类
        classifyAdapter = new ClassifyAdapter(getContext());
        classifyGridView = bindView(R.id.music_recommend_songclassify_gridview);
        //歌单推荐
        theSongMenuAdapter = new TheSongMenuAdapter(getContext());
        songMenuGridView = bindView(R.id.music_recommend_songmenu_gridview);
        songMenuIv = bindView(R.id.music_recommend_songmenu_recommend_iv);
        //新碟上架
        newCDGridView = bindView(R.id.music_recommend_mewcd_gridview);
        newCDAdapter = new NewCDAdapter(getContext());
        newCDIv = bindView(R.id.music_recommend_newcd_iv);
        //热销专辑
        hotSellGridView = bindView(R.id.music_recommend_hotsell_gridview);
        hotSellAdapter = new HotSellAdapter(getContext());
        hotSellIv = bindView(R.id.music_recommend_hotsell_iv);
        //场景电台
        videoIv = bindView(R.id.music_recommend_video_iv);
        videoOneIv = bindView(R.id.recommend_video_one_iv);
        videoTwoIv = bindView(R.id.recommend_video_two_iv);
        videoThreeIv = bindView(R.id.recommend_video_three_iv);
        videoFourIv = bindView(R.id.recommend_video_four_iv);
        videoOneTv = bindView(R.id.recommend_video_one_tv);
        videoTwoTv = bindView(R.id.recommend_video_two_tv);
        videoThreeTv = bindView(R.id.recommend_video_three_tv);
        videoFourTv = bindView(R.id.recommend_video_four_tv);
        //今日推荐
        todayListView = bindView(R.id.music_recommend_today_listview);
        todayAdapter = new TodayAdapter(getContext());
        todayIv = bindView(R.id.music_recommend_today_iv);
        //T榜
        tAdapter = new TAdapter(getContext());
        tGridView = bindView(R.id.music_recommend_t_gridview);
        //最热MV
        hotMVGridView = bindView(R.id.music_recommend_hotmv_gridview);
        hotMVAdapter = new HotMVAdapter(getContext());
        //乐播节目
        happyAdapter = new HappyAdapter(getContext());
        happyGridView = bindView(R.id.music_recommend_happy_gridview);
        //专栏
        specialAdapter = new SpecialAdapter(getContext());
        specialListView = bindView(R.id.music_recommend_special_listview);

        tIv = bindView(R.id.music_recommend_t_iv);
        hotMVIv = bindView(R.id.music_recommend_hotmv_iv);
        happyIv = bindView(R.id.music_recommend_happy_iv);
        specialIv = bindView(R.id.music_recommend_special_iv);


    }

    @Override
    protected void initData() {
        VolleySingle.getInstance()._addRequest(UrlTool.RECOMMEND_ALL,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }, new Response.Listener<RecommendData>() {
                    @Override
                    public void onResponse(RecommendData response) {
                        classifyAdapter.setData(response);
                        theSongMenuAdapter.setData(response);
                        Picasso.with(getContext()).load(response.getModule().get(2).getPicurl()).resize(25, 25).into(songMenuIv);
                        Picasso.with(getContext()).load(response.getModule().get(5).getPicurl()).resize(25, 25).into(newCDIv);
                        newCDAdapter.setData(response);
                        hotSellAdapter.setData(response);
                        Picasso.with(getContext()).load(response.getModule().get(6).getPicurl()).resize(25, 25).into(hotSellIv);
                        //场景电台
                        Picasso.with(getContext()).load(response.getModule().get(7).getPicurl()).resize(25, 25).into(videoIv);
                        Picasso.with(getContext()).load(response.getResult().getScene().getResult().getAction().get(0).getIcon_android()).into(videoOneIv);
                        Picasso.with(getContext()).load(response.getResult().getScene().getResult().getAction().get(1).getIcon_android()).into(videoTwoIv);
                        Picasso.with(getContext()).load(response.getResult().getScene().getResult().getAction().get(2).getIcon_android()).into(videoThreeIv);
                        Picasso.with(getContext()).load(response.getResult().getScene().getResult().getAction().get(3).getIcon_android()).into(videoFourIv);
                        videoOneTv.setText(response.getResult().getScene().getResult().getAction().get(0).getScene_name());
                        videoTwoTv.setText(response.getResult().getScene().getResult().getAction().get(1).getScene_name());
                        videoThreeTv.setText(response.getResult().getScene().getResult().getAction().get(2).getScene_name());
                        videoFourTv.setText(response.getResult().getScene().getResult().getAction().get(3).getScene_name());

                        //今日推荐
                        Picasso.with(getContext()).load(response.getModule().get(8).getPicurl()).resize(25, 25).into(todayIv);
                        Picasso.with(getContext()).load(response.getModule().get(9).getPicurl()).resize(25, 25).into(tIv);
                        Picasso.with(getContext()).load(response.getModule().get(10).getPicurl()).resize(25, 25).into(hotMVIv);
                        Picasso.with(getContext()).load(response.getModule().get(11).getPicurl()).resize(25, 25).into(happyIv);
                        Picasso.with(getContext()).load(response.getModule().get(12).getPicurl()).resize(25, 25).into(specialIv);

                        todayAdapter.setData(response);
                        tAdapter.setData(response);
                        hotMVAdapter.setData(response);
                        happyAdapter.setData(response);
                        specialAdapter.setData(response);

                    }
                }, RecommendData.class);
        classifyGridView.setAdapter(classifyAdapter);
        songMenuGridView.setAdapter(theSongMenuAdapter);
        newCDGridView.setAdapter(newCDAdapter);
        hotSellGridView.setAdapter(hotSellAdapter);
        todayListView.setAdapter(todayAdapter);
        tGridView.setAdapter(tAdapter);
        hotMVGridView.setAdapter(hotMVAdapter);
        happyGridView.setAdapter(happyAdapter);
        specialListView.setAdapter(specialAdapter);


    }

    /**
     * 轮播图
     */
    private void initRecycler() {
        VolleySingle.getInstance()._addRequest(UrlTool.RECOMMEND_ALL, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }, new Response.Listener<RecommendData>() {
            @Override
            public void onResponse(RecommendData response) {
                recommendData = response;
                iconUrls = new ArrayList<String>();//初始化
                //循环加入图片网址
                for (int i = 0; i < recommendData.getResult().getFocus().getResult().size(); i++) {
                    iconUrls.add(recommendData.getResult().getFocus().getResult().get(i).getRandpic());
                }
                //加到Adapter中
                recyclerAdapter.setIconUrls(iconUrls);
                //获取轮播图的数量
                imageNums = new ImageView[iconUrls.size()];
                //画小点的方法
                initPoint();
            }
        }, RecommendData.class);
        viewPager.setAdapter(recyclerAdapter);
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                //获取当前的位置,再将viewpager刷新到下一页
                int current = viewPager.getCurrentItem();
                viewPager.setCurrentItem(current + 1);
                return false;
            }
        });
        //开启线程去执行轮播
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (threadAlive) {
                    //每隔5s刷新一次
                    for (sleepTick = 0; sleepTick < 5; sleepTick++) {
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (!userTouch) {
                            handler.sendEmptyMessage(0);
                        }

                    }

                }

            }
        }).start();
        //当用户点击的时候就不会再发轮播图了
        //轮播图就会暂停轮播
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //用户触摸了轮播图
                        userTouch = true;
                        break;
                    case MotionEvent.ACTION_UP:
                        //手离开轮播图
                        userTouch = false;
                        //重新开始计时
                        sleepTick = 0;
                        break;
                }
                return false;
            }
        });
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setImageBackground(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    //设置轮播图的小点
    private void setImageBackground(int position) {
        for (int i = 0; i < imageNums.length; i++) {
            if (i == position % imageNums.length) {
                imageNums[i].setBackgroundResource(R.mipmap.ic_resume_play_yes);
            } else {
                imageNums[i].setBackgroundResource(R.mipmap.ic_resume_play_no);
            }
        }
    }

    /**
     * 画小点的方法
     */
    private void initPoint() {
        for (int i = 0; i < imageNums.length; i++) {
            ImageView imageView = new ImageView(getContext());
            if (i == 0) {
                imageView.setBackgroundResource(R.mipmap.ic_resume_play_yes);
            } else {
                imageView.setBackgroundResource(R.mipmap.ic_resume_play_no);
            }
            imageNums[i] = imageView;//再次加入数组中
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(10, 20));//设置萧条的宽高
            //设置小点View的边距
            layoutParams.leftMargin = 10;
            layoutParams.rightMargin = 10;
            viewGroup.addView(imageView, layoutParams);

        }

    }


}
