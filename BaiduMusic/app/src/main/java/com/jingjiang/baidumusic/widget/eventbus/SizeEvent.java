package com.jingjiang.baidumusic.widget.eventbus;

/**
 * Created by dllo on 16/7/12.
 */
public class SizeEvent {
    private int size;

    public SizeEvent(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
