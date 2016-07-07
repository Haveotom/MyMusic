package com.jingjiang.baidumusic.inmusicrecommend.data;

import java.util.List;

/**
 * Created by dllo on 16/7/5.
 */
public class HappyMoreData {

    /**
     * error_code : 22000
     * result : {"total":8,"taglist":[{"tagid":"11373552","tag_name":"音乐故事","tag_pic":"http://hiphotos.baidu.com/ting/pic/item/a8ec8a13632762d0097eb683a7ec08fa513dc64e.jpg"},{"tagid":"11373553","tag_name":"都市情感","tag_pic":"http://a.hiphotos.baidu.com/ting/pic/item/b3b7d0a20cf431ad234afee04936acaf2fdd984b.jpg"},{"tagid":"11373548","tag_name":"脱口秀","tag_pic":"http://c.hiphotos.baidu.com/ting/pic/item/0df431adcbef7609a1c524462cdda3cc7dd99e19.jpg"},{"tagid":"11373547","tag_name":"笑话段子","tag_pic":"http://g.hiphotos.baidu.com/ting/pic/item/a8014c086e061d95c43152e279f40ad163d9caf1.jpg"},{"tagid":"11373550","tag_name":"综艺娱乐","tag_pic":"http://d.hiphotos.baidu.com/ting/pic/item/30adcbef76094b364759a634a1cc7cd98c109d24.jpg"},{"tagid":"11373557","tag_name":"母婴儿童","tag_pic":"http://h.hiphotos.baidu.com/ting/pic/item/adaf2edda3cc7cd9629289003b01213fb90e91a8.jpg"},{"tagid":"11373554","tag_name":"相声曲艺","tag_pic":"http://b.hiphotos.baidu.com/ting/pic/item/0df3d7ca7bcb0a464beb36e06963f6246a60af1a.jpg"},{"tagid":"11373555","tag_name":"教育","tag_pic":"http://hiphotos.baidu.com/ting/pic/item/b03533fa828ba61e2b08da134634970a304e5923.jpg"}]}
     */

    private int error_code;
    /**
     * total : 8
     * taglist : [{"tagid":"11373552","tag_name":"音乐故事","tag_pic":"http://hiphotos.baidu.com/ting/pic/item/a8ec8a13632762d0097eb683a7ec08fa513dc64e.jpg"},{"tagid":"11373553","tag_name":"都市情感","tag_pic":"http://a.hiphotos.baidu.com/ting/pic/item/b3b7d0a20cf431ad234afee04936acaf2fdd984b.jpg"},{"tagid":"11373548","tag_name":"脱口秀","tag_pic":"http://c.hiphotos.baidu.com/ting/pic/item/0df431adcbef7609a1c524462cdda3cc7dd99e19.jpg"},{"tagid":"11373547","tag_name":"笑话段子","tag_pic":"http://g.hiphotos.baidu.com/ting/pic/item/a8014c086e061d95c43152e279f40ad163d9caf1.jpg"},{"tagid":"11373550","tag_name":"综艺娱乐","tag_pic":"http://d.hiphotos.baidu.com/ting/pic/item/30adcbef76094b364759a634a1cc7cd98c109d24.jpg"},{"tagid":"11373557","tag_name":"母婴儿童","tag_pic":"http://h.hiphotos.baidu.com/ting/pic/item/adaf2edda3cc7cd9629289003b01213fb90e91a8.jpg"},{"tagid":"11373554","tag_name":"相声曲艺","tag_pic":"http://b.hiphotos.baidu.com/ting/pic/item/0df3d7ca7bcb0a464beb36e06963f6246a60af1a.jpg"},{"tagid":"11373555","tag_name":"教育","tag_pic":"http://hiphotos.baidu.com/ting/pic/item/b03533fa828ba61e2b08da134634970a304e5923.jpg"}]
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
        private int total;
        /**
         * tagid : 11373552
         * tag_name : 音乐故事
         * tag_pic : http://hiphotos.baidu.com/ting/pic/item/a8ec8a13632762d0097eb683a7ec08fa513dc64e.jpg
         */

        private List<TaglistBean> taglist;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<TaglistBean> getTaglist() {
            return taglist;
        }

        public void setTaglist(List<TaglistBean> taglist) {
            this.taglist = taglist;
        }

        public static class TaglistBean {
            private String tagid;
            private String tag_name;
            private String tag_pic;

            public String getTagid() {
                return tagid;
            }

            public void setTagid(String tagid) {
                this.tagid = tagid;
            }

            public String getTag_name() {
                return tag_name;
            }

            public void setTag_name(String tag_name) {
                this.tag_name = tag_name;
            }

            public String getTag_pic() {
                return tag_pic;
            }

            public void setTag_pic(String tag_pic) {
                this.tag_pic = tag_pic;
            }
        }
    }
}
