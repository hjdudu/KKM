package com.kekemei.kekemei.bean;

import java.io.Serializable;
import java.util.List;

public class SearchResultBean implements Serializable {

    private List<BaseBean> project;
    private List<ShopBean> shop;
    private List<BeauticianBean> beautician;

    public List<BaseBean> getProject() {
        return project;
    }

    public void setProject(List<BaseBean> project) {
        this.project = project;
    }

    public List<ShopBean> getShop() {
        return shop;
    }

    public void setShop(List<ShopBean> shop) {
        this.shop = shop;
    }

    public List<BeauticianBean> getBeautician() {
        return beautician;
    }

    public void setBeautician(List<BeauticianBean> beautician) {
        this.beautician = beautician;
    }
}
