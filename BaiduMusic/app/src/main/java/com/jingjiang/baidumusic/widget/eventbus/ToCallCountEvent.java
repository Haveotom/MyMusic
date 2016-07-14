package com.jingjiang.baidumusic.widget.eventbus;

/**
 * Created by dllo on 16/7/12.
 */
public class ToCallCountEvent {
    private String call;

    public ToCallCountEvent(String call) {
        this.call = call;
    }

    public String getCall() {
        return call;
    }

    public void setCall(String call) {
        this.call = call;
    }
}
