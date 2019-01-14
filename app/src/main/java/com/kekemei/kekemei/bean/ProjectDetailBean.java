package com.kekemei.kekemei.bean;

import com.kekemei.kekemei.utils.StringUtils;

import java.io.Serializable;
import java.util.List;

public class ProjectDetailBean implements Serializable {

    /**
     * comment : {"all":[],"count":0,"haveimg":[],"new":[],"tags":[{"id":1,"name":"服务热情","state":"1","state_text":"正常","tag_type":"1","tag_type_text":"Tag_type 1","weigh":0},{"id":2,"name":"环境优雅","state":"1","state_text":"正常","tag_type":"2","tag_type_text":"Tag_type 2","weigh":0},{"id":3,"name":"美容师专业","state":"1","state_text":"正常","tag_type":"1","tag_type_text":"Tag_type 1","weigh":0},{"id":4,"name":"效果赞","state":"1","state_text":"正常","tag_type":"3,","tag_type_text":"","weigh":0},{"id":5,"name":"干净卫生","state":"1","state_text":"正常","tag_type":"2","tag_type_text":"Tag_type 2","weigh":0}]}
     * content : 测试测试测试测试测试
     * coupon : [{"activitytime":"2018-10-07 12:53:59","activitytime_end":"2019-07-27 12:53:59","createtime":1538888184,"id":1,"image":"/uploads/20181001/8cf03f354750e3e38664485e647c4af4.jpeg","name":"满599减50","price_reduction":50,"price_satisfy":599,"state":"1","state_text":"正常","weigh":1}]
     * coupon_ids : 1
     * createtime : 1538449687
     * hotdata : [{"content":"测试测试测试测试测试","coupon":["111111"],"coupon_ids":"1","createtime":1538449687,"id":1,"image":"/uploads/20181022/5e50668033beb8d9d671da58b598fa79.jpg","images":"/uploads/20181022/979c9ab660275292aefc5c6b7632b2a8.jpg,/uploads/20181022/b532838d95d86275310107d512861b6c.jpg","like_count":0,"name":"蒙贝面部紧致提升","price_discount":100,"price_market":0,"price_member":0,"price_newmember":0,"price_second":0,"project_category_id":3,"quality":"","quality_text":"","redenvelopes":["111111"],"redenvelopes_ids":"1","state":"2","state_text":"推荐","treatment_count":"0","treatment_price":"0.00","views":0,"weigh":100},{"content":"55555","coupon":["111111"],"coupon_ids":"1","createtime":1538450467,"id":2,"image":"/uploads/20181022/b532838d95d86275310107d512861b6c.jpg","images":"/uploads/20181022/eba3d6f9ffc2bd7f7791f407bccec354.jpg","like_count":0,"name":"东方芒果台芭抗衰修复","price_discount":100,"price_market":0,"price_member":0,"price_newmember":0,"price_second":0,"project_category_id":1,"quality":"","quality_text":"","redenvelopes":["111111"],"redenvelopes_ids":"1","state":"1","state_text":"正常","treatment_count":"0","treatment_price":"0.00","views":0,"weigh":100},{"content":"1111","coupon":["111111"],"coupon_ids":"1","createtime":1538452488,"id":3,"image":"/uploads/20181022/eba3d6f9ffc2bd7f7791f407bccec354.jpg","images":"/uploads/20181022/979c9ab660275292aefc5c6b7632b2a8.jpg,/uploads/20181022/5e50668033beb8d9d671da58b598fa79.jpg,/uploads/20181022/b532838d95d86275310107d512861b6c.jpg","like_count":1,"name":"金因姜时光肌密面膜","price_discount":66,"price_market":666,"price_member":555,"price_newmember":0,"price_second":0,"project_category_id":1,"quality":"","quality_text":"","redenvelopes":["111111"],"redenvelopes_ids":"1","state":"1","state_text":"正常","treatment_count":"0","treatment_price":"55.00","views":0,"weigh":100},{"content":"55","coupon":["111111"],"coupon_ids":"1","createtime":1538487574,"id":4,"image":"/uploads/20181022/0847d20fcb22b348e29a61e333a363b4.jpg","images":"/uploads/20181022/b532838d95d86275310107d512861b6c.jpg,/uploads/20181022/5e50668033beb8d9d671da58b598fa79.jpg,/uploads/20181022/b532838d95d86275310107d512861b6c.jpg,/uploads/20181022/b532838d95d86275310107d512861b6c.jpg","like_count":0,"name":"粉红丝带","price_discount":100,"price_market":0,"price_member":0,"price_newmember":33,"price_second":0,"project_category_id":1,"quality":"","quality_text":"","redenvelopes":["111111"],"redenvelopes_ids":"1","state":"1","state_text":"正常","treatment_count":"3","treatment_price":"44.00","views":0,"weigh":4}]
     * id : 1
     * image : /uploads/20181022/5e50668033beb8d9d671da58b598fa79.jpg
     * images : /uploads/20181022/979c9ab660275292aefc5c6b7632b2a8.jpg,/uploads/20181022/b532838d95d86275310107d512861b6c.jpg
     * memberdata : [{"content":"1111","coupon_ids":"1","createtime":1538452488,"id":3,"image":"/uploads/20181022/eba3d6f9ffc2bd7f7791f407bccec354.jpg","images":"/uploads/20181022/979c9ab660275292aefc5c6b7632b2a8.jpg,/uploads/20181022/5e50668033beb8d9d671da58b598fa79.jpg,/uploads/20181022/b532838d95d86275310107d512861b6c.jpg","like_count":1,"name":"金因姜时光肌密面膜","price_discount":66,"price_market":666,"price_member":555,"price_newmember":0,"price_second":0,"project_category_id":1,"quality":"","quality_text":"","redenvelopes_ids":"1","state":"1","state_text":"正常","treatment_count":"0","treatment_price":"55.00","views":0,"weigh":100}]
     * name : 蒙贝面部紧致提升
     * newmemberdata : [{"content":"55","coupon":["111111"],"coupon_ids":"1","createtime":1538487574,"id":4,"image":"/uploads/20181022/0847d20fcb22b348e29a61e333a363b4.jpg","images":"/uploads/20181022/b532838d95d86275310107d512861b6c.jpg,/uploads/20181022/5e50668033beb8d9d671da58b598fa79.jpg,/uploads/20181022/b532838d95d86275310107d512861b6c.jpg,/uploads/20181022/b532838d95d86275310107d512861b6c.jpg","like_count":0,"name":"粉红丝带","price_discount":100,"price_market":0,"price_member":0,"price_newmember":33,"price_second":0,"project_category_id":1,"quality":"","quality_text":"","redenvelopes":["111111"],"redenvelopes_ids":"1","state":"1","state_text":"正常","treatment_count":"3","treatment_price":"44.00","views":0,"weigh":4}]
     * price_discount : 100
     * price_market : 0
     * price_member : 0
     * price_newmember : 0
     * price_second : 0
     * project_category_id : 3
     * quality :
     * quality_text :
     * redenvelopes : [{"count":9,"createtime":1537844659,"id":1,"image":"/assets/img/qrcode.png","name":"111111","price_reduction":100,"price_satisfy":0,"state":"1","state_text":"正常","type":"1","type_text":"Type 1"}]
     * redenvelopes_ids : 1
     * specialdata : [{"content":"测试测试测试测试测试","coupon":["111111"],"coupon_ids":"1","createtime":1538449687,"id":1,"image":"/uploads/20181022/5e50668033beb8d9d671da58b598fa79.jpg","images":"/uploads/20181022/979c9ab660275292aefc5c6b7632b2a8.jpg,/uploads/20181022/b532838d95d86275310107d512861b6c.jpg","like_count":0,"name":"蒙贝面部紧致提升","price_discount":100,"price_market":0,"price_member":0,"price_newmember":0,"price_second":0,"project_category_id":3,"quality":"","quality_text":"","redenvelopes":["111111"],"redenvelopes_ids":"1","state":"2","state_text":"推荐","treatment_count":"0","treatment_price":"0.00","views":0,"weigh":100}]
     * state : 2
     * state_text : 推荐
     * treatment_count : 0
     * treatment_price : 0.00
     * views : 0
     * weigh : 100
     */

