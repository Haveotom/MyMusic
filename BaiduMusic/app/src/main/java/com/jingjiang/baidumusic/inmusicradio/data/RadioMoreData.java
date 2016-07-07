package com.jingjiang.baidumusic.inmusicradio.data;

import java.util.List;

/**
 * Created by dllo on 16/7/6.
 */
public class RadioMoreData {

    /**
     * error_code : 22000
     * error_message : ok
     * result : [{"scene_id":"42","scene_name":"国语","icon_ios":"http://c.hiphotos.baidu.com/ting/pic/item/b2de9c82d158ccbfe74098ca1fd8bc3eb0354145.jpg","icon_android":"http://a.hiphotos.baidu.com/ting/pic/item/d439b6003af33a87423e9e19c15c10385343b566.jpg","bgpic_ios":"","bgpic_android":"","scene_model":"1","scene_desc":""},{"scene_id":"43","scene_name":"粤语","icon_ios":"http://b.hiphotos.baidu.com/ting/pic/item/5243fbf2b2119313f781d24063380cd790238d7b.jpg","icon_android":"http://c.hiphotos.baidu.com/ting/pic/item/b17eca8065380cd726893ceea644ad345982811a.jpg","bgpic_ios":"","bgpic_android":"","scene_model":"1","scene_desc":""},{"scene_id":"44","scene_name":"英语","icon_ios":"http://c.hiphotos.baidu.com/ting/pic/item/fd039245d688d43f3648bc027b1ed21b0ff43b58.jpg","icon_android":"http://b.hiphotos.baidu.com/ting/pic/item/a8773912b31bb051a0622d2b317adab44aede06b.jpg","bgpic_ios":"","bgpic_android":"","scene_model":"1","scene_desc":""},{"scene_id":"45","scene_name":"韩语","icon_ios":"http://b.hiphotos.baidu.com/ting/pic/item/908fa0ec08fa513d34ec402e3b6d55fbb3fbd9ff.jpg","icon_android":"http://a.hiphotos.baidu.com/ting/pic/item/83025aafa40f4bfbbb080057044f78f0f63618dc.jpg","bgpic_ios":"","bgpic_android":"","scene_model":"1","scene_desc":""},{"scene_id":"46","scene_name":"日语","icon_ios":"http://b.hiphotos.baidu.com/ting/pic/item/14ce36d3d539b60006d12347ef50352ac65cb726.jpg","icon_android":"http://a.hiphotos.baidu.com/ting/pic/item/8c1001e93901213f73e5d0df53e736d12e2e95db.jpg","bgpic_ios":"","bgpic_android":"","scene_model":"1","scene_desc":""}]
     */

    private int error_code;
    private String error_message;
    /**
     * scene_id : 42
     * scene_name : 国语
     * icon_ios : http://c.hiphotos.baidu.com/ting/pic/item/b2de9c82d158ccbfe74098ca1fd8bc3eb0354145.jpg
     * icon_android : http://a.hiphotos.baidu.com/ting/pic/item/d439b6003af33a87423e9e19c15c10385343b566.jpg
     * bgpic_ios :
     * bgpic_android :
     * scene_model : 1
     * scene_desc :
     */

    private List<ResultBean> result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        private String scene_id;
        private String scene_name;
        private String icon_ios;
        private String icon_android;
        private String bgpic_ios;
        private String bgpic_android;
        private String scene_model;
        private String scene_desc;

        public String getScene_id() {
            return scene_id;
        }

        public void setScene_id(String scene_id) {
            this.scene_id = scene_id;
        }

        public String getScene_name() {
            return scene_name;
        }

        public void setScene_name(String scene_name) {
            this.scene_name = scene_name;
        }

        public String getIcon_ios() {
            return icon_ios;
        }

        public void setIcon_ios(String icon_ios) {
            this.icon_ios = icon_ios;
        }

        public String getIcon_android() {
            return icon_android;
        }

        public void setIcon_android(String icon_android) {
            this.icon_android = icon_android;
        }

        public String getBgpic_ios() {
            return bgpic_ios;
        }

        public void setBgpic_ios(String bgpic_ios) {
            this.bgpic_ios = bgpic_ios;
        }

        public String getBgpic_android() {
            return bgpic_android;
        }

        public void setBgpic_android(String bgpic_android) {
            this.bgpic_android = bgpic_android;
        }

        public String getScene_model() {
            return scene_model;
        }

        public void setScene_model(String scene_model) {
            this.scene_model = scene_model;
        }

        public String getScene_desc() {
            return scene_desc;
        }

        public void setScene_desc(String scene_desc) {
            this.scene_desc = scene_desc;
        }
    }
}
