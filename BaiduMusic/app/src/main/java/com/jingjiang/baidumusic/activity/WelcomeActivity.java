package com.jingjiang.baidumusic.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.base.BaseActivity;
import com.jingjiang.baidumusic.base.MyApplication;
import com.jingjiang.baidumusic.widget.othertool.DiskCache;
import com.jingjiang.baidumusic.widget.threadpool.MyThreadPool;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by dllo on 16/7/12.
 */
public class WelcomeActivity extends BaseActivity implements View.OnClickListener {
    private ImageView backgroundIv;
    private TextView timeTv;
    private CountDownTimer timer;

    @Override
    protected int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        backgroundIv = bindView(R.id.aty_welcome_background_iv);
        timeTv = bindView(R.id.aty_welcome_to_skip_tv);
        timeTv.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        new InternetBitmapTask().execute("http://imgsrc.baidu.com/forum/w%3D580/sign=eb0dcc9318d5ad6eaaf964e2b1cb39a3/c033ad6eddc451dab8ddfbbcb5fd5266d11632a0.jpg");
        //倒计时
        timer = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeTv.setText(millisUntilFinished / 1000 + "s 跳过");
            }

            @Override
            public void onFinish() {
                startActivity(new Intent(MyApplication.context, MainActivity.class));
                finish();
            }
        }.start();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.aty_welcome_to_skip_tv:
                //在跳转的时候,取消timer
                timer.cancel();
                startActivity(new Intent(this, MainActivity.class));
                //让其他界面返回时,不会达到欢迎界面
                finish();
                break;
        }
    }

    class InternetBitmapTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... params) {
            //从网络下载图片
            String imgUrl = params[0];
            Bitmap bitmap;
            HttpURLConnection connection = null;
            InputStream is = null;
            //还没有进行网络拉取的时候  准备检查缓存
            DiskCache diskCache = new DiskCache(getApplicationContext());
            //从硬盘的这个路径取出图片
            bitmap = diskCache.getBitmap(imgUrl);
            if (bitmap != null) {
                return bitmap;
            }
            try {
                URL url = new URL(imgUrl);
                connection = (HttpURLConnection) url.openConnection();
                is = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(is);
                //成功从网络拉取到图片
                //存到硬盘里
                diskCache.putBitmap(imgUrl, bitmap);
                return bitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();

            } finally {
                try {
                    if (is != null) {
                        is.close();
                        connection.disconnect();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            backgroundIv.setImageBitmap(bitmap);
        }

    }

}
