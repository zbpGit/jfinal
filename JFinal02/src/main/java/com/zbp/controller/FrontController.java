package com.zbp.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.zbp.Inerceptor.ClassInerceptor;
import com.zbp.model.User;
import org.apache.log4j.Logger;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Administrator on 2017/6/19.
 */
public class FrontController extends Controller {

    private static Logger logger = Logger.getLogger(FrontController.class);

    @ActionKey("/front")
    public void front(){
        setAttr("front","首页");
        getModel(User.class);
        renderTemplate("index.jsp");
    }

    @ActionKey("/admin")
    public void admin(){
        //传参
        setAttr("admin","后台");

        /*接收单个参数*/
        String hello = getPara("hello","world!");
        String world =getPara("world");

        String [] vuals = getParaValues("world");
        setAttr("hello","Hello------->,"+world);
        renderTemplate("admin.jsp");
    }

    @ActionKey("/add")
    public void add(){
        renderTemplate("add.jsp");
    }

    @ActionKey("/doAdd")
    public void aoAdd(){
        System.out.println(getModel(User.class,"u"));
        renderText("提交成功！");
    }

    @ActionKey("/sxss")
    public void sxss(){
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        renderJson(list);
    }

    @ActionKey("/DT")
    public void dt(){
        /*重定向*/
        /*redirect("/sxss");*/
        /*跳转*/
        forwardAction("/sxss");
    }

    /**
     * 文件上传
     */
    @ActionKey("/file")
    public void file() {
        /*顺序*/
        getFile();
        getPara();
    }

}
