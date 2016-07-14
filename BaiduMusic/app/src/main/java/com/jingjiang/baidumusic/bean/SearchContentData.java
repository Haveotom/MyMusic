package com.jingjiang.baidumusic.bean;

import java.util.List;

/**
 * Created by dllo on 16/6/25.
 */
public class SearchContentData {


    /**
     * error_code : 22000
     * result : {"query":"魔法城堡","syn_words":"","rqt_type":3,"song_info":{"total":3,"song_list":[{"content":"","copy_type":"1","toneid":"0","info":"","all_rate":"24,64,128","resource_type":2,"relate_status":0,"has_mv_mobile":0,"versions":"","song_id":"34581956","title":"魔法城堡","ting_uid":"85850177","author":"朴有天","album_id":"34581946","album_title":"2013湖南卫视元宵晚会","is_first_publish":0,"havehigh":0,"charge":0,"has_mv":1,"learn":0,"song_source":"web","piao_id":"0","korean_bb_song":"0","resource_type_ext":"0","mv_provider":"1000000000","artist_id":"921","all_artist_id":"921","lrclink":"http://musicdata.baidu.com/data2/lrc/106248810/106248810.lrc","data_source":0,"cluster_id":0,"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}"},{"content":"","copy_type":"1","toneid":"0","info":"","all_rate":"24,64,128,192","resource_type":0,"relate_status":1,"has_mv_mobile":0,"versions":"","song_id":"73903916","title":"【魔法城堡】（九夜灯）","ting_uid":"110948348","author":"九夜灯","album_id":"0","album_title":"","is_first_publish":0,"havehigh":0,"charge":0,"has_mv":0,"learn":0,"song_source":"yyr","piao_id":"0","korean_bb_song":"0","resource_type_ext":"0","mv_provider":"0000000000","artist_id":"73342908","all_artist_id":"73342908","lrclink":"http://musicdata.baidu.com/data2/lrc/126398902/126398902.lrc","data_source":0,"cluster_id":0,"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}"},{"content":"就是可以去<em>魔法城堡<\/em>探险\n翻过寒冷的雪山\n越过沙漠和草原\nBecause I am a brave man\n我不怕路途遥远\n我不怕再多困难\n要让大家知道我有多勇敢\n梦很甜\n梦里我长出翅膀 飞上了蓝天\n梦很咸\n","copy_type":"1","toneid":"0","info":"","all_rate":"","resource_type":0,"relate_status":3,"has_mv_mobile":0,"versions":"","song_id":"245851994","title":"梦","ting_uid":"","author":"王沛霖","album_id":"0","album_title":"","is_first_publish":0,"havehigh":2,"charge":0,"has_mv":0,"learn":0,"song_source":"web","piao_id":"0","korean_bb_song":"0","resource_type_ext":"0","mv_provider":"0000000000","artist_id":"243467560","all_artist_id":"243467560","lrclink":"http://musicdata.baidu.com/data2/lrc/245881040/245881040.lrc","data_source":0,"cluster_id":0,"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}"}]}}
     */

    private int error_code;
    /**
     * query : 魔法城堡
     * syn_words :
     * rqt_type : 3
     * song_info : {"total":3,"song_list":[{"content":"","copy_type":"1","toneid":"0","info":"","all_rate":"24,64,128","resource_type":2,"relate_status":0,"has_mv_mobile":0,"versions":"","song_id":"34581956","title":"魔法城堡","ting_uid":"85850177","author":"朴有天","album_id":"34581946","album_title":"2013湖南卫视元宵晚会","is_first_publish":0,"havehigh":0,"charge":0,"has_mv":1,"learn":0,"song_source":"web","piao_id":"0","korean_bb_song":"0","resource_type_ext":"0","mv_provider":"1000000000","artist_id":"921","all_artist_id":"921","lrclink":"http://musicdata.baidu.com/data2/lrc/106248810/106248810.lrc","data_source":0,"cluster_id":0,"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}"},{"content":"","copy_type":"1","toneid":"0","info":"","all_rate":"24,64,128,192","resource_type":0,"relate_status":1,"has_mv_mobile":0,"versions":"","song_id":"73903916","title":"【魔法城堡】（九夜灯）","ting_uid":"110948348","author":"九夜灯","album_id":"0","album_title":"","is_first_publish":0,"havehigh":0,"charge":0,"has_mv":0,"learn":0,"song_source":"yyr","piao_id":"0","korean_bb_song":"0","resource_type_ext":"0","mv_provider":"0000000000","artist_id":"73342908","all_artist_id":"73342908","lrclink":"http://musicdata.baidu.com/data2/lrc/126398902/126398902.lrc","data_source":0,"cluster_id":0,"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}"},{"content":"就是可以去<em>魔法城堡<\/em>探险\n翻过寒冷的雪山\n越过沙漠和草原\nBecause I am a brave man\n我不怕路途遥远\n我不怕再多困难\n要让大家知道我有多勇敢\n梦很甜\n梦里我长出翅膀 飞上了蓝天\n梦很咸\n","copy_type":"1","toneid":"0","info":"","all_rate":"","resource_type":0,"relate_status":3,"has_mv_mobile":0,"versions":"","song_id":"245851994","title":"梦","ting_uid":"","author":"王沛霖","album_id":"0","album_title":"","is_first_publish":0,"havehigh":2,"charge":0,"has_mv":0,"learn":0,"song_source":"web","piao_id":"0","korean_bb_song":"0","resource_type_ext":"0","mv_provider":"0000000000","artist_id":"243467560","all_artist_id":"243467560","lrclink":"http://musicdata.baidu.com/data2/lrc/245881040/245881040.lrc","data_source":0,"cluster_id":0,"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}"}]}
     */

