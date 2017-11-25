package com.wx.Controller;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.GET;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.wx.Util.AuthorizationUtil;
import com.wx.Util.SignUtil;
import com.wx.model.Pay;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import static com.wx.Util.FileUtil.price;
import static com.wx.Util.FileUtil.readTxtFile;

/**
 * Created by Administrator on 2017/7/6.
 */
public class IndexController extends Controller {

    /**
     * 授权
     * @throws UnsupportedEncodingException
     */
    public void index() throws UnsupportedEncodingException {
        String money = getPara("money");
        String s = PathKit.getWebRootPath()+"/File/array.txt";
        String path = PathKit.getWebRootPath()+"/File/pay.txt";
        Pay pay = price(path,s);
        String url = AuthorizationUtil.wxLogin(pay,money);
        redirect(url);
    }

    /**
     * 授权回调
     * @throws ServletException
     * @throws IOException
     */
    public void callBack() throws ServletException, IOException {
        String state = getPara("state");
        String code = getPara("code");
        String s = PathKit.getWebRootPath()+"/File/array.txt";
        String path = PathKit.getWebRootPath()+"/File/pay.txt";
        Pay pay = price(path,s);
        System.out.println(pay);
        JSONObject infouser = AuthorizationUtil.callBack(code,pay);
        Map map = infouser;
        String openid = (String) map.get("openid");
        setSessionAttr("openid",openid);
        redirect("/payment?money="+state);
    }

    /**
     * 支付
     * @throws IOException
     * @throws SAXException
     */
    public void payment() throws IOException, SAXException, ParserConfigurationException {
        Integer money = Integer.valueOf(getPara("money"));
        String openId = getSessionAttr("openid");
        String s = PathKit.getWebRootPath()+"/File/array.txt";
        String path = PathKit.getWebRootPath()+"/File/pay.txt";
        Pay pay = price(path,s);
        System.out.println(pay);
        String py = AuthorizationUtil.Pay(openId, money,pay);
        renderText(py);
    }
}
