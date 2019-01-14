package com.kekemei.kekemei.bean;

import java.util.List;

public class ForYouBean {

    /**
     * code : 1
     * msg : 请求成功
     * time : 1539400736
     * data : [{"id":1,"name":"热门项目","project_category_id":3,"image":"/uploads/20181001/8cf03f354750e3e38664485e647c4af4.jpeg","images":"/uploads/20181001/8cf03f354750e3e38664485e647c4af4.jpeg,/uploads/20180925/29393d1005dc4ffabaee1736aaa2b6a0.jpg","price":150,"price_market":0,"price_discount":0,"treatment_price":"0.00","price_newmember":0,"price_member":0,"price_second":0,"treatment_count":"0","quality":"","activitytime":"2018-10-02 11:01:59","activitytime_end":"2018-10-02 11:01:59","content":"测试测试测试测试测试","flag":"","redenvelopes_ids":"1","coupon_ids":null,"state":"2","weigh":100,"views":0,"createtime":1538449687,"quality_text":"","flag_text":"","state_text":"推荐"}]
     */

    private int code;
    private String msg;
    private String time;
    private List<BaseBean> data;

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

    public List<BaseBean> getData() {
        return data;
    }

    public void setData(List<BaseBean> data) {
        this.data = data;
    }
}
