package com.example.cromarmot.myapplication.View;

import android.content.Context;

/**
 * Created by cromarmot on 17-7-10.
 */
public class PopupWindowManager {
    static public ImagePopupWindow ipw;
    static public CommentPopupWindow cpw;
    static public DeletePopupWindow dpw;
    static public LikeCommentPopupWindow lcpw;

    static public void init(Context mContext){
        ipw = new ImagePopupWindow(mContext);
        cpw = new CommentPopupWindow(mContext);
        dpw = new DeletePopupWindow(mContext);
        lcpw = new LikeCommentPopupWindow(mContext,cpw);
    }
}
