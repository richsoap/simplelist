package com.example.richsoap.backgroundData;

import java.util.UUID;

/**
 * Created by richsoap on 18-3-13.
 */

public class DataItem {
    private String title;
    private String detail;
    private boolean done;
    private UUID father;

    public DataItem() {
        title = null;
        detail = null;
        done = false;
        father = null;
    }
    public String getTitle() {
        return title;
    }
    public String getDetail() {
        return detail;
    }
    public boolean getDone() {
        return done;
    }
    public UUID getFather() {
        return father;
    }
    public void setTitle(String _title) {
        title = _title;
    }
    public void setDetail(String _detail) {
        detail = _detail;
    }
    public void setDone(boolean _done) {
        done = _done;
    }
    public void setFather(UUID _father) {
        father = _father;
    }
    public void clear() {
        title = null;
        father = null;
        done = false;
    }

}
