package com.kekemei.kekemei.bean;

import java.util.List;

public class EvaluateListBean {
    private List<Integer> count;
    private List<List<EvaluateBean>> data;

    public List<Integer> getCount() {
        return count;
    }

    public void setCount(List<Integer> count) {
        this.count = count;
    }

    public List<List<EvaluateBean>> getData() {
        return data;
    }

    public void setData(List<List<EvaluateBean>> data) {
        this.data = data;
    }
}
