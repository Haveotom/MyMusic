package com.jingjiang.baidumusic.showsong.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.base.BaseFragment;
import com.jingjiang.baidumusic.inmymusic.data.SingleSongData;
import com.jingjiang.baidumusic.widget.database.SongValues;
import com.jingjiang.baidumusic.widget.eventbus.PlaySongEvent;
import com.jingjiang.baidumusic.widget.single.SingleLiteOrm;
import com.jingjiang.baidumusic.widget.single.SingleQueue;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by dllo on 16/7/2.
 */
public class PictureFragment extends BaseFragment implements View.OnClickListener {
    private ImageView pictureIv;
    private String picture;
    private ImageView noLikeIv, likeIv, downLoadIv;
    private String title, name, songId;
    private static final String DB_SINGLE_SONG = "";
    private LiteOrm liteOrm;//本地数据库
    private SingleSongData singleSongData;
    private String userName = "";
    private SingleSongData user;
    private PopupWindow popupWindow;

    @Override
    protected int initLayout() {
        return R.layout.showsong_f_picture;
    }

    public PictureFragment() {

        EventBus.getDefault().register(this);
    }

    //从服务来
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getImage(PlaySongEvent event) {
        if (event.getData().getSonginfo().getSong_id() != "") {
            picture = event.getData().getSonginfo().getPic_huge();
            title = event.getData().getSonginfo().getTitle();
            name = event.getData().getSonginfo().getAuthor();
            songId = event.getData().getSonginfo().getSong_id();
        }
    }

    @Override
    protected void initView() {
        downLoadIv = bindView(R.id.showsong_f_picture_download_iv);
        noLikeIv = bindView(R.id.showsong_f_picture_like_no_iv);
        likeIv = bindView(R.id.showsong_f_picture_like_yes_iv);
        pictureIv = bindView(R.id.showsong_f_picture_big_icon_iv);
        if (picture != null) {
            SingleQueue.getSingleQueue(getContext()).getImageLoader()
                    .get(picture, ImageLoader.getImageListener(pictureIv, R.mipmap.default_album_playing, R.mipmap.default_album_playing));
        }
        likeIv.setOnClickListener(this);
        noLikeIv.setOnClickListener(this);
        downLoadIv.setOnClickListener(this);
        liteOrm = LiteOrm.newCascadeInstance(getContext(), DB_SINGLE_SONG);//创建数据库
        singleSongData = new SingleSongData();


    }


