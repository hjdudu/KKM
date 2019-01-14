package com.kekemei.kekemei.bean;

import com.google.gson.annotations.SerializedName;

public class BannerBean {

    /**
     * id : 12
     * title : 发现Banner第二屏
     * adv_pos_id : 10361
     * image : /uploads/20181107/8c83b8fd48c4dd6f2acf048da3fc9ee5.png
     * jumbdata : web
     * project_project_id : 0
     * shop_shop_id : 0
     * becautician_becautician_id : 0
     * content : 发现Banner第二屏
     * url :
     * views : 0
     * starttime : 2018-11-05
     * endtime : 2019-12-26
     * createtime : 1542088579
     * weigh : 12
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