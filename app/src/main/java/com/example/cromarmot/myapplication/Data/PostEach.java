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
    private String shareUrl;

    static public Integer currentPostIndex = -1;

    // photos
    public PostEach(int id, int upid, Bitmap [] ims, String da, String de, String sp, List<Integer> l, List<CommentEach> c){
        this(id,upid,ims,da,de,sp,l,c,"");
    }

    // share
    public PostEach(int id, int upid, String shareurl, String da, String de, String sp, List<Integer> l, List<CommentEach> c){
        this(id,upid,null,da,de,sp,l,c,shareurl);
    }

    // text only
    public PostEach(int id, int upid, String da, String de, String sp, List<Integer> l, List<CommentEach> c){
        this(id,upid,null,da,de,sp,l,c,"");
    }

    private PostEach(int id, int upid, Bitmap [] ims, String da, String de, String sp, List<Integer> l, List<CommentEach> c,String su){
        uid=id;
        upostid = upid;
        images = ims;
        data = da;
        date = de;
        special = sp;
        likes = l==null ?  new ArrayList<Integer>():l;
        comments = c==null ? new ArrayList<CommentEach>():c;
        shareUrl = su;
    }

    public int getUid() {
        return uid;
    }
    public int getUpostid() {
        return upostid;
    }

    public Bitmap[] getImages() {
        return images;
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

    public List<Integer> getLikes() {
        return likes;
    }

    public List<CommentEach> getComments() {
        return comments;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public static Integer getCurrentPostIndex() {
        return currentPostIndex;
    }

    public static void setCurrentPostIndex(Integer currentPostIndex) {
        PostEach.currentPostIndex = currentPostIndex;
    }
}
