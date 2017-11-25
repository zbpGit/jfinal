package com.zbp.controller;

import com.jfinal.core.Controller;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * Created by Administrator on 2017/9/22.
 */
public class IndexController extends Controller{
    public void index(){
        renderTemplate("login.html");
    }

    /**
     * 登录
     */
    public void login(){
        String userName = getPara("userName");
        String password = getPara("password");
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName,password);
        try {
            subject.login(token);
            renderJsp("success.jsp");
        }catch (Exception e){
            e.printStackTrace();
            setAttr("errorInfo","error");
            renderJsp("login.html");
        }
    }

    public void test02(){
        renderText("123");
    }

}
