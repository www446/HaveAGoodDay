package com.www446.haveagoodday.model;

import java.util.ArrayList;

/**
 * 频道的数据model
 */
public class ChannelModel {

    private String msg;
    private int code;
    private DataList data;

    public ChannelModel(String msg, int code, DataList data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public DataList getData() {
        return data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public ArrayList<String> getList(){
        return data.list;
    }


    static class DataList{
        ArrayList<String> list;
    }
}
