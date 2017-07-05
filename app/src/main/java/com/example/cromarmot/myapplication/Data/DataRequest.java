package com.example.cromarmot.myapplication.Data;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.cromarmot.myapplication.R;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cromarmot on 17-7-4.
 */
public class DataRequest {
    static Context mContext;
    public static void setContext(Context mc){
        mContext=mc;
    }
    // TODO add json to javadatastructure
    public static LinkedList<fc_post> getPostListFrom(int date){
        switch (date){
            case 0:{
                LinkedList<fc_post> mData = new LinkedList<fc_post>();

                mData.add(new fc_post(1, 12,null,"post data here","1 hour ago","",new ArrayList<Integer>(){{add(4);add(2);add(3);add(5);add(6);add(7);add(8);}},null));

                Bitmap[] bm_picsingle;
                bm_picsingle=new Bitmap[1];
                bm_picsingle[0]= BitmapFactory.decodeResource(mContext.getResources(), R.drawable.p1);
                mData.add(new fc_post(2, 22,bm_picsingle,"demotextdemotextdemotextdemotextdemotextdemotext","2 hours ago","",null,null));

                Bitmap [] bm_picmul;
                bm_picmul=new Bitmap[5];
                bm_picmul[0]=BitmapFactory.decodeResource(mContext.getResources(), R.drawable.p1);
                bm_picmul[1]=BitmapFactory.decodeResource(mContext.getResources(), R.drawable.p1);
                bm_picmul[2]=BitmapFactory.decodeResource(mContext.getResources(), R.drawable.p1);
                bm_picmul[3]=BitmapFactory.decodeResource(mContext.getResources(), R.drawable.p1);
                bm_picmul[4]=BitmapFactory.decodeResource(mContext.getResources(), R.drawable.p2);
                mData.add(new fc_post(3, 32,bm_picmul,"neirongneirongneirongneirongneirongneirongneirong","10:20年","from alipay",new ArrayList<Integer>(){{add(3);add(5);add(7);}},null));

                List<fc_comment> democomment = new ArrayList<>();
                democomment.add(new fc_comment(2,3,"hello"));
                democomment.add(new fc_comment(3,2,"thank you"));
                democomment.add(new fc_comment(4,3,"thank you very much ! !! ! !! ! !!!"));
                mData.add(new fc_post(4, 42,null,"demotextdemotext","3 hours ago","",null,democomment));

                mData.add(new fc_post(5, 52,null,"demotextdemotextdemotextdemotextdemotext","3 hours ago","",null,null));
                mData.add(new fc_post(6, 62,null,"demotextdemotextdemotextdemotextdemotextdemotextdemotextdemotextdemotextdemotext","3 hours ago","",null,null));
                mData.add(new fc_post(7, 72,null,"demotextdemotextdemotextdemotextdemotextdemotextdemotextdemotextdemotextdemotextdemotext","3 hours ago","from firefox",null,null));
                return mData;
            }
            case 1: {
                LinkedList<fc_post> mData = new LinkedList<fc_post>();
                mData.add(new fc_post(1, 221, null, "新增2-1", "5hours ago", "", null, null));
                mData.add(new fc_post(2, 222, null, "新增2-2", "5hours ago", "from chrome", null, null));
                mData.add(new fc_post(3, 223, null, "新增2-3", "5hours ago", "", null, null));
                mData.add(new fc_post(4, 224, null, "新增2-4", "5hours ago", "", null, null));
                mData.add(new fc_post(5, 225, null, "新增2-5", "5hours ago", "", null, null));
                mData.add(new fc_post(6, 226, null, "新增2-6", "5hours ago", "", null, null));
                return mData;
            }
            case 2: {
                LinkedList<fc_post> mData = new LinkedList<fc_post>();
                mData.add(new fc_post(1, 331, null, "3新增-1", "5hours ago", "", null, null));
                mData.add(new fc_post(2, 332, null, "3新增-2", "5hours ago年", "", null, null));
                mData.add(new fc_post(3, 333, null, "3新增-3", "5hours ago", "from qq", null, null));
                mData.add(new fc_post(4, 334, null, "3新增-4", "5hours ago", "", null, null));
                mData.add(new fc_post(5, 335, null, "3新增-5", "5hours ago", "from wx", null, null));
                mData.add(new fc_post(6, 336, null, "3新增-6", "5hours ago", "", null, null));
                return mData;
            }
            default:
            return null;
        }
    }

    public static User getUser(int uid){
        switch (uid){
            case 0:
                return new User(0,"MYSELF",BitmapFactory.decodeResource(mContext.getResources(), R.drawable.p1));
            case 1:
                return new User(1,"user1",BitmapFactory.decodeResource(mContext.getResources(), R.drawable.p1));
            case 2:
                return new User(2,"user2",BitmapFactory.decodeResource(mContext.getResources(), R.drawable.p2));
            case 3:
                return new User(3,"user3",BitmapFactory.decodeResource(mContext.getResources(), R.drawable.p3));
            case 4:
                return new User(4,"user4","picurl4");
            case 5:
                return new User(5,"user5","picurl5");
            case 6:
                return new User(6,"user6","picurl6");
            case 7:
                return new User(7,"user7","picurl7");
            case 8:
                return new User(8,"user8","picurl8");
        }
        return null;
    }

}
