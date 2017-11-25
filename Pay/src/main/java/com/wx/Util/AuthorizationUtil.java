package com.wx.Util;

import com.google.gson.Gson;
import com.jfinal.plugin.activerecord.Record;
import com.wx.model.Pay;
import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.XML;
import org.xml.sax.SAXException;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import static com.wx.Util.SignUtil.createSign;
import static com.wx.Util.SignUtil.createSignO;


/**
 * Created by Administrator on 2017/5/14.
 */
public class AuthorizationUtil {
    /**
     * 授权
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String wxLogin(Pay pay, String money) throws UnsupportedEncodingException {
        String url = null;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("https://open.weixin.qq.com/connect/oauth2/authorize?");
        /*stringBuffer.append("appid="+pay.getSubAppId());*/
        stringBuffer.append("appid=wxce03abf4c7e9d23f");
        /*stringBuffer.append("&redirect_uri="+URLEncoder.encode("http://"+pay.getUrl()+"/pay/callBack","UTF-8"));*/
        stringBuffer.append("&redirect_uri="+URLEncoder.encode("http://pay.qingmeng168.com/pay/callBack","UTF-8"));
        stringBuffer.append("&response_type=code");
        stringBuffer.append("&scope=snsapi_base");
        stringBuffer.append("&state="+money+"#wechat_redirect");
        url = stringBuffer.toString();
        return  url;
    }

    /**
     * 授权回调取得的参数
     * @return
     * @throws IOException
     */
    public static JSONObject callBack(String code,Pay pay) throws IOException {
        String url = null;
        String infoUrl = null;
        StringBuffer stringBuffer = new StringBuffer();
        //通过之前的Util来请求接口
        stringBuffer.append("https://api.weixin.qq.com/sns/oauth2/access_token?");
        /*stringBuffer.append("appid="+pay.getSubAppId());
        stringBuffer.append("&secret="+pay.getAppsecret());*/
        stringBuffer.append("appid=wxce03abf4c7e9d23f");
        stringBuffer.append("&secret=76e03562769eee18102a9ed674105121");
        stringBuffer.append("&code="+code);
        stringBuffer.append("&grant_type=authorization_code");
        url= stringBuffer.toString();

        JSONObject jsonObject = AuthorizationUtil.doGetJson(url);
        String openid = jsonObject.getString("openid");
        String token = jsonObject.getString("access_token");

        StringBuffer buffer = new StringBuffer();
        buffer.append("https://api.weixin.qq.com/sns/userinfo?");
        buffer.append("access_token="+token);
        buffer.append("&openid="+openid);
        buffer.append("&lang=zh_CN");
        infoUrl = buffer.toString();
        JSONObject userInfo = AuthorizationUtil.doGetJson(infoUrl);
        return userInfo;
    }

    public static String Pay(String openid,Integer money,Pay pay) throws IOException, SAXException, ParserConfigurationException {
        //下单
        Gson gson =new Gson();
        String sum = moeny(money);
        SortedMap<String,Object> map = new TreeMap<String,Object>();

        //map.put("merNo",pay.getMerNo());//商户号
        map.put("merNo","MS0000001624099");
        map.put("orderNo",SignUtil.getRandomStringByLength(20));//商户订单号或平台方流水号
        map.put("amount",sum);//订单金额
        map.put("reqId",SignUtil.getRandomStringByLength(25));//商户请求交易的流水号
        map.put("reqTime",DateUtil.servicer());//请求时间
        /*map.put("subAppId",pay.getSubAppId());//接入方微信公众号 id（于微信分配）*/
        map.put("subAppId","wxce03abf4c7e9d23f");//接入方微信公众号 id（于微信分配）
        map.put("subOpenId",openid);//用户在subAppId下的唯一标识
        /*map.put("notifyUrl",pay.getNotifyUrl());//异步通知*/
        map.put("notifyUrl","http://110.80.142.84/pay/pay_handleObj_v2.php");//异步通知


        /*String mySign = createSign("UTF-8",map, String.valueOf(pay.getKey()));*/
        String mySign = createSign("UTF-8",map,"fdb2841c18114a14b283c15cf8dc47b1");
        map.put("signIn",mySign);//Md5加密过的字符串

        //封装成字符串
        String xml = createSignO("UTF-8",map);
        System.out.println("xml=="+xml);
        xml = xml.substring(0,xml.length()-1);
        System.out.println("xml==="+xml);
        /*String xx= HttpRequester.sendPost(pay.getPayUrl(),xml);*/
        String xx= HttpRequester.sendPost("http://scp.yufu99.com/scanpay-api/api/wxGZHUnifiedOrder20",xml);
        String py = gson.toJson(xx);
        return py;
    }

    /**
     *
     * @param money
     * @return
     */
    public static String moeny(Integer money){
        String sum = String.valueOf(money*100);
        return sum;
    }

    public static JSONObject doGetJson(String url) throws IOException {
        JSONObject jsonObject = null;
        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();
        if (entity != null){
            String result = EntityUtils.toString(entity,"UTF-8");
            jsonObject = JSONObject.fromObject(result);
        }
        httpGet.releaseConnection();
        return jsonObject;
    }
}
