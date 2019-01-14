package com.kekemei.kekemei.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 店铺
 */
public class ShopBean implements Serializable {


    /**
     * id : 100000
     * name : 东直门店
     * image : /uploads/20181022/67f217375e12f531168371f316cd91ce.jpeg
     * images : /uploads/20181022/67f217375e12f531168371f316cd91ce.jpeg
     * city : 北京/北京市/崇文区
     * redenvelopes_ids : 1
     * coupon_ids : 3
     * address : 1
     * tel : 1111
     * longitude : 116.375157338
     * latitude : 39.9088596409
     * content : <p>东直门店位于朝阳</p>
     * state : 1
     * createtime : 1542197813
     * shop_service_ids : 3,1,2
     * complaint_cus_id : 2
     * user_id : 1
     * order_count : 2
     * appointment : 3
     * satisfaction : 0
     * start : 0
     * service : ["品质保障","随时调换","7天退"]
     * redenvelopes : ["抢50元"]
     * coupon : ["满599减50"]
     * full : ["满1099减100"]
     * distance : 7072.2
     * comment_count : 0
     * state_text : 正常
     * minimum : 88
     * tag : 真棒
     * starts : 0
     */

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
    private int complaint_cus_id;
    private int user_id;
    private int order_count;
    private int appointment;
    private float start;
    private String distance;
    private int comment_count;
    private String state_text;
    private List<String> service;
    private List<String> redenvelopes;
    private List<String> coupon;
    private List<String> full;
    private double satisfaction;
    private String tag;
    private int minimum;
    private float starts;

    public float getStarts() {
        return starts;
    }

    public void setStarts(float starts) {
        this.starts = starts;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getMinimum() {
        return minimum;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

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

    public int getComplaint_cus_id() {
        return complaint_cus_id;
    }

    public void setComplaint_cus_id(int complaint_cus_id) {
        this.complaint_cus_id = complaint_cus_id;
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

    public int getAppointment() {
        return appointment;
    }

    public void setAppointment(int appointment) {
        this.appointment = appointment;
    }

    public double getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(double satisfaction) {
        this.satisfaction = satisfaction;
    }

    public float getStart() {
        return start;
    }

    public void setStart(float start) {
        this.start = start;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
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

    public List<String> getRedenvelopes() {
        return redenvelopes;
    }

    public void setRedenvelopes(List<String> redenvelopes) {
        this.redenvelopes = redenvelopes;
    }

    public List<String> getCoupon() {
        return coupon;
    }

    public void setCoupon(List<String> coupon) {
        this.coupon = coupon;
    }

    public List<String> getFull() {
        return full;
    }

    public void setFull(List<String> full) {
        this.full = full;
    }
}
