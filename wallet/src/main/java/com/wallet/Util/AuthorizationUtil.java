package com.wallet.Util;

import com.google.gson.Gson;
import com.jfinal.plugin.activerecord.Record;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import static com.wallet.Util.DateUtil.servicer;
import static com.wallet.Util.SignUtil.createSign;
import static com.wallet.Util.SignUtil.createSignO;
import static com.wallet.Util.SignUtil.getRandomStringByLength;


/**
 * Created by Administrator on 2017/5/14.
 */
public class AuthorizationUtil {

    /**
     * 授权
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String wxLogin(Record record) throws UnsupportedEncodingException {
        String url = null;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("https://open.weixin.qq.com/connect/oauth2/authorize?");
        stringBuffer.append("appid="+record.get("subAppId"));
        stringBuffer.append("&redirect_uri="+URLEncoder.encode("http://"+record.get("url")+"/wallet/callBack","UTF-8"));
        stringBuffer.append("&response_type=code");
        stringBuffer.append("&scope=snsapi_base");
        stringBuffer.append("&state=STATE#wechat_redirect");
        url = stringBuffer.toString();
        return  url;
    }

    /**
     * 授权回调取得的参数
     * @return
     * @throws IOException
     */
    public static JSONObject callBack(String code,Record record) throws IOException {
        String url = null;
        String infoUrl = null;
        StringBuffer stringBuffer = new StringBuffer();
        //通过之前的Util来请求接口
        stringBuffer.append("https://api.weixin.qq.com/sns/oauth2/access_token?");
        stringBuffer.append("appid="+record.get("subAppId"));
        stringBuffer.append("&secret="+record.get("appsecret"));
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

    /**
     * 微信红包接口
     * @param openid
     * @param money
     * @param sum
     * @param wishing
     * @param record
     * @return
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public static String Pay(String openid,Integer money,String sum,String wishing,Record record) throws IOException, SAXException, ParserConfigurationException {
        //下单
        Gson gson =new Gson();
        SortedMap<String,Object> map = new TreeMap<String,Object>();

        map.put("nonce_str",getRandomStringByLength(32));
        map.put("mch_billno",record.get("merNo")+servicer()+getRandomStringByLength(10));
        map.put("mch_id",record.get("merNo"));
        map.put("wxappid",record.get("subAppId"));
        map.put("send_name","美约通告");
        map.put("re_openid",openid);
        map.put("total_amount",moeny(money));
        map.put("total_num",sum);
        map.put("amt_type","ALL_RAND");
        map.put("wishing",wishing);
        map.put("act_name","红包");
        map.put("remark","猜越多得越多，快来抢！");
        String mySign = createSign("UTF-8",map, String.valueOf(record.get("key")));
        map.put("sign",mySign);//Md5加密过的字符串
        //封装成xml
        String xml = XMLUtil.ArrayToXml(map);
        String xx= HttpRequester.sendPost("https://api.mch.weixin.qq.com/mmpaymkttransfers/sendgroupredpack",xml);
        String py = gson.toJson(xx);
        return py;
    }

    /**
     * 第三方支付
     * @param openid
     * @param money
     * @param record
     * @return
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public static String PAY(String openid,Integer money,Record record) throws IOException, SAXException, ParserConfigurationException {
        //下单
        Gson gson =new Gson();
        String sum = moeny(money);
        SortedMap<String,Object> map = new TreeMap<String,Object>();
        map.put("merNo",record.get("merNo"));//商户号
        map.put("orderNo",SignUtil.getRandomStringByLength(20));//商户订单号或平台方流水号
        map.put("amount",sum);//订单金额
        map.put("reqId",SignUtil.getRandomStringByLength(25));//商户请求交易的流水号
        map.put("reqTime",DateUtil.servicer());//请求时间
        map.put("subAppId",record.get("subAppId"));//接入方微信公众号 id（于微信分配）
        map.put("subOpenId",openid);//用户在subAppId下的唯一标识
        map.put("notifyUrl",record.get("payUrl"));//异步通知

        String mySign = SignUtil.Pay("UTF-8",map,"秘钥");
        map.put("signIn",mySign);//Md5加密过的字符串

        //封装成字符串
        String xml = SignUtil.PayO("UTF-8",map);
        String xx= HttpRequester.sendPost((String) record.get("payUrl"),xml);
        String py = gson.toJson(xx);
        return py;
    }

    /**
     * H5页面支付
     * @param openid
     * @param money
     * @param out_trade_no
     * @param payTwo
     * @return
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public static String H5(String openid,Integer money,String out_trade_no,String payTwo) throws IOException, SAXException, ParserConfigurationException {
        //下单
        Gson gson =new Gson();
        String sum = moeny(money);
        SortedMap<String,Object> map = new TreeMap<String,Object>();
        map.put("appid", "公众账号ID");//公众账号ID
        map.put("attach", "红磨馆");//附加数据
        map.put("body","充值");//商品描述
        map.put("mch_id","商户号");//商户号
        map.put("device_info","WEB");//设备号
        map.put("trade_type","JSAPI");//交易类型
        map.put("nonce_str", SignUtil.getRandomStringByLength(32));//随机字符串
        map.put("notify_url",payTwo);//通知地址
        map.put("out_trade_no",out_trade_no);//商户订单号
        map.put("total_fee",sum);//金额
        map.put("openid",openid);//openid
        String mySign = SignUtil.Pay("UTF-8",map,"秘钥");
        map.put("sign",mySign);//签名
        //封装成xml
        String xml = XMLUtil.ArrayToXml(map);
        String xx= HttpRequester.sendPost("https://api.mch.weixin.qq.com/pay/unifiedorder",xml);
        //获取prepay_id
        Map map1 = XMLUtil.getMapFromXML(xx);
        String prepay_id=(String) map1.get("prepay_id");
        String prepay = "prepay_id="+prepay_id;
        String nonce_str = SignUtil.getRandomStringByLength(32);
        String timeStamp = SignUtil.create_timestamp();
        //提交订单
        SortedMap<String,Object> sortedMap = new TreeMap<String,Object>();
        sortedMap.put("appId","");
        sortedMap.put("timeStamp",timeStamp);
        sortedMap.put("nonceStr",nonce_str);
        sortedMap.put("package",prepay);
        sortedMap.put("signType","MD5");
        String paySign = SignUtil.Pay("UTF-8",sortedMap,"秘钥");
        sortedMap.put("paySign",paySign);
        String pay = gson.toJson(sortedMap);
        return pay;
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
