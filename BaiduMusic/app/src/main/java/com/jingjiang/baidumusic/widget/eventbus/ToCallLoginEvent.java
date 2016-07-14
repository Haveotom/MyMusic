package com.jingjiang.baidumusic.widget.eventbus;

/**
 * Created by dllo on 16/7/9.
 */
public class ToCallLoginEvent {
    private String login;

    public ToCallLoginEvent(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
