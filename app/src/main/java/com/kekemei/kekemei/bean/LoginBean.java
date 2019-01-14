package com.kekemei.kekemei.bean;

/**
 * Created by peiyangfan on 2018/10/29.
 */

public class LoginBean {


    /**
     * code : 1
     * data : {"userinfo":{"avatar":"/assets/img/avatar.png","createtime":1542004058,"expires_in":2592000,"expiretime":1544596058,"id":5,"isnew":0,"mobile":"18635073387","nickname":"18635073387","score":0,"token":"b9f87774-e7df-4dc3-9fb5-f8d52abbfc56","user_id":5,"username":"18635073387"}}
     * msg : 登录成功
     * time : 1542004058
     */

    private int code;
    private DataBean data;
    private String msg;
    private String time;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
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

    public static class DataBean {
        /**
         * userinfo : {"avatar":"/assets/img/avatar.png","createtime":1542004058,"expires_in":2592000,"expiretime":1544596058,"id":5,"isnew":0,"mobile":"18635073387","nickname":"18635073387","score":0,"token":"b9f87774-e7df-4dc3-9fb5-f8d52abbfc56","user_id":5,"username":"18635073387"}
         */

        private UserinfoBean userinfo;

        public UserinfoBean getUserinfo() {
            return userinfo;
        }

        public void setUserinfo(UserinfoBean userinfo) {
            this.userinfo = userinfo;
        }

        public static class UserinfoBean {
            /**
             * avatar : /assets/img/avatar.png
             * createtime : 1542004058
             * expires_in : 2592000
             * expiretime : 1544596058
             * id : 5
             * isnew : 0
             * mobile : 18635073387
             * nickname : 18635073387
             * score : 0
             * token : b9f87774-e7df-4dc3-9fb5-f8d52abbfc56
             * user_id : 5
             * username : 18635073387
             */

            private String avatar;
            private int createtime;
            private int expires_in;
            private int expiretime;
            private int id;
            private int isnew;
            private String mobile;
            private String nickname;
            private int score;
            private String token;
            private int user_id;
            private String username;

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public int getCreatetime() {
                return createtime;
            }

            public void setCreatetime(int createtime) {
                this.createtime = createtime;
            }

            public int getExpires_in() {
                return expires_in;
            }

            public void setExpires_in(int expires_in) {
                this.expires_in = expires_in;
            }

            public int getExpiretime() {
                return expiretime;
            }

            public void setExpiretime(int expiretime) {
                this.expiretime = expiretime;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getIsnew() {
                return isnew;
            }

            public void setIsnew(int isnew) {
                this.isnew = isnew;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
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

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }
        }
    }
}
