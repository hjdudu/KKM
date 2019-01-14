package com.kekemei.kekemei.bean;

import java.util.List;

public class CreditBean {
    private String zongfen;

    public String getZongfen() {
        return zongfen;
    }

    public void setZongfen(String zongfen) {
        this.zongfen = zongfen;
    }

    public List<JIFEN> getJifens() {
        return jifens;
    }

    public void setJifens(List<JIFEN> jifens) {
        this.jifens = jifens;
    }

    public static class JIFEN {
        private String data;
        private String price;

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }

    private List<JIFEN> jifens;
}
