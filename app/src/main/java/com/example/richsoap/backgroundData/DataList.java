package com.example.richsoap.backgroundData;

import java.util.List;
import java.util.UUID;

/**
 * Created by richsoap on 18-3-13.
 */

public class DataList {
    public class TodoList {
        private	String title;
        private	String tag;
        private	String detail;
        private List<DataItem> DataItems;
        private UUID id;

        public void string2list(String putin) {
            String[] buffer = putin.split("\n");
            DataItem tempItem = new DataItem();
            boolean inItem = false;
            StringBuffer stringbuffer = new StringBuffer();
            for(int i = 0;i < buffer.length;i ++) {
                if(buffer[i].startsWith("# ")) {
                    title = buffer[i].substring(2);
                }
                else if(buffer[i].startsWith("/: ")) {
                    tag = buffer[i].substring(3);
                }
                else if(buffer[i].startsWith("## ")) {
                    if(inItem) {
                        tempItem.setDetail(stringbuffer.toString());
                        DataItems.add(tempItem);
                    }
                    else {
                        detail = stringbuffer.toString();
                    }
                    stringbuffer = new StringBuffer();
                    tempItem.clear();
                    tempItem.setFather(id);
                    inItem = true;
                }
                else if(buffer[i].startsWith("-[")) {
                    if(buffer[i].charAt(2) == 'x') {
                        tempItem.setDone(true);
                    }
                    else{
                        tempItem.setDone(false);
                    }
                }
                else {
                    stringbuffer.append(buffer[i]);
                    stringbuffer.append('\n');
                }
            }
            if(inItem) {
                tempItem.setDetail(stringbuffer.toString());
                DataItems.add(tempItem);
            }
        }
        String list2string() {
            StringBuffer buffer;
            return null;
        }

    }

}
