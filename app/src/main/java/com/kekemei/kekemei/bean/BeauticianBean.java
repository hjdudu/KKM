package com.kekemei.kekemei.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 美容师
 */
public class BeauticianBean implements Serializable {

    /**
     * appointment : 2
     * comment_count : 0
     * content : 美容师美容师美容师
     * coupon_ids : 0
     * distance : 19.54
     * id : 1
     * identity :
     * image : /uploads/20181022/63b5dc1f595937b86f1a7ad7e21185fa.jpg
     * images : /uploads/20181022/2c5d84349965e653baa9870a5311d15d.jpg,/uploads/20181022/0847d20fcb22b348e29a61e333a363b4.jpg,/uploads/20181022/d92905316745b7ce3c0e3d6ddc382c98.jpg
     * isfriend : 0
     * name : 美容师
     * order_count : 33
     * place :
     * redenvelopes_ids : 0
     * satisfaction : 0
     * shop_shop_ids : 2,1
     * shops : [{"address":"德胜门店","city":"北京/北京市/西城区","complaint_cus_id":2,"content":"<p>11111<\/p>","coupon_ids":1,"createtime":1538567762,"distance":19.54,"id":2,"image":"/uploads/20181022/223862141ebbdec2dbcbbd0d976383e0.jpeg","images":"/uploads/20181022/67f217375e12f531168371f316cd91ce.jpeg,/uploads/20181022/5a7cd90e4e2ba8bffc9d9c714aa70c99.jpeg","latitude":"39.9088596409","longitude":"116.3975157338","name":"德胜门店","redenvelopes_ids":1,"shop_service_ids":"3,1,2","state":"1","state_text":"正常","tel":"111111","user_id":1},{"address":"2222","city":"北京/北京市/西城区","complaint_cus_id":2,"content":"<p>2222<\/p>","coupon_ids":1,"createtime":1537862188,"distance":85.43,"id":1,"image":"/uploads/20181022/5a7cd90e4e2ba8bffc9d9c714aa70c99.jpeg","images":"/uploads/20181022/5a7cd90e4e2ba8bffc9d9c714aa70c99.jpeg,/uploads/20181022/67f217375e12f531168371f316cd91ce.jpeg,/uploads/20181022/223862141ebbdec2dbcbbd0d976383e0.jpeg","latitude":"39.3088596409","longitude":"116.3875157338","name":"西直门店","redenvelopes_ids":1,"shop_service_ids":"3,1,2","state":"1","state_text":"正常","tel":"2222","user_id":1}]
     * speciality :
     * state : 1
     * state_text : 正常
     * tag : [""]
     * tag_text : [null]
     * user_id : 4
     * user_nickname : 13269501766
     */

    private int appointment;
    private int comment_count;
    private String content;
    private int coupon_ids;
    private double distance;
    private String id;
    private String identity;
    private String image;
    private String images;
    private int isfirend;
    private String name;
    private int order_count;
    private String place;
    private int redenvelopes_ids;
    private double satisfaction;
    private String shop_shop_ids;
    private String speciality;
    private String state;
    private String state_text;
    private int user_id;
    private String user_nickname;
    private List<MeiRongShiListBean.DataBean.ShopsBean> shops;
    private List<String> tag;
    private List<String> tag_text;
    private float start;

    public int getAppointment() {
        return appointment;
    }

    public void setAppointment(int appointment) {
        this.appointment = appointment;
    }
    public float getStart() {
        return start;
    }

    public void setStart(float start) {
        this.start = start;
    }
    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCoupon_ids() {
        return coupon_ids;
    }

    public void setCoupon_ids(int coupon_ids) {
        this.coupon_ids = coupon_ids;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
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

    public int getIsfriend() {
        return isfirend;
    }

    public void setIsfriend(int isfirend) {
        this.isfirend = isfirend;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder_count() {
        return order_count;
    }

    public void setOrder_count(int order_count) {
        this.order_count = order_count;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getRedenvelopes_ids() {
        return redenvelopes_ids;
    }

    public void setRedenvelopes_ids(int redenvelopes_ids) {
        this.redenvelopes_ids = redenvelopes_ids;
    }

    public double getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(double satisfaction) {
        this.satisfaction = satisfaction;
    }

    public String getShop_shop_ids() {
        return shop_shop_ids;
    }

    public void setShop_shop_ids(String shop_shop_ids) {
        this.shop_shop_ids = shop_shop_ids;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public List<MeiRongShiListBean.DataBean.ShopsBean> getShops() {
        return shops;
    }

    public void setShops(List<MeiRongShiListBean.DataBean.ShopsBean> shops) {
        this.shops = shops;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }

    public List<String> getTag_text() {
        return tag_text;
    }

    public void setTag_text(List<String> tag_text) {
        this.tag_text = tag_text;
    }


}
