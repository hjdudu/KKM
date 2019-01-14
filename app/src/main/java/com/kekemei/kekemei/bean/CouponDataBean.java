package com.kekemei.kekemei.bean;

import java.util.List;

public class CouponDataBean {

    /**
     * code : 1
     * msg : 请求成功
     * time : 1541407168
     * data : [{"id":1,"coupon_type":"1","coupon_id":1,"user_id":1,"project_project_id":3,"shop_shop_id":0,"beautician_beautician_id":0,"createtime":1538893112,"state":"1","coupon_name":"满599减50","coupon":{"id":1,"name":"满599减50","image":"/uploads/20181001/8cf03f354750e3e38664485e647c4af4.jpeg","price_satisfy":599,"price_reduction":50,"activitytime":"2018-10-07 12:53:59","activitytime_end":"2019-07-27 12:53:59","createtime":1538888184,"weigh":1,"state":"1","state_text":"State 1"},"coupon_type_text":"Coupon_type 1","state_text":"State 1"}]
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

    public static class DataBean {
        /**
         * id : 1
         * coupon_type : 1
         * coupon_id : 1
         * user_id : 1
         * project_project_id : 3
         * shop_shop_id : 0
         * beautician_beautician_id : 0
         * createtime : 1538893112
         * state : 1
         * coupon_name : 满599减50
         * coupon : {"id":1,"name":"满599减50","image":"/uploads/20181001/8cf03f354750e3e38664485e647c4af4.jpeg","price_satisfy":599,"price_reduction":50,"activitytime":"2018-10-07 12:53:59","activitytime_end":"2019-07-27 12:53:59","createtime":1538888184,"weigh":1,"state":"1","state_text":"State 1"}
         * coupon_type_text : Coupon_type 1
         * state_text : State 1
         */

        private int id;
        private String coupon_type;
        private int coupon_id;
        private int user_id;
        private int project_project_id;
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

        public static class CouponBean {
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
             * state_text : State 1
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

            public String getState_text() {
                return state_text;
            }

            public void setState_text(String state_text) {
                this.state_text = state_text;
            }
        }
    }
}
