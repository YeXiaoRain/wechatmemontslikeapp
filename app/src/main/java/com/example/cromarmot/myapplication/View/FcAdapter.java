package com.example.cromarmot.myapplication.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cromarmot.myapplication.Data.fc_comment;
import com.example.cromarmot.myapplication.Data.fc_post;
import com.example.cromarmot.myapplication.MainActivity;
import com.example.cromarmot.myapplication.Data.PostDataManager;
import com.example.cromarmot.myapplication.R;
import com.example.cromarmot.myapplication.Data.User;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by cromarmot on 17-7-5.
 */
public class FcAdapter  extends BaseAdapter{

    private LinkedList<fc_post> mPostData;
    private Context mContext;
    private Map<Integer, User> mUsermap ;

    public FcAdapter(LinkedList<fc_post> md,Context mc,Map<Integer, User> mu){
        mPostData =md;
        mContext=mc;
        mUsermap=mu;
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
            view = LayoutInflater.from(mContext).inflate(R.layout.item,viewGroup,false);
        }
        view.setTag(new Integer(i));

        ImageView img_avatar = (ImageView) view.findViewById(R.id.Avatar);
        TextView txt_uname = (TextView) view.findViewById(R.id.username);
        TextView txt_pdetail = (TextView) view.findViewById(R.id.postdetail);
        TextView txt_ptime = (TextView) view.findViewById(R.id.time);
        TextView txt_pinfo = (TextView) view.findViewById(R.id.info);

        OnClickDisplayImgView img_pimage = (OnClickDisplayImgView) view.findViewById(R.id.singleimg);
        GridLayout layout_imggroup = (GridLayout) view.findViewById(R.id.fc_imggroup);
        WrapViewGroup wrapview_likes = (WrapViewGroup) view.findViewById(R.id.likesview);
        ImageView img_landcview = (ImageView) view.findViewById(R.id.landcview);
        LinearLayout llayout_comment = (LinearLayout) view.findViewById(R.id.commentview);

        //TODO comments

        fc_post correspondingdata = mPostData.get(i);

        User u = mUsermap.get(new Integer(correspondingdata.getUid()));

        img_avatar.setImageBitmap(u.getUimage());
        txt_uname.setText(u.getUname());

        txt_pdetail.setText(correspondingdata.getData());
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
                fc_post.currentPostIndex = (Integer) ((LinearLayout)v.getParent().getParent().getParent()).getTag();

                boolean isliked = PostDataManager.isLiked(MainActivity.CURRENTUSERID,fc_post.currentPostIndex);

                int[] location = new int[2];
                v.getLocationOnScreen(location);
                MainActivity.lcpw.show(location,isliked);
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

        List<fc_comment> comments = correspondingdata.getComments();
        int j,maxj=comments.size();
        llayout_comment.removeAllViews();
        for (j=0;j<maxj;j++){
            fc_comment tmp_comment = comments.get(j);
            CommentsEachView cev=new CommentsEachView(mContext);
            cev.init(tmp_comment.getFromid() == MainActivity.CURRENTUSERID ,i,j);
            cev.setText(PostDataManager.uid2uname(tmp_comment.getFromid())
                    +" write to "
                    +PostDataManager.uid2uname(tmp_comment.getToid())
                    +":"
                    +tmp_comment.getData());
            llayout_comment.addView(cev);
        }

        return view;
    }
}
