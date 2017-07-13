package com.example.cromarmot.myapplication.View;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by cromarmot on 17-7-13.
 */
public class NetService {
    public static InputStream getInputStreamByUrl(String address){
        try {
            URL url = new URL(address);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(2 * 1000);
            urlConnection.setRequestMethod("GET");
            return urlConnection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
