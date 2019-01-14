package com.kekemei.kekemei.bean;

import java.util.List;

public class ProjectShaiXuanListBean {
    private int code;
    private String msg;
    private String time;

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

    private List<BaseBean> data;
}
