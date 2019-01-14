package com.kekemei.kekemei.bean;

public class ALiPayResultBean {
    /**
     * code : 1
     * msg : 下单成功
     * time : 1542118623
     * data : {"info":"alipay_sdk=alipay-sdk-php-20161101&app_id=2018060860352001&biz_content=%7B%22body%22%3A%22%5Cu7c89%5Cu7ea2%5Cu4e1d%5Cu5e26%22%2C%22subject%22%3A%22%5Cu7c89%5Cu7ea2%5Cu4e1d%5Cu5e26%22%2C%22out_trade_no%22%3A%22Al10767817561542118623%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A0%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fkekemei.ecooth.com%2Fapi%2Forder%2Fzfbnotify&sign_type=RSA2&timestamp=2018-11-13+22%3A17%3A03&version=1.0&sign=FrbFhzSdI%2FnFjoAui7gZmVPFFWfPKPwG0dOX0PbCdXL9eTgS%2FajDCvO9Wg%2F59X4ZvOYP6XMpCgef%2FeAKUenl6si9CF5tJy20RoDhJJCVApYx4KnUbVXw%2FRstoh3p49Rs5nemh0k7fRzZUtqddXN8VOLBzOclGktL%2BSAjwWQ1UO%2BKa8u%2FlmG%2BBO7INQC73q7nalNaky1PQcLQEzpcFoH3CPAu9wZkCSs25TZPQxvfBy0ijCJugiXVB8pkeOzDedaBr7wMcB%2B%2BFmJP%2Fc%2BrDl3xI7h6vq8pVDvNMVKy%2FinXLoZLLYBiC0RNlB%2F65SymUkZDbDEhSC%2BZQAQRXuVntk%2Bh1Q%3D%3D","out_trade_no":"Al10767817561542118623"}
     */

    private int code;
    private String msg;
    private String time;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * info : alipay_sdk=alipay-sdk-php-20161101&app_id=2018060860352001&biz_content=%7B%22body%22%3A%22%5Cu7c89%5Cu7ea2%5Cu4e1d%5Cu5e26%22%2C%22subject%22%3A%22%5Cu7c89%5Cu7ea2%5Cu4e1d%5Cu5e26%22%2C%22out_trade_no%22%3A%22Al10767817561542118623%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A0%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fkekemei.ecooth.com%2Fapi%2Forder%2Fzfbnotify&sign_type=RSA2&timestamp=2018-11-13+22%3A17%3A03&version=1.0&sign=FrbFhzSdI%2FnFjoAui7gZmVPFFWfPKPwG0dOX0PbCdXL9eTgS%2FajDCvO9Wg%2F59X4ZvOYP6XMpCgef%2FeAKUenl6si9CF5tJy20RoDhJJCVApYx4KnUbVXw%2FRstoh3p49Rs5nemh0k7fRzZUtqddXN8VOLBzOclGktL%2BSAjwWQ1UO%2BKa8u%2FlmG%2BBO7INQC73q7nalNaky1PQcLQEzpcFoH3CPAu9wZkCSs25TZPQxvfBy0ijCJugiXVB8pkeOzDedaBr7wMcB%2B%2BFmJP%2Fc%2BrDl3xI7h6vq8pVDvNMVKy%2FinXLoZLLYBiC0RNlB%2F65SymUkZDbDEhSC%2BZQAQRXuVntk%2Bh1Q%3D%3D
         * out_trade_no : Al10767817561542118623
         */

        private String info;
        private String out_trade_no;

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getOut_trade_no() {
            return out_trade_no;
        }

        public void setOut_trade_no(String out_trade_no) {
            this.out_trade_no = out_trade_no;
        }
    }
}
