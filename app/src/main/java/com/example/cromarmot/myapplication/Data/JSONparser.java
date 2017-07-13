package com.example.cromarmot.myapplication.Data;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cromarmot on 17-7-13.
 */
public class JSONparser {
    public static List<PostEach> parsePosts(String json){
        LinkedList<PostEach> res = new LinkedList<>();
        try{
            JSONArray jsonArray = new JSONArray(json);
            for(int i = 0;i < jsonArray.length();i++){
                JSONArray jsonEach = jsonArray.getJSONArray(i);
                int uid = jsonEach.getInt(0);
                int postid = jsonEach.getInt(1);
                List <String> imagesurl = parseImgUrl(jsonEach.getString(2));
                String data = jsonEach.getString(3);
                String time = jsonEach.getString(4);
                String info = jsonEach.getString(5);
                List<Integer> likes = parseLikes(jsonEach.getString(6));
                List<CommentEach> comments = parseComments(jsonEach.getString(7));
                String shareUrl = jsonEach.getString(8);
                res.add(new PostEach(uid,postid,imagesurl,data,time,info,likes,comments,shareUrl));
            }
        }catch (Exception e){e.printStackTrace();}
        return res;
    }

    private  static List<String> parseImgUrl(String imgs){
        List<String> res = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(imgs);
            if(jsonArray.length() == 0)
                return res;
            for(int i = 0;i < jsonArray.length();i++){
                res.add(jsonArray.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return res;
    }

    private  static List<Integer> parseLikes(String likes){
        List<Integer> res = null;
        try {
            JSONArray jsonArray = new JSONArray(likes);
            if(jsonArray.length() == 0)
                return res;
            res = new ArrayList<>();
            for(int i = 0;i < jsonArray.length();i++){
                res.add(jsonArray.getInt(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return res;
    }

    private  static List<CommentEach> parseComments(String likes){
        List<CommentEach> res = null;
        try {
            JSONArray jsonArray = new JSONArray(likes);
            if(jsonArray.length() == 0)
                return res;
            res = new ArrayList<>();
            for(int i = 0;i < jsonArray.length();i++){
                JSONArray jsonEach = new JSONArray(jsonArray);
                res.add(new CommentEach(jsonEach.getInt(0),jsonEach.getInt(1),jsonEach.getString(2)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static UserEach parseUser(String user){
        UserEach res = null ;
        try {
            JSONArray jsonArray = new JSONArray(user);
            if(jsonArray.length() != 3)
                return null;
            int uid = jsonArray.getInt(0);
            String uname = jsonArray.getString(1);
            String uimgurl = jsonArray.getString(2);
            res = new UserEach(uid,uname,uimgurl);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return res;
    }

}
