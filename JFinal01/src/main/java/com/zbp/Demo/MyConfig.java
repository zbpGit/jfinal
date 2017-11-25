package com.zbp.Demo;


import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.template.Engine;
import com.zbp.controller.IndexController;

/**
 * Created by Administrator on 2017/6/10.
 */
public class MyConfig extends JFinalConfig{

    public void configConstant(Constants constants) {

    }
    /*模板引擎配置*/
    public void configRoute(Routes me) {
        /*me.add("/", IndexController.class);*/
        me.add("/", IndexController.class,"/index");
    }

    public void configEngine(Engine engine) {

    }

    public void configPlugin(Plugins plugins) {

    }

    public void configInterceptor(Interceptors interceptors) {

    }

    public void configHandler(Handlers handlers) {

    }



    @Override
    public void afterJFinalStart() {
        System.out.println("==========");
        super.afterJFinalStart();
    }

    public static void main(String[] args) {
        JFinal.start("JFinal01/src/main/webapp",8080,"/");
    }
}
