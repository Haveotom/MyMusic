package com.jingjiang.baidumusic.widget.service;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.RemoteViews;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.widget.eventbus.PlaySongEvent;
import com.jingjiang.baidumusic.widget.eventbus.SeekBarTimeEvent;
import com.jingjiang.baidumusic.widget.eventbus.StartEvent;
import com.jingjiang.baidumusic.widget.eventbus.PauseEvent;
import com.jingjiang.baidumusic.widget.eventbus.StringEvent;
import com.jingjiang.baidumusic.widget.eventbus.TypeEvent;
import com.jingjiang.baidumusic.inmusiclibrary.bean.EverySongData;
import com.jingjiang.baidumusic.widget.threadpool.MyThreadPool;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by dllo on 16/7/1.
 */
public class PlaySongService extends Service {
    private String songId;
    //在服务这个类中,创建binder对象
    //之后再客户端中操作的binder对象
    //都是这个对象
    private MyBinder binder = new MyBinder();
    //当手机中并不存在该服务的实体时
    //启动服务才会调用此生命周期
    public EverySongData data;
    public MediaPlayer mediaPlayer = new MediaPlayer();

    /*第二步广播*/
    public MyBroadcastReceiver broadcastReceiver;
    private long current, allTime;

    /*****************************************/
    @Override
    public void onCreate() {
        EventBus.getDefault().register(this);
        super.onCreate();
        ////Service虽然是在后台进行,但是还是在主线程中的
        //所以所有的耗时操作,都需要放到子线程中
        //不能在子线程中添加吐司,因为吐司是在UI中界面显示,所以必然是在主线程中


        /*广播实例化*/
        broadcastReceiver = new MyBroadcastReceiver();
        Register();//注册广播的方法
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getSongId(StringEvent event) {
        songId = event.getString();
        String leftUrl = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=webapp_music&method=baidu.ting.song.play&format=json&callback=&songid=";
        String rightUrl = "&_=1413017198449";
        String songUrl = leftUrl + songId + rightUrl;
        Log.d("PlaySongService", songId);
        /*原生方法*/
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(songUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                data = gson.fromJson(response.substring(1, response.length() - 2), //截取
                        EverySongData.class);
                initPlaySong();
                initSend();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(stringRequest);

    }
    //http://tingapi.ting.baidu.com/v1/restserver/ting?from=webapp_music&method=baidu.ting.song.play&format=json&callback=&songid=107596984&_=1413017198449


    /*接到showSongActivity传来的信息,然后再会给它*/
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getType(TypeEvent event) {
        String type = event.getType();
        EventBus.getDefault().post(new PlaySongEvent(data));

    }

    public void initSend() {
        //eventbus  传值
        EventBus.getDefault().post(new PlaySongEvent(data));
    }

    private void initPlaySong() {
        mediaPlayer.reset();//为了重用MediaPlayer对象
        try {
            mediaPlayer.setDataSource(data.getBitrate().getFile_link());
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayer.start();//开始
                    showMusicNotification();//notification
                    initThread();
                }
            });
            mediaPlayer.prepareAsync();//异步
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void initThread() {
        ThreadPoolExecutor threadPool = MyThreadPool.getOurInstance().getThreadPoolExecutor();
        //向线程池中添加新的任务
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    while (mediaPlayer.isPlaying()) {
                        EventBus.getDefault().post(
                                new SeekBarTimeEvent(mediaPlayer.getCurrentPosition(),
                                        mediaPlayer.getDuration(),
                                        data.getSonginfo().getLrclink()));
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        });
//        //关闭线程池  ,线程池会完成现有的工作  然后关闭
//        threadPool.shutdown();
//        //线程池会放弃现有的工作,立刻关闭
//        threadPool.shutdownNow();
//
    }


    //每一次执行startService的方法时
    //都会调用该声明周期
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    //该生命周期是继承服务后
    //唯一要求我们重写的生命周期
    //会在客户端与服务绑定的时候回调
    //要求我们返回一个Ibinder对象
    //这个对象会返回到客户端
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    //自定义一个类,继承Binder
    //该类用于服务
    //继承Binder,Binder实现了Ibinder接口
    //在该类中可以写一些用来操作服务的方法
    //该类的对象,会通过onBinder生命周期return出去
    public class MyBinder extends Binder {
        public void pause() {
            mediaPlayer.pause();

        }

        public void start() {
            mediaPlayer.start();
        }


    }

    //notification
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void showMusicNotification() {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.mipmap.icon);
        //为Notification设置自定义布局
        RemoteViews views = new RemoteViews(getPackageName(), R.layout.notifycation_song);
        //图片
        /**
         *
         */
        views.setImageViewUri(R.id.notification_play_music_bar_icon_iv, Uri.parse(data.getSonginfo().getPic_small()));
        //标题
        views.setTextViewText(R.id.notification_play_music_bar_title_iv, data.getSonginfo().getTitle());
        //人名
        views.setTextViewText(R.id.notification_play_music_bar_name_iv, data.getSonginfo().getAuthor());
        Intent pauseIntent = new Intent(getPackageName() + ".MUSIC_PAUSE");
        PendingIntent pausePendingIntent = PendingIntent.getBroadcast(this, 100, pauseIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        //设置监听
        views.setOnClickPendingIntent(R.id.notification_play_music_bar_pause_iv, pausePendingIntent);
        builder.setContent(views);
        Notification notification = builder.build();
        manager.notify(0, notification);


    }

    //第三步  创建一个函数用于注册广播
    public void Register() {
        IntentFilter intentFilter = new IntentFilter();
        //注册广播
        intentFilter.addAction("SeekBar_Control");
        registerReceiver(broadcastReceiver, intentFilter);

    }

    //第一步  建立广播接收类
    class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int progress = intent.getIntExtra("seekBar", 0);
            mediaPlayer.seekTo(progress);

        }
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }
    //http://tingapi.ting.baidu.com/v1/restserver/ting?from=webapp_music&method=baidu.ting.song.play&format=json&callback=&songid=266784254&_=1413017198449
}
