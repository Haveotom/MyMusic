package com.jingjiang.baidumusic.widget.eventbus;

/**
 * Created by dllo on 16/7/4.
 */
public class SongMenuDetailEvent {
    private String string;

    public SongMenuDetailEvent(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
