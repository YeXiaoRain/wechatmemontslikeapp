package com.example.cromarmot.myapplication.Data;

import android.graphics.Bitmap;

import com.example.cromarmot.myapplication.View.NetServiceTask;
import com.example.cromarmot.myapplication.View.URLPostHandler;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by cromarmot on 17-7-13.
 */
public class ImgManager {
    static private Map<String,Bitmap> url2bitmap = new HashMap<>();

    // TODO LOCK
    static public Bitmap getBitmapByUrl(String url){
        if(url == null)
            return null;
        if(url2bitmap.containsKey(url)){
            return url2bitmap.get(url);
        }else{
            url2bitmap.put(url,null);//Reduce data race
            NetServiceTask netServerTask= new NetServiceTask(url,new URLPostHandler() {
                @Override
                public void PostHandler(Bitmap bitmap) {
                }
            });
            netServerTask.run();
            return null;
        }
    }
    static public void mapUrlBitmap(String url,Bitmap bitmap){
        url2bitmap.put(url,bitmap);
    }
}
