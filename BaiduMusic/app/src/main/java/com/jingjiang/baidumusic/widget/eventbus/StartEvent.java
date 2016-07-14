package com.jingjiang.baidumusic.widget.eventbus;

/**
 * Created by dllo on 16/7/8.
 */
public class StartEvent {
    private int start;

    public StartEvent(int start) {
        this.start = start;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }
}
