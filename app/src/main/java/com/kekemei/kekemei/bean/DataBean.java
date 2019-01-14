package com.kekemei.kekemei.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;

import static com.kekemei.kekemei.bean.OrderListBean.ORDER_STATUS_ALL;
import static com.kekemei.kekemei.bean.OrderListBean.ORDER_STATUS_COMMENT;
import static com.kekemei.kekemei.bean.OrderListBean.ORDER_STATUS_FINISHED;
import static com.kekemei.kekemei.bean.OrderListBean.ORDER_STATUS_QUIT;
import static com.kekemei.kekemei.bean.OrderListBean.ORDER_STATUS_SERVED;
import static com.kekemei.kekemei.bean.OrderListBean.ORDER_STATUS_SERVING;
import static com.kekemei.kekemei.bean.OrderListBean.ORDER_STATUS_TO_BE_APPOINTMENT;
import static com.kekemei.kekemei.bean.OrderListBean.ORDER_STATUS_TO_BE_PAID;
import static com.kekemei.kekemei.bean.OrderListBean.ORDER_STATUS_TO_WAIT_SERVER;

public class DataBean extends BaseBean implements Serializable, MultiItemEntity {

    @Override
    public int getItemType() {
        int ORDER_STATUS;
        switch (state) {
            case "0":
                ORDER_STATUS = ORDER_STATUS_TO_BE_PAID;
                break;
            case "1":
                ORDER_STATUS = ORDER_STATUS_TO_BE_APPOINTMENT;
                break;
            case "2":
                ORDER_STATUS = ORDER_STATUS_TO_WAIT_SERVER;
                break;
            case "3":
                ORDER_STATUS = ORDER_STATUS_SERVING;
                break;
            case "4":
                ORDER_STATUS = ORDER_STATUS_SERVED;
                break;
            case "5":
                ORDER_STATUS = ORDER_STATUS_COMMENT;
                break;
            case "6":
                ORDER_STATUS = ORDER_STATUS_FINISHED;
                break;
            case "10":
                ORDER_STATUS = ORDER_STATUS_QUIT;
                break;
            default:
                ORDER_STATUS = ORDER_STATUS_ALL;
                break;
        }
        return ORDER_STATUS;
    }

    /**
     * id : 1
     * name : 订单！！！！
     * price : 0
     * user_id : 1
     * project_project_id : 3
     * shop_id : 2
     * state : 1
     * pay_type : null
     * beautician_id : 2
     * createtime : 1538613801
     * image : /uploads/20181001/8cf03f354750e3e38664485e647c4af4.jpeg
     * state_text : 待预约
     * pay_type_text :
     */

    private String id;
    private String name;
    private float price;
    private String user_id;
    private String project_project_id;
    private String shop_id;
    private String state;
    private Object pay_type;
    private String beautician_id;
    private String image;
    private String state_text;
    private String pay_type_text;
    private float createtime;

    public float getCreatetime() {
        return createtime;
    }

    public void setCreatetime(float createtime) {
        this.createtime = createtime;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getProject_project_id() {
        return project_project_id;
    }

    public void setProject_project_id(String project_project_id) {
        this.project_project_id = project_project_id;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Object getPay_type() {
        return pay_type;
    }

    public void setPay_type(Object pay_type) {
        this.pay_type = pay_type;
    }

    public String getBeautician_id() {
        return beautician_id;
    }

    public void setBeautician_id(String beautician_id) {
        this.beautician_id = beautician_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getState_text() {
        return state_text;
    }

    public void setState_text(String state_text) {
        this.state_text = state_text;
    }

    public String getPay_type_text() {
        return pay_type_text;
    }

    public void setPay_type_text(String pay_type_text) {
        this.pay_type_text = pay_type_text;
    }


}
