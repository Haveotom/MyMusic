package com.jingjiang.baidumusic.widget.single;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by dllo on 16/7/7.
 */
public class SingleQueue {
    private static SingleQueue singleQueue;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    private SingleQueue(Context context) {
        requestQueue = Volley.newRequestQueue(context);
        imageLoader = new ImageLoader(requestQueue, new MemoryCache());
    }

    public static SingleQueue getSingleQueue(Context context) {
        if (singleQueue == null) {
            synchronized (SingleQueue.class) {
                if (singleQueue == null) {
                    singleQueue = new SingleQueue(context);
                }
            }
        }
        return singleQueue;
    }
}
