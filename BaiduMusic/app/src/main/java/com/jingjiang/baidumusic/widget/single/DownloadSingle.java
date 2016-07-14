package com.jingjiang.baidumusic.widget.single;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.jingjiang.baidumusic.base.MyApplication;
import com.jingjiang.baidumusic.bean.DownloadData;
import com.jingjiang.baidumusic.inmusiclibrary.bean.EverySongData;
import com.litesuits.orm.db.assit.QueryBuilder;

/**
 * Created by dllo on 16/7/11.
 */
public class DownloadSingle {
    private static DownloadSingle singleDownload;

    private DownloadManager downloadManager;
    private static final String LEFT_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=webapp_music&method=baidu.ting.song.play&format=json&callback=&songid=";
    private static final String RIGHT_URL = "&_=1413017198449";

    private DownloadSingle() {
        downloadManager = (DownloadManager) MyApplication.context.getSystemService(Context.DOWNLOAD_SERVICE);
    }

    public static DownloadSingle getSingleDownload() {
        if (singleDownload == null) {
            synchronized (DownloadSingle.class) {
                if (singleDownload == null) {
                    singleDownload = new DownloadSingle();
                }
            }
        }
        return singleDownload;
    }

    public void Download(String songId) {
        RequestQueue queue = Volley.newRequestQueue(MyApplication.context);
        String songUrl = LEFT_URL + songId + RIGHT_URL;
        StringRequest stringRequest = new StringRequest(songUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                EverySongData data = gson.fromJson(response.substring(1, response.length() - 2), //截取
                        EverySongData.class);
                String downUrl = data.getBitrate().getFile_link();
                String songId = data.getSonginfo().getSong_id();
                String title = data.getSonginfo().getTitle();
                String author = data.getSonginfo().getAuthor();
                QueryBuilder<DownloadData> queryBuilder = new QueryBuilder<>(DownloadData.class);
                queryBuilder.whereEquals("title", title);
                if (SingleLiteOrm.getSingleLiteOrm().getLiteOrm().query(queryBuilder).size() != 0) {
                    Toast.makeText(MyApplication.context, "已经下载过该歌曲,请勿重复下载", Toast.LENGTH_SHORT).show();
                } else {
                    // 开始下载
                    Uri resource = Uri.parse(downUrl);
                    DownloadManager.Request request = new DownloadManager.Request(resource);
                    request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
                    request.setAllowedOverRoaming(false);
                    // 设置文件类型
                    MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
                    String mimiString = mimeTypeMap.getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(downUrl));
                    request.setMimeType(mimiString);
                    //在通知栏中显示
                    request.setShowRunningNotification(true);
                    request.setVisibleInDownloadsUi(true);
                    //sdcard的目录下的download文件夹
                    request.setDestinationInExternalPublicDir("/music/mp3", title + ".mp3");
                    long id = downloadManager.enqueue(request);
                    SingleLiteOrm.getSingleLiteOrm().getLiteOrm().insert(new DownloadData(songId, title, author));
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        queue.add(stringRequest);

    }


}
