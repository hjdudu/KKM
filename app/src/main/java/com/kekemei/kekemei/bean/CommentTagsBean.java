package com.kekemei.kekemei.bean;

import java.io.Serializable;

public class CommentTagsBean implements Serializable{
    /**
     * id : 3
     * name : 美容师专业
     * state : 1
     * weigh : 0
     * tag_type : 1
     * state_text : 正常
     * tag_type_text : Tag_type 1
     */

    private int id;
    private String name;
    private String state;
    private int weigh;
    private String tag_type;
    private String state_text;
    private String tag_type_text;

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getWeigh() {
        return weigh;
    }

    public void setWeigh(int weigh) {
        this.weigh = weigh;
    }

    public String getTag_type() {
        return tag_type;
    }

    public void setTag_type(String tag_type) {
        this.tag_type = tag_type;
    }

    public String getState_text() {
        return state_text;
    }

    public void setState_text(String state_text) {
        this.state_text = state_text;
    }

    public String getTag_type_text() {
        return tag_type_text;
    }

    public void setTag_type_text(String tag_type_text) {
        this.tag_type_text = tag_type_text;
    }
}
