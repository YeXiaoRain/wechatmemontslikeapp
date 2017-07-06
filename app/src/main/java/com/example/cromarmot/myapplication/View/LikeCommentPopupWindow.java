package com.example.cromarmot.myapplication.View;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.cromarmot.myapplication.Data.PostEach;
import com.example.cromarmot.myapplication.Data.PostDataManager;
import com.example.cromarmot.myapplication.R;

/**
 * Created by cromarmot on 17-7-5.
 */
public class LikeCommentPopupWindow extends PopupWindow {
    Context mContext;
    private LayoutInflater mInflater;
    private View mContentView;
    private PopupWindow self;
    private CommentPopupWindow cpw;

    public LikeCommentPopupWindow(Context context,CommentPopupWindow commentPopupWindow) {
        super(context);
        this.mContext=context;
        this.self=this;
        cpw=commentPopupWindow;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        mContentView = mInflater.inflate(R.layout.like_and_comment,null);
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


        TextView txt_like = (TextView) mContentView.findViewById(R.id.like_button);
        TextView txt_unlike = (TextView) mContentView.findViewById(R.id.unlike_button);
        TextView txt_comment = (TextView) mContentView.findViewById(R.id.comment_button);

        txt_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                self.dismiss();
                if(PostEach.currentPostIndex!=-1){
                    PostDataManager.likes(PostDataManager.CURRENTUSERID, PostEach.currentPostIndex);
                }
            }
        });

        txt_unlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                self.dismiss();
                if(PostEach.currentPostIndex!=-1){
                    PostDataManager.unlike(PostDataManager.CURRENTUSERID, PostEach.currentPostIndex);
                }
            }
        });

        txt_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                self.dismiss();
                if(PostEach.currentPostIndex!=-1){
                    cpw.show(PostEach.currentPostIndex, PostDataManager.getPostByIndex(PostEach.currentPostIndex).getUid());
                }
            }
        });

    }

    public void show(){
        this.showAtLocation(mContentView, Gravity.CENTER, 0, 0);
    }

    public void show(int []location,boolean likeState){
        TextView tv_like=(TextView) mContentView.findViewById(R.id.like_button);
        TextView tv_unlike=(TextView) mContentView.findViewById(R.id.unlike_button);
        if(likeState){
            tv_like.setVisibility(View.GONE);
            tv_unlike.setVisibility(View.VISIBLE);
        }else{
            tv_like.setVisibility(View.VISIBLE);
            tv_unlike.setVisibility(View.GONE);
        }

        this.showAtLocation(mContentView,Gravity.LEFT|Gravity.TOP,location[0],location[1]);
    }

}
