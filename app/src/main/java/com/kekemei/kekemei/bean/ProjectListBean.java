package com.kekemei.kekemei.bean;


import java.util.List;

public class ProjectListBean {

    /**
     * code : 1
     * msg : 请求成功
     * time : 1542101313
     * data : {"banner":[{"id":12,"title":"发现Banner第二屏","adv_pos_id":10361,"image":"/uploads/20181107/8c83b8fd48c4dd6f2acf048da3fc9ee5.png","jumbdata":"web","project_project_id":0,"shop_shop_id":0,"becautician_becautician_id":0,"content":"发现Banner第二屏","url":"","views":0,"starttime":"2018-11-05","endtime":"2019-12-26","createtime":1542088579,"weigh":12,"switch":1,"jumbdata_text":"Jumbdata web"},{"id":13,"title":"发现Banner第三屏","adv_pos_id":10362,"image":"/uploads/20181022/4208428115e55145e7ca26f73fa5c6d3.jpg","jumbdata":"web","project_project_id":0,"shop_shop_id":0,"becautician_becautician_id":0,"content":"发现Banner第三屏","url":"","views":0,"starttime":"2017-11-13","endtime":"2019-11-13","createtime":1542088640,"weigh":13,"switch":1,"jumbdata_text":"Jumbdata web"}],"data":[{"id":1,"name":"蒙贝面部紧致提升","project_category_id":3,"image":"/uploads/20181022/5e50668033beb8d9d671da58b598fa79.jpg","images":"/uploads/20181022/979c9ab660275292aefc5c6b7632b2a8.jpg,/uploads/20181022/b532838d95d86275310107d512861b6c.jpg","price_market":0,"price_discount":100,"treatment_price":"0.00","price_newmember":0,"price_member":0,"price_second":0,"treatment_count":"0","quality":"","content":"测试测试测试测试测试","coupon_ids":"1","redenvelopes_ids":"1","state":"2","weigh":100,"views":0,"createtime":1538449687,"redenvelopes":["111111"],"coupon":["满599减50"],"distance":10.15,"quality_text":"","state_text":"推荐"},{"id":2,"name":"东方芒果台芭抗衰修复","project_category_id":1,"image":"/uploads/20181022/b532838d95d86275310107d512861b6c.jpg","images":"/uploads/20181022/eba3d6f9ffc2bd7f7791f407bccec354.jpg","price_market":0,"price_discount":100,"treatment_price":"0.00","price_newmember":0,"price_member":0,"price_second":0,"treatment_count":"0","quality":"","content":"55555","coupon_ids":"1","redenvelopes_ids":"1","state":"1","weigh":100,"views":0,"createtime":1538450467,"redenvelopes":["111111"],"coupon":["满599减50"],"distance":10.15,"quality_text":"","state_text":"正常"},{"id":3,"name":"金因姜时光肌密面膜","project_category_id":1,"image":"/uploads/20181022/eba3d6f9ffc2bd7f7791f407bccec354.jpg","images":"/uploads/20181022/979c9ab660275292aefc5c6b7632b2a8.jpg,/uploads/20181022/5e50668033beb8d9d671da58b598fa79.jpg,/uploads/20181022/b532838d95d86275310107d512861b6c.jpg","price_market":666,"price_discount":66,"treatment_price":"55.00","price_newmember":0,"price_member":555,"price_second":0,"treatment_count":"0","quality":"","content":"1111","coupon_ids":"1","redenvelopes_ids":"1","state":"1","weigh":100,"views":0,"createtime":1538452488,"redenvelopes":["111111"],"coupon":["满599减50"],"distance":10.15,"quality_text":"","state_text":"正常"},{"id":4,"name":"粉红丝带","project_category_id":1,"image":"/uploads/20181022/0847d20fcb22b348e29a61e333a363b4.jpg","images":"/uploads/20181022/b532838d95d86275310107d512861b6c.jpg,/uploads/20181022/5e50668033beb8d9d671da58b598fa79.jpg,/uploads/20181022/b532838d95d86275310107d512861b6c.jpg,/uploads/20181022/b532838d95d86275310107d512861b6c.jpg","price_market":0,"price_discount":100,"treatment_price":"44.00","price_newmember":33,"price_member":0,"price_second":0,"treatment_count":"3","quality":"","content":"55","coupon_ids":"1","redenvelopes_ids":"1","state":"1","weigh":4,"views":0,"createtime":1538487574,"redenvelopes":["111111"],"coupon":["满599减50"],"distance":10.15,"quality_text":"","state_text":"正常"},{"id":5,"name":"5555","project_category_id":3,"image":"/uploads/20181022/63b5dc1f595937b86f1a7ad7e21185fa.jpg","images":"/uploads/20181022/63b5dc1f595937b86f1a7ad7e21185fa.jpg","price_market":99,"price_discount":99,"treatment_price":"99.00","price_newmember":0,"price_member":0,"price_second":0,"treatment_count":"11","quality":"","content":"111","coupon_ids":"3,1","redenvelopes_ids":"1","state":"1","weigh":5,"views":0,"createtime":1540957529,"redenvelopes":["111111"],"coupon":["满599减50","满1099减100"],"distance":10.15,"quality_text":"","state_text":"正常"}]}
     */

    private int code;
    private String msg;
    private String time;
    private DataBeanX data;

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

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        private List<BannerBean> banner;

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public List<BaseBean> getData() {
            return data;
        }

        public void setData(List<BaseBean> data) {
            this.data = data;
        }

        private List<BaseBean> data;

    }
}
