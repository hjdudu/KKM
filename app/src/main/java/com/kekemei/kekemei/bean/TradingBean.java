package com.kekemei.kekemei.bean;

public class TradingBean {
    private String trading;
    private boolean isSelect;

    public TradingBean(String trading, boolean isSelect) {
        this.trading = trading;
        this.isSelect = isSelect;
    }

    public String getTrading() {
        return trading;
    }

    public void setTrading(String trading) {
        this.trading = trading;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
