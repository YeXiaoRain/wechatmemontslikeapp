package com.example.cromarmot.myapplication.View;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.cromarmot.myapplication.Data.PostDataManager;
import com.example.cromarmot.myapplication.R;

/**
 * Created by cromarmot on 17-7-5.
 */

public class CommentPopupWindow extends PopupWindow {
    private Context mContext;
    private LayoutInflater mInflater;
    private View mContentView;
    private int touserid;
    private int postindex;
    private PopupWindow self;

    public CommentPopupWindow(Context context) {
        super(context);

        this.mContext=context;
        this.self = this;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        mContentView = mInflater.inflate(R.layout.bottom_inputbox_popup,null);
        setContentView(mContentView);
        setBackgroundDrawable(new ColorDrawable());
        setFocusable(true);
        setOutsideTouchable(true);
        setTouchable(true);
        setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_OUTSIDE){

                    return true;
                }
                return false;
            }
        });

        Button bt=(Button)mContentView.findViewById(R.id.add_comment_button);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv= (TextView) mContentView.findViewById(R.id.comment_inputbox);
                PostDataManager.addComments(postindex,touserid,tv.getText().toString());
                tv.setText("");
                self.dismiss();
            }
        });
    }
    public void show(int pi,int tui){
        postindex=pi;
        touserid=tui;
        TextView tv= (TextView) mContentView.findViewById(R.id.comment_inputbox);
        this.showAtLocation(mContentView , Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }
}
