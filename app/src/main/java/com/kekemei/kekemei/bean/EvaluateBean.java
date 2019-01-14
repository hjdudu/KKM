package com.kekemei.kekemei.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class EvaluateBean implements Serializable{
    /**
     * avatar : /assets/img/avatar.png
     * beautician_beautician_id : 2
     * coment_type_text :
     * comment_tag : ["服务热情","环境优雅"]
     * comment_tag_ids : 1,2
     * comment_type : 1
     * content : 66666
     * createtime : 1540049490
     * id : 7
     * images : /uploads/20181107/8c83b8fd48c4dd6f2acf048da3fc9ee5.png
     * nickname : admin
     * order_id : 1
     * project_project_id : 3
     * reply : [{"beautician_beautician_id":2,"comment_comment_id":7,"content":"回复内容","createtime":1541126489,"id":1,"state":"1","state_text":"State 1"}]
     * satisfaction : 1
     * satisfaction_text : Satisfaction 1
     * shop_shop_id : 2
     * start : 3
     * start_text : Start 3
     * state : 1
     * state_text : State 1
     * switch : 1
     * user_id : 1
     */

    private String avatar;
    private int beautician_beautician_id;
    private String coment_type_text;
    private String comment_tag_ids;
    private String comment_type;
    private String content;
    private int createtime;
    private int id;
    private String images;
    private String nickname;
    private int order_id;
    private int project_project_id;
    private String satisfaction;
    private String satisfaction_text;
    private int shop_shop_id;
    private String start;
    private String start_text;
    private String state;
    private String state_text;
    @SerializedName("switch")
    private int switchX;
    private int user_id;
    private List<String> comment_tag;
    private List<ReplyBean> reply;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getBeautician_beautician_id() {
        return beautician_beautician_id;
    }

    public void setBeautician_beautician_id(int beautician_beautician_id) {
        this.beautician_beautician_id = beautician_beautician_id;
    }

    public String getComent_type_text() {
        return coment_type_text;
    }

    public void setComent_type_text(String coment_type_text) {
        this.coment_type_text = coment_type_text;
    }

    public String getComment_tag_ids() {
        return comment_tag_ids;
    }

    public void setComment_tag_ids(String comment_tag_ids) {
        this.comment_tag_ids = comment_tag_ids;
    }

    public String getComment_type() {
        return comment_type;
    }

    public void setComment_type(String comment_type) {
        this.comment_type = comment_type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCreatetime() {
        return createtime;
    }

    public void setCreatetime(int createtime) {
        this.createtime = createtime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getProject_project_id() {
        return project_project_id;
    }

    public void setProject_project_id(int project_project_id) {
        this.project_project_id = project_project_id;
    }

    public String getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(String satisfaction) {
        this.satisfaction = satisfaction;
    }

    public String getSatisfaction_text() {
        return satisfaction_text;
    }

    public void setSatisfaction_text(String satisfaction_text) {
        this.satisfaction_text = satisfaction_text;
    }

    public int getShop_shop_id() {
        return shop_shop_id;
    }

    public void setShop_shop_id(int shop_shop_id) {
        this.shop_shop_id = shop_shop_id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getStart_text() {
        return start_text;
    }

    public void setStart_text(String start_text) {
        this.start_text = start_text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState_text() {
        return state_text;
    }

    public void setState_text(String state_text) {
        this.state_text = state_text;
    }

    public int getSwitchX() {
        return switchX;
    }

    public void setSwitchX(int switchX) {
        this.switchX = switchX;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public List<String> getComment_tag() {
        return comment_tag;
    }

    public void setComment_tag(List<String> comment_tag) {
        this.comment_tag = comment_tag;
    }

    public List<ReplyBean> getReply() {
        return reply;
    }

    public void setReply(List<ReplyBean> reply) {
        this.reply = reply;
    }

    public static class ReplyBean implements Serializable{
        /**
         * beautician_beautician_id : 2
         * comment_comment_id : 7
         * content : 回复内容
         * createtime : 1541126489
         * id : 1
         * state : 1
         * state_text : State 1
         */

        private int beautician_beautician_id;
        private int comment_comment_id;
        private String content;
        private int createtime;
        private int id;
        private String state;
        private String state_text;

        public int getBeautician_beautician_id() {
            return beautician_beautician_id;
        }

        public void setBeautician_beautician_id(int beautician_beautician_id) {
            this.beautician_beautician_id = beautician_beautician_id;
        }

        public int getComment_comment_id() {
            return comment_comment_id;
        }

        public void setComment_comment_id(int comment_comment_id) {
            this.comment_comment_id = comment_comment_id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getCreatetime() {
            return createtime;
        }

        public void setCreatetime(int createtime) {
            this.createtime = createtime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getState_text() {
            return state_text;
        }

        public void setState_text(String state_text) {
            this.state_text = state_text;
        }
    }
}
