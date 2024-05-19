package com.www446.haveagoodday.model;

import java.util.ArrayList;

/**
 * 新闻的数据model
 */
public class NewsModel {

    private String msg;
    private int code;
    private DataList data;

    public NewsModel(String msg, int code, DataList data) {
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

    public ArrayList<NewsDetail> getList() {
        return data.list;
    }


    static class DataList {
        ArrayList<NewsDetail> list;

        String channel;

        int num;
    }
}
