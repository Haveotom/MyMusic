package com.jingjiang.baidumusic.fragment;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.adapter.KSongAdapter;
import com.jingjiang.baidumusic.adapter.KSongResumPlayAdapter;
import com.jingjiang.baidumusic.base.BaseFragment;
import com.jingjiang.baidumusic.bean.KSongAllSingData;
import com.jingjiang.baidumusic.bean.KSongPlayData;
import com.jingjiang.baidumusic.inksong.ResumeDetailFragment;
import com.jingjiang.baidumusic.widget.view.NoScrollListView;
import com.jingjiang.baidumusic.widget.myinterface.OnDrawerListener;
import com.jingjiang.baidumusic.widget.myinterface.OnViewPagerClickListener;
import com.jingjiang.baidumusic.widget.UrlTool;
import com.jingjiang.baidumusic.widget.single.VolleySingle;

import java.util.ArrayList;

/**
 * Created by dllo on 16/6/21.
 */
public class KSongFragment extends BaseFragment implements View.OnClickListener {
    private NoScrollListView listView;
    private KSongAdapter adapter;
    private ScrollView scrollView;
    private LinearLayout searchLl;
    //轮播图
    private KSongResumPlayAdapter kSongResumPlayAdapter;
    private KSongPlayData data;
    private ViewPager viewPager;
    private ViewGroup viewGroup;
    private ArrayList<String> imgUrls;
    private ImageView[] banners;//轮播图的数量
    private int sleepTick;
    private Handler handler;
    private boolean threadAlive = true, userThouch = false;
    private OnDrawerListener onDrawerListener;

    private FrameLayout frameLayout;
    private DrawerLayout resumeDL;
    private ResumeDetailFragment resumeDetailFragment;


    @Override
    protected int initLayout() {
        return R.layout.fragment_ksong;
    }

    @Override
    protected void initView() {
        listView = bindView(R.id.ksong_allsing_listview);
        adapter = new KSongAdapter(getContext());
        scrollView = bindView(R.id.ksong_scrollview);
        searchLl = bindView(R.id.ksong_search_ll);
        searchLl.setOnClickListener(this);

        //显示在0,0位置

        /*轮播图*/
        viewPager = bindView(R.id.ksong_resume_playpicture_vp);
        viewGroup = bindView(R.id.ksong_resume_point_viewgroup);
        kSongResumPlayAdapter = new KSongResumPlayAdapter(getContext());
        frameLayout = bindView(R.id.fragment_ksong_framelayout);
        resumeDL = bindView(R.id.fragment_ksong_drawerlayout);
        resumeDL.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

    }


    @Override
    protected void initData() {
        VolleySingle.getInstance()._addRequest(UrlTool.KSONG_ALLSING,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }, new Response.Listener<KSongAllSingData>() {
                    @Override
                    public void onResponse(KSongAllSingData response) {
                        adapter.setData(response);
                        scrollView.scrollTo(0, 0);
                    }
                }, KSongAllSingData.class);
        listView.setAdapter(adapter);

        /*轮播图的方法*/
        initResumePlay();
        scrollView.scrollTo(0, 0);
    }

    private void initResumePlay() {
        VolleySingle.getInstance()._addRequest(UrlTool.KSONG_RESUME_PLAY,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }, new Response.Listener<KSongPlayData>() {
                    @Override
                    public void onResponse(KSongPlayData response) {
                        data = response;
                        imgUrls = new ArrayList<String>();
                        for (int i = 0; i < data.getResult().size(); i++) {
                            imgUrls.add(data.getResult().get(i).getPicture());
                        }
                        kSongResumPlayAdapter.setImgUrls(imgUrls);
                        //获取轮播图数量
                        banners = new ImageView[imgUrls.size()];
                        //画小点的方法
                        initPoint();

                        kSongResumPlayAdapter.setListener(new OnViewPagerClickListener() {
                            @Override
                            public void onClick(View view, int postion) {
                                scrollView.scrollTo(0, 0);
                                resumeDL.openDrawer(GravityCompat.END);
                                resumeDL.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);


                            }
                        });

                    }
                }, KSongPlayData.class);
        viewPager.setAdapter(kSongResumPlayAdapter);
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                //获取当前的位置,再将ViewPager刷新到下一页
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
                    //每隔3s刷新一次ViewPager的数据
                    for (sleepTick = 0; sleepTick < 4; sleepTick++) {
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (!userThouch) {
                            handler.sendEmptyMessage(0);
                        }

                    }
                }
            }
        }).start();

        //当用户点击的时候就不会再出发轮播图了
        //轮播图就会暂停轮播
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //用户触摸了轮播图的时候
                        userThouch = true;

                        break;
                    case MotionEvent.ACTION_UP:
                        //手指离开轮播图的时候
                        userThouch = false;
                        //当每次抬起手指的时候,就会重新开始计时
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


    //
    private void initPoint() {
        for (int i = 0; i < banners.length; i++) {
            ImageView imageView = new ImageView(getContext());
            if (i == 0) {
                imageView.setBackgroundResource(R.mipmap.ic_resume_play_yes);
            } else {
                imageView.setBackgroundResource(R.mipmap.ic_resume_play_no);
            }
            banners[i] = imageView;
            LinearLayout.LayoutParams layoutParams =
                    new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(10, 20));//设置跟随条的宽高
            //设置小点view的左边距
            layoutParams.leftMargin = 10;
            layoutParams.rightMargin = 10;
            viewGroup.addView(imageView, layoutParams);
        }
    }

    //设置轮播图的小点
    public void setImageBackground(int items) {
        if (banners != null && banners.length > 0) {
            for (int i = 0; i < banners.length; i++) {
                if (i == items % banners.length) {
                    banners[i].setBackgroundResource(R.mipmap.ic_resume_play_yes);
                } else {
                    banners[i].setBackgroundResource(R.mipmap.ic_resume_play_no);
                }
            }
        }
    }

    public void setScroll() {
        if (scrollView != null) {
            scrollView.scrollTo(0, 0);
        }
    }

    public void setOnDrawerListener(OnDrawerListener onDrawerListener) {
        this.onDrawerListener = onDrawerListener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ksong_search_ll:
                onDrawerListener.openDrawer();

                break;
        }
    }
}
