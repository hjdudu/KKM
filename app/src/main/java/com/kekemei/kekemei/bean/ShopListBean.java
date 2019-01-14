package com.kekemei.kekemei.bean;

import java.util.List;

public class ShopListBean {

    private List<BannerBean> banner;

    public List<ShopBean> getData() {
        return data;
    }

    public void setData(List<ShopBean> data) {
        this.data = data;
    }

    private List<ShopBean> data;

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }
}
