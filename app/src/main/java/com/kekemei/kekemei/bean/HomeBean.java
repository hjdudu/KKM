package com.kekemei.kekemei.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomeBean {

    private CommentdataBean commentdata;
    private List<BeauticianBean> beautician;
    private List<ShopBean> shop;
    private List<BannerBean> banneradv;
    private List<BaseBean> hotdata;
    private List<BaseBean> specialdata;
    private List<BaseBean> newmemberdata;
    private List<BaseBean> memberdata;
    private List<IndexCoupon> indexcoupon;

    public List<BaseBean> getHotdata() {
        return hotdata;
    }

    public void setHotdata(List<BaseBean> hotdata) {
        this.hotdata = hotdata;
    }

    public List<BaseBean> getSpecialdata() {
        return specialdata;
    }

    public void setSpecialdata(List<BaseBean> specialdata) {
        this.specialdata = specialdata;
    }

    public List<BaseBean> getNewmemberdata() {
        return newmemberdata;
    }

    public void setNewmemberdata(List<BaseBean> newmemberdata) {
        this.newmemberdata = newmemberdata;
    }

    public List<BaseBean> getMemberdata() {
        return memberdata;
    }

    public void setMemberdata(List<BaseBean> memberdata) {
        this.memberdata = memberdata;
    }

    public CommentdataBean getCommentdata() {
        return commentdata;
    }

    public void setCommentdata(CommentdataBean commentdata) {
        this.commentdata = commentdata;
    }

    public List<BeauticianBean> getBeautician() {
        return beautician;
    }

    public void setBeautician(List<BeauticianBean> beautician) {
        this.beautician = beautician;
    }

    public List<ShopBean> getShop() {
        return shop;
    }

    public void setShop(List<ShopBean> shop) {
        this.shop = shop;
    }

    public List<BannerBean> getBanneradv() {
        return banneradv;
    }

    public void setBanneradv(List<BannerBean> banneradv) {
        this.banneradv = banneradv;
    }

    public List<IndexCoupon> getIndexcoupon() {
        return indexcoupon;
    }

    public void setIndexcoupon(List<IndexCoupon> indexcoupon) {
        this.indexcoupon = indexcoupon;
    }

    public static class CommentdataBean {
        /**
         * tags : [{"id":3,"name":"美容师专业","state":"1","weigh":0,"tag_type":"1","state_text":"正常","tag_type_text":"Tag_type 1"},{"id":5,"name":"干净卫生","state":"1","weigh":0,"tag_type":"2","state_text":"正常","tag_type_text":"Tag_type 2"}]
         * count : 1
         * all : [{"id":1,"user_id":1,"project_project_id":3,"shop_shop_id":1,"beautician_beautician_id":1,"comment_tag_ids":"5,3","order_id":0,"content":"很好","images":"","coment_type":"1","start":"3","state":"1","createtime":1538469581,"satisfaction":"2","switch":1,"nickname":"admin","user_avatar":"/assets/img/avatar.png","reply":null,"coment_type_text":"Coment_type 1","start_text":"Start 3","state_text":"正常","satisfaction_text":"Satisfaction 2"}]
         * new : [{"id":1,"user_id":1,"project_project_id":3,"shop_shop_id":1,"beautician_beautician_id":1,"comment_tag_ids":"5,3","order_id":0,"content":"很好","images":"","coment_type":"1","start":"3","state":"1","createtime":1538469581,"satisfaction":"2","switch":1,"nickname":"admin","user_avatar":"/assets/img/avatar.png","reply":null,"coment_type_text":"Coment_type 1","start_text":"Start 3","state_text":"正常","satisfaction_text":"Satisfaction 2"}]
         * haveimg : []
         */

        private int count;
        private List<CommentTagsBean> tags;
        private List<EvaluateBean> all;
        @SerializedName("new")
        private List<EvaluateBean> newX;
        private List<EvaluateBean> haveimg;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<CommentTagsBean> getTags() {
            return tags;
        }

        public void setTags(List<CommentTagsBean> tags) {
            this.tags = tags;
        }

        public List<EvaluateBean> getAll() {
            return all;
        }

        public void setAll(List<EvaluateBean> all) {
            this.all = all;
        }

        public List<EvaluateBean> getNewX() {
            return newX;
        }

        public void setNewX(List<EvaluateBean> newX) {
            this.newX = newX;
        }

        public List<EvaluateBean> getHaveimg() {
            return haveimg;
        }

        public void setHaveimg(List<EvaluateBean> haveimg) {
            this.haveimg = haveimg;
        }
    }

    public static class IndexCoupon {

        /**
         * activitytime : 2018-11-01 11:49:37
         * activitytime_end : 2019-09-28 11:49:37
         * createtime : 1541044307
         * id : 3
         * image : /uploads/20181022/63b5dc1f595937b86f1a7ad7e21185fa.jpg
         * name : 满1099减100
         * price_reduction : 100
         * price_satisfy : 1099
         * state : 1
         * state_text : State 1
         * weigh : 3
         */

        private String activitytime;
        private String activitytime_end;
        private int createtime;
        private int id;
        private String image;
        private String name;
        private int price_reduction;
        private long price_satisfy;
        private String state;
        private String state_text;
        private int weigh;

        public String getActivitytime() {
            return activitytime;
        }

        public void setActivitytime(String activitytime) {
            this.activitytime = activitytime;
        }

        public String getActivitytime_end() {
            return activitytime_end;
        }

        public void setActivitytime_end(String activitytime_end) {
            this.activitytime_end = activitytime_end;
        }

        public int getCreatetime() {
            return createtime;
        }

        public void setCreatetime(int createtime) {
            this.createtime = createtime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPrice_reduction() {
            return price_reduction;
        }

        public void setPrice_reduction(int price_reduction) {
            this.price_reduction = price_reduction;
        }

        public long getPrice_satisfy() {
            return price_satisfy;
        }

        public void setPrice_satisfy(long price_satisfy) {
            this.price_satisfy = price_satisfy;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getState_text() {
            return state_text;
        }

        public void setState_text(String state_text) {
            this.state_text = state_text;
        }

        public int getWeigh() {
            return weigh;
        }

        public void setWeigh(int weigh) {
            this.weigh = weigh;
        }
    }
}
