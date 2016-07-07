package com.jingjiang.baidumusic.widget.eventbus;

/**
 * Created by dllo on 16/7/1.
 */
public class IntegerEvent {
    private int anInt;

    public IntegerEvent(int anInt) {
        this.anInt = anInt;
    }

    public int getAnInt() {
        return anInt;
    }

    public void setAnInt(int anInt) {
        this.anInt = anInt;
    }
}
