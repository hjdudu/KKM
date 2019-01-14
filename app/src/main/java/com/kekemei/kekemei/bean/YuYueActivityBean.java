package com.kekemei.kekemei.bean;

import java.io.Serializable;

public class YuYueActivityBean implements Serializable {
    private String orderIconUrl;
    private String orderName;
    private float orderPrice;
    private String orderCreateTime;
    private int orderCount;
    private String orderId;

    private String project_id;

    private int timeSelect;
    private long dateSelect;

    private boolean fromDetail;


    private String timeSelectName;

    private BeauticianDetailBean beauticianDetailBean;
    private ShopDetailBean shopDetailBean;
    private NearShopBean nearShopBean;
    private int red_id;
    private int coupon_id;
    private String red_text;

    public NearShopBean getNearShopBean() {
        return nearShopBean;
    }

    public void setNearShopBean(NearShopBean nearShopBean) {
        this.nearShopBean = nearShopBean;
    }

    public int getRed_id() {
        return red_id;
    }

    public void setRed_id(int red_id) {
        this.red_id = red_id;
    }

    public int getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(int coupon_id) {
        this.coupon_id = coupon_id;
    }

    public String getRed_text() {
        return red_text;
    }

    public void setRed_text(String red_text) {
        this.red_text = red_text;
    }

    public String getCoupon_text() {
        return coupon_text;
    }

    public void setCoupon_text(String coupon_text) {
        this.coupon_text = coupon_text;
    }

    private String coupon_text;

    public boolean getFromDetail() {
        return fromDetail;
    }

    public void setFromDetail(boolean fromDetail) {
        this.fromDetail = fromDetail;
    }

    public String getOrderIconUrl() {
        return orderIconUrl;
    }

    public void setOrderIconUrl(String orderIconUrl) {
        this.orderIconUrl = orderIconUrl;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public float getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(float orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(String orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getTimeSelect() {
        return timeSelect;
    }

    public void setTimeSelect(int timeSelect) {
        this.timeSelect = timeSelect;
    }

    public long getDateSelect() {
        return dateSelect;
    }

    public void setDateSelect(long dateSelect) {
        this.dateSelect = dateSelect;
    }

    public BeauticianDetailBean getBeauticianDetailBean() {
        return beauticianDetailBean;
    }

    public void setBeauticianDetailBean(BeauticianDetailBean beauticianDetailBean) {
        this.beauticianDetailBean = beauticianDetailBean;
    }

    public ShopDetailBean getShopDetailBean() {
        return shopDetailBean;
    }

    public void setShopDetailBean(ShopDetailBean shopDetailBean) {
        this.shopDetailBean = shopDetailBean;
    }

    public String getTimeSelectName() {
        return timeSelectName;
    }

    public void setTimeSelectName(String timeSelectName) {
        this.timeSelectName = timeSelectName;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }
}
