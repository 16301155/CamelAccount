package com.example.myungger.pocket.manager;

import com.example.myungger.pocket.entity.Record;
import com.example.myungger.pocket.entity.SynchronizeData;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SynManager {
    //返回需要同步到服务器的数据集合
    public static List<Record> getNoSyn(){
        List<Record> temp = new ArrayList<>();
        List<Record> records = LitePal.order("record_date desc").find(Record.class);
        Date date = LitePal.findFirst(SynchronizeData.class).getSynTime();
        for(Record mm : records){
            if(mm.getRecord_date().after(date))
                temp.add(mm);
            else
                break;
        }
        return temp;

    }
    //获取下载记录的起始时间
    public static Date getDownLoadTime(){
        Date date = LitePal.findFirst(SynchronizeData.class).getDownTime();

        return date;
    }
//输入属性名称以及新的日期,true成功，false失败
    public static boolean updateTime(String type, Date date){
        SynchronizeData syn = new SynchronizeData();
        boolean flag = false;
        if(type.equals("synTime")){
            syn.setSynTime(date);
            if (syn.updateAll() == 1)
                flag = true;
            else
                flag = false;
        }else if(type.equals("downTime")){
            syn.setDownTime(date);
            if(syn.updateAll() == 1)
                flag = true;
            else
                flag = false;
        }
        return flag;
    }


}
