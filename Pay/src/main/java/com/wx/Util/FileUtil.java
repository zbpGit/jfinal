package com.wx.Util;

import com.jfinal.kit.PathKit;
import com.wx.model.Pay;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;
import sun.java2d.pipe.SolidTextRenderer;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/7.
 */
public class FileUtil {
    public static String readTxtFile(String filePath){
        String txt = null;
        try {
            String encoding="GBK";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    txt=lineTxt;
                }
                read.close();
            }else{
                System.out.println("找不到指定的文件");
            }
            return txt;
        } catch (Exception e) {
            txt = "读取文件内容出错";
            e.printStackTrace();
            return txt;
        }
    }

    public static Pay price(String filePath,String s){
        Pay pay = new Pay();
        String message =readTxtFile(filePath);
        String s1 =readTxtFile(s);
        JSONArray jsonArray = JSONArray.fromObject(message);
        System.out.println(jsonArray.size());
        JSONObject jUser = jsonArray.getJSONObject(Integer.parseInt(s1));
        pay.setMerNo(jUser.getString("merNo"));
        pay.setSubAppId(jUser.getString("subAppId"));
        pay.setAppsecret(jUser.getString("appsecret"));
        pay.setKey(jUser.getString("key"));
        pay.setUrl(jUser.getString("url"));
        pay.setNotifyUrl(jUser.getString("notifyUrl"));
        pay.setPayUrl(jUser.getString("payUrl"));
        pay.setTimes(jUser.getString("times"));
        pay.setPresent(jUser.getString("present"));
        return pay;
    }

    public static List<Pay> ListPay(String filePath){
        List<Pay> payList = new ArrayList<Pay>();
        String message =readTxtFile(filePath);
        JSONArray jsonArray = JSONArray.fromObject(message);
        Iterator<JSONArray> it = jsonArray.iterator();
        while (it.hasNext()){
            JSONObject ob = JSONObject.fromObject(it.next());
            Pay pay = new Pay();
            pay.setMerNo(ob.getString("merNo"));
            pay.setSubAppId(ob.getString("subAppId"));
            pay.setAppsecret(ob.getString("appsecret"));
            pay.setKey(ob.getString("key"));
            pay.setUrl(ob.getString("url"));
            pay.setNotifyUrl(ob.getString("notifyUrl"));
            pay.setPayUrl(ob.getString("payUrl"));
            pay.setTimes(ob.getString("times"));
            pay.setPresent(ob.getString("present"));
            payList.add(pay);
        }
        return payList;
    }

    public static String sava(String filePath,Pay pay){
        String message =readTxtFile(filePath);
        //将jsonArray字符串转化为JSONArray
        JSONArray jsonArray = JSONArray.fromObject(message);
        jsonArray.add(pay);
        return file(filePath,jsonArray);
    }

    public static String switchove(String filePath,String filePath1){
        String message =readTxtFile(filePath);
        String message1 =readTxtFile(filePath1);
        JSONArray jsonArray = JSONArray.fromObject(message);
        Integer sum = jsonArray.size();
        Integer add = Integer.valueOf(message1);
        Integer j = sum -add;
        if (j > 1){
            JSONObject jUser = jsonArray.getJSONObject(add);
            jUser.put("present","0");
            add++;
            jUser = jsonArray.getJSONObject(add);
            jUser.put("present","1");
            file(filePath,jsonArray);
            file1(filePath1, String.valueOf(add));
            return "0";
        }else {
            return "1";
        }

    }

    public static String file(String filePath,JSONArray jsonArray){
        try {
            // 创建文件对象
            File fileText = new File(filePath);
            // 向文件写入对象写入信息
            FileWriter fileWriter = new FileWriter(fileText);
            // 写文件
            fileWriter.write(jsonArray.toString());
            // 关闭
            fileWriter.close();
        } catch (IOException e) {
            //
            e.printStackTrace();
            return "error";
        }

        return "ok";
    }

    public static String file1(String filePath,String massage){
        try {
            // 创建文件对象
            File fileText = new File(filePath);
            // 向文件写入对象写入信息
            FileWriter fileWriter = new FileWriter(fileText);
            // 写文件
            fileWriter.write(massage);
            // 关闭
            fileWriter.close();
        } catch (IOException e) {
            //
            e.printStackTrace();
            return "error";
        }

        return "ok";
    }


}
