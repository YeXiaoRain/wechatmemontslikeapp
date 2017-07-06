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
    private Bitmap uimage = null;

    public UserEach(int id, String n, Bitmap bm) {
        uid = id;
        uname = n;
        imagepath = "";
        uimage = bm;
    }

    public UserEach(int id, String n, String ip) {
        uid = id;
        uname = n;
        imagepath = ip;
        File file = new File(imagepath);
        if (file.exists()) {
            uimage = BitmapFactory.decodeFile(imagepath);
        }
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

    public Bitmap getUimage() {
        return uimage;
    }

    public void setUimage(Bitmap uimage) {
        this.uimage = uimage;
    }
}
