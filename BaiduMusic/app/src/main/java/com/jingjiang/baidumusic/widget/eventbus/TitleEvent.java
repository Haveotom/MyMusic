package com.jingjiang.baidumusic.widget.eventbus;

/**
 * Created by dllo on 16/7/11.
 */
public class TitleEvent {
    private String title;

    public TitleEvent(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
