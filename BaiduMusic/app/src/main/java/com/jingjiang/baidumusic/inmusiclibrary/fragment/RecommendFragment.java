package com.jingjiang.baidumusic.inmusiclibrary.fragment;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.base.BaseFragment;
import com.jingjiang.baidumusic.inmusiclibrary.adapter.recommend.ClassifyAdapter;
import com.jingjiang.baidumusic.inmusiclibrary.adapter.recommend.HappyAdapter;
import com.jingjiang.baidumusic.inmusiclibrary.adapter.recommend.HotMVAdapter;
import com.jingjiang.baidumusic.inmusiclibrary.adapter.recommend.HotSellAdapter;
import com.jingjiang.baidumusic.inmusiclibrary.adapter.recommend.NewCDAdapter;
import com.jingjiang.baidumusic.inmusiclibrary.adapter.recommend.RecyclerAdapter;
import com.jingjiang.baidumusic.inmusiclibrary.adapter.recommend.SpecialAdapter;
import com.jingjiang.baidumusic.inmusiclibrary.adapter.recommend.TAdapter;
import com.jingjiang.baidumusic.inmusiclibrary.adapter.recommend.TheSongMenuAdapter;
import com.jingjiang.baidumusic.inmusiclibrary.adapter.recommend.TodayAdapter;
import com.jingjiang.baidumusic.inmusiclibrary.bean.RecommendData;
import com.jingjiang.baidumusic.widget.eventbus.SongMenuDetailEvent;
import com.jingjiang.baidumusic.widget.threadpool.MyThreadPool;
import com.jingjiang.baidumusic.widget.view.MyGridView;
import com.jingjiang.baidumusic.widget.view.NoScrollListView;
import com.jingjiang.baidumusic.widget.myinterface.OnFragmentSkipListener;
import com.jingjiang.baidumusic.widget.myinterface.OnClickSomeListener;
import com.jingjiang.baidumusic.widget.othertool.UrlTool;
import com.jingjiang.baidumusic.widget.single.VolleySingle;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * Created by dllo on 16/6/21.
 */
public class RecommendFragment extends BaseFragment implements View.OnClickListener {
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
    private OnFragmentSkipListener recommendSkipListener;
    private OnClickSomeListener onclickSomeListener;//跳fragment的点击事件

    public void setOnclickSomeListener(OnClickSomeListener onclickSomeListener) {
        this.onclickSomeListener = onclickSomeListener;
    }

    public void setRecommendSkipListener(OnFragmentSkipListener recommendSkipListener) {
        this.recommendSkipListener = recommendSkipListener;
    }


    /* ******************************************************* */
    @Override
    protected int initLayout() {
        return R.layout.music_f_recommend;
    }

