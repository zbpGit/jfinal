package com.wx.Controller;

import com.jfinal.core.Controller;
import com.jfinal.kit.PathKit;
import com.wx.Util.FileUtil;
import com.wx.model.Pay;

import java.util.List;

import static com.wx.Util.DateUtil.Stop;
import static com.wx.Util.FileUtil.*;

/**
 * Created by Administrator on 2017/8/7.
 */
public class AdminController extends Controller{

    public void index(){
        String s = PathKit.getWebRootPath()+"/File/array.txt";
        String path = PathKit.getWebRootPath()+"/File/pay.txt";
        Pay pay = price(path,s);
        List<Pay> payList = ListPay(path);
        setAttr("pay",pay).setAttr("ListPay",payList).renderTemplate("/Admin/all.html");
    }

    public void sava(){
        String path = PathKit.getWebRootPath()+"/File/pay.txt";
        Pay pay = new Pay(getPara("merNo"),getPara("subAppId"),getPara("appsecret"),getPara("key"),getPara("url"),getPara("notifyUrl"),getPara("payUrl"),Stop(),"0");
        System.out.println(FileUtil.sava(path,pay));
        redirect("/Admin/index");
    }

    public void switchover(){
        String s = PathKit.getWebRootPath()+"/File/array.txt";
        String path = PathKit.getWebRootPath()+"/File/pay.txt";
        String error =switchove(path,s);
        renderJson(error);
    }



}
