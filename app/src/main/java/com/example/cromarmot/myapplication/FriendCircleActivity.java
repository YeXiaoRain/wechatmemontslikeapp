package com.example.cromarmot.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cromarmot.myapplication.Adapter.FriendCircleAdapter;
import com.example.cromarmot.myapplication.Data.FakeDataRequest;
import com.example.cromarmot.myapplication.Data.PostDataManager;
import com.example.cromarmot.myapplication.Data.PostEach;
import com.example.cromarmot.myapplication.Data.UserEach;
import com.example.cromarmot.myapplication.View.FcScrollerListener;
import com.example.cromarmot.myapplication.View.PopupWindowManager;
import com.example.cromarmot.myapplication.View.ScollerFreshableListView;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FriendCircleActivity extends AppCompatActivity implements FcScrollerListener {

    private int lastget = 0;

    private List<PostEach> mData = null;
    private Map<Integer,UserEach> mUsermap = null;

    private Context mContext;

    private ScollerFreshableListView list_fc;
    private FriendCircleAdapter mAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = FriendCircleActivity.this;

        FakeDataRequest.setContext(mContext);
        PopupWindowManager.init(mContext);

        list_fc = (ScollerFreshableListView) findViewById(R.id.friendcircle_displaylist);

        mData =  new LinkedList<PostEach>();
        PostDataManager.SetPosts(mData);

        mUsermap = new HashMap<>();
        PostDataManager.SetUsers(mUsermap);

        mAdapter = new FriendCircleAdapter((LinkedList<PostEach>) mData, mContext,mUsermap);
        PostDataManager.SetAdapter(mAdapter);

        List<PostEach> newpostdata= FakeDataRequest.getPostListFrom(lastget++);

        mData.addAll(newpostdata);
        mUsermap.put(PostDataManager.CURRENTUSERID, FakeDataRequest.getUser(PostDataManager.CURRENTUSERID));
        PostDataManager.autoUpdateUsersByNewPost(newpostdata);

        list_fc.setAdapter(mAdapter);
        list_fc.setOnRefreshListener(this);
    }

    @Override
    public void onLoadingMore() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                SystemClock.sleep(1000);
                List<PostEach> newdatagroup = FakeDataRequest.getPostListFrom(lastget++)  ;
                if(newdatagroup!=null)
                    mData.addAll(newdatagroup);
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                mAdapter.notifyDataSetChanged();
                list_fc.hideFooterView();
            }
        }.execute(new Void[]{});
    }

    @Override
    protected void onActivityResult( int requestCode, int resultCode, Intent data )
    {
        switch ( resultCode ) {
            case RESULT_OK :
                String shareUrl = data.getExtras().getString("ShareUrl");
                if(shareUrl != null){
                    PostDataManager.shareWebSite(shareUrl);
                }
                break;
            default :
                break;
        }

    }
}
