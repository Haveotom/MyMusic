package com.jingjiang.baidumusic.widget.eventbus;

/**
 * Created by dllo on 16/7/7.
 */
public class SeekBarTimeEvent {
    private long currentTime, allTime;
    private String lyric;

    public SeekBarTimeEvent(long currentTime, long allTime, String lyric) {
        this.currentTime = currentTime;
        this.allTime = allTime;
        this.lyric = lyric;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }

    public long getAllTime() {
        return allTime;
    }

    public void setAllTime(long allTime) {
        this.allTime = allTime;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }
}
