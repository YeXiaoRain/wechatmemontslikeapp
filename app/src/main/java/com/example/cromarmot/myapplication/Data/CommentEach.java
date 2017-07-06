package com.example.cromarmot.myapplication.Data;

/**
 * Created by cromarmot on 17-7-5.
 */
public class CommentEach {
    private int fromid;
    private int toid;
    private String data;

    public CommentEach(int f, int t, String d){
        fromid=f;
        toid=t;
        data=d;
    }

    public int getFromid() {
        return fromid;
    }

    public int getToid() {
        return toid;
    }

    public String getData() {
        return data;
    }

}
