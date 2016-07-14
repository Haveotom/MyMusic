package com.jingjiang.baidumusic.inmymusic.data;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * Created by dllo on 16/7/8.
 * 在 AndroidStudio 依次点击  tools Android  enable adb
 */
public class SingleSongData extends BmobObject {
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;
    private String userName, title, author, songId;
    private boolean isLike;

//    public SingleSongData() {
//        this.setTableName("SingleSong_tab");
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }
}
