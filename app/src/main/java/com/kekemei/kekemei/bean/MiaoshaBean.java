package com.kekemei.kekemei.bean;

import java.util.List;

public class MiaoshaBean {

    /**
     * code : 1
     * data : {"banner":[{"adv_pos_id":10344,"becautician_becautician_id":0,"content":"杀Banner广告","createtime":1539694959,"endtime":"2019-10-16","id":3,"image":"/uploads/20181022/4208428115e55145e7ca26f73fa5c6d3.jpg","jumbdata":"project","jumbdata_text":"Jumbdata project","project_project_id":4,"shop_shop_id":0,"starttime":"2018-10-16","switch":1,"title":"杀Banner广告","url":"","views":0,"weigh":0},{"adv_pos_id":10346,"becautician_becautician_id":0,"content":"秒杀广告","createtime":1539697575,"endtime":"2019-12-28","id":4,"image":"/uploads/20181022/0847d20fcb22b348e29a61e333a363b4.jpg","jumbdata":"web","jumbdata_text":"Jumbdata web","project_project_id":0,"shop_shop_id":0,"starttime":"2018-10-16","switch":1,"title":"秒杀广告","url":"","views":0,"weigh":0}],"second":[{"endtimes":"13:00:00","id":1,"name":"12:00专场","project_data":[{"content":"1111","coupon_ids":"1","createtime":1538452488,"id":3,"image":"/uploads/20181022/eba3d6f9ffc2bd7f7791f407bccec354.jpg","images":"/uploads/20181022/979c9ab660275292aefc5c6b7632b2a8.jpg,/uploads/20181022/5e50668033beb8d9d671da58b598fa79.jpg,/uploads/20181022/b532838d95d86275310107d512861b6c.jpg","name":"金因姜时光肌密面膜","price_discount":66,"price_market":666,"price_member":555,"price_newmember":0,"price_second":0,"project_category_id":1,"quality":"","quality_text":"","redenvelopes_ids":"1","state":"1","state_text":"正常","treatment_count":"0","treatment_price":"55.00","views":0,"weigh":100},{"content":"55","coupon_ids":"1","createtime":1538487574,"id":4,"image":"/uploads/20181022/0847d20fcb22b348e29a61e333a363b4.jpg","images":"/uploads/20181022/b532838d95d86275310107d512861b6c.jpg,/uploads/20181022/5e50668033beb8d9d671da58b598fa79.jpg,/uploads/20181022/b532838d95d86275310107d512861b6c.jpg,/uploads/20181022/b532838d95d86275310107d512861b6c.jpg","name":"粉红丝带","price_discount":100,"price_market":0,"price_member":0,"price_newmember":33,"price_second":0,"project_category_id":1,"quality":"","quality_text":"","redenvelopes_ids":"1","state":"1","state_text":"正常","treatment_count":"3","treatment_price":"44.00","views":0,"weigh":4}],"project_project_ids":"3,4","starttimes":"12:00:00","state":"1","state_text":"正常"}]}
     * msg : 请求成功
     * time : 1542011424
     */

    private int code;
    private DataBean data;
    private String msg;
    private String time;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
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

    public static class DataBean {
        private List<BannerBean> banner;
        private List<SecondBean> second;

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public List<SecondBean> getSecond() {
            return second;
        }

        public void setSecond(List<SecondBean> second) {
            this.second = second;
        }


        public static class SecondBean {
            /**
             * endtimes : 13:00:00
             * id : 1
             * name : 12:00专场
             * project_data : [{"content":"1111","coupon_ids":"1","createtime":1538452488,"id":3,"image":"/uploads/20181022/eba3d6f9ffc2bd7f7791f407bccec354.jpg","images":"/uploads/20181022/979c9ab660275292aefc5c6b7632b2a8.jpg,/uploads/20181022/5e50668033beb8d9d671da58b598fa79.jpg,/uploads/20181022/b532838d95d86275310107d512861b6c.jpg","name":"金因姜时光肌密面膜","price_discount":66,"price_market":666,"price_member":555,"price_newmember":0,"price_second":0,"project_category_id":1,"quality":"","quality_text":"","redenvelopes_ids":"1","state":"1","state_text":"正常","treatment_count":"0","treatment_price":"55.00","views":0,"weigh":100},{"content":"55","coupon_ids":"1","createtime":1538487574,"id":4,"image":"/uploads/20181022/0847d20fcb22b348e29a61e333a363b4.jpg","images":"/uploads/20181022/b532838d95d86275310107d512861b6c.jpg,/uploads/20181022/5e50668033beb8d9d671da58b598fa79.jpg,/uploads/20181022/b532838d95d86275310107d512861b6c.jpg,/uploads/20181022/b532838d95d86275310107d512861b6c.jpg","name":"粉红丝带","price_discount":100,"price_market":0,"price_member":0,"price_newmember":33,"price_second":0,"project_category_id":1,"quality":"","quality_text":"","redenvelopes_ids":"1","state":"1","state_text":"正常","treatment_count":"3","treatment_price":"44.00","views":0,"weigh":4}]
             * project_project_ids : 3,4
             * starttimes : 12:00:00
             * state : 1
             * state_text : 正常
             */

            private String endtimes;
            private int id;
            private String name;
            private String project_project_ids;
            private String starttimes;
            private String state;
            private String state_text;
            private List<BaseBean> project_data;

            public String getEndtimes() {
                return endtimes;
            }

            public void setEndtimes(String endtimes) {
                this.endtimes = endtimes;
            }

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

            public String getProject_project_ids() {
                return project_project_ids;
            }

            public void setProject_project_ids(String project_project_ids) {
                this.project_project_ids = project_project_ids;
            }

            public String getStarttimes() {
                return starttimes;
            }

            public void setStarttimes(String starttimes) {
                this.starttimes = starttimes;
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

            public List<BaseBean> getProject_data() {
                return project_data;
            }

            public void setProject_data(List<BaseBean> project_data) {
                this.project_data = project_data;
            }
        }
    }
}
