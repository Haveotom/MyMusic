package com.jingjiang.baidumusic.bean;

import android.annotation.TargetApi;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by dllo on 16/7/11.
 */
public class DownloadData {
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private String songId, title, author;

    public DownloadData(String songId, String title, String author) {
        this.songId = songId;
        this.title = title;
        this.author = author;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
