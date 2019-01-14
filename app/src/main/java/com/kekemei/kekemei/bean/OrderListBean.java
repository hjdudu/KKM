package com.kekemei.kekemei.bean;


import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;
import java.util.List;

public class OrderListBean implements Serializable {

    /**
     * 状态值:0=待支付,1=待预约,2=待服务,3=服务中,4=服务完成
     * 状态值:1=待预约,2=待服务,3=服务中,4=服务完成,5=待回复（回复客户评价）,6=已完成,10=已取消
     */
    public static final int ORDER_STATUS_ALL = -10;     //全部订单
    public static final int ORDER_STATUS_TO_BE_PAID = 0;     //待支付
    public static final int ORDER_STATUS_TO_BE_APPOINTMENT = 1; //待预约
    public static final int ORDER_STATUS_TO_WAIT_SERVER = 2; //待服务
    public static final int ORDER_STATUS_SERVING = 3;  //服务中
    public static final int ORDER_STATUS_SERVED = 4;  //服务完成
    public static final int ORDER_STATUS_COMMENT = 5;      //待评价
    public static final int ORDER_STATUS_FINISHED = 6;  //完成
    public static final int ORDER_STATUS_QUIT = 10;  //取消


    /**
     * code : 1
     * data : [{"beautician_beautician_id":2,"count":1,"createtime":1539742031,"id":2,"image":"/uploads/20181022/63b5dc1f595937b86f1a7ad7e21185fa.jpg","name":"3333","pay_type_text":"","price":100,"project_project_id":5,"state":"1","state_text":"待预约","user_id":1},{"count":1,"createtime":1541926073,"id":6,"image":"/uploads/20181022/eba3d6f9ffc2bd7f7791f407bccec354.jpg","name":"订单！！！！","pay_type_text":"","price":0,"project_project_id":3,"state":"0","state_text":"待支付","user_id":1},{"count":1,"createtime":1541927417,"id":7,"image":"/uploads/20181022/5e50668033beb8d9d671da58b598fa79.jpg","name":"美容师","pay_type_text":"","price":0,"project_project_id":1,"state":"0","state_text":"待支付","user_id":1}]
     * msg : 请求成功
     * time : 1542008144
     */

    private int code;
    private String msg;
    private String time;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable, MultiItemEntity {
        /**
         * beautician_beautician_id : 2
         * count : 1
         * createtime : 1539742031
         * id : 2
         * image : /uploads/20181022/63b5dc1f595937b86f1a7ad7e21185fa.jpg
         * name : 3333
         * pay_type_text :
         * price : 100
         * project_project_id : 5
         * state : 1
         * state_text : 待预约
         * user_id : 1
         */
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

        private int beautician_beautician_id;
        private int count;
        private int createtime;
        private String id;
        private String image;
        private String name;
        private String pay_type_text;
        private float price;
        private String project_project_id;
        private String state;
        private String state_text;
        private int user_id;
        private int shop_shop_id;
        private String source;

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPay_type_text() {
            return pay_type_text;
        }

        public void setPay_type_text(String pay_type_text) {
            this.pay_type_text = pay_type_text;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }

        public String getProject_project_id() {
            return project_project_id;
        }

        public void setProject_project_id(String project_project_id) {
            this.project_project_id = project_project_id;
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
    }
}

