package com.example.cromarmot.myapplication.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.example.cromarmot.myapplication.R;

/**
 * Created by cromarmot on 17-7-5.
 */
public class ImagePopupWindow extends PopupWindow {

    private LayoutInflater mInflater;
    private View mContentView;

    public ImagePopupWindow(Context context) {
        super(context);

        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);

        mContentView = mInflater.inflate(R.layout.image_display,null);
        setContentView(mContentView);
        setBackgroundDrawable(new ColorDrawable());
        setFocusable(true);
        setOutsideTouchable(true);
        setTouchable(true);

        setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_OUTSIDE){

                    return true;
                }
                return false;
            }
        });


        ImageView iv = (ImageView)mContentView.findViewById(R.id.single_image_view);

        ((RelativeLayout) iv.getParent()).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupWindowManager.ipw.dismiss();
            }
        });
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupWindowManager.ipw.dismiss();
            }
        });
    }
    public void show(Bitmap bm){
        this.showAtLocation(mContentView, Gravity.CENTER, 0, 0);
        ImageView imageView = (ImageView) mContentView.findViewById(R.id.single_image_view);
        imageView.setImageBitmap(bm);
    }

}
