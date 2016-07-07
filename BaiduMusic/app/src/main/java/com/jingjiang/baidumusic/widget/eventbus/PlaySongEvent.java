package com.jingjiang.baidumusic.widget.eventbus;

import com.jingjiang.baidumusic.inmusiclibrary.bean.EverySongData;

/**
 * Created by dllo on 16/7/2.
 */
public class PlaySongEvent {
    private EverySongData data;

    public PlaySongEvent(EverySongData data) {
        this.data = data;
    }

    public EverySongData getData() {
        return data;
    }

    public void setData(EverySongData data) {
        this.data = data;
    }
}
