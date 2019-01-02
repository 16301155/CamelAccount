package com.example.myungger.pocket.entity;

import android.content.Intent;
import android.util.Log;

import com.example.myungger.pocket.activity.ActivityShare;

import com.example.myungger.pocket.manager.NetTask;
import com.example.myungger.pocket.manager.SynManager;


import org.json.JSONArray;
import org.litepal.LitePal;

import java.text.SimpleDateFormat;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class Upload implements Runnable, NetTask.NetListener {
    public String tips = "";
    @Override
    public void run(){
        if(SynManager.getNoSyn().size() != 0){

            for(Record mm : SynManager.getNoSyn()){
                NetTask netTask = new NetTask(ActivityShare.url + "/upload",1, Upload.this);
                netTask.execute(getUpLoadRequestBody(mm));
            }
            SynManager.setUploadTime();
//            SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            Log.d("时间",sdf.format(LitePal.findFirst(SynchronizeData.class).getSynTime()));
        }

    }
    public static RequestBody getUpLoadRequestBody(Record record) {
        RequestBody requestBody;
       // Log.d("电话请求体",ActivityShare.password);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = sdf.format(record.getRecord_date());
        String flagString = String.valueOf(record.getFlag());
        String cateString = String.valueOf(record.getCategory());
        String moneyString = String.valueOf(record.getMoney());
        requestBody = new FormBody.Builder()
                .add("phone_number", SynManager.getUserPhone())
                .add("record_date",dateString)
                .add("flag",flagString)
                .add("money",moneyString)
                .add("category",cateString)
                .build();
        return requestBody;
    }

    @Override
    public void onNetSuccess(JSONArray jsonData, int code, String message){
        this.tips = message;
    }
    @Override
    public void onNetError(int errorCode, String errorMessage){
        this.tips = errorMessage;
    }
}
