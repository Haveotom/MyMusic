package com.jingjiang.baidumusic.widget;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

/**
 * Created by dllo on 16/7/4.
 * 将图片处理成圆角矩形
 */
public class RoundDrawable extends Drawable {
    private Bitmap bitmap;
    private Paint paint;
    private RectF rectF;
    private int x, y;

    public RoundDrawable(Bitmap bitmap) {
        this.bitmap = bitmap;
        paint = new Paint();
        //设置画笔的着色器
        //可以理解成画笔的花纹
        //当设置好了之后 用画笔画任何东西,都是这张Bitmap
        //例如 画一个圆角矩形  就是这张Bitmap被处理成圆角矩形的样子
        //ClAMP 是将bitmap周围一圈像素  拉伸
        BitmapShader bitmapShader = new BitmapShader(
                bitmap,
                Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP);
        paint.setShader(bitmapShader);
        paint.setAntiAlias(true);//抗锯齿

    }

    //当ImageView的宽高是wrap_content的时候  来告诉Drawable它的宽高
    @Override
    public int getIntrinsicWidth() {
        return bitmap.getWidth();
    }

    @Override
    public int getIntrinsicHeight() {
        return bitmap.getHeight();
    }

    //核心方法  可以将我们需要的内容绘制到屏幕上
    @Override
    public void draw(Canvas canvas) {
        int min = Math.min(x, y);
        canvas.drawCircle(min / 2, min / 2, min / 2, paint);
        canvas.drawRoundRect(rectF, 100, 100, paint);

    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);

    }

    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);
        rectF = new RectF(left, top, right, bottom);
        x = right - left;
        y = bottom - top;
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return 0;
    }
}
