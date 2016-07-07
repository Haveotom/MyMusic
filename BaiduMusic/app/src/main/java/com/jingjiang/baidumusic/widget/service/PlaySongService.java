package com.jingjiang.baidumusic.widget.service;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.RemoteViews;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.widget.eventbus.PlaySongEvent;
import com.jingjiang.baidumusic.widget.eventbus.StringEvent;
import com.jingjiang.baidumusic.widget.eventbus.TypeEvent;
import com.jingjiang.baidumusic.inmusiclibrary.bean.EverySongData;
import com.jingjiang.baidumusic.widget.myinterface.OnClickSomeListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;

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
    private OnClickSomeListener clickSomeListener;

    public void setClickSomeListener(OnClickSomeListener clickSomeListener) {
        this.clickSomeListener = clickSomeListener;
    }

    @Override
    public void onCreate() {
        EventBus.getDefault().register(this);
        super.onCreate();
        ////Service虽然是在后台进行,但是还是在主线程中的
        //所以所有的耗时操作,都需要放到子线程中
        //不能在子线程中添加吐司,因为吐司是在UI中界面显示,所以必然是在主线程中

//        Intent intent = new Intent();
//        intent.setClass(this, ShowSongActivity.class);
//        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
//        startService(intent);

    }
//http://tingapi.ting.baidu.com/v1/restserver/ting?from=webapp_music&method=baidu.ting.song.play&format=json&callback=&songid=242078437&_=1413017198449";


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getSongId(StringEvent event) {
        songId = event.getString();
        String leftUrl = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=webapp_music&method=baidu.ting.song.play&format=json&callback=&songid=";
        String rightUrl = "&_=1413017198449";
        String songUrl = leftUrl + songId + rightUrl;
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

    //接到showSongActivity传来的信息,然后再会给它

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getType(TypeEvent event) {
        String type = event.getType();
        EventBus.getDefault().post(new PlaySongEvent(data));

    }

    public void initSend() {
        //eventbus  传值
        EventBus.getDefault().post(new PlaySongEvent(data));

//        //传值
//        Intent intent = new Intent();
//        //设置传播的键值对:
//        intent.putExtra("information", data.getSonginfo().getTitle());
//        Log.d("PlaySongService", data.getSonginfo().getTitle());
//        intent.setAction(ACCESSIBILITY_SERVICE);
//        //发送广播
//        sendBroadcast(intent);

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
                }
            });
            mediaPlayer.prepareAsync();//异步
        } catch (IOException e) {
            e.printStackTrace();
        }

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


    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
    //http://tingapi.ting.baidu.com/v1/restserver/ting?from=webapp_music&method=baidu.ting.song.play&format=json&callback=&songid=266784254&_=1413017198449
}
