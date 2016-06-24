package com.jingjiang.baidumusic.widget;

/**
 * Created by dllo on 16/6/23.
 */
public final class Url {
    //乐库
    public static final String MUSICLIBRARY_RANKLIST = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.billboard.billCategory&format=json&from=ios&version=5.2.1&from=ios&channel=appstore";
    public static final String MUSICLIBRARY_SONGMENU = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.diy.gedan&page_no=1";
    public static final String MUSICLIBRARY_RADIO = "";
    public static final String MUSICLIBRARY_MV_NEW = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.0.1&channel=xiaomi&operator=2&provider=11%2C12&method=baidu.ting.mv.searchMV&format=json&order=1&page_num=1";
    public static final String MUSICLIBRARY_MV_HOT = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.0.1&channel=xiaomi&operator=2&provider=11%2C12&method=baidu.ting.mv.searchMV&format=json&order=0&page_num=1&page_size=20&query=%E5%85%A8%E9%83%A8";
    //K歌
    public static final String KSONG_RECYCLERPICTURE = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.0.1&channel=xiaomi&operator=2&method=baidu.ting.active.showList";
    public static final String KSONG_KTV = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.0.1&channel=xiaomi&operator=2&method=baidu.ting.learn.detail&page_no=0&page_size=50&query=all&value=0&desc=KTV%E7%83%AD%E6%AD%8C%E6%A6%9C";
    public static final String KSONG_CHINESE = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.0.1&channel=xiaomi&operator=2&method=baidu.ting.learn.detail&page_no=0&page_size=50&query=area&value=1&desc=%E5%8D%8E%E8%AF%AD%E9%87%91%E6%9B%B2";
    public static final String KSONG_EUROPE = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.0.1&channel=xiaomi&operator=2&method=baidu.ting.learn.detail&page_no=0&page_size=50&query=area&value=2&desc=%E6%AC%A7%E7%BE%8E%E7%BB%8F%E5%85%B8";
    public static final String KSONG_BOY = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.0.1&channel=xiaomi&operator=2&method=baidu.ting.learn.detail&query=gender&value=0&desc=%E7%94%B7%E6%AD%8C%E6%89%8B";
    public static final String KSONG_GIRL = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.0.1&channel=xiaomi&operator=2&method=baidu.ting.learn.detail&query=gender&value=1&desc=%E5%A5%B3%E6%AD%8C%E6%89%8B";
    public static final String KSONG_BAND = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.0.1&channel=xiaomi&operator=2&method=baidu.ting.learn.detail&query=gender&value=2&desc=%E4%B9%90%E9%98%9F%E7%BB%84%E5%90%88";
    public static final String KSONG_ALLSING = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.0.1&channel=xiaomi&operator=2&method=baidu.ting.learn.now&page_size=50 ";
    //直播
    public static final String LIVE_HOT = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.0.1&channel=xiaomi&operator=2&method=baidu.ting.show.item&page_size=30&page_no=1&category=hot";

}
