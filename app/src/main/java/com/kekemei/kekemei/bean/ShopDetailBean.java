package com.kekemei.kekemei.bean;

import com.kekemei.kekemei.utils.StringUtils;

import java.io.Serializable;
import java.util.List;

public class ShopDetailBean implements Serializable {
    private String id;
    private String name;
    private String image;
    private String images;
    private String city;
    private int redenvelopes_ids;
    private int coupon_ids;
    private String address;
    private String tel;
    private String longitude;
    private String latitude;
    private String content;
    private String state;
    private int createtime;
    private String shop_service_ids;
    private int user_id;
    private int order_count;
    private int collection_count;
    private int iscollection;
    private String satisfaction;
    private String peer;
    private String distance;
    private float start;
    private String state_text;
    private WaiterBean waiter;
    private CommentdataBean commentData;
    private List<RedenvloesDataBean> redenvloesdata;
    private List<String> service;
    private List<CouponBean> coupon;
    private List<RedenvelopesBean> redenvelopes;
    private List<FullBean> full;
    private List<BeauticianBean> beautician;
    private List<String> strading;
    private List<BaseBean> hotdata;
    private List<BaseBean> specialdata;
    private List<BaseBean> newmemberdata;
    private List<BaseBean> memberdata;

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public int getCoupon_ids() {
        return coupon_ids;
    }

    public void setCoupon_ids(int coupon_ids) {
        this.coupon_ids = coupon_ids;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getShop_service_ids() {
        return shop_service_ids;
    }

    public void setShop_service_ids(String shop_service_ids) {
        this.shop_service_ids = shop_service_ids;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getOrder_count() {
        return order_count;
    }

    public void setOrder_count(int order_count) {
        this.order_count = order_count;
    }

    public int getCollection_count() {
        return collection_count;
    }

    public void setCollection_count(int collection_count) {
        this.collection_count = collection_count;
    }

    public int getIscollection() {
        return iscollection;
    }

    public void setIscollection(int iscollection) {
        this.iscollection = iscollection;
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

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public float getStart() {
        return start;
    }

    public void setStart(float start) {
        this.start = start;
    }

    public WaiterBean getWaiter() {
        return waiter;
    }

    public void setWaiter(WaiterBean waiter) {
        this.waiter = waiter;
    }

    public CommentdataBean getCommentdata() {
        return commentData;
    }

    public List<RedenvloesDataBean> getRedenvloesdata() {
        return redenvloesdata;
    }

    public void setRedenvloesdata(List<RedenvloesDataBean> redenvloesdata) {
        this.redenvloesdata = redenvloesdata;
    }

    public void setCommentdata(CommentdataBean commentdata) {
        this.commentData = commentdata;
    }

    public String getState_text() {
        return state_text;
    }

    public void setState_text(String state_text) {
        this.state_text = state_text;
    }

    public List<String> getService() {
        return service;
    }

    public void setService(List<String> service) {
        this.service = service;
    }

    public List<CouponBean> getCoupon() {
        return coupon;
    }

    public void setCoupon(List<CouponBean> coupon) {
        this.coupon = coupon;
    }

    public List<RedenvelopesBean> getRedenvelopes() {
        return redenvelopes;
    }

    public void setRedenvelopes(List<RedenvelopesBean> redenvelopes) {
        this.redenvelopes = redenvelopes;
    }

    public List<BeauticianBean> getBeautician() {
        return beautician;
    }

    public void setBeautician(List<BeauticianBean> beautician) {
        this.beautician = beautician;
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

    public static class RedenvloesDataBean implements Serializable {

        /**
         * id : 1
         * name : 抢50元
         * price : 0
         * type : 1
         */

        private int id;
        private String name;
        private int price;
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

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }

    public static class CouponBean implements Serializable {
        /**
         * id : 1
         * name : 满599减50
         * image : /uploads/20181001/8cf03f354750e3e38664485e647c4af4.jpeg
         * price_satisfy : 599
         * price_reduction : 50
         * activitytime : 2018-10-07 12:53:59
         * activitytime_end : 2019-07-27 12:53:59
         * createtime : 1538888184
         * weigh : 1
         * state : 1
         * statu : 1
         * state_text : 正常
         */

        private int id;
        private String name;
        private String image;
        private int price_satisfy;
        private int price_reduction;
        private String activitytime;
        private String activitytime_end;
        private int createtime;
        private int weigh;
        private String state;
        private int statu;
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

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
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

        public int getWeigh() {
            return weigh;
        }

        public void setWeigh(int weigh) {
            this.weigh = weigh;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public int getStatu() {
            return statu;
        }

        public void setStatu(int statu) {
            this.statu = statu;
        }

        public String getState_text() {
            return state_text;
        }

        public void setState_text(String state_text) {
            this.state_text = state_text;
        }
    }

    public static class RedenvelopesBean implements Serializable {
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
}
