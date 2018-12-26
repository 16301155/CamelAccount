package com.example.myungger.pocket.manager;

import com.example.myungger.pocket.entity.Record;

import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class RecordManager {

    public static Date getCurrentDate(){
        Date date = new Date();
        return date;
    }
    // 存入一系列记录
    public static void saveRecords(List<Record> records){
        if (records.isEmpty())
        LitePal.saveAll(records);
    }
    //存入单条记录
    public static boolean saveRecord(Record record){
        if ((record.getFlag() == ManageFlag.GET_IN) | (record.getFlag() == ManageFlag.GET_OUT))
            record.save();
        return record.isSaved();
    }

    //查询最新记录返回单条记录对象
    public static Record getFirstRecord(){
        return LitePal.findFirst(Record.class);
    }

    //查询最早记录返回单条记录对象
    public static Record getLastRecord(){
        return LitePal.findLast(Record.class);
    }

    //查询某段时间的记录返回记录对象集合
    public static List<Record> getRecordsByTime(Date begin, Date end){
        List<Record> temp = LitePal.findAll(Record.class);
        List<Record> records = new ArrayList<>();
        for(Record mm:temp){
            if(mm.getRecord_date().before(end) && mm.getRecord_date().after(begin))//((mm.getRecord_date().getTime() > begin.getTime()) && (mm.getRecord_date().getTime() < end.getTime()))
                records.add(mm);
        }
        return  records;
    }

    //查询某段时间的 消费 记录返回记录对象集合
    public static List<Record> getRecordsByTimeIn(Date begin, Date end){
        List<Record> temp = LitePal.findAll(Record.class);
        List<Record> records = new ArrayList<>();
        for(Record mm:temp){
            if(mm.getRecord_date().before(end) && mm.getRecord_date().after(begin) && mm.getFlag() == ManageFlag.GET_OUT)//((mm.getRecord_date().getTime() > begin.getTime()) && (mm.getRecord_date().getTime() < end.getTime()))
                records.add(mm);
        }
        return  records;
    }

    //查询某段时间的 收入 记录返回记录对象集合
    public static List<Record> getRecordsByTimeOut(Date begin, Date end){
        List<Record> temp = LitePal.findAll(Record.class);
        List<Record> records = new ArrayList<>();
        for(Record mm:temp){
            if(mm.getRecord_date().before(end) && mm.getRecord_date().after(begin) && mm.getFlag() == ManageFlag.GET_IN)//((mm.getRecord_date().getTime() > begin.getTime()) && (mm.getRecord_date().getTime() < end.getTime()))
                records.add(mm);
        }
        return  records;
    }

    //删除所有数据
    public static int deleteAll(){
        return LitePal.deleteAll(Record.class);
    }
    //删除某段时间的记录,返回删除记录数量
    public static int deleterRecordsByTime(Date begin, Date end){
        List<Record> temp = getRecordsByTime(begin,end);
            for(Record mm:temp){
                mm.delete();
            }
            return temp.size();
    }

    //删除单条数据
    public static void deleteRecord(int id){
        LitePal.delete(Record.class, id);
    }
    //按数量每次返回固定数量的记录List， 用循环使得ManageFlag.OFFSET增加count个！！！！！！！！！！
    public static List<Record> getRecordsByCount(int count){
        List<Record> records = LitePal.order("record_date desc").limit(count).offset(ManageFlag.OFFSET).find(Record.class);
        return records;
    }
    //List集合内Money 的总和
    public static float getMoenySum(List<Record> records){
        float temp = 0;
        if(records.size() != 0){
            for(Record mm : records){
                temp += mm.getMoney();
            }
            return temp;
        }else
            return temp;
    }
    //获取记录的某个单属性

    //ID
    public static List<Integer> getAllID(){
        List<Record> temp = LitePal.findAll(Record.class);
        List<Integer> ID = new ArrayList<>();
        if (temp.size() != 0){
            for(Record mm : temp){
                ID.add(mm.getId());
            }
        }

        return ID;
    }
    //钱
    public static List<Float> getAllMoney(List<Record> records){
        List<Float> temp = new ArrayList<>();
        if (records.size() != 0){
            for(Record mm : records){
                temp.add(mm.getMoney());
            }
        }

        return temp;
    }
    //返回 某一消费类型的总金额
    public static  List<Float> getMoneyByCategory(String type, List<Record> records){
        List<Float> temp = new ArrayList<>();
        if (records.size() != 0){
            for(Record mm : records){
                if(mm.getCategory().equals(type))
                temp.add(mm.getMoney());
                else
                    continue;
            }
        }
        return temp;
    }
    //根据flag获取收入还是支出，输出字符
    public static  String getContent(int flag){
        if (flag == 5)
            return new String("收入：");
        if(flag == 6)
            return new String("支出：");
        else
            return new String("错误！");
    }


    //返回一个Record集合的String（应用在TextView中）字符串
    public static String getStringByList(List<Record> records){
        String temp = "";
        if (records.size() != 0){
            for(Record mm : records){
                temp += "账单编号:" + String.valueOf(mm.getId()) + "     "+ RecordManager.getContent(mm.getFlag()) + " " + mm.getMoney() + "\r\n"
                        + "类型：" + mm.getCategory() + "  时间：" + RecordManager.dateToStrLong(mm.getRecord_date())+ "\r\n";
            }
            return temp;
        }else
            return "暂无数据";

    }
    //获取当天开始时间
    public static Date getDayBegin() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    //date转String
    public static String dateToStrLong(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(dateDate);
        return dateString;
    }
}
