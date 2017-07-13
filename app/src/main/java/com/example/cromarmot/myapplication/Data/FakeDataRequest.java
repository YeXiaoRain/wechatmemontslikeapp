package com.example.cromarmot.myapplication.Data;

import android.content.Context;
import java.util.List;

/**
 * Created by cromarmot on 17-7-4.
 */
public class FakeDataRequest {
    static Context mContext;
    public static void setContext(Context mc){
        mContext=mc;
    }
    public static List<PostEach> getPostListFrom(int date){
        String parserString = fakeJSONprovider(date);
        System.out.println("parse String:" + parserString);
        return JSONparser.parsePosts(parserString);
    }

    public static UserEach getUser(int uid){
        return JSONparser.parseUser(fakeUserJSONprovider(uid));
    }

    private static String fakeUserJSONprovider(int uid){
        switch (uid){
            case 0:
                return "[0,\"MYSELF\",\"http://www.qqzhuangban.com/uploadfile/2014/08/1/20140817072951663.jpg\"]";
            case 1:
                return "[1,\"user1\",\"http://www.qqai.net/uploads/i_2_2427435142x3736431374_21.jpg\"]";
            case 2:
                return "[2,\"user1\",\"http://img3.imgtn.bdimg.com/it/u=4177629561,4019303243&fm=214&gp=0.jpg\"]";
            default:
                return "["+uid+",\"user"+uid+"\",\"\"]";
        }
    }

    private static String fakeJSONprovider(int date){
        switch (date) {
            case 0:
                return "[" +
                        "[1, 12,[],\"post data here\",\"1 hour ago\",\"\",[4,2,3,5,6,7,8],[],\"\"]" + "," +
                        "[2, 22, [\"http://pic.35pic.com/normal/07/89/45/11295670_190951512136_2.jpg\"], \"demotextdemotextdemotextdemotextdemotextdemotext\", \"2 hours ago\", \"\", [], [],\"\"]"+","+
                        "[3, 32, ["+
                            "\"http://img0.imgtn.bdimg.com/it/u=1707455155,3757910259&fm=26&gp=0.jpg\","+
                            "\"http://img1.imgtn.bdimg.com/it/u=1410013081,1524825774&fm=26&gp=0.jpg\","+
                            "\"http://img1.imgtn.bdimg.com/it/u=1884738033,3459235394&fm=26&gp=0.jpg\","+
                            "\"http://pic.58pic.com/58pic/13/71/05/32M58PICZKY_1024.jpg\","+
                            "\"http://img0.imgtn.bdimg.com/it/u=2460944166,2174425750&fm=26&gp=0.jpg\"],"+
                        "\"neirongneirongneirongneirongneirongneirongneirong\", \"10:20\", \"from alipay\", [3,5,7],[],\"\"]"+","+
                        "[4, 42, [],\"demotextdemotext\", \"3 hours ago\", \"\", [], ["+
                            "[2,3,\"hello\"],"+
                            "[3,2,\"thank you\"],"+
                            "[4,3,\"thank you very much !! !! !! !! !! !!\"]],\"\"]"+","+
                        "[5,52, [], \"demotextdemotextdemotextdemotextdemotext\", \"3 hours ago\", \"\", [],[],\"https://exxxxample\"]"+","+
                        "[6,62, [] , \"demotextdemotextdemotextdemotextdemotextdemotextdemotextdemotextdemotextdemotext\", \"3 hours ago\", \"\",[], [],\"\"]"+","+
                        "[7,72, [] , \"demotextdemotextdemotextdemotextdemotextdemotextdemotextdemotextdemotextdemotextdemotext\", \"3 hours ago\", \"from firefox\",[],[],\"\"]"+
               "]";

            case 1:
                return "[" +
                        "[1, 221, [] , \"新增2-1\", \"5hours ago\", \"\", [], [] , \"\"]"+ "," +
                        "[2, 222, [] , \"新增2-2\", \"5hours ago\", \"from chrome\", [], [], \"\"]"+ "," +
                        "[3, 223, [] , \"新增2-3\", \"5hours ago\", \"\", [], [], \"\"]"+ "," +
                        "[4, 224, [] , \"新增2-4\", \"5hours ago\", \"\", [], [], \"\"]"+ "," +
                        "[5, 225, [] , \"新增2-5\", \"5hours ago\", \"\", [], [], \"\"]"+ "," +
                        "[6, 226, [] , \"新增2-6\", \"5hours ago\", \"\", [], [], \"\"]"+
                    "]";
            case 2:
                return "[" +
                        "[1, 331, [] , \"3new-1\", \"5hours ago\", \"\", [], [],\"\"]"+ "," +
                        "[2, 332, [] , \"3new-2\", \"5hours ago\", \"\", [], [],\"\"]"+ "," +
                        "[3, 333, [] , \"3new-3\", \"5hours ago\", \"from qq\", [], [],\"\"]"+ "," +
                        "[4, 334, [] , \"3new-4\", \"5hours ago\", \"\", [], [],\"\"]"+ "," +
                        "[5, 335, [] , \"3new-5\", \"5hours ago\", \"from wx\", [], [],\"\"]"+ "," +
                        "[6, 336, [] , \"3new-6\", \"5hours ago\", \"\", [], [],\"\"]"+
                        "]";
        }
        return "[]";
    }
}
