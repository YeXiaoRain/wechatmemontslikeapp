package com.example.cromarmot.myapplication.Data;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;

/**
 * Created by cromarmot on 17-7-4.
 */
public class UserEach {
    private int uid;
    private String uname;
    private String imagepath;

    public UserEach(int id, String n, String ip) {
        uid = id;
        uname = n;
        imagepath = ip;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

}
