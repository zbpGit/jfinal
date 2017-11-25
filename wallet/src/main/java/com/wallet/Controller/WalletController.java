package com.wallet.Controller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.wallet.Util.*;
import javafx.util.Pair;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.nustaq.serialization.annotations.Serialize;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.*;

import static com.wallet.Util.XMLUtil.getMapFromXML;
import static com.wallet.Util.XMLUtil.setXml;


/**
 * Created by Administrator on 2017/8/9.
 */
public class WalletController extends Controller{

    /**
     * 授权
     * @throws UnsupportedEncodingException
     */
    public void index() throws UnsupportedEncodingException {
        String wallet = Db.getSql("wallet.all");
        Record record = Db.findFirst(wallet);
        String url = AuthorizationUtil.wxLogin(record);
        redirect(url);
    }

    /**
     * 授权回调
     * @throws ServletException
     * @throws IOException
     */
    public void callBack() throws ServletException, IOException {
        String code = getPara("code");
        String wallet = Db.getSql("wallet.all");
        Record record = Db.findFirst(wallet);
        JSONObject infouser = AuthorizationUtil.callBack(code,record);
        Map map = infouser;
        String openid = (String) map.get("openid");
        setSessionAttr("openid",openid);
        redirect("/payment");
    }

    /**
     * 裂变红包支付
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public void wallet() throws ParserConfigurationException, SAXException, IOException, ClassNotFoundException {
        //金额
        Integer money = Integer.valueOf(getPara("money"));
        //人数
        String sum = getPara("sum");
        //祝福语
        String wishing = getPara("wishing");
        String openId = getSessionAttr("openid");
        String wallet = Db.getSql("wallet.all");
        Record record = Db.findFirst(wallet);
        String py = AuthorizationUtil.Pay(openId, money,sum,wishing,record);

        py = SerializeUtil.Serialize(py);
        Map<String,Object> map =getMapFromXML(py);
        if (map.get("result_code") == "SUCCESS" && map.get("return_code") =="SUCCESS"){
            renderText("SUCCESS");
        }else {
            renderText("FAIL");
        }
    }

    /**
     * 第三方支付
     * @throws IOException
     * @throws SAXException
     */
    public void payment() throws IOException, SAXException, ParserConfigurationException {
        Integer money = Integer.valueOf(getPara("money"));
        String openId = getSessionAttr("openid");
        String wallet = Db.getSql("wallet.all");
        Record record = Db.findFirst(wallet);
        String py = AuthorizationUtil.PAY(openId, money,record);
        renderText(py);
    }


    /**
     *H5页面支付
     * @return
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public String pay() throws IOException, SAXException, ParserConfigurationException {
        Integer money = Integer.valueOf(getPara("money"));
        String openId = getSessionAttr("openid");
        String out_trade_no = SignUtil.getRandomStringByLength(32);//商户订单号
        String pay = AuthorizationUtil.H5(openId, money,out_trade_no,"回调支付");
        return pay;
    }

    /**
     * H5异步回调
     * @return
     * @throws Exception
     */
    public void syntony() throws Exception {
        String resXml = null;
        //读取参数
        InputStream inputStream;
        StringBuffer sb = new StringBuffer();
        inputStream = getRequest().getInputStream();
        String s;
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        while ((s = in.readLine()) != null) {
            sb.append(s);
        }
        in.close();
        inputStream.close();
        System.out.println("回调参数="+sb.toString());
        //解析xml成map
        Map<String, Object> m = new HashMap<String, Object>();
        m = XMLUtil.getMapFromXML(sb.toString());

        //过滤空 设置 TreeMap
        SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
        Iterator it = m.keySet().iterator();
        while (it.hasNext()) {
            String parameter = (String) it.next();
            String parameterValue = (String) m.get(parameter);
            String v = "";
            if (null != parameterValue) {
                v = parameterValue.trim();
            }
            packageParams.put(parameter, v);
        }
        // 账号信息
        String key = "秘钥"; // key
        //判断签名是否正确
        if (SignUtil.isTenpaySign("UTF-8", packageParams, key)) {
            //------------------------------
            //处理业务开始
            //------------------------------
            try {
                if ("SUCCESS".equals((String) packageParams.get("result_code"))) {
                    String  success = null;
                    // 这里是支付成功，返回参数
                    resXml = setXml("SUCCESS", "OK");
                    //////////执行自己的业务逻辑////////////////
                    String out_trade_no = (String) packageParams.get("out_trade_no");



                }else {
                    resXml =  setXml("fail", "微信返回的交易状态不正确（result_code=" + "SUCCESS" + "）");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        } else {
            resXml =  setXml("fail", "签名失败");
        }
        //------------------------------
        //处理业务完毕
        //------------------------------
        BufferedOutputStream out = new BufferedOutputStream(getResponse().getOutputStream());
        out.write(resXml.getBytes());
        out.flush();
        out.close();
    }




}
