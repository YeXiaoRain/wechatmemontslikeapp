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
 * Created by cromarmot on 17-7-6.
 */
public class DeletePopupWindow extends PopupWindow {
    private LayoutInflater mInflater;
    private View mContentView;
    private PopupWindow myself;
    private int postindex;
    private int commentsindex;

    public DeletePopupWindow(Context context) {
        super(context);
        this.myself = this;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        mContentView = mInflater.inflate(R.layout.delete_popup,null);
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

        Button bt = (Button)mContentView.findViewById(R.id.delete_comment_button);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myself.dismiss();
                PostDataManager.deleteComments(postindex,commentsindex);
            }
        });
    }

    public void show(int [] location,int pindex,int cindex){
        postindex=pindex;
        commentsindex=cindex;
        TextView tv= (TextView) mContentView.findViewById(R.id.comment_inputbox);
        this.showAtLocation(mContentView,Gravity.LEFT|Gravity.TOP,location[0],location[1]);
    }
}
