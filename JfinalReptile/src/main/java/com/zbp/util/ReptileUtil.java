package com.zbp.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/14.
 */
public class ReptileUtil {

    /**
     * 页面交互
     * @param urlStr
     * @param encoding
     * @return
     */
    public static String getHtml(String urlStr,String encoding){
        URL url;
        StringBuffer html = new StringBuffer();
        try {
            url = new URL(urlStr);
        //打开这个url输入输出流
        URLConnection urlConnection = url.openConnection();
        //将字节流转换为字节流
        InputStreamReader isr = new InputStreamReader(urlConnection.getInputStream(),encoding);
        //使用缓存读取器
        BufferedReader reader = new BufferedReader(isr);
        //定义一个变量来接收缓存读取器的数据，在传输到html变量中
        String tmp = "";
        while ((tmp = reader.readLine()) != null){
            html.append(tmp);
        }
        reader.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return html.toString();
    }

    public static Map<String,Integer> getReptiles(String urlStr){
        Map<String,Integer> map = new HashMap<String,Integer>();
        for (int i = 1;i<=12;i++){
            String tmpUrl = "";
            if (i>9){
                tmpUrl = urlStr.replace("##",""+i);
            }else {
                tmpUrl = urlStr.replace("##","0"+i);
            }
            String html = getHtml(tmpUrl,"gbk");
            //解析html中的值获取到我们需要的数据，
            Document document = Jsoup.parse(html.toString());
            //找到当前薪资
            Element element = document.getElementById("filter_providesalary");
            //System.out.println(element.getElementsByClass("dw_c_orange").get(0).text());
            //获取当前公司数量
            Element element1 = document.getElementById("resultList");
            //找到当前目标的class
            //System.out.println(Integer.valueOf(element1.getElementsByClass("rt").get(0).text().replace("共","").replace("条职位","")));
            map.put(element.getElementsByClass("dw_c_orange").get(0).text(),Integer.valueOf(element1.getElementsByClass("rt").get(0).text().replace("共","").replace("条职位","")));
        }
        return map;
    }



    /**
     *
     * @Title:
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param urlStr 要抓取的网页地址
     * @param encoding 被抓取的网页的编码
     * @return    设定文件
     * @return String    返回类型
     * @throws
     */
    public static String getHtml02(String urlStr,String encoding){
        URL url;
        StringBuffer html = new StringBuffer();
        try {
            url = new URL(urlStr);

            //打开这个url 输入输出流
            URLConnection conn = url.openConnection();
            //将字节流转换成字符刘
            InputStreamReader isr = new InputStreamReader(conn.getInputStream(),encoding);
            //使用缓存读取器
            BufferedReader reader = new BufferedReader(isr);

            String tmp = "";
            while((tmp = reader.readLine())!=null){
                html.append(tmp);
            }
            reader.close();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return html.toString();
    }

    /**
     * 前程无忧岗位需求爬虫
     * @param urlStr
     * @param encoding
     * @return
     * @throws IOException
     */
    public static void getReptile(String urlStr,String encoding) throws IOException {
        System.out.println("----------------------------");
        for (int i = 1;i<=12;i++){
            String tmpUrl = "";
            if (i>9){
                tmpUrl = urlStr.replace("##",""+i);
            }else {
                tmpUrl = urlStr.replace("##","0"+i);
            }
            URL url;
            StringBuffer html = new StringBuffer();
            url = new URL(tmpUrl);
            //打开这个url输入输出流
            URLConnection urlConnection = url.openConnection();
            //将字节流转换为字节流
            InputStreamReader isr = new InputStreamReader(urlConnection.getInputStream(),encoding);
            //使用缓存读取器
            BufferedReader reader = new BufferedReader(isr);
            //定义一个变量来接收缓存读取器的数据，在传输到html变量中
            String tmp = "";
            while ((tmp = reader.readLine()) != null){
                html.append(tmp);
            }
            reader.close();

            //解析html中的值获取到我们需要的数据，
            Document document = Jsoup.parse(html.toString());
            //找到当前薪资
            Element element = document.getElementById("filter_providesalary");
            System.out.println(element.getElementsByClass("dw_c_orange").get(0).text());
            //获取当前公司数量
            Element element1 = document.getElementById("resultList");
            //找到当前目标的class
            System.out.println(element1.getElementsByClass("rt").get(0).text());
            //System.out.println(elements.get(0).text());
        }
    }

    public static void main(String[] args) throws IOException {
        getReptile("3","gbk");
    }
}
