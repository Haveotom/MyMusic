package com.jingjiang.baidumusic.bean;

import java.util.List;

/**
 * Created by dllo on 16/6/24.
 */
public class KSongPlayData {


    /**
     * error_code : 22000
     * result : [{"type":"learn","picture":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_30c31a450efa3d5b07b7c48a0e0d7151.jpg","picture_iphone6":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_0bbb14d539a9a95621da7c8f0e71289a.jpg","web_url":"http://music.baidu.com/cms/webview/ktv_activity/20160617/index.html"},{"type":"learn","picture":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_5cb537e9cc86a5a60b63887dfa998c5c.jpg","picture_iphone6":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_7d8e8eca5627bceec17ee3748bcbfd1c.jpg","web_url":"http://music.baidu.com/cms/webview/ktv_activity/20160531/index.html"},{"type":"learn","picture":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_ef0bbbc8dc82843ee62cb33cbec5b44c.jpg","picture_iphone6":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_9f4e9c75b722d4e8c442223e14ffadff.jpg","web_url":"http://music.baidu.com/cms/webview/ktv_activity/20160511/index.html"},{"type":"learn","picture":"http://a.hiphotos.baidu.com/ting/pic/item/d8f9d72a6059252d2e4d7891339b033b5ab5b9c9.jpg","picture_iphone6":"http://b.hiphotos.baidu.com/ting/pic/item/55e736d12f2eb938b7f41f67d2628535e4dd6f8d.jpg","web_url":"http://music.baidu.com/cms/webview/ktv_activity/20160401/index.html"}]
     */

    private int error_code;
    /**
     * type : learn
     * picture : http://business.cdn.qianqian.com/qianqian/pic/bos_client_30c31a450efa3d5b07b7c48a0e0d7151.jpg
     * picture_iphone6 : http://business.cdn.qianqian.com/qianqian/pic/bos_client_0bbb14d539a9a95621da7c8f0e71289a.jpg
     * web_url : http://music.baidu.com/cms/webview/ktv_activity/20160617/index.html
     */

    private List<ResultBean> result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        private String type;
        private String picture;
        private String picture_iphone6;
        private String web_url;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getPicture_iphone6() {
            return picture_iphone6;
        }

        public void setPicture_iphone6(String picture_iphone6) {
            this.picture_iphone6 = picture_iphone6;
        }

        public String getWeb_url() {
            return web_url;
        }

        public void setWeb_url(String web_url) {
            this.web_url = web_url;
        }
    }
}
