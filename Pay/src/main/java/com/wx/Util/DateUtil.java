package com.wx.Util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2017/5/23.
 */
public class DateUtil {
    /**
     * 日期转换成字符串
     * @param date
     * @return str
     */
    public static String DateToStr(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(date);
        return str;
    }

    /**
     * 字符串转换成日期
     * @param str
     * @return date
     */
    public static Date StrToDate(String str) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String Stop(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");//设置日期格式
        return df.format(new Date());
    }
    /**
     * 获取网上当前时间
     * @return
     */
    public static String date(){
        long curren = System.currentTimeMillis();
        curren += 60 * 1000;
        Date da = new Date(curren);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(da);
    }

    /*获取服务器当前时间*/
    public static String servicer(){
        String ddate=new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
        return ddate;
    }

    public static String DateDouble(String date,Double time){
        Date dt = StrToDate(date);
        long lSysTime1 = dt.getTime();
        lSysTime1 += (long)(time*60) * 60 * 1000;
        Date da = new Date(lSysTime1);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(da);
    }

}
