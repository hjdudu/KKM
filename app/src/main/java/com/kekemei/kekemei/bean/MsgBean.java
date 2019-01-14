package com.kekemei.kekemei.bean;

import java.util.List;

public class MsgBean {

    /**
     * code : 1
     * msg : 请求地方成功
     * time : 1541923139
     * data : [{"id":3,"name":"333333","type":"0","user_id":1,"state":"1","createtime":1540005837,"type_text":"系统消息","state_text":"未读"},{"id":2,"name":"2222","type":"0","user_id":2,"state":"1","createtime":1540003275,"type_text":"系统消息","state_text":"未读"}]
     */

    private int code;
    private String msg;
    private String time;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 3
         * name : 333333
         * type : 0
         * user_id : 1
         * state : 1
         * createtime : 1540005837
         * type_text : 系统消息
         * state_text : 未读
         */

        private int id;
        private String name;
        private String type;
        private int user_id;
        private String state;
        private int createtime;
        private String type_text;
        private String state_text;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public int getCreatetime() {
            return createtime;
        }

        public void setCreatetime(int createtime) {
            this.createtime = createtime;
        }

        public String getType_text() {
            return type_text;
        }

        public void setType_text(String type_text) {
            this.type_text = type_text;
        }

        public String getState_text() {
            return state_text;
        }

        public void setState_text(String state_text) {
            this.state_text = state_text;
        }
    }
}
