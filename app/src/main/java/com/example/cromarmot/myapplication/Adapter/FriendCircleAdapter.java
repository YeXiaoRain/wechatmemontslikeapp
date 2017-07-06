package com.example.cromarmot.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cromarmot.myapplication.Data.CommentEach;
import com.example.cromarmot.myapplication.Data.PostEach;
import com.example.cromarmot.myapplication.Data.PostDataManager;
import com.example.cromarmot.myapplication.R;
import com.example.cromarmot.myapplication.Data.UserEach;
import com.example.cromarmot.myapplication.ShareActivity;
import com.example.cromarmot.myapplication.View.CommentEachView;
import com.example.cromarmot.myapplication.View.CommentPopupWindow;
import com.example.cromarmot.myapplication.View.DeletePopupWindow;
import com.example.cromarmot.myapplication.View.ImagePopupWindow;
import com.example.cromarmot.myapplication.View.LikeCommentPopupWindow;
import com.example.cromarmot.myapplication.View.OnClickDisplayImgView;
import com.example.cromarmot.myapplication.View.WrapViewGroup;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by cromarmot on 17-7-5.
 */
public class FriendCircleAdapter extends BaseAdapter{

    private LinkedList<PostEach> mPostData;
    private Context mContext;
    private Map<Integer, UserEach> mUsermap ;
    private ImagePopupWindow ipw;
    private LikeCommentPopupWindow lcpw;
    private CommentPopupWindow cpw;
    private DeletePopupWindow dpw;

    public FriendCircleAdapter(LinkedList<PostEach> md, Context mc, Map<Integer, UserEach> mu,
                               ImagePopupWindow imgpw,
                               LikeCommentPopupWindow likeCommentPopupWindow,
                               CommentPopupWindow commentPopupWindow,
                               DeletePopupWindow deletePopupWindow){
        mPostData = md;
        mContext = mc;
        mUsermap = mu;
        ipw = imgpw;
        lcpw = likeCommentPopupWindow;
        cpw = commentPopupWindow;
        dpw = deletePopupWindow;
    }