    @Override
    protected void initData() {
        //  为收藏添加状态,判断是否存在,看红心的状态
        if (songId != null) {
            user = BmobUser.getCurrentUser(getContext(), SingleSongData.class);//用户登录的名
            if (null != user) {
                final BmobQuery<SingleSongData> query = new BmobQuery<SingleSongData>();
                userName = String.valueOf(BmobUser.getCurrentUser(getContext()).getUsername());//获取当前app中登录的账号

                query.addWhereEqualTo("userName", userName);
                //返回50条数据，如果不加上这条语句，默认返回10条数据
                query.setLimit(50);
                query.findObjects(getContext(), new FindListener<SingleSongData>() {
                    @Override
                    public void onSuccess(List<SingleSongData> list) {
                        //遍历所有UserName
                        for (int i = 0; i < list.size(); i++) {
                            if ((list.get(i).getSongId()).equals(songId)) {
                                noLikeIv.setVisibility(View.GONE);
                                likeIv.setVisibility(View.VISIBLE);
                            }
                        }
                    }

                    @Override
                    public void onError(int i, String s) {
                    }
                });

            } else {//本地数据库
                //查询  得到集合singleSong
                List<SingleSongData> singleSong = SingleLiteOrm.getSingleLiteOrm().getLiteOrm().query(new QueryBuilder<SingleSongData>(SingleSongData.class)
                        .distinct(true)//去重
                        .where(SongValues.SV_SONGID + " LIKE ?", new String[]{songId}));
                //如果集合不等于"0"  则说明其中有歌曲 则让其变红
                if (singleSong.size() != 0) {
                    Log.d("PictureFragment", "singleSong:" + singleSong);
                    noLikeIv.setVisibility(View.GONE);
                    likeIv.setVisibility(View.VISIBLE);
                }

            }
        }

    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.showsong_f_picture_like_no_iv:
                addSingleSong();
                noLikeIv.setVisibility(View.GONE);
                likeIv.setVisibility(View.VISIBLE);
                break;
            case R.id.showsong_f_picture_like_yes_iv:
                deleteSingleSong();
                likeIv.setVisibility(View.GONE);
                noLikeIv.setVisibility(View.VISIBLE);
                break;
            case R.id.showsong_f_picture_download_iv:


                break;
        }
    }


    private void deleteSingleSong() {
        final BmobQuery<SingleSongData> query = new BmobQuery<SingleSongData>();
        query.addWhereEqualTo("userName", userName);
        if (null != null) {
            query.findObjects(getContext(), new FindListener<SingleSongData>() {
                @Override
                public void onSuccess(List<SingleSongData> list) {
                    for (SingleSongData singleSongData : list) {
                        query.addWhereEqualTo("songId", songId);
                        query.findObjects(getContext(), new FindListener<SingleSongData>() {
                            @Override
                            public void onSuccess(List<SingleSongData> list) {
                                for (SingleSongData data : list) {
                                    data.delete(getContext());
                                    Toast.makeText(getContext(), "已取消喜欢", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onError(int i, String s) {
                                Toast.makeText(getContext(), "取消失败", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }

                @Override
                public void onError(int i, String s) {

                }
            });
        } else {
            //本地的删除
            //根据歌曲的id  来删除
            SingleLiteOrm.getSingleLiteOrm().getLiteOrm().delete(new WhereBuilder(SingleSongData.class)
                    .where(SongValues.SV_SONGID + " LIKE ?", new String[]{songId}));
            Toast.makeText(getContext(), "已取消喜欢", Toast.LENGTH_SHORT).show();


        }


    }

    private void addSingleSong() {
        if (songId != null) {//对是否在播放歌曲进行判断
            singleSongData.setUserName(userName);
            singleSongData.setTitle(title);
            singleSongData.setAuthor(name);
            singleSongData.setSongId(songId);
            //存入账号数据库
            if (null != user) {
                final BmobQuery<SingleSongData> query = new BmobQuery<SingleSongData>();

                query.addWhereEqualTo("userName", userName);
                //返回50条数据，如果不加上这条语句，默认返回10条数据
                query.setLimit(50);
                query.findObjects(getContext(), new FindListener<SingleSongData>() {
                    boolean flag = false;

                    @Override
                    public void onSuccess(List<SingleSongData> list) {

                        //遍历所有UserName
                        for (int i = 0; i < list.size(); i++) {
                            if ((list.get(i).getSongId()).equals(songId)) {
                                Toast.makeText(getActivity(), "曾经就已经是喜欢的单曲哦~", Toast.LENGTH_SHORT).show();
                                flag = true;
                            }
                        }
                        if (!flag) {
                            Log.d("PictureFragment", "flag:" + !flag);
                            singleSongData.save(getContext(), new SaveListener() {
                                @Override
                                public void onSuccess() {
                                    Toast.makeText(getContext(), "已添加到我喜欢的单曲", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onFailure(int i, String s) {
                                    Toast.makeText(getContext(), "添加失败", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }

                    @Override
                    public void onError(int i, String s) {
                    }
                });

            } else {//没有账号登录的时候
                //存入本地数据库

                //查询的方法
                SingleLiteOrm.getSingleLiteOrm().getLiteOrm().query(new QueryBuilder<SingleSongData>(SingleSongData.class)
                        .distinct(true)//去重
                        .where(SongValues.SV_SONGID + " LIKE ?", new String[]{songId}));
                //插入
                singleSongData.setSongId(songId);
                singleSongData.setTitle(title);
                singleSongData.setAuthor(name);
                SingleLiteOrm.getSingleLiteOrm().getLiteOrm().insert(singleSongData);//加入数据库
                Toast.makeText(getContext(), "已添加到我喜欢的单曲", Toast.LENGTH_SHORT).show();
            }


        } else {//根据songId判断歌曲是否存在
            Toast.makeText(getContext(), "当前没有播放歌曲", Toast.LENGTH_SHORT).show();
        }
    }


}
