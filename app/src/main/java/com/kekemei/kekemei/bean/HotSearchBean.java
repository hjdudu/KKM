package com.kekemei.kekemei.bean;

import java.io.Serializable;
import java.util.List;

public class HotSearchBean implements Serializable {
    private List<String> host;
    private List<String> history;

    public List<String> getHost() {
        return host;
    }

    public void setHost(List<String> host) {
        this.host = host;
    }

    public List<String> getHistory() {
        return history;
    }

    public void setHistory(List<String> history) {
        this.history = history;
    }
}
