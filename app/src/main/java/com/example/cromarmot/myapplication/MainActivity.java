package com.example.cromarmot.myapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cromarmot.myapplication.Data.*;
import com.example.cromarmot.myapplication.View.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements FcScrollerListener {
    public static final int CURRENTUSERID = 0;

    private int lastget = 0;

    private List<fc_post> mData = null;
    private Map<Integer,User> mUsermap = null;

    private Context mContext;
    private ScollerFreshableListView list_fc;

    private FcAdapter mAdapter = null;
    static public LikeCommentPopWindow lcpw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = MainActivity.this;
        DataRequest.setContext(mContext);
        lcpw = new LikeCommentPopWindow(mContext);

        list_fc = (ScollerFreshableListView) findViewById(R.id.fc_displaylist);

        mData =  new LinkedList<fc_post>();
        PostDataManager.SetPosts(mData);

        mUsermap = new HashMap<>();
        PostDataManager.SetUsers(mUsermap);

        mAdapter = new FcAdapter((LinkedList<fc_post>) mData, mContext,mUsermap);
        PostDataManager.SetAdapter(mAdapter);

        LinkedList<fc_post> newpostdata= DataRequest.getPostListFrom(lastget++);
        mData.addAll(newpostdata);
        mUsermap.put(MainActivity.CURRENTUSERID, DataRequest.getUser(MainActivity.CURRENTUSERID));
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
                LinkedList<fc_post> newdatagroup = DataRequest.getPostListFrom(lastget++)  ;
                if(newdatagroup!=null)
                    mData.addAll(newdatagroup );
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                mAdapter.notifyDataSetChanged();
                list_fc.hideFooterView();
            }
        }.execute(new Void[]{});
    }
}
