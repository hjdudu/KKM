package com.kekemei.kekemei.bean;

import java.io.Serializable;

public class WaiterBean implements Serializable {
    /**
     * id : 2
     * nickname : 13269501725
     */

    private String id;
    private String nickname;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
