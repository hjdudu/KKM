package com.kekemei.kekemei.bean;

import com.kekemei.kekemei.utils.StringUtils;

import java.io.Serializable;
import java.util.List;

public class BeauticianDetailBean implements Serializable {

    private String id;
    private String name;
    private int user_id;
    private int redenvelopes_ids;
    private int coupon_ids;
    private String image;
    private String images;
    private String shop_shop_ids;
    private String content;
    private String place;
    private String speciality;
    private String identity;
    private String state;
    private String nickname;
    private int isfriend;
    private int friend_count;
    private int order_count;
    private float average_price;
    private List<String> auth;
    private List<CouponBean> coupon;
    private List<FullBean> full;
    private List<RedEnvelopes> redenvelopes;
    private float start;
    private String satisfaction;
    private String peer;
    private String address;
    private String distance;
    private int like_people;
    private String state_text;
    private List<String> shop_text;
    private List<String> strading;
    private List<BaseBean> hotdata;
    private List<BaseBean> specialdata;
    private List<BaseBean> newmemberdata;
    private List<BaseBean> memberdata;
    private List<RecBeauticianBean> rec_beautician;
    private CommentdataBean commentData;
    private List<RedenvloesDataBean> redenvloesdata;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getRedenvelopes_ids() {
        return redenvelopes_ids;
    }

    public void setRedenvelopes_ids(int redenvelopes_ids) {
        this.redenvelopes_ids = redenvelopes_ids;
    }

    public List<FullBean> getFull() {
        return full;
    }