    private String id;
    private String name;
    private String image;
    private String images;
    private String redenvelopes_ids;
    private String coupon_ids;
    private String content;
    private String state;
    private int createtime;
    private float price_discount;
    private float price_market;
    private float price_member;
    private float price_newmember;
    private float price_second;
    private int project_category_id;
    private String quality;
    private String quality_text;
    private String state_text;
    private String treatment_count;
    private String treatment_price;
    private String satisfaction;
    private String peer;
    private int iscollection;
    private int views;
    private int weigh;
    private int collection_count;
    private WaiterBean waiter;
    private CommentdataBean commentData;
    private List<CouponBean> coupon;
    private List<BaseBean> hotdata;
    private List<BaseBean> memberdata;
    private List<BaseBean> newmemberdata;
    private List<BaseBean> specialdata;
    private List<RedenvelopesBean> redenvelopes;
    private List<FullBean> full;
    private List<String> strading;
    private List<String> service;
    private List<RedenvloesDataBean> redenvloesdata;
    private List<MyDenvelopes> myDenvelopes;
    private List<MyCoupon> myCoupons;
    public NearShopBean getNearshop() {
        return nearshop;
    }

    public void setNearshop(NearShopBean nearshop) {
        this.nearshop = nearshop;
    }

    private NearShopBean nearshop;


