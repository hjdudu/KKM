package com.kekemei.kekemei.bean;

import java.util.List;

/**
 * Created by peiyangfan on 2018/11/7.
 */

public class YuYueDataBean {

    /**
     * code : 1
     * msg : 请求成功
     * time : 1541555754
     * data : [{"id":1,"name":"8:00","state":0},{"id":2,"name":"8:30","state":0},{"id":3,"name":"9:00","state":0},{"id":4,"name":"9:30","state":0},{"id":5,"name":"10:00","state":0},{"id":6,"name":"10:30","state":0},{"id":7,"name":"11:00","state":0},{"id":8,"name":"11:30","state":0},{"id":9,"name":"12:00","state":0},{"id":10,"name":"12:30","state":0},{"id":11,"name":"13:00","state":0},{"id":12,"name":"13:30","state":0},{"id":13,"name":"14:00","state":0},{"id":14,"name":"14:30","state":0},{"id":15,"name":"15:00","state":0},{"id":16,"name":"15:30","state":0},{"id":17,"name":"16:00","state":0},{"id":18,"name":"16:30","state":0},{"id":19,"name":"17:00","state":0},{"id":20,"name":"17:30","state":0},{"id":21,"name":"18:00","state":0},{"id":22,"name":"18:30","state":0},{"id":23,"name":"19:00","state":0},{"id":24,"name":"19:30","state":0},{"id":25,"name":"20:00","state":0},{"id":26,"name":"20:30","state":0}]
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
         * id : 1
         * name : 8:00
         * state : 0
         */

        private int id;
        private String name;
        private int state;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        private boolean isSelect;

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

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }
    }
}