    public void setFull(List<FullBean> full) {
        this.full = full;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public int getCoupon_ids() {
        return coupon_ids;
    }

    public void setCoupon_ids(int coupon_ids) {
        this.coupon_ids = coupon_ids;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getShop_shop_ids() {
        return shop_shop_ids;
    }

    public void setShop_shop_ids(String shop_shop_ids) {
        this.shop_shop_ids = shop_shop_ids;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getIsfriend() {
        return isfriend;
    }

    public void setIsfriend(int isfriend) {
        this.isfriend = isfriend;
    }

    public int getFriend_count() {
        return friend_count;
    }

    public void setFriend_count(int friend_count) {
        this.friend_count = friend_count;
    }

    public int getOrder_count() {
        return order_count;
    }

    public void setOrder_count(int order_count) {
        this.order_count = order_count;
    }

    public float getAverage_price() {
        return average_price;
    }

    public void setAverage_price(float average_price) {
        this.average_price = average_price;
    }

    public List<String> getAuth() {
        return auth;
    }

    public void setAuth(List<String> auth) {
        this.auth = auth;
    }

    public List<CouponBean> getCoupon() {
        return coupon;
    }

    public void setCoupon(List<CouponBean> coupon) {
        this.coupon = coupon;
    }

    public List<RedEnvelopes> getRedenvelopes() {
        return redenvelopes;
    }

    public void setRedenvelopes(List<RedEnvelopes> redenvelopes) {
        this.redenvelopes = redenvelopes;
    }

    public float getStart() {
        return start;
    }

    public void setStart(float start) {
        this.start = start;
    }

    public String getSatisfaction() {
        if (StringUtils.isNotEmpty(satisfaction) && satisfaction.contains(".")) {
            String[] split = satisfaction.split("\\.");
            return split[1];
        }
        return satisfaction;
    }

    public void setSatisfaction(String satisfaction) {
        this.satisfaction = satisfaction;
    }

    public String getPeer() {
        if (StringUtils.isNotEmpty(peer) && peer.contains(".")) {
            String[] split = peer.split("\\.");
            return split[1];
        }
        return peer;
    }

    public void setPeer(String peer) {
        this.peer = peer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getLike_people() {
        return like_people;
    }

    public void setLike_people(int like_people) {
        this.like_people = like_people;
    }

    public String getState_text() {
        return state_text;
    }

    public void setState_text(String state_text) {
        this.state_text = state_text;
    }

    public List<String> getShop_text() {
        return shop_text;
    }

    public void setShop_text(List<String> shop_text) {
        this.shop_text = shop_text;
    }

    public List<String> getStrading() {
        return strading;
    }

    public void setStrading(List<String> strading) {
        this.strading = strading;
    }

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

    public List<RecBeauticianBean> getRec_beautician() {
        return rec_beautician;
    }

    public void setRec_beautician(List<RecBeauticianBean> rec_beautician) {
        this.rec_beautician = rec_beautician;
    }

    public CommentdataBean getCommentdata() {
        return commentData;
    }

    public void setCommentdata(CommentdataBean commentData) {
        this.commentData = commentData;
    }

    public List<RedenvloesDataBean> getRedenvloesdata() {
        return redenvloesdata;
    }

    public void setRedenvloesdata(List<RedenvloesDataBean> redenvloesdata) {
        this.redenvloesdata = redenvloesdata;
    }

    public static class RedenvloesDataBean implements Serializable {

        /**
         * id : 1
         * name : 抢50元
         * price : 0
         * type : 1
         */

        private int id;
        private String name;
        private double price;
        private int type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }

    public static class RedEnvelopes implements Serializable {

        /**
         * count : 9
         * createtime : 1537844659
         * id : 1
         * image : /assets/img/qrcode.png
         * name : 111111
         * price_reduction : 100
         * price_satisfy : 0
         * state : 1
         * state_text : 正常
         * type : 1
         * type_text : Type 1
         */

        private int count;
        private int createtime;
        private int id;
        private String image;
        private String name;
        private int price_reduction;
        private int price_satisfy;
        private String state;
        private String state_text;
        private String type;
        private String type_text;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
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

        public int getPrice_satisfy() {
            return price_satisfy;
        }

        public void setPrice_satisfy(int price_satisfy) {
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getType_text() {
            return type_text;
        }

        public void setType_text(String type_text) {
            this.type_text = type_text;
        }
    }

    public static class FullBean implements Serializable {

        /**
         * activitytime : 2018-10-07 12:53:59
         * activitytime_end : 2019-07-27 12:53:59
         * createtime : 1538888184
         * id : 1
         * image : /uploads/20181001/8cf03f354750e3e38664485e647c4af4.jpeg
         * name : 满599减50
         * price_reduction : 1
         * price_satisfy : 599
         * state : 1
         * state_text : 正常
         * weigh : 1
         */

        private String activitytime;
        private String activitytime_end;
        private int createtime;
        private int id;
        private String image;
        private String name;
        private int price_reduction;
        private int price_satisfy;
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

        public int getPrice_satisfy() {
            return price_satisfy;
        }

        public void setPrice_satisfy(int price_satisfy) {
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

    public static class CouponBean implements Serializable {
        /**
         * id : 1
         * name : 111111
         * type : 1
         * price_satisfy : 0
         * price_reduction : 100
         * image : /assets/img/qrcode.png
         * count : 9
         * state : 1
         * createtime : 1537844659
         * type_text : Type 1
         * state_text : 正常
         */

        private int id;
        private String name;
        private String type;
        private int price_satisfy;
        private int price_reduction;
        private String image;
        private int count;
        private String state;
        private int createtime;
        private String type_text;
        private String state_text;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getPrice_satisfy() {
            return price_satisfy;
        }

        public void setPrice_satisfy(int price_satisfy) {
            this.price_satisfy = price_satisfy;
        }

        public int getPrice_reduction() {
            return price_reduction;
        }

        public void setPrice_reduction(int price_reduction) {
            this.price_reduction = price_reduction;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public int getCreatetime() {
            return createtime;
        }

        public void setCreatetime(int createtime) {
            this.createtime = createtime;
        }

        public String getType_text() {
            return type_text;
        }

        public void setType_text(String type_text) {
            this.type_text = type_text;
        }

        public String getState_text() {
            return state_text;
        }

        public void setState_text(String state_text) {
            this.state_text = state_text;
        }
    }

    public static class RecBeauticianBean implements Serializable {
        /**
         * id : 2
         * name : 专业美容师
         * user_id : 2
         * redenvelopes_ids : 1
         * coupon_ids : 1
         * image : /uploads/20181022/10d83ebbe169c77cbbbdbf1d9111e01d.jpg
         * images : /uploads/20181022/2c5d84349965e653baa9870a5311d15d.jpg,/uploads/20181022/d92905316745b7ce3c0e3d6ddc382c98.jpg,/uploads/20181022/b532838d95d86275310107d512861b6c.jpg
         * shop_shop_ids : 2,1
         * content : fwfw
         * place : 籍贯
         * speciality : 特长
         * identity : 身份证
         * state : 2
         * state_text : 推荐
         */

        private int id;
        private String name;
        private int user_id;
        private int redenvelopes_ids;
        private int coupon_ids;
        private String image;
        private String images;
        private String shop_shop_ids;
        private String content;
        private String place;
        private String speciality;
        private String identity;
        private String state;
        private String state_text;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getRedenvelopes_ids() {
            return redenvelopes_ids;
        }

        public void setRedenvelopes_ids(int redenvelopes_ids) {
            this.redenvelopes_ids = redenvelopes_ids;
        }

        public int getCoupon_ids() {
            return coupon_ids;
        }

        public void setCoupon_ids(int coupon_ids) {
            this.coupon_ids = coupon_ids;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public String getShop_shop_ids() {
            return shop_shop_ids;
        }

        public void setShop_shop_ids(String shop_shop_ids) {
            this.shop_shop_ids = shop_shop_ids;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public String getSpeciality() {
            return speciality;
        }

        public void setSpeciality(String speciality) {
            this.speciality = speciality;
        }

        public String getIdentity() {
            return identity;
        }

        public void setIdentity(String identity) {
            this.identity = identity;
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
    }
}
