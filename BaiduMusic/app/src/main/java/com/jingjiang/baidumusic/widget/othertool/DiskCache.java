package com.jingjiang.baidumusic.widget.othertool;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by dllo on 16/7/12.
 */
public class DiskCache {
    String cacheDir;//文件缓存的路径

    public DiskCache(Context context) {
        cacheDir = context.getCacheDir() + "/Image";
    }

    public Bitmap getBitmap(String url) {
        //图片的url转换成md5格式
        url = MD5Util.getMD5String(url);
        return BitmapFactory.decodeFile(cacheDir + url + ".png");
    }

    public void putBitmap(String url, Bitmap bitmap) {
        File file = new File(cacheDir);
        if (!file.exists()) {
            file.mkdir();
        }
        url = MD5Util.getMD5String(url);
        File imageFile = new File(cacheDir, url + ".png");
        if (!imageFile.exists()) {
            FileOutputStream fileOutputStream = null;
            try {
                imageFile.createNewFile();

                fileOutputStream = new FileOutputStream(imageFile);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
