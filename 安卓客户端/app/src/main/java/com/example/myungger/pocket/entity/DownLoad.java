package com.example.myungger.pocket.entity;

import android.util.Log;

import com.example.myungger.pocket.activity.ActivityShare;
import com.example.myungger.pocket.manager.NetTask;
import com.example.myungger.pocket.manager.SynManager;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.SimpleDateFormat;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class DownLoad  implements Runnable, NetTask.NetListener{

    public String tips = "";
    @Override
    public void run(){
        NetTask netTask = new NetTask(ActivityShare.url + "/download?phone_number=" + SynManager.getUserPhone(),0,DownLoad.this);
        netTask.execute();
    }


    @Override
    public void onNetSuccess(JSONArray jsonData, int code, String message){
        try{if(jsonData.length() != 0){
            SynManager.transJsonArray(jsonData);
            Log.d("存入","成功" );
        }

        }catch (JSONException e){
            e.printStackTrace();
        }

        this.tips = message;
    }
    @Override
    public void onNetError(int errorCode, String errorMessage){
        this.tips = errorMessage;
    }
}
