package com.example.cromarmot.myapplication.Data;

import com.example.cromarmot.myapplication.Adapter.FriendCircleAdapter;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by cromarmot on 17-7-3.
 */
public class PostDataManager {
    public static final int CURRENTUSERID = 0; // should in another struct which record current user=.=
    static List<PostEach> mPostData;
    static Map<Integer,UserEach> mUsermap;
    static FriendCircleAdapter mAdapter;
    static public void SetUsers(Map<Integer,UserEach> mum){
        mUsermap = mum;
    }
    static public void SetPosts(List<PostEach> mpd){
        mPostData=mpd;
    }
    static public void SetAdapter(FriendCircleAdapter madp){
        mAdapter=madp;
    }
    
    //TODO add variable record state? 
    static public boolean isLiked(int uid,int postindex){
        PostEach post = getPostByIndex(postindex);
        List<Integer> likes= post.getLikes();
        for(int i=0,maxi=likes.size();i<maxi;i++){
            if(likes.get(i) == uid){
                return true;
            }
        }
        return false;
    }

    static public void likes(int uid,int postindex){
        PostEach post = getPostByIndex(postindex);
        List<Integer> likes= post.getLikes();
        for(int i=0,maxi=likes.size();i<maxi;i++){
            if(likes.get(i) == uid){
                return ;
            }
        }
        likes.add(new Integer(uid));
        mAdapter.notifyDataSetChanged();
    }

    static public void unlike(int uid,int postindex){
        PostEach post = getPostByIndex(postindex);
        List<Integer> likes= post.getLikes();
        for(int i=0,maxi=likes.size();i<maxi;i++){
            if(likes.get(i) == uid){
                likes.remove(i) ;
                mAdapter.notifyDataSetChanged();
                return;
            }
        }
    }

    static public void addComments(int postindex,int touserid,String data){
        PostEach post = getPostByIndex(postindex);
        List<CommentEach> comments =  post.getComments();
        comments.add(new CommentEach(CURRENTUSERID,touserid,data));
        mAdapter.notifyDataSetChanged();
    }

    static public void deleteComments(int postindex,int commentsindex){
        PostEach post = getPostByIndex(postindex);
        List<CommentEach> comments =  post.getComments();
        comments.remove(commentsindex);
        mAdapter.notifyDataSetChanged();
    }

    //TODO =.= in real , the list should be user's friend list
    static public void autoUpdateUsersByNewPost(List<PostEach> p){
        int i=0,maxi=p.size();
        for(;i<maxi;i++){
            Integer uid =  p.get(i).getUid();
            if(!mUsermap.containsKey(uid)) {
                mUsermap.put(uid, FakeDataRequest.getUser(uid));
            }
            List<Integer> likes =p.get(i).getLikes();
            for(int j=0,maxj=likes.size();j<maxj;j++){
                if(!mUsermap.containsKey(likes.get(j))) {
                    mUsermap.put(likes.get(j), FakeDataRequest.getUser(likes.get(j)));
                }
            }
            List<CommentEach> comments =p.get(i).getComments();
            for(int j=0,maxj=comments.size();j<maxj;j++){
                if(!mUsermap.containsKey(comments.get(j))) {
                    mUsermap.put(comments.get(j).getFromid(), FakeDataRequest.getUser(comments.get(j).getFromid()));
                    mUsermap.put(comments.get(j).getToid(), FakeDataRequest.getUser(comments.get(j).getToid()));
                }
            }
        }
    }

    static public PostEach getPostByIndex(int postindex){
        int i=0,maxi = mPostData.size();
        if(postindex >= 0 && postindex < maxi)
            return mPostData.get(postindex);
        return null;
    }
    //TODO speed up and redesign struct fit both search and display
    static  private PostEach getPostByID(int postid){
        int i=0,maxi=mPostData.size();
        for(;i<maxi;++i)
            if(mPostData.get(i).getUpostid()==postid)
                return mPostData.get(i);
        return null;
    }

    public static String uid2uname(int uid){
        assert (mUsermap.containsKey(uid));
        UserEach u = mUsermap.get(new Integer(uid));
        return u==null?"WRONG USER":u.getUname();
    }

    public static void shareWebSite(String url){
        assert (url!=null && !url.equals(""));
        mPostData.add(0,new PostEach(CURRENTUSERID, new Random().nextInt(),null,"","1 min ago",url,null,null,url));
        mAdapter.notifyDataSetChanged();
    }

    public static void successGotImage(){
        mAdapter.notifyDataSetChanged();
    }
}
