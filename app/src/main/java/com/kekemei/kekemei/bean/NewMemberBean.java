package com.kekemei.kekemei.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewMemberBean {

    /**
     * ismember : 0
     * banner : {"id":10,"title":"新人会员卡","adv_pos_id":10359,"image":"/uploads/20181107/8c83b8fd48c4dd6f2acf048da3fc9ee5.png","jumbdata":"web","project_project_id":0,"shop_shop_id":0,"becautician_becautician_id":0,"content":"","url":"","views":0,"starttime":"2018-11-07","endtime":"2018-11-07","createtime":1541558147,"weigh":10,"switch":1,"jumbdata_text":"Jumbdata web"}
     * member : [{"id":4,"name":"粉红丝带","project_category_id":1,"image":"/uploads/20181022/0847d20fcb22b348e29a61e333a363b4.jpg","images":"/uploads/20181022/b532838d95d86275310107d512861b6c.jpg,/uploads/20181022/5e50668033beb8d9d671da58b598fa79.jpg,/uploads/20181022/b532838d95d86275310107d512861b6c.jpg,/uploads/20181022/b532838d95d86275310107d512861b6c.jpg","price_market":0,"price_discount":100,"treatment_price":"44.00","price_newmember":33,"price_member":0,"price_second":0,"treatment_count":"3","quality":"","content":"55","coupon_ids":"1","redenvelopes_ids":"1","state":"1","weigh":4,"views":0,"createtime":1538487574,"redenvelopes":["111111"],"coupon":["满599减50"],"quality_text":"","state_text":"正常"}]
     * projectall : [{"id":1,"name":"蒙贝面部紧致提升","project_category_id":3,"image":"/uploads/20181022/5e50668033beb8d9d671da58b598fa79.jpg","images":"/uploads/20181022/979c9ab660275292aefc5c6b7632b2a8.jpg,/uploads/20181022/b532838d95d86275310107d512861b6c.jpg","price_market":0,"price_discount":100,"treatment_price":"0.00","price_newmember":0,"price_member":0,"price_second":0,"treatment_count":"0","quality":"","content":"测试测试测试测试测试","coupon_ids":"1","redenvelopes_ids":"1","state":"2","weigh":100,"views":0,"createtime":1538449687,"redenvelopes":["111111"],"coupon":["满599减50"],"quality_text":"","state_text":"推荐"},{"id":2,"name":"东方芒果台芭抗衰修复","project_category_id":1,"image":"/uploads/20181022/b532838d95d86275310107d512861b6c.jpg","images":"/uploads/20181022/eba3d6f9ffc2bd7f7791f407bccec354.jpg","price_market":0,"price_discount":100,"treatment_price":"0.00","price_newmember":0,"price_member":0,"price_second":0,"treatment_count":"0","quality":"","content":"55555","coupon_ids":"1","redenvelopes_ids":"1","state":"1","weigh":100,"views":0,"createtime":1538450467,"redenvelopes":["111111"],"coupon":["满599减50"],"quality_text":"","state_text":"正常"},{"id":3,"name":"金因姜时光肌密面膜","project_category_id":1,"image":"/uploads/20181022/eba3d6f9ffc2bd7f7791f407bccec354.jpg","images":"/uploads/20181022/979c9ab660275292aefc5c6b7632b2a8.jpg,/uploads/20181022/5e50668033beb8d9d671da58b598fa79.jpg,/uploads/20181022/b532838d95d86275310107d512861b6c.jpg","price_market":666,"price_discount":66,"treatment_price":"55.00","price_newmember":0,"price_member":555,"price_second":0,"treatment_count":"0","quality":"","content":"1111","coupon_ids":"1","redenvelopes_ids":"1","state":"1","weigh":100,"views":0,"createtime":1538452488,"redenvelopes":["111111"],"coupon":["满599减50"],"quality_text":"","state_text":"正常"},{"id":4,"name":"粉红丝带","project_category_id":1,"image":"/uploads/20181022/0847d20fcb22b348e29a61e333a363b4.jpg","images":"/uploads/20181022/b532838d95d86275310107d512861b6c.jpg,/uploads/20181022/5e50668033beb8d9d671da58b598fa79.jpg,/uploads/20181022/b532838d95d86275310107d512861b6c.jpg,/uploads/20181022/b532838d95d86275310107d512861b6c.jpg","price_market":0,"price_discount":100,"treatment_price":"44.00","price_newmember":33,"price_member":0,"price_second":0,"treatment_count":"3","quality":"","content":"55","coupon_ids":"1","redenvelopes_ids":"1","state":"1","weigh":4,"views":0,"createtime":1538487574,"redenvelopes":["111111"],"coupon":["满599减50"],"quality_text":"","state_text":"正常"}]
     * foryou : [{"id":1,"name":"蒙贝面部紧致提升","project_category_id":3,"image":"/uploads/20181022/5e50668033beb8d9d671da58b598fa79.jpg","images":"/uploads/20181022/979c9ab660275292aefc5c6b7632b2a8.jpg,/uploads/20181022/b532838d95d86275310107d512861b6c.jpg","price_market":0,"price_discount":100,"treatment_price":"0.00","price_newmember":0,"price_member":0,"price_second":0,"treatment_count":"0","quality":"","content":"测试测试测试测试测试","coupon_ids":"1","redenvelopes_ids":"1","state":"2","weigh":100,"views":0,"createtime":1538449687,"redenvelopes":["111111"],"coupon":["满599减50"],"quality_text":"","state_text":"推荐"}]
     */

    private int ismember;
    private BannerBean banner;
    private List<BaseBean> member;
    private List<BaseBean> projectall;
    private List<BaseBean> foryou;

    public int getIsmember() {
        return ismember;
    }

    public void setIsmember(int ismember) {
        this.ismember = ismember;
    }

    public BannerBean getBanner() {
        return banner;
    }

    public void setBanner(BannerBean banner) {
        this.banner = banner;
    }

    public List<BaseBean> getMember() {
        return member;
    }

    public void setMember(List<BaseBean> member) {
        this.member = member;
    }

    public List<BaseBean> getProjectall() {
        return projectall;
    }

    public void setProjectall(List<BaseBean> projectall) {
        this.projectall = projectall;
    }

    public List<BaseBean> getForyou() {
        return foryou;
    }

    public void setForyou(List<BaseBean> foryou) {
        this.foryou = foryou;
    }

    public static class BannerBean {
        /**
         * id : 10
         * title : 新人会员卡
         * adv_pos_id : 10359
         * image : /uploads/20181107/8c83b8fd48c4dd6f2acf048da3fc9ee5.png
         * jumbdata : web
         * project_project_id : 0
         * shop_shop_id : 0
         * becautician_becautician_id : 0
         * content :
         * url :
         * views : 0
         * starttime : 2018-11-07
         * endtime : 2018-11-07
         * createtime : 1541558147
         * weigh : 10
         * switch : 1
         * jumbdata_text : Jumbdata web
         */

        private int id;
        private String title;
        private int adv_pos_id;
        private String image;
        private String jumbdata;
        private int project_project_id;
        private int shop_shop_id;
        private int becautician_becautician_id;
        private String content;
        private String url;
        private int views;
        private String starttime;
        private String endtime;
        private int createtime;
        private int weigh;
        @SerializedName("switch")
        private int switchX;
        private String jumbdata_text;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getAdv_pos_id() {
            return adv_pos_id;
        }

        public void setAdv_pos_id(int adv_pos_id) {
            this.adv_pos_id = adv_pos_id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getJumbdata() {
            return jumbdata;
        }

        public void setJumbdata(String jumbdata) {
            this.jumbdata = jumbdata;
        }

        public int getProject_project_id() {
            return project_project_id;
        }

        public void setProject_project_id(int project_project_id) {
            this.project_project_id = project_project_id;
        }

        public int getShop_shop_id() {
            return shop_shop_id;
        }

        public void setShop_shop_id(int shop_shop_id) {
            this.shop_shop_id = shop_shop_id;
        }

        public int getBecautician_becautician_id() {
            return becautician_becautician_id;
        }

        public void setBecautician_becautician_id(int becautician_becautician_id) {
            this.becautician_becautician_id = becautician_becautician_id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getViews() {
            return views;
        }

        public void setViews(int views) {
            this.views = views;
        }

        public String getStarttime() {
            return starttime;
        }

        public void setStarttime(String starttime) {
            this.starttime = starttime;
        }

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }

        public int getCreatetime() {
            return createtime;
        }

        public void setCreatetime(int createtime) {
            this.createtime = createtime;
        }

        public int getWeigh() {
            return weigh;
        }

        public void setWeigh(int weigh) {
            this.weigh = weigh;
        }

        public int getSwitchX() {
            return switchX;
        }

        public void setSwitchX(int switchX) {
            this.switchX = switchX;
        }

        public String getJumbdata_text() {
            return jumbdata_text;
        }

        public void setJumbdata_text(String jumbdata_text) {
            this.jumbdata_text = jumbdata_text;
        }
    }
}
