package com.example.cromarmot.myapplication.Data;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cromarmot on 17-7-5.
 */
public class PostEach {
    private int uid;
    private int upostid;
    private Bitmap [] images ;
    private String data;
    private String date;
    private String special;
    private List<Integer> likes;
    private List<CommentEach> comments;

    static public Integer currentPostIndex = -1;

    public PostEach(int id, int upid, Bitmap [] ims, String da, String de, String sp, List<Integer> l, List<CommentEach> c){
        uid=id;
        upostid = upid;
        images = ims;
        data = da;
        date = de;
        special = sp;
        likes = l==null ?  new ArrayList<Integer>():l;
        comments = c==null ? new ArrayList<CommentEach>():c;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getUpostid() {
        return upostid;
    }

    public void setUpostid(int upostid) {
        this.upostid = upostid;
    }

    public Bitmap[] getImages() {
        return images;
    }

    public void setImages(Bitmap[] images) {
        this.images = images;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public List<Integer> getLikes() {
        return likes;
    }

    public List<CommentEach> getComments() {
        return comments;
    }

    public void setComments(List<CommentEach> comments) {
        this.comments = comments;
    }
}
