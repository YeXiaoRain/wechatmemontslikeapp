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

import com.example.cromarmot.myapplication.Data.fc_post;
import com.example.cromarmot.myapplication.MainActivity;
import com.example.cromarmot.myapplication.Data.PostDataManager;
import com.example.cromarmot.myapplication.R;

/**
 * Created by cromarmot on 17-7-5.
 */
public class LikeCommentPopWindow extends PopupWindow {
    Context mContext;
    private LayoutInflater mInflater;
    private View mContentView;

    public LikeCommentPopWindow(Context context) {
        super(context);
        this.mContext=context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        mContentView = mInflater.inflate(R.layout.likeandcomment,null);
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


        TextView txt_like = (TextView) mContentView.findViewById(R.id.lac_like);
        TextView txt_unlike = (TextView) mContentView.findViewById(R.id.lac_unlike);
        TextView txt_comment = (TextView) mContentView.findViewById(R.id.lac_comment);

        txt_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.lcpw.dismiss();
                if(fc_post.currentPostIndex!=-1){
                    PostDataManager.likes(MainActivity.CURRENTUSERID,fc_post.currentPostIndex);
                }
            }
        });

        txt_unlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.lcpw.dismiss();
                if(fc_post.currentPostIndex!=-1){
                    PostDataManager.unlike(MainActivity.CURRENTUSERID,fc_post.currentPostIndex);
                }
            }
        });

        txt_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.lcpw.dismiss();
                if(fc_post.currentPostIndex!=-1){
                    CommentPopupWindow cpw=new CommentPopupWindow(mContext);
                    cpw.show(fc_post.currentPostIndex, PostDataManager.getPostByIndex(fc_post.currentPostIndex).getUid());
                }
            }
        });

    }

    public void show(){
        this.showAtLocation(mContentView, Gravity.CENTER, 0, 0);
    }

    public void show(int []location,boolean likeState){
        TextView tv_like=(TextView) mContentView.findViewById(R.id.lac_like);
        TextView tv_unlike=(TextView) mContentView.findViewById(R.id.lac_unlike);
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
