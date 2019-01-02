package com.example.myungger.pocket.manager;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.myungger.pocket.activity.ActivityShare;
import com.example.myungger.pocket.activity.MainActivity;
import com.example.myungger.pocket.entity.Record;
import com.example.myungger.pocket.entity.SynchronizeData;
import com.example.myungger.pocket.util.ToastMessage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.LitePal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class SynManager{
    public final static int upload = 0;
    public final static int downLoad = 1;
    private int method;

    public Activity current_act;

    public int getMethod() {
        return method;
    }

    public void setMethod(int method) {
        this.method = method;
    }

    //判断是否登录
    public static boolean isLogin(){
        SynchronizeData tt = LitePal.findFirst(SynchronizeData.class);
        if(tt == null)
            return false;
        else
            return true;
    }
    public SynManager(boolean netFlag, final Activity current_act, int method){
        this.current_act = current_act;
        this.method = method;
    }
    //获取当前本地账号电话号
    public static String getUserPhone(){
        SynchronizeData tt = LitePal.findFirst(SynchronizeData.class);
        String temp = tt.getPhoneNumber();
        return temp;
    }
    //设置本地账户(存入电话号)
    public static void setUser(String phone){
        SynchronizeData temp = new SynchronizeData();
        temp.setPhoneNumber(phone);
        temp.setSynTime(new Date());
        temp.save();
    }

    //设置本地最近更新的数据时间
    public static void setUploadTime(){
        SynchronizeData syn = new SynchronizeData();
        syn.setSynTime(new Date());
        syn.updateAll();
    }

    //返回需要同步到服务器的数据集合
    public static List<Record> getNoSyn(){
        List<Record> temp = new ArrayList<>();
        List<Record> records = LitePal.order("record_date desc").find(Record.class);
        Date date = LitePal.findFirst(SynchronizeData.class).getSynTime();
        for(Record mm : records){
            if(mm.getRecord_date().after(date))
                temp.add(mm);
        }
        return temp;

    }


    // 生成requestbody
    public static RequestBody getUpLoadRequestBody() {
        RequestBody requestBody;
        //Log.d("测试","生成请求体");
        Log.d("电话请求体",ActivityShare.password);
        requestBody = new FormBody.Builder()
                .add("phone_number", ActivityShare.phone_num)
                .add("password", ActivityShare.password)
                .build();
        return requestBody;
    }
 //解析下载的记录并且同步到本地数据库
    public static String transJsonArray(JSONArray jsonArray)throws JSONException {
        if (jsonArray.length() != 0){
            List<Record> records = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++){
                Record record = new Record();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String tempDate = jsonObject.getString("record_date");
                try{
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    record.setRecord_date(sdf.parse(tempDate));
                    record.setFlag(Integer.parseInt(jsonObject.getString("flag")));
                    record.setMoney(Float.parseFloat(jsonObject.getString("money")));
                    Log.d("类型",jsonObject.getString("money"));
                    record.setCategory(jsonObject.getString("category"));
                    RecordManager.saveRecord(record);
                    records.add(record);
                }catch(ParseException e){
                    e.printStackTrace();
                }

            }

            Log.d("抽查",LitePal.findFirst(Record.class).getCategory());

            return new String("下载云端数据成功！");
        }else
            return new String("解析数据失败！");
    }

    /**
     * 获取网络状态
     * @param context
     * @return
     */
    public static int getNetWorkState(Context context) {
        // 得到连接管理器对象
        ConnectivityManager connectivityManager = (ConnectivityManager) context .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            if (activeNetworkInfo.getType() == (ConnectivityManager.TYPE_WIFI))
                return NetFlag.NETWORK_WIFI;
            else
                return NetFlag.NETWORK_NONE;
        }else
            return NetFlag.NETWORK_NONE;
    }

    /**
     *
     * @param context
     * @return
     */
    public static boolean isNetConnect(Context context) {
        if (getNetWorkState(context) == NetFlag.NETWORK_WIFI) {
            return true;
        } else {
            return false;
        }
    }
}
