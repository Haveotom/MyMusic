package com.jingjiang.baidumusic.widget.single;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by dllo on 16/7/7.
 */
public class MemoryCache implements ImageLoader.ImageCache {
    //LruCache 可以用来缓存,内部帮我们写好了算法
    //可以在缓存满的时候,把最近最少使用的东西 干掉
    private LruCache<String, Bitmap> lruCache;

    public MemoryCache() {
        int maxSize = (int) (Runtime.getRuntime().maxMemory() / 1024 / 8);
        lruCache = new LruCache<String, Bitmap>(maxSize) {
            //告诉LruCache 每一个Bitmap 占有多大内存
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
    }

    @Override
    public Bitmap getBitmap(String url) {
        return lruCache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        lruCache.put(url, bitmap);

    }
}