    public int getIscollection() {
        return iscollection;
    }

    public CommentdataBean getCommentData() {
        return commentData;
    }

    public void setCommentData(CommentdataBean commentData) {
        this.commentData = commentData;
    }

    public int getCollection_count() {
        return collection_count;
    }

    public void setCollection_count(int collection_count) {
        this.collection_count = collection_count;
    }

    public List<MyDenvelopes> getMyDenvelopes() {
        return myDenvelopes;
    }

    public void setMyDenvelopes(List<MyDenvelopes> myDenvelopes) {
        this.myDenvelopes = myDenvelopes;
    }

    public List<MyCoupon> getMyCoupons() {
        return myCoupons;
    }

    public void setMyCoupons(List<MyCoupon> myCoupons) {
        this.myCoupons = myCoupons;
    }

    public WaiterBean getWaiter() {
        return waiter;
    }

    public void setWaiter(WaiterBean waiter) {
        this.waiter = waiter;
    }

    public CommentdataBean getComment() {
        return commentData;
    }

    public void setComment(CommentdataBean commentData) {
        this.commentData = commentData;
    }

    public void setIscollection(int iscollection) {
        this.iscollection = iscollection;
    }
    public List<String> getService() {
        return service;
    }

    public void setService(List<String> service) {
        this.service = service;
    }

    public List<String> getStrading() {
        return strading;
    }

