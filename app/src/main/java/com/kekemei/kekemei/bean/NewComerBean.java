package com.kekemei.kekemei.bean;

import java.util.List;

public class NewComerBean {

    /**
     * isnew : 0
     * banner : {"id":10,"title":"新人会员卡","adv_pos_id":10359,"image":"/uploads/20181107/8c83b8fd48c4dd6f2acf048da3fc9ee5.png","jumbdata":"web","project_project_id":0,"shop_shop_id":0,"becautician_becautician_id":0,"content":"","url":"","views":0,"starttime":"2018-11-07","endtime":"2018-11-07","createtime":1541558147,"weigh":10,"switch":1,"jumbdata_text":"Jumbdata web"}
     * newpopledata : [{"id":4,"name":"粉红丝带","project_category_id":1,"image":"/uploads/20181022/0847d20fcb22b348e29a61e333a363b4.jpg","images":"/uploads/20181022/b532838d95d86275310107d512861b6c.jpg,/uploads/20181022/5e50668033beb8d9d671da58b598fa79.jpg,/uploads/20181022/b532838d95d86275310107d512861b6c.jpg,/uploads/20181022/b532838d95d86275310107d512861b6c.jpg","price_market":0,"price_discount":100,"treatment_price":"44.00","price_newmember":33,"price_member":0,"price_second":0,"treatment_count":"3","quality":"","content":"55","coupon_ids":"1","redenvelopes_ids":"1","state":"1","weigh":4,"views":0,"createtime":1538487574,"redenvelopes":["111111"],"coupon":["满599减50"],"quality_text":"","state_text":"正常"}]
     * projectall : [{"id":1,"name":"蒙贝面部紧致提升","project_category_id":3,"image":"/uploads/20181022/5e50668033beb8d9d671da58b598fa79.jpg","images":"/uploads/20181022/979c9ab660275292aefc5c6b7632b2a8.jpg,/uploads/20181022/b532838d95d86275310107d512861b6c.jpg","price_market":0,"price_discount":100,"treatment_price":"0.00","price_newmember":0,"price_member":0,"price_second":0,"treatment_count":"0","quality":"","content":"测试测试测试测试测试","coupon_ids":"1","redenvelopes_ids":"1","state":"2","weigh":100,"views":0,"createtime":1538449687,"redenvelopes":["111111"],"coupon":["满599减50"],"quality_text":"","state_text":"推荐"},{"id":2,"name":"东方芒果台芭抗衰修复","project_category_id":1,"image":"/uploads/20181022/b532838d95d86275310107d512861b6c.jpg","images":"/uploads/20181022/eba3d6f9ffc2bd7f7791f407bccec354.jpg","price_market":0,"price_discount":100,"treatment_price":"0.00","price_newmember":0,"price_member":0,"price_second":0,"treatment_count":"0","quality":"","content":"55555","coupon_ids":"1","redenvelopes_ids":"1","state":"1","weigh":100,"views":0,"createtime":1538450467,"redenvelopes":["111111"],"coupon":["满599减50"],"quality_text":"","state_text":"正常"},{"id":3,"name":"金因姜时光肌密面膜","project_category_id":1,"image":"/uploads/20181022/eba3d6f9ffc2bd7f7791f407bccec354.jpg","images":"/uploads/20181022/979c9ab660275292aefc5c6b7632b2a8.jpg,/uploads/20181022/5e50668033beb8d9d671da58b598fa79.jpg,/uploads/20181022/b532838d95d86275310107d512861b6c.jpg","price_market":666,"price_discount":66,"treatment_price":"55.00","price_newmember":0,"price_member":555,"price_second":0,"treatment_count":"0","quality":"","content":"1111","coupon_ids":"1","redenvelopes_ids":"1","state":"1","weigh":100,"views":0,"createtime":1538452488,"redenvelopes":["111111"],"coupon":["满599减50"],"quality_text":"","state_text":"正常"},{"id":4,"name":"粉红丝带","project_category_id":1,"image":"/uploads/20181022/0847d20fcb22b348e29a61e333a363b4.jpg","images":"/uploads/20181022/b532838d95d86275310107d512861b6c.jpg,/uploads/20181022/5e50668033beb8d9d671da58b598fa79.jpg,/uploads/20181022/b532838d95d86275310107d512861b6c.jpg,/uploads/20181022/b532838d95d86275310107d512861b6c.jpg","price_market":0,"price_discount":100,"treatment_price":"44.00","price_newmember":33,"price_member":0,"price_second":0,"treatment_count":"3","quality":"","content":"55","coupon_ids":"1","redenvelopes_ids":"1","state":"1","weigh":4,"views":0,"createtime":1538487574,"redenvelopes":["111111"],"coupon":["满599减50"],"quality_text":"","state_text":"正常"}]
     * foryou : [{"id":1,"name":"蒙贝面部紧致提升","project_category_id":3,"image":"/uploads/20181022/5e50668033beb8d9d671da58b598fa79.jpg","images":"/uploads/20181022/979c9ab660275292aefc5c6b7632b2a8.jpg,/uploads/20181022/b532838d95d86275310107d512861b6c.jpg","price_market":0,"price_discount":100,"treatment_price":"0.00","price_newmember":0,"price_member":0,"price_second":0,"treatment_count":"0","quality":"","content":"测试测试测试测试测试","coupon_ids":"1","redenvelopes_ids":"1","state":"2","weigh":100,"views":0,"createtime":1538449687,"redenvelopes":["111111"],"coupon":["满599减50"],"quality_text":"","state_text":"推荐"}]
     */

    private int isnew;
    private BannerBean banner;
    private List<BaseBean> newpopledata;
    private List<BaseBean> projectall;
    private List<BaseBean> foryou;
    private WaiterBean admin;

    public WaiterBean getAdmin() {
        return admin;
    }

    public void setAdmin(WaiterBean admin) {
        this.admin = admin;
    }

    public int getIsnew() {
        return isnew;
    }

    public void setIsnew(int isnew) {
        this.isnew = isnew;
    }

    public BannerBean getBanner() {
        return banner;
    }

    public void setBanner(BannerBean banner) {
        this.banner = banner;
    }

    public List<BaseBean> getNewpopledata() {
        return newpopledata;
    }

    public void setNewpopledata(List<BaseBean> newpopledata) {
        this.newpopledata = newpopledata;
    }

    public List<BaseBean> getProjectall() {
        return projectall;
    }

    public void setProjectall(List<BaseBean> projectall) {
        this.projectall = projectall;
    }

    public List<BaseBean> getForyou() {
        return foryou;
    }

    public void setForyou(List<BaseBean> foryou) {
        this.foryou = foryou;
    }
}
