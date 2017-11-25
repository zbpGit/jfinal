package com.wx.configuration;

import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.kit.PathKit;
import com.jfinal.template.Engine;
import com.wx.Interceptor.GlobalInterceptor;
import com.wx.Route.IndexRoute;

/**
 * Created by Administrator on 2017/7/6.
 */
public class Mybogl extends JFinalConfig {

    public void configConstant(Constants constants) {
        constants.setDevMode(true);
        constants.setEncoding("UTF-8");
    }

    public void configRoute(Routes routes) {
        routes.add(new IndexRoute());
    }

    public void configEngine(Engine engine) {
        /*导入函数路径*/
        engine.setBaseTemplatePath(PathKit.getWebRootPath());
    }

    public void configPlugin(Plugins plugins) {

    }

    public void configInterceptor(Interceptors interceptors) {
        interceptors.addGlobalActionInterceptor(new GlobalInterceptor());
    }

    public void configHandler(Handlers handlers) {
        handlers.add(new ContextPathHandler("ctx"));
    }

    public static void main(String[] args) {
        JFinal.start("Pay/src/main/webapp",8080,"/");
    }
}