    public void setStrading(List<String> strading) {
        this.strading = strading;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCoupon_ids() {
        return coupon_ids;
    }

    public void setCoupon_ids(String coupon_ids) {
        this.coupon_ids = coupon_ids;
    }

    public int getCreatetime() {
        return createtime;
    }

    public void setCreatetime(int createtime) {
        this.createtime = createtime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice_discount() {
        return price_discount;
    }

    public void setPrice_discount(float price_discount) {
        this.price_discount = price_discount;
    }

    public float getPrice_market() {
        return price_market;
    }

    public void setPrice_market(float price_market) {
        this.price_market = price_market;
    }

    public float getPrice_member() {
        return price_member;
    }

    public void setPrice_member(float price_member) {
        this.price_member = price_member;
    }

    public float getPrice_newmember() {
        return price_newmember;
    }

    public void setPrice_newmember(float price_newmember) {
        this.price_newmember = price_newmember;
    }

    public float getPrice_second() {
        return price_second;
    }

    public void setPrice_second(float price_second) {
        this.price_second = price_second;
    }

    public int getProject_category_id() {
        return project_category_id;
    }

    public void setProject_category_id(int project_category_id) {
        this.project_category_id = project_category_id;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getQuality_text() {
        return quality_text;
    }

    public void setQuality_text(String quality_text) {
        this.quality_text = quality_text;
    }

    public String getRedenvelopes_ids() {
        return redenvelopes_ids;
    }

    public void setRedenvelopes_ids(String redenvelopes_ids) {
        this.redenvelopes_ids = redenvelopes_ids;
    }

    public List<FullBean> getFull() {
        return full;
    }

    public void setFull(List<FullBean> full) {
        this.full = full;
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

    public String getTreatment_count() {
        return treatment_count;
    }

    public void setTreatment_count(String treatment_count) {
        this.treatment_count = treatment_count;
    }

    public String getTreatment_price() {
        return treatment_price;
    }

    public void setTreatment_price(String treatment_price) {
        this.treatment_price = treatment_price;
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

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getWeigh() {
        return weigh;
    }

    public void setWeigh(int weigh) {
        this.weigh = weigh;
    }

    public List<CouponBean> getCoupon() {
        return coupon;
    }

    public void setCoupon(List<CouponBean> coupon) {
        this.coupon = coupon;
    }

    public List<BaseBean> getHotdata() {
        return hotdata;
    }

    public void setHotdata(List<BaseBean> hotdata) {
        this.hotdata = hotdata;
    }

    public List<BaseBean> getMemberdata() {
        return memberdata;
    }

    public void setMemberdata(List<BaseBean> memberdata) {
        this.memberdata = memberdata;
    }

    public List<BaseBean> getNewmemberdata() {
        return newmemberdata;
    }

    public void setNewmemberdata(List<BaseBean> newmemberdata) {
        this.newmemberdata = newmemberdata;
    }

    public List<BaseBean> getSpecialdata() {
        return specialdata;
    }

    public void setSpecialdata(List<BaseBean> specialdata) {
        this.specialdata = specialdata;
    }

    public List<RedenvelopesBean> getRedenvelopes() {
        return redenvelopes;
    }

    public void setRedenvelopes(List<RedenvelopesBean> redenvelopes) {
        this.redenvelopes = redenvelopes;
    }

    public static class CouponBean implements Serializable {
        /**
         * activitytime : 2018-10-07 12:53:59
         * activitytime_end : 2019-07-27 12:53:59
         * createtime : 1538888184
         * id : 1
         * image : /uploads/20181001/8cf03f354750e3e38664485e647c4af4.jpeg
         * name : 满599减50
         * price_reduction : 50
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

    public static class RedenvelopesBean implements Serializable {
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


    public class MyCoupon implements Serializable {

        /**
         * id : 7
         * coupon_type : 1
         * price_reduction : 0
         * coupon_id : 2
         * user_id : 7
         * project_project_id : null
         * shop_shop_id : 0
         * beautician_beautician_id : 0
         * createtime : 1542689980
         * state : 1
         * coupon_name : 满899减80
         * coupon : {"id":2,"name":"满899减80","image":"/uploads/20181022/63b5dc1f595937b86f1a7ad7e21185fa.jpg","price_satisfy":899,"price_reduction":80,"activitytime":"2018-11-01 11:47:48","activitytime_end":"2019-11-23 11:47:48","createtime":1541044162,"state":"1","state_text":"正常"}
         * coupon_type_text : Coupon_type 1
         * state_text : 正常
         */

        private int id;
        private String coupon_type;
        private int price_reduction;
        private int coupon_id;
        private int user_id;
        private Object project_project_id;
        private int shop_shop_id;
        private int beautician_beautician_id;
        private int createtime;
        private String state;
        private String coupon_name;
        private CouponBean coupon;
        private String coupon_type_text;
        private String state_text;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCoupon_type() {
            return coupon_type;
        }

        public void setCoupon_type(String coupon_type) {
            this.coupon_type = coupon_type;
        }

        public int getPrice_reduction() {
            return price_reduction;
        }

        public void setPrice_reduction(int price_reduction) {
            this.price_reduction = price_reduction;
        }

        public int getCoupon_id() {
            return coupon_id;
        }

        public void setCoupon_id(int coupon_id) {
            this.coupon_id = coupon_id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public Object getProject_project_id() {
            return project_project_id;
        }

        public void setProject_project_id(Object project_project_id) {
            this.project_project_id = project_project_id;
        }

        public int getShop_shop_id() {
            return shop_shop_id;
        }

        public void setShop_shop_id(int shop_shop_id) {
            this.shop_shop_id = shop_shop_id;
        }

        public int getBeautician_beautician_id() {
            return beautician_beautician_id;
        }

        public void setBeautician_beautician_id(int beautician_beautician_id) {
            this.beautician_beautician_id = beautician_beautician_id;
        }

        public int getCreatetime() {
            return createtime;
        }

        public void setCreatetime(int createtime) {
            this.createtime = createtime;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCoupon_name() {
            return coupon_name;
        }

        public void setCoupon_name(String coupon_name) {
            this.coupon_name = coupon_name;
        }

        public CouponBean getCoupon() {
            return coupon;
        }

        public void setCoupon(CouponBean coupon) {
            this.coupon = coupon;
        }

        public String getCoupon_type_text() {
            return coupon_type_text;
        }

        public void setCoupon_type_text(String coupon_type_text) {
            this.coupon_type_text = coupon_type_text;
        }

        public String getState_text() {
            return state_text;
        }

        public void setState_text(String state_text) {
            this.state_text = state_text;
        }

        public class CouponBean {
            /**
             * id : 2
             * name : 满899减80
             * image : /uploads/20181022/63b5dc1f595937b86f1a7ad7e21185fa.jpg
             * price_satisfy : 899
             * price_reduction : 80
             * activitytime : 2018-11-01 11:47:48
             * activitytime_end : 2019-11-23 11:47:48
             * createtime : 1541044162
             * state : 1
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

    public class MyDenvelopes implements Serializable {

        /**
         * id : 11
         * redenvelopes_id : 1
         * price_reduction : 1000
         * red_type : 2
         * user_id : 7
         * project_project_id : 0
         * shop_shop_id : 0
         * beautician_beautician_id : 2
         * state : 1
         * createtime : 1542880968
         * name : 抢50元
         * limttime : 长期有效
         * res : {"id":1,"name":"抢50元","price_reduction":1000,"count":99,"surplus_price":427,"surplus_count":99,"rangedata":"1","reduction_type":"1","createtime":1537844659,"state":"1","state_text":"正常","rangedata_text":"Rangedata 1","reduction_type_text":"Reduction_type 1"}
         * red_type_text : Red_type 2
         * state_text : 正常
         */

        private int id;
        private int redenvelopes_id;
        private int price_reduction;
        private String red_type;
        private int user_id;
        private int project_project_id;
        private int shop_shop_id;
        private int beautician_beautician_id;
        private String state;
        private int createtime;
        private String name;
        private String limttime;
        private ResBean res;
        private String red_type_text;
        private String state_text;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getRedenvelopes_id() {
            return redenvelopes_id;
        }

        public void setRedenvelopes_id(int redenvelopes_id) {
            this.redenvelopes_id = redenvelopes_id;
        }

        public int getPrice_reduction() {
            return price_reduction;
        }

        public void setPrice_reduction(int price_reduction) {
            this.price_reduction = price_reduction;
        }

        public String getRed_type() {
            return red_type;
        }

        public void setRed_type(String red_type) {
            this.red_type = red_type;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
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

        public int getBeautician_beautician_id() {
            return beautician_beautician_id;
        }

        public void setBeautician_beautician_id(int beautician_beautician_id) {
            this.beautician_beautician_id = beautician_beautician_id;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLimttime() {
            return limttime;
        }

        public void setLimttime(String limttime) {
            this.limttime = limttime;
        }

        public ResBean getRes() {
            return res;
        }

        public void setRes(ResBean res) {
            this.res = res;
        }

        public String getRed_type_text() {
            return red_type_text;
        }

        public void setRed_type_text(String red_type_text) {
            this.red_type_text = red_type_text;
        }

        public String getState_text() {
            return state_text;
        }

        public void setState_text(String state_text) {
            this.state_text = state_text;
        }

        public class ResBean {
            /**
             * id : 1
             * name : 抢50元
             * price_reduction : 1000
             * count : 99
             * surplus_price : 427
             * surplus_count : 99
             * rangedata : 1
             * reduction_type : 1
             * createtime : 1537844659
             * state : 1
             * state_text : 正常
             * rangedata_text : Rangedata 1
             * reduction_type_text : Reduction_type 1
             */

            private int id;
            private String name;
            private int price_reduction;
            private int count;
            private int surplus_price;
            private int surplus_count;
            private String rangedata;
            private String reduction_type;
            private int createtime;
            private String state;
            private String state_text;
            private String rangedata_text;
            private String reduction_type_text;

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

            public int getPrice_reduction() {
                return price_reduction;
            }

            public void setPrice_reduction(int price_reduction) {
                this.price_reduction = price_reduction;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public int getSurplus_price() {
                return surplus_price;
            }

            public void setSurplus_price(int surplus_price) {
                this.surplus_price = surplus_price;
            }

            public int getSurplus_count() {
                return surplus_count;
            }

            public void setSurplus_count(int surplus_count) {
                this.surplus_count = surplus_count;
            }

            public String getRangedata() {
                return rangedata;
            }

            public void setRangedata(String rangedata) {
                this.rangedata = rangedata;
            }

            public String getReduction_type() {
                return reduction_type;
            }

            public void setReduction_type(String reduction_type) {
                this.reduction_type = reduction_type;
            }

            public int getCreatetime() {
                return createtime;
            }

            public void setCreatetime(int createtime) {
                this.createtime = createtime;
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

            public String getRangedata_text() {
                return rangedata_text;
            }

            public void setRangedata_text(String rangedata_text) {
                this.rangedata_text = rangedata_text;
            }

            public String getReduction_type_text() {
                return reduction_type_text;
            }

            public void setReduction_type_text(String reduction_type_text) {
                this.reduction_type_text = reduction_type_text;
            }
        }
    }


}