    private ResultBean result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private String query;
        private String syn_words;
        private int rqt_type;
        /**
         * total : 3
         * song_list : [{"content":"","copy_type":"1","toneid":"0","info":"","all_rate":"24,64,128","resource_type":2,"relate_status":0,"has_mv_mobile":0,"versions":"","song_id":"34581956","title":"魔法城堡","ting_uid":"85850177","author":"朴有天","album_id":"34581946","album_title":"2013湖南卫视元宵晚会","is_first_publish":0,"havehigh":0,"charge":0,"has_mv":1,"learn":0,"song_source":"web","piao_id":"0","korean_bb_song":"0","resource_type_ext":"0","mv_provider":"1000000000","artist_id":"921","all_artist_id":"921","lrclink":"http://musicdata.baidu.com/data2/lrc/106248810/106248810.lrc","data_source":0,"cluster_id":0,"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}"},{"content":"","copy_type":"1","toneid":"0","info":"","all_rate":"24,64,128,192","resource_type":0,"relate_status":1,"has_mv_mobile":0,"versions":"","song_id":"73903916","title":"【魔法城堡】（九夜灯）","ting_uid":"110948348","author":"九夜灯","album_id":"0","album_title":"","is_first_publish":0,"havehigh":0,"charge":0,"has_mv":0,"learn":0,"song_source":"yyr","piao_id":"0","korean_bb_song":"0","resource_type_ext":"0","mv_provider":"0000000000","artist_id":"73342908","all_artist_id":"73342908","lrclink":"http://musicdata.baidu.com/data2/lrc/126398902/126398902.lrc","data_source":0,"cluster_id":0,"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}"},{"content":"就是可以去<em>魔法城堡<\/em>探险\n翻过寒冷的雪山\n越过沙漠和草原\nBecause I am a brave man\n我不怕路途遥远\n我不怕再多困难\n要让大家知道我有多勇敢\n梦很甜\n梦里我长出翅膀 飞上了蓝天\n梦很咸\n","copy_type":"1","toneid":"0","info":"","all_rate":"","resource_type":0,"relate_status":3,"has_mv_mobile":0,"versions":"","song_id":"245851994","title":"梦","ting_uid":"","author":"王沛霖","album_id":"0","album_title":"","is_first_publish":0,"havehigh":2,"charge":0,"has_mv":0,"learn":0,"song_source":"web","piao_id":"0","korean_bb_song":"0","resource_type_ext":"0","mv_provider":"0000000000","artist_id":"243467560","all_artist_id":"243467560","lrclink":"http://musicdata.baidu.com/data2/lrc/245881040/245881040.lrc","data_source":0,"cluster_id":0,"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}"}]
         */

        private SongInfoBean song_info;

        public String getQuery() {
            return query;
        }

        public void setQuery(String query) {
            this.query = query;
        }

        public String getSyn_words() {
            return syn_words;
        }

        public void setSyn_words(String syn_words) {
            this.syn_words = syn_words;
        }

        public int getRqt_type() {
            return rqt_type;
        }

        public void setRqt_type(int rqt_type) {
            this.rqt_type = rqt_type;
        }

        public SongInfoBean getSong_info() {
            return song_info;
        }

        public void setSong_info(SongInfoBean song_info) {
            this.song_info = song_info;
        }

        public static class SongInfoBean {
            private int total;
            /**
             * content :
             * copy_type : 1
             * toneid : 0
             * info :
             * all_rate : 24,64,128
             * resource_type : 2
             * relate_status : 0
             * has_mv_mobile : 0
             * versions :
             * song_id : 34581956
             * title : 魔法城堡
             * ting_uid : 85850177
             * author : 朴有天
             * album_id : 34581946
             * album_title : 2013湖南卫视元宵晚会
             * is_first_publish : 0
             * havehigh : 0
             * charge : 0
             * has_mv : 1
             * learn : 0
             * song_source : web
             * piao_id : 0
             * korean_bb_song : 0
             * resource_type_ext : 0
             * mv_provider : 1000000000
             * artist_id : 921
             * all_artist_id : 921
             * lrclink : http://musicdata.baidu.com/data2/lrc/106248810/106248810.lrc
             * data_source : 0
             * cluster_id : 0
             * bitrate_fee : {"0":"0|0","1":"0|0"}
             */

            private List<SongListBean> song_list;

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public List<SongListBean> getSong_list() {
                return song_list;
            }

            public void setSong_list(List<SongListBean> song_list) {
                this.song_list = song_list;
            }

