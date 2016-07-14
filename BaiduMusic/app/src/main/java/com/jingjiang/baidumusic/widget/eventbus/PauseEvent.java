package com.jingjiang.baidumusic.widget.eventbus;

/**
 * Created by dllo on 16/7/8.
 */
public class PauseEvent {
    private int stop;

    public PauseEvent(int stop) {
        this.stop = stop;
    }

    public int getStop() {
        return stop;
    }

    public void setStop(int stop) {
        this.stop = stop;
    }
}
