package com.kekemei.kekemei.bean;

import java.util.Calendar;
import java.util.List;

/**
 * Created by peiyangfan on 2018/11/7.
 */

public class CanlBean {
    private String canYuYue;

    public String getCanYuYue() {
        return canYuYue;
    }

    public void setCanYuYue(String canYuYue) {
        this.canYuYue = canYuYue;
    }

    public List<Calendar> getDataBean() {
        return dataBean;
    }

    public void setDataBean(List<Calendar> dataBean) {
        this.dataBean = dataBean;
    }

    private List<Calendar> dataBean;



}
