package com.jingjiang.baidumusic.individual.bean;

import java.util.List;

/**
 * Created by dllo on 16/6/29.
 */
public class UseData {


    /**
     * error_code : 22000
     * recommend : [{"pic":"http://c.hiphotos.baidu.com/ting/pic/item/2fdda3cc7cd98d1070a13b59263fb80e7bec9064.jpg","title":"应用宝","desc":"腾讯应用宝，最丰富、优质、安全、个性化的安卓软件游戏下载~","link":"http://a.app.qq.com/o/myapp-down?g_f=1000659","version":"6.1"},{"pic":"http://c.hiphotos.baidu.com/ting/pic/item/fc1f4134970a304ef4c54ba2d6c8a786c8175c87.jpg","title":"都市言情","desc":"热门男女生必看小说！","link":"http://cache.3g.cn/software/softfile/201603/253520/dushiyanqing-APPbdyydsyq(2)_1603141421.apk","version":"8.0"},{"pic":"http://c.hiphotos.baidu.com/ting/pic/item/b3fb43166d224f4a7387994b0ef790529822d148.jpg","title":"搜狐违章查询","desc":"查违章又快又准，还能代缴罚款！","link":"http://2e0e93529700b.cdn.sohucs.com/aves-apps/driverhelper-android-baidumusic-4.3.2.1456800863155.apk","version":"4.3.2"},{"pic":"http://c.hiphotos.baidu.com/ting/pic/item/f603918fa0ec08fa92d441cd5fee3d6d54fbda80.jpg","title":"亲妈粉","desc":"母婴界的什么值得买","link":"http://dl.gouwu.sogou.com/app/babyapp_android_4012.apk","version":"1.1.0"},{"pic":"http://a.hiphotos.baidu.com/ting/pic/item/4034970a304e251f92157abda286c9177e3e5390.jpg","title":"百度安全卫士","desc":"手机更快更安全！","link":"http://dxurl.cn/bd/yhds/baidumusic","version":"6.1.0"},{"pic":"http://b.hiphotos.baidu.com/ting/pic/item/eac4b74543a982267c1c56dd8e82b9014b90eb6f.jpg","title":"百度乐播","desc":"DJ电台、有声小说、相声、笑话段子各种有声节目应有尽有，完全免费，百度出品，必属精品。","link":"http://music.baidu.com/cms/mobile/static/lb/Baidu_Lebo_momusic.apk","version":"2.2.0"},{"pic":"http://a.hiphotos.baidu.com/ting/pic/item/d31b0ef41bd5ad6e9523dc8880cb39dbb7fd3cfd.jpg","title":"手机助手","desc":"百度手机助手，集万千Android软件汇聚，一触即得","link":"http://ting.baidu.com/mobile/appsearch_AndroidPhone_1-0-16-36_1001777a.apk","version":"4.0"},{"pic":"http://d.hiphotos.baidu.com/ting/pic/item/c8ea15ce36d3d539514b48b03b87e950352ab04b.jpg","title":"百度随心听","desc":"不用为听什么再发愁，百度随心听，会让百万首好音乐主动来找你！","link":"http://music.baidu.com/cms/mobile/static/apk/baidufm_Android_native.apk","version":"1.0"},{"pic":"http://b.hiphotos.baidu.com/ting/pic/item/eaf81a4c510fd9f90caf121a242dd42a2834a40d.jpg","title":"百度","desc":"把百度装进手机","link":"http://shouji.baidu.com/download/757p/Baidu_Android_4-0-0-0_757p.apk?from=757p&ref=plat","version":"4.0"}]
     */

    private int error_code;
    /**
     * pic : http://c.hiphotos.baidu.com/ting/pic/item/2fdda3cc7cd98d1070a13b59263fb80e7bec9064.jpg
     * title : 应用宝
     * desc : 腾讯应用宝，最丰富、优质、安全、个性化的安卓软件游戏下载~
     * link : http://a.app.qq.com/o/myapp-down?g_f=1000659
     * version : 6.1
     */

    private List<RecommendBean> recommend;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<RecommendBean> getRecommend() {
        return recommend;
    }

    public void setRecommend(List<RecommendBean> recommend) {
        this.recommend = recommend;
    }

    public static class RecommendBean {
        private String pic;
        private String title;
        private String desc;
        private String link;
        private String version;

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }
}
