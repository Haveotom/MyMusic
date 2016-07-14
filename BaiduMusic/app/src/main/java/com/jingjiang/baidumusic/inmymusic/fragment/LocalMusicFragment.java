package com.jingjiang.baidumusic.inmymusic.fragment;

import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.base.BaseFragment;
import com.jingjiang.baidumusic.inmymusic.data.SingleSongData;
import com.jingjiang.baidumusic.inmymusic.adapter.ILikeAdapter;
import com.jingjiang.baidumusic.widget.eventbus.SendSongInforEvent;
import com.jingjiang.baidumusic.widget.eventbus.SizeEvent;
import com.jingjiang.baidumusic.widget.eventbus.StringEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/7/11.
 */
public class LocalMusicFragment extends BaseFragment implements View.OnClickListener {
    private LinearLayout returnLl;
    private ListView listView;
    private SingleSongData data;
    private ILikeAdapter adapter;
    private String receiveTitle, receiveSongId;
    private String title;
    private String songId = "";
    private String author;

    @Override
    protected int initLayout() {
        return R.layout.mymusic_f_local;
    }

    @Override
    protected void initView() {
        adapter = new ILikeAdapter(getContext());
        returnLl = bindView(R.id.mymusic_f_local_return_ll);
        bindView(R.id.mymusic_f_local_return_tv).setOnClickListener(this);
        listView = bindView(R.id.mymusic_f_local_listview);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getSongInfor(SendSongInforEvent event) {
        receiveTitle = event.getTitle();
        receiveSongId = event.getSongId();
        Log.d("LocalMusicFragment", receiveTitle);

    }

    /**
     * Cursor  query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder)；
     * Uri：指明要查询的数据库名称加上表的名称，从MediaStore中我们可以找到相应信息的参数。
     * Projection: 指定查询数据库表中的哪几列，返回的游标中将包括相应的信息。Null则返回所有信息。
     * selection: 指定查询条件
     * selectionArgs：参数selection里有 ？这个符号是，这里可以以实际值代替这个问号。如果selection这个没有？的话，那么这个String数组可以为null。
     * SortOrder：指定查询结果的排列顺序
     * 下面的命令将返回所有在外部存储卡上的音乐文件的信息：
     * Cursor cursor = query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
     */

    @Override
    protected void initData() {
        Cursor cursor = getContext().getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        final List<SingleSongData> datas = new ArrayList<>();
        for (int i = 0; i < cursor.getCount(); i++) {
            data = new SingleSongData();
            cursor.moveToNext();
            title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
            author = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));

            int isMusic = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC));
            if (isMusic != 0) {
                data.setTitle(title);
                data.setAuthor(author);
                data.setSongId(songId);
                datas.add(data);
            }
        }
        adapter.setSongList(datas);
        listView.setAdapter(adapter);
        EventBus.getDefault().post(new SizeEvent(datas.size()));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (title == receiveTitle) {
                    songId = receiveSongId;
                EventBus.getDefault().post(new StringEvent(songId));
                }
                Log.d("DownloadFragment", songId);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mymusic_f_local_return_tv:
                getFragmentManager().popBackStack();
                break;
        }

    }
}
