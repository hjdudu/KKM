package com.kekemei.kekemei.bean;

/**
 * Created by peiyangfan on 2018/11/22.
 */

public class OrderDetailBean {
    /**
     * code : 1
     * msg : 请求成功
     * time : 1542874383
     * data : {"id":1000000006,"name":"东方芒果台芭抗衰修复","price":100,"user_id":7,"count":1,"project_project_id":2,"shop_shop_id":null,"state":"0","pay_type":null,"beautician_beautician_id":null,"coupon_id":null,"myredenvelopes_id":null,"servicetime":0,"finishtime":0,"createtime":1542874202,"source":"1","address":null,"beauticianname":null,"beauticianid":null,"project_id":2,"project_price":100,"project_image":"/uploads/20181022/b532838d95d86275310107d512861b6c.jpg","redenvelopes":null,"conpou":null,"full":null,"pay_type_text":"","servicetime_text":"1970-01-01 08:00:00","finishtime_text":"1970-01-01 08:00:00","source_text":"Source 1","state_text":"待支付"}
     */

    private int code;
    private String msg;
    private String time;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1000000006
         * name : 东方芒果台芭抗衰修复
         * price : 100
         * user_id : 7
         * count : 1
         * project_project_id : 2
         * shop_shop_id : null
         * state : 0
         * pay_type : null
         * beautician_beautician_id : null
         * coupon_id : null
         * myredenvelopes_id : null
         * servicetime : 0
         * finishtime : 0
         * createtime : 1542874202
         * source : 1
         * address : null
         * beauticianname : null
         * beauticianid : null
         * project_id : 2
         * project_price : 100
         * project_image : /uploads/20181022/b532838d95d86275310107d512861b6c.jpg
         * redenvelopes : null
         * conpou : null
         * full : null
         * pay_type_text :
         * servicetime_text : 1970-01-01 08:00:00
         * finishtime_text : 1970-01-01 08:00:00
         * source_text : Source 1
         * state_text : 待支付
         */

        private int id;
        private String name;
        private float price;
        private int user_id;
        private int count;
        private int project_project_id;
        private Object shop_shop_id;
        private String state;
        private Object pay_type;
        private Object beautician_beautician_id;
        private Object coupon_id;
        private Object myredenvelopes_id;
        private int servicetime;
        private int finishtime;
        private int createtime;
        private String source;
        private String address;
        private Object beauticianname;
        private Object beauticianid;
        private Object project_id;
        private float project_price;
        private String project_image;
        private Object redenvelopes;
        private Object conpou;
        private Object full;
        private String pay_type_text;
        private String servicetime_text;
        private String finishtime_text;
        private String source_text;
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

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getProject_project_id() {
            return project_project_id;
        }

        public void setProject_project_id(int project_project_id) {
            this.project_project_id = project_project_id;
        }

        public Object getShop_shop_id() {
            return shop_shop_id;
        }

        public void setShop_shop_id(Object shop_shop_id) {
            this.shop_shop_id = shop_shop_id;
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

        public Object getBeautician_beautician_id() {
            return beautician_beautician_id;
        }

        public void setBeautician_beautician_id(Object beautician_beautician_id) {
            this.beautician_beautician_id = beautician_beautician_id;
        }

        public Object getCoupon_id() {
            return coupon_id;
        }

        public void setCoupon_id(Object coupon_id) {
            this.coupon_id = coupon_id;
        }

        public Object getMyredenvelopes_id() {
            return myredenvelopes_id;
        }

        public void setMyredenvelopes_id(Object myredenvelopes_id) {
            this.myredenvelopes_id = myredenvelopes_id;
        }

        public int getServicetime() {
            return servicetime;
        }

        public void setServicetime(int servicetime) {
            this.servicetime = servicetime;
        }

        public int getFinishtime() {
            return finishtime;
        }

        public void setFinishtime(int finishtime) {
            this.finishtime = finishtime;
        }

        public int getCreatetime() {
            return createtime;
        }

        public void setCreatetime(int createtime) {
            this.createtime = createtime;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Object getBeauticianname() {
            return beauticianname;
        }

        public void setBeauticianname(Object beauticianname) {
            this.beauticianname = beauticianname;
        }

        public Object getBeauticianid() {
            return beauticianid;
        }

        public void setBeauticianid(Object beauticianid) {
            this.beauticianid = beauticianid;
        }

        public Object getProject_id() {
            return project_id;
        }

        public void setProject_id(Object project_id) {
            this.project_id = project_id;
        }

        public float getProject_price() {
            return project_price;
        }

        public void setProject_price(float project_price) {
            this.project_price = project_price;
        }

        public String getProject_image() {
            return project_image;
        }

        public void setProject_image(String project_image) {
            this.project_image = project_image;
        }

        public Object getRedenvelopes() {
            return redenvelopes;
        }

        public void setRedenvelopes(Object redenvelopes) {
            this.redenvelopes = redenvelopes;
        }

        public Object getConpou() {
            return conpou;
        }

        public void setConpou(Object conpou) {
            this.conpou = conpou;
        }

        public Object getFull() {
            return full;
        }

        public void setFull(Object full) {
            this.full = full;
        }

        public String getPay_type_text() {
            return pay_type_text;
        }

        public void setPay_type_text(String pay_type_text) {
            this.pay_type_text = pay_type_text;
        }

        public String getServicetime_text() {
            return servicetime_text;
        }

        public void setServicetime_text(String servicetime_text) {
            this.servicetime_text = servicetime_text;
        }

        public String   getFinishtime_text() {
            return finishtime_text;
        }

        public void setFinishtime_text(String finishtime_text) {
            this.finishtime_text = finishtime_text;
        }

        public String getSource_text() {
            return source_text;
        }

        public void setSource_text(String source_text) {
            this.source_text = source_text;
        }

        public String getState_text() {
            return state_text;
        }

        public void setState_text(String state_text) {
            this.state_text = state_text;
        }
    }
}
