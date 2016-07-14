package com.jingjiang.baidumusic.widget.eventbus;

/**
 * Created by dllo on 16/7/9.
 */
public class InforFinishEvent {
    private int type;

    public InforFinishEvent(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
