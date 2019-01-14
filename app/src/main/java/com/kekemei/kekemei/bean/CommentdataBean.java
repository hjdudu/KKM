package com.kekemei.kekemei.bean;

import com.google.gson.annotations.SerializedName;
import com.kekemei.kekemei.utils.StringUtils;

import java.io.Serializable;
import java.util.List;

public class CommentdataBean implements Serializable {

    /**
     * tags : [{"id":3,"name":"美容师专业","state":"1","weigh":0,"tag_type":"1","state_text":"正常","tag_type_text":"Tag_type 1"},{"id":5,"name":"干净卫生","state":"1","weigh":0,"tag_type":"2","state_text":"正常","tag_type_text":"Tag_type 2"}]
     * count : 1
     * all : [{"id":1,"user_id":1,"project_project_id":3,"shop_shop_id":1,"beautician_beautician_id":1,"comment_tag_ids":"5,3","order_id":0,"content":"很好","images":"","coment_type":"1","start":"3","state":"1","createtime":1538469581,"satisfaction":"2","switch":1,"nickname":"admin","user_avatar":"/assets/img/avatar.png","reply":null,"coment_type_text":"Coment_type 1","start_text":"Start 3","state_text":"正常","satisfaction_text":"Satisfaction 2"}]
     * new : [{"id":1,"user_id":1,"project_project_id":3,"shop_shop_id":1,"beautician_beautician_id":1,"comment_tag_ids":"5,3","order_id":0,"content":"很好","images":"","coment_type":"1","start":"3","state":"1","createtime":1538469581,"satisfaction":"2","switch":1,"nickname":"admin","user_avatar":"/assets/img/avatar.png","reply":null,"coment_type_text":"Coment_type 1","start_text":"Start 3","state_text":"正常","satisfaction_text":"Satisfaction 2"}]
     * haveimg : []
     * peer : 99
     * satisfaction :99
     * start : 0
     */

    private int count;
    private List<CommentTagsBean> tags;
    private List<EvaluateBean> all;
    @SerializedName("new")
    private List<EvaluateBean> newX;
    private List<EvaluateBean> haveimg;
    private String peer;
    private String satisfaction;
    private float start;

    public String getSatisfaction() {
        if (StringUtils.isNotEmpty(satisfaction) && satisfaction.contains(".")) {
            String[] split = satisfaction.split("\\.");
            return split[1];
        }
        return satisfaction;
    }

    public void setSatisfaction(String satisfaction) {
        this.satisfaction = satisfaction;
    }

    public String getPeer() {
        if (StringUtils.isNotEmpty(peer) && peer.contains(".")) {
            String[] split = peer.split("\\.");
            return split[1];
        }
        return peer;
    }

    public void setPeer(String peer) {
        this.peer = peer;
    }

    public float getStart() {
        return start;
    }

    public void setStart(float start) {
        this.start = start;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<CommentTagsBean> getTags() {
        return tags;
    }

    public void setTags(List<CommentTagsBean> tags) {
        this.tags = tags;
    }

    public List<EvaluateBean> getAll() {
        return all;
    }

    public void setAll(List<EvaluateBean> all) {
        this.all = all;
    }

    public List<EvaluateBean> getNewX() {
        return newX;
    }

    public void setNewX(List<EvaluateBean> newX) {
        this.newX = newX;
    }

    public List<EvaluateBean> getHaveimg() {
        return haveimg;
    }

    public void setHaveimg(List<EvaluateBean> haveimg) {
        this.haveimg = haveimg;
    }
}
