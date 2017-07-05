package com.example.cromarmot.myapplication.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.example.cromarmot.myapplication.Data.PostDataManager;

/**
 * Created by cromarmot on 17-7-6.
 */
public class CommentsEachView extends TextView  implements View.OnClickListener,View.OnLongClickListener{
    private boolean isCurrentUserPost;
    private int postIndex;
    private int commentsIndex;
    private Context mContext;

    public CommentsEachView(Context context) {
        super(context);
        mContext=context;
    }

    public CommentsEachView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
    }

    public CommentsEachView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
    }

    public CommentsEachView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext=context;
    }

    public void init(Boolean icup,int pi,int ci){
        isCurrentUserPost = icup;
        postIndex = pi;
        commentsIndex = ci;
        setListener();
    }

    private void setListener(){
        if(isCurrentUserPost)
            this.setOnLongClickListener(this);
        else
            this.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        System.out.println("CommentsEachView on click");
        System.out.println(postIndex);
        System.out.println(PostDataManager.getPostByIndex(postIndex).getComments().get(commentsIndex).getFromid());

        CommentPopupWindow cpw=new CommentPopupWindow(mContext);
        cpw.show(postIndex, PostDataManager.getPostByIndex(postIndex).getComments().get(commentsIndex).getFromid());
    }

    @Override
    public boolean onLongClick(View v) {
        System.out.println("CommentsEachView on Long click");
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        DeletePopupWindow dpw=new DeletePopupWindow(mContext);
        dpw.show(location,postIndex,commentsIndex);
        return true;
    }
}
