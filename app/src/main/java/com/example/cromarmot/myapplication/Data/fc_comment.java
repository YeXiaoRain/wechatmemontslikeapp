package com.example.cromarmot.myapplication.Data;

/**
 * Created by cromarmot on 17-7-5.
 */
public class fc_comment {
    private int fromid;
    private int toid;
    private String data;

    public fc_comment(int f,int t,String d){
        fromid=f;
        toid=t;
        data=d;
    }

    public int getFromid() {
        return fromid;
    }

    public void setFromid(int fromid) {
        this.fromid = fromid;
    }

    public int getToid() {
        return toid;
    }

    public void setToid(int toid) {
        this.toid = toid;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
