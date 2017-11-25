package com.zbp.controllor;

import com.jfinal.core.Controller;
import com.zbp.util.ReptileUtil;

/**
 * Created by Administrator on 2017/7/1.
 */
public class TestControllor extends Controller {

    public void index(){
        renderTemplate("/index.jsp");
    }

    public void getHtml(){
        String url =getPara("url");
        System.out.println(url);
        System.out.println("123"+ReptileUtil.getReptiles(url));
        renderJson(ReptileUtil.getReptiles(url));
    }




}