    @Override
    public void onAttach(Context context) {
        recommendSkipListener = (OnFragmentSkipListener) context;
        super.onAttach(context);
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
        bindView(R.id.music_recommend_songmenu_recommend_more_tv).setOnClickListener(this);//更多
        //新碟上架
        newCDGridView = bindView(R.id.music_recommend_mewcd_gridview);
        newCDAdapter = new NewCDAdapter(getContext());
        newCDIv = bindView(R.id.music_recommend_newcd_iv);
        bindView(R.id.music_recommend_newcd_more_tv).setOnClickListener(this);
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
        bindView(R.id.music_recommend_today_more_tv).setOnClickListener(this);
        //T榜
        tAdapter = new TAdapter(getContext());
        tGridView = bindView(R.id.music_recommend_t_gridview);
        //最热MV
        hotMVGridView = bindView(R.id.music_recommend_hotmv_gridview);
        hotMVAdapter = new HotMVAdapter(getContext());
        //乐播节目
        happyAdapter = new HappyAdapter(getContext());
        happyGridView = bindView(R.id.music_recommend_happy_gridview);
        bindView(R.id.music_recommend_happy_more_tv).setOnClickListener(this);//更多
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
                        newCDAdapter.setData(response);
                        hotSellAdapter.setData(response);
                        todayAdapter.setData(response);
                        tAdapter.setData(response);
                        hotMVAdapter.setData(response);
                        happyAdapter.setData(response);
                        specialAdapter.setData(response);

                        for (int i = 0; i < response.getModule().size(); i++) {
                            int key = response.getModule().get(i).getStyle();
                            if (key == 15) {
                                Picasso.with(getContext()).load(response.getModule().get(i).getPicurl()).resize(25, 25).into(songMenuIv);
                            } else if (key == 9) {
                                Picasso.with(getContext()).load(response.getModule().get(i).getPicurl()).resize(25, 25).into(newCDIv);
                                Picasso.with(getContext()).load(response.getModule().get(i).getPicurl()).resize(25, 25).into(hotMVIv);
                            } else if (key == 8) {
                                Picasso.with(getContext()).load(response.getModule().get(i).getPicurl()).resize(25, 25).into(hotSellIv);
                                Picasso.with(getContext()).load(response.getModule().get(i).getPicurl()).resize(25, 25).into(tIv);

                            } else if (key == 2) {
                                Picasso.with(getContext()).load(response.getModule().get(i).getPicurl()).resize(25, 25).into(videoIv);

                            } else if (key == 10) {
                                Picasso.with(getContext()).load(response.getModule().get(i).getPicurl()).resize(25, 25).into(todayIv);

                            } else if (key == 14) {
                                Picasso.with(getContext()).load(response.getModule().get(i).getPicurl()).resize(25, 25).into(happyIv);

                            } else if (key == 11) {
                                Picasso.with(getContext()).load(response.getModule().get(i).getPicurl()).resize(25, 25).into(specialIv);

                            }
                        }
                        //场景电台
                        Picasso.with(getContext()).load(response.getResult().getScene().getResult().getAction().get(0).getIcon_android()).into(videoOneIv);
                        Picasso.with(getContext()).load(response.getResult().getScene().getResult().getAction().get(1).getIcon_android()).into(videoTwoIv);
                        Picasso.with(getContext()).load(response.getResult().getScene().getResult().getAction().get(2).getIcon_android()).into(videoThreeIv);
                        Picasso.with(getContext()).load(response.getResult().getScene().getResult().getAction().get(3).getIcon_android()).into(videoFourIv);
                        videoOneTv.setText(response.getResult().getScene().getResult().getAction().get(0).getScene_name());
                        videoTwoTv.setText(response.getResult().getScene().getResult().getAction().get(1).getScene_name());
                        videoThreeTv.setText(response.getResult().getScene().getResult().getAction().get(2).getScene_name());
                        videoFourTv.setText(response.getResult().getScene().getResult().getAction().get(3).getScene_name());

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
        initClick();


    }

    private void initClick() {
        classifyGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (recommendSkipListener != null) {
                    if (position == 1) {
                        recommendSkipListener.toSkipFragment(3);
                    }
                }
            }
        });
        //歌单推荐
        songMenuGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (recommendSkipListener != null) {
                    recommendSkipListener.toSkipFragment(2);
                    EventBus.getDefault().post(new SongMenuDetailEvent(recommendData.getResult().getDiy().getResult().get(position).getListid()));
                }
            }
        });


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
        final ThreadPoolExecutor threadPoolExecutor = MyThreadPool.getOurInstance().getThreadPoolExecutor();
        threadPoolExecutor.execute(new Runnable() {
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
        });

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.music_recommend_songmenu_recommend_more_tv:
                if (onclickSomeListener != null) {
                    onclickSomeListener.onClickSome();
                }
                break;
            case R.id.music_recommend_newcd_more_tv:
                if (recommendSkipListener != null) {
                    recommendSkipListener.toSkipFragment(5);//新碟上架更多
                }
                break;
            case R.id.music_recommend_happy_more_tv:
                if (recommendSkipListener != null) {
                    recommendSkipListener.toSkipFragment(6);//乐播节目更多
                }
                break;
            case R.id.music_recommend_today_more_tv:
                if (recommendSkipListener != null) {
                    recommendSkipListener.toSkipFragment(7);//今日推荐更多
                }
                break;


        }

    }

}
