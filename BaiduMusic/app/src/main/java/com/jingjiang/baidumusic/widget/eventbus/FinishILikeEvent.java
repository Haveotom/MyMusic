package com.jingjiang.baidumusic.widget.eventbus;

/**
 * Created by dllo on 16/7/12.
 */
public class FinishILikeEvent {
    private int finish;

    public FinishILikeEvent(int finish) {
        this.finish = finish;
    }

    public int getFinish() {
        return finish;
    }

    public void setFinish(int finish) {
        this.finish = finish;
    }
}
