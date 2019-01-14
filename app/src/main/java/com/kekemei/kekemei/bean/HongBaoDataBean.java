package com.kekemei.kekemei.bean;

import java.util.List;

public class HongBaoDataBean {

    /**
     * code : 1
     * msg : 请求成功
     * time : 1542897921
     * data : [{"id":11,"redenvelopes_id":1,"price_reduction":1000,"red_type":"2","user_id":7,"project_project_id":0,"shop_shop_id":0,"beautician_beautician_id":2,"state":"1","createtime":1542880968,"name":"抢50元","limttime":"长期有效","res":{"id":1,"name":"抢50元","price_reduction":1000,"count":99,"state":"1","createtime":1537844659,"surplus_price":427,"surplus_count":99,"rangedata":"1","state_text":"State 1","rangedata_text":"Rangedata 1"},"red_type_text":"Red_type 2","state_text":"State 1"}]
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
         * res : {"id":1,"name":"抢50元","price_reduction":1000,"count":99,"state":"1","createtime":1537844659,"surplus_price":427,"surplus_count":99,"rangedata":"1","state_text":"State 1","rangedata_text":"Rangedata 1"}
         * red_type_text : Red_type 2
         * state_text : State 1
         */

        private int id;
        private int redenvelopes_id;
        private double price_reduction;
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

        public double getPrice_reduction() {
            return price_reduction;
        }

        public void setPrice_reduction(double price_reduction) {
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

        public static class ResBean {
            /**
             * id : 1
             * name : 抢50元
             * price_reduction : 1000
             * count : 99
             * state : 1
             * createtime : 1537844659
             * surplus_price : 427
             * surplus_count : 99
             * rangedata : 1
             * state_text : State 1
             * rangedata_text : Rangedata 1
             */

            private int id;
            private String name;
            private int price_reduction;
            private int count;
            private String state;
            private int createtime;
            private int surplus_price;
            private int surplus_count;
            private String rangedata;
            private String state_text;
            private String rangedata_text;

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
        }
    }
}
