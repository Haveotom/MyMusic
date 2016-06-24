package com.jingjiang.baidumusic.widget;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jingjiang.baidumusic.base.MyApplication;

/**
 * Created by dllo on 16/6/21.
 * 单例
 */
public class VolleySingle {
    private static Context mContext;
    private RequestQueue queue;//请求队列
    private static VolleySingle ourInstance = new VolleySingle();

    //获得单例的对象
    public static VolleySingle getInstance() {
        return ourInstance;
    }

    public VolleySingle() {
        //获取MyApplication里面的context
        mContext = MyApplication.getContext();
        queue = getQueue();

    }

    //提供一个方法来新建请求队列
    public RequestQueue getQueue() {
        if (queue == null) {
            queue = Volley.newRequestQueue(mContext);
        }
        return queue;
    }

    public static final String TAG = "VolleySingleton";

    //添加队列
    public <T> void _addRequest(Request<T> request) {
        request.setTag(TAG);//为我们的请求添加标签,方便管理
        queue.add(request);//将请求添加到队列当中
    }

    public <T> void _addRequest(Request<T> request, Object tag) {
        request.setTag(tag);
        queue.add(request);
    }

    /**
     * 创建StringRequest
     *
     * @param url           接口网址
     * @param listener      成功时候的回调
     * @param errorListener 失败时候的回调
     */
    public void _addRequest(String url,
                            Response.Listener<String> listener,
                            Response.ErrorListener errorListener) {
        StringRequest stringRequest = new StringRequest(url, listener, errorListener);
        //将请求加入队列
        _addRequest(stringRequest);
    }

    /**
     * Gson
     *
     * @param url
     * @param errorListener
     * @param listener
     * @param mClass
     * @param <T>
     */
    public <T> void _addRequest(String url,
                                Response.ErrorListener errorListener,
                                Response.Listener<T> listener,
                                Class<T> mClass) {
        GsonRequest gsonRequest = new GsonRequest(Request.Method.GET, url, errorListener, listener, mClass);
        _addRequest(gsonRequest);

    }

    public static void _addRequest(String url,
                                   Response.ErrorListener errorListener,
                                   Response.Listener<String> listener) {
        //获取单例的对象,调用添加请求的方法(这个是StringRequest的请求)
        getInstance()._addRequest(url, errorListener, listener);
    }

    public static <T> void addRequest(String url,
                                      Response.ErrorListener errorListener,
                                      Response.Listener<T> listener,
                                      Class<T> mClass) {
        //跟上一方法一样,将GsonRequest请求加入单例的队列里
        getInstance()._addRequest(url, errorListener, listener, mClass);
    }

    /**
     * 这种方法是讲请求移除队列
     *
     * @param tag 根据不同的tag移除出队列
     */
    public void removeRequest(Object tag) {
        queue.cancelAll(tag);
    }


}
