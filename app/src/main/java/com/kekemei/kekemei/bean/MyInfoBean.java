package com.kekemei.kekemei.bean;

public class MyInfoBean {

    /**
     * code : 1
     * msg : 请求成功
     * time : 1543286255
     * data : {"id":8,"username":"18210462051","nickname":"18210462051","mobile":"18210462051","avatar":"/assets/img/avatar.png","score":0,"isnew":0,"token":"fc000c1d-3d83-48e9-942b-d30a11ad0087","user_id":8,"createtime":1543286255,"expiretime":1545878255,"expires_in":2592000,"coupon_count":2,"redenvelopes_count":1,"isbeautician":1,"ismaster":0,"admin":{"id":1,"nickname":"admin"}}
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
         * id : 8
         * username : 18210462051
         * nickname : 18210462051
         * mobile : 18210462051
         * avatar : /assets/img/avatar.png
         * score : 0
         * isnew : 0
         * token : fc000c1d-3d83-48e9-942b-d30a11ad0087
         * user_id : 8
         * createtime : 1543286255
         * expiretime : 1545878255
         * expires_in : 2592000
         * coupon_count : 2
         * redenvelopes_count : 1
         * isbeautician : 1
         * ismaster : 0
         * admin : {"id":1,"nickname":"admin"}
         */

        private int id;
        private String username;
        private String nickname;
        private String mobile;
        private String avatar;
        private int score;
        private int isnew;
        private String token;
        private int user_id;
        private int createtime;
        private int expiretime;
        private int expires_in;
        private int coupon_count;
        private int redenvelopes_count;
        private int isbeautician;
        private int ismaster;
        private WaiterBean admin;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getIsnew() {
            return isnew;
        }

        public void setIsnew(int isnew) {
            this.isnew = isnew;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getCreatetime() {
            return createtime;
        }

        public void setCreatetime(int createtime) {
            this.createtime = createtime;
        }

        public int getExpiretime() {
            return expiretime;
        }

        public void setExpiretime(int expiretime) {
            this.expiretime = expiretime;
        }

        public int getExpires_in() {
            return expires_in;
        }

        public void setExpires_in(int expires_in) {
            this.expires_in = expires_in;
        }

        public int getCoupon_count() {
            return coupon_count;
        }

        public void setCoupon_count(int coupon_count) {
            this.coupon_count = coupon_count;
        }

        public int getRedenvelopes_count() {
            return redenvelopes_count;
        }

        public void setRedenvelopes_count(int redenvelopes_count) {
            this.redenvelopes_count = redenvelopes_count;
        }

        public int getIsbeautician() {
            return isbeautician;
        }

        public void setIsbeautician(int isbeautician) {
            this.isbeautician = isbeautician;
        }

        public int getIsmaster() {
            return ismaster;
        }

        public void setIsmaster(int ismaster) {
            this.ismaster = ismaster;
        }

        public WaiterBean getAdmin() {
            return admin;
        }

        public void setAdmin(WaiterBean admin) {
            this.admin = admin;
        }
    }
}
