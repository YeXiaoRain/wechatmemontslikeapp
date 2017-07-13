package com.example.cromarmot.myapplication.View;

/**
 * Created by cromarmot on 17-7-13.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.example.cromarmot.myapplication.Data.ImgManager;
import com.example.cromarmot.myapplication.Data.PostDataManager;

import java.io.BufferedInputStream;
import java.io.InputStream;

public class NetServiceTask extends AsyncTask<String,Integer,Bitmap>{
    private final String address;
    private final URLPostHandler urlPostHandler;
    public NetServiceTask(String address,URLPostHandler urlPostHandler) {
        this.address = address;
        this.urlPostHandler = urlPostHandler;
    }
    @Override
    protected Bitmap doInBackground(String... arg0) {
        InputStream inputStream=NetService.getInputStreamByUrl(arg0[0]);
        if(inputStream!=null){
            return BitmapFactory.decodeStream(new BufferedInputStream(inputStream));
        }
        return null;
    }
    @Override
    protected void onPostExecute(Bitmap result) {
        super.onPostExecute(result);
        if(this.urlPostHandler != null ){// && result!=null){
            this.urlPostHandler.PostHandler(result);
            ImgManager.mapUrlBitmap(address,result);
            PostDataManager.successGotImage();
        }
    }
    public void run() {
        execute(this.address);
    }
}
