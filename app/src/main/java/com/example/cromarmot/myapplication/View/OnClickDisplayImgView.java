package com.example.cromarmot.myapplication.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by cromarmot on 17-7-4.
 */
public class OnClickDisplayImgView extends ImageView implements View.OnClickListener {
    private int imgid = -1;
    private Bitmap largeimg = null;
    ImagePopupWindow ipw;

    public OnClickDisplayImgView(Context context) {
        super(context);
        init();
    }

    public OnClickDisplayImgView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public OnClickDisplayImgView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        setOnClickListener(this);
    }

    public void setIPW(ImagePopupWindow ipw){
        this.ipw = ipw;
    }

    @Override
    public void onClick(View v) {
        if(largeimg==null)
            System.out.println("OnClickDisplayImgView : NO LARGE IMAGE");//TODO try get largeimg by imgid;
        if(largeimg!=null) {
            ipw.show(largeimg);
        }
    }

    public void setLargeimg(Bitmap li){
        largeimg=li;
    }

    public void setImgid(int ii){
        imgid=ii;
    }
}
