package com.jingjiang.baidumusic.widget.eventbus;

/**
 * Created by dllo on 16/7/1.
 */
public class StringEvent {
    private String string;

    public StringEvent(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
