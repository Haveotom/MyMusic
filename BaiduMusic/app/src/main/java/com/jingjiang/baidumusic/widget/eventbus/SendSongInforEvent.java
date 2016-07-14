package com.jingjiang.baidumusic.widget.eventbus;

/**
 * Created by dllo on 16/7/12.
 */
public class SendSongInforEvent {
    private String title, songId;

    public SendSongInforEvent(String songId, String title) {
        this.songId = songId;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }
}