    @Override
    public int getCount() {
        return mPostData.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.posteach_item,viewGroup,false);
        }
        view.setTag(new Integer(i));

        ImageView img_avatar = (ImageView) view.findViewById(R.id.avatar);
        TextView txt_uname = (TextView) view.findViewById(R.id.username);
        TextView txt_pdetail = (TextView) view.findViewById(R.id.postdetail);
        TextView txt_ptime = (TextView) view.findViewById(R.id.time);
        TextView txt_pinfo = (TextView) view.findViewById(R.id.info);

        OnClickDisplayImgView img_pimage = (OnClickDisplayImgView) view.findViewById(R.id.singleimg);
        GridLayout layout_imggroup = (GridLayout) view.findViewById(R.id.post_imagegroup);
        WrapViewGroup wrapview_likes = (WrapViewGroup) view.findViewById(R.id.likes_usergroup);
        ImageView img_landcview = (ImageView) view.findViewById(R.id.like_and_comment_button);
        LinearLayout llayout_comment = (LinearLayout) view.findViewById(R.id.comments_view);
        RelativeLayout rl = (RelativeLayout)view.findViewById(R.id.sharebox);
        TextView tv_shareurl = (TextView)view.findViewById(R.id.sharebox_url);


        PostEach correspondingdata = mPostData.get(i);

        UserEach u = mUsermap.get(new Integer(correspondingdata.getUid()));

        img_avatar.setImageBitmap(u.getUimage());
        txt_uname.setText(u.getUname());

        String da = correspondingdata.getData();
        if(da!=null && !da.equals("")) {
            txt_pdetail.setText(correspondingdata.getData());
            txt_pdetail.setVisibility(View.VISIBLE);
        }else{
            txt_pdetail.setVisibility(View.GONE);
        }

        txt_ptime.setText(correspondingdata.getDate());
        txt_pinfo.setText(correspondingdata.getSpecial());

        Bitmap [] imgs = correspondingdata.getImages();
        if(imgs==null || imgs.length==0){
            img_pimage.setVisibility(View.GONE);
            layout_imggroup.setVisibility(View.GONE);
        }else if(imgs.length==1){
            img_pimage.setVisibility(View.VISIBLE);
            layout_imggroup.setVisibility(View.GONE);
            img_pimage.setImageBitmap(imgs[0]);
            img_pimage.setLargeimg(imgs[0]);
            img_pimage.setIPW(ipw);
        }else{
            img_pimage.setVisibility(View.GONE);
            layout_imggroup.setVisibility(View.VISIBLE);
            OnClickDisplayImgView [] imgviews={
                    (OnClickDisplayImgView) layout_imggroup.findViewById(R.id.imgmul0),
                    (OnClickDisplayImgView) layout_imggroup.findViewById(R.id.imgmul1),
                    (OnClickDisplayImgView) layout_imggroup.findViewById(R.id.imgmul2),
                    (OnClickDisplayImgView) layout_imggroup.findViewById(R.id.imgmul3),
                    (OnClickDisplayImgView) layout_imggroup.findViewById(R.id.imgmul4),
                    (OnClickDisplayImgView) layout_imggroup.findViewById(R.id.imgmul5),
                    (OnClickDisplayImgView) layout_imggroup.findViewById(R.id.imgmul6),
                    (OnClickDisplayImgView) layout_imggroup.findViewById(R.id.imgmul7),
                    (OnClickDisplayImgView) layout_imggroup.findViewById(R.id.imgmul8),
            };
            int j,maxj=imgs.length;
            for(j=0;j<9;j++){
                imgviews[j].setIPW(ipw);
            }
            for(j=0;j<maxj;j++){
                imgviews[j].setVisibility(View.VISIBLE);
                imgviews[j].setImageBitmap(imgs[j]);
                imgviews[j].setLargeimg(imgs[j]);
            }
            for(;j<9;j++) {
                imgviews[j].setVisibility(View.GONE);
            }
        }

        img_landcview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostEach.currentPostIndex = (Integer) ((LinearLayout)v.getParent().getParent().getParent()).getTag();

                boolean isliked = PostDataManager.isLiked(PostDataManager.CURRENTUSERID, PostEach.currentPostIndex);

                int[] location = new int[2];
                v.getLocationOnScreen(location);
                lcpw.show(location,isliked);
            }
        });

        List<Integer> likes=correspondingdata.getLikes();
        if(likes.size()==0){
            wrapview_likes.setVisibility(View.GONE);
        }else{
            wrapview_likes.setVisibility(View.VISIBLE);
            int j,maxj=likes.size();
            wrapview_likes.removeAllViews();
            for(j=0;j<maxj;j++){
                TextView tv = new TextView(mContext);
                tv.setText((mUsermap.get(likes.get(j))).getUname());
                wrapview_likes.addView(tv);
            }
        }

        List<CommentEach> comments = correspondingdata.getComments();
        int j,maxj=comments.size();
        llayout_comment.removeAllViews();
        for (j=0;j<maxj;j++){
            CommentEach tmp_comment = comments.get(j);
            CommentEachView cev=new CommentEachView(mContext);
            cev.init(tmp_comment.getFromid() == PostDataManager.CURRENTUSERID ,i,j,cpw,dpw);
            cev.setText(PostDataManager.uid2uname(tmp_comment.getFromid())
                    +" write to "
                    +PostDataManager.uid2uname(tmp_comment.getToid())
                    +" : "
                    +tmp_comment.getData());
            llayout_comment.addView(cev);
        }

        String shareurl = correspondingdata.getShareUrl();
        if(shareurl != null && !shareurl.equals("")){
            rl.setVisibility(View.VISIBLE);
            tv_shareurl.setText(shareurl);
            rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String su = ((TextView)v.findViewById(R.id.sharebox_url)).getText().toString();
                    Intent intent =new Intent(mContext,ShareActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("ShareUrl", su);
                    intent.putExtras(bundle);
                    ((AppCompatActivity) mContext).startActivityForResult(intent,0);
                }
            });

        }else{
            rl.setVisibility(View.GONE);
        }
        return view;
    }
}