            public static class SongListBean {
                private String content;
                private String copy_type;
                private String toneid;
                private String info;
                private String all_rate;
                private int resource_type;
                private int relate_status;
                private int has_mv_mobile;
                private String versions;
                private String song_id;
                private String title;
                private String ting_uid;
                private String author;
                private String album_id;
                private String album_title;
                private int is_first_publish;
                private int havehigh;
                private int charge;
                private int has_mv;
                private int learn;
                private String song_source;
                private String piao_id;
                private String korean_bb_song;
                private String resource_type_ext;
                private String mv_provider;
                private String artist_id;
                private String all_artist_id;
                private String lrclink;
                private int data_source;
                private int cluster_id;
                private String bitrate_fee;

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public String getCopy_type() {
                    return copy_type;
                }

                public void setCopy_type(String copy_type) {
                    this.copy_type = copy_type;
                }

                public String getToneid() {
                    return toneid;
                }

                public void setToneid(String toneid) {
                    this.toneid = toneid;
                }

                public String getInfo() {
                    return info;
                }

                public void setInfo(String info) {
                    this.info = info;
                }

                public String getAll_rate() {
                    return all_rate;
                }

                public void setAll_rate(String all_rate) {
                    this.all_rate = all_rate;
                }

                public int getResource_type() {
                    return resource_type;
                }

                public void setResource_type(int resource_type) {
                    this.resource_type = resource_type;
                }

                public int getRelate_status() {
                    return relate_status;
                }

                public void setRelate_status(int relate_status) {
                    this.relate_status = relate_status;
                }

                public int getHas_mv_mobile() {
                    return has_mv_mobile;
                }

                public void setHas_mv_mobile(int has_mv_mobile) {
                    this.has_mv_mobile = has_mv_mobile;
                }

                public String getVersions() {
                    return versions;
                }

                public void setVersions(String versions) {
                    this.versions = versions;
                }

                public String getSong_id() {
                    return song_id;
                }

                public void setSong_id(String song_id) {
                    this.song_id = song_id;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getTing_uid() {
                    return ting_uid;
                }

                public void setTing_uid(String ting_uid) {
                    this.ting_uid = ting_uid;
                }

                public String getAuthor() {
                    return author;
                }

                public void setAuthor(String author) {
                    this.author = author;
                }

                public String getAlbum_id() {
                    return album_id;
                }

                public void setAlbum_id(String album_id) {
                    this.album_id = album_id;
                }

                public String getAlbum_title() {
                    return album_title;
                }

                public void setAlbum_title(String album_title) {
                    this.album_title = album_title;
                }

                public int getIs_first_publish() {
                    return is_first_publish;
                }

                public void setIs_first_publish(int is_first_publish) {
                    this.is_first_publish = is_first_publish;
                }

                public int getHavehigh() {
                    return havehigh;
                }

                public void setHavehigh(int havehigh) {
                    this.havehigh = havehigh;
                }

                public int getCharge() {
                    return charge;
                }

                public void setCharge(int charge) {
                    this.charge = charge;
                }

                public int getHas_mv() {
                    return has_mv;
                }

                public void setHas_mv(int has_mv) {
                    this.has_mv = has_mv;
                }

                public int getLearn() {
                    return learn;
                }

                public void setLearn(int learn) {
                    this.learn = learn;
                }

                public String getSong_source() {
                    return song_source;
                }

                public void setSong_source(String song_source) {
                    this.song_source = song_source;
                }

                public String getPiao_id() {
                    return piao_id;
                }

                public void setPiao_id(String piao_id) {
                    this.piao_id = piao_id;
                }

                public String getKorean_bb_song() {
                    return korean_bb_song;
                }

                public void setKorean_bb_song(String korean_bb_song) {
                    this.korean_bb_song = korean_bb_song;
                }

                public String getResource_type_ext() {
                    return resource_type_ext;
                }

                public void setResource_type_ext(String resource_type_ext) {
                    this.resource_type_ext = resource_type_ext;
                }

                public String getMv_provider() {
                    return mv_provider;
                }

                public void setMv_provider(String mv_provider) {
                    this.mv_provider = mv_provider;
                }

                public String getArtist_id() {
                    return artist_id;
                }

                public void setArtist_id(String artist_id) {
                    this.artist_id = artist_id;
                }

                public String getAll_artist_id() {
                    return all_artist_id;
                }

                public void setAll_artist_id(String all_artist_id) {
                    this.all_artist_id = all_artist_id;
                }

                public String getLrclink() {
                    return lrclink;
                }

                public void setLrclink(String lrclink) {
                    this.lrclink = lrclink;
                }

                public int getData_source() {
                    return data_source;
                }

                public void setData_source(int data_source) {
                    this.data_source = data_source;
                }

                public int getCluster_id() {
                    return cluster_id;
                }

                public void setCluster_id(int cluster_id) {
                    this.cluster_id = cluster_id;
                }

                public String getBitrate_fee() {
                    return bitrate_fee;
                }

                public void setBitrate_fee(String bitrate_fee) {
                    this.bitrate_fee = bitrate_fee;
                }
            }
        }
    }
}
