package com.zbp.manage;

import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;
import com.zbp.Inerceptor.GlobalInterceptor;
import com.zbp.Route.FrontRoute;
import com.zbp.Route.InterceptRoute;
import com.zbp.model.User;

/**
 * Created by Administrator on 2017/6/19.
 */
public class MyJFinal extends JFinalConfig {

    public void configConstant(Constants constants) {
        constants.setDevMode(true);
        PropKit.use("jdbc.properties");
        /*设置错误页面*/
        constants.setError404View("404.jsp");
        constants.setError500View("500.jsp");
        /*设置文件上传路径*/
        constants.setBaseUploadPath("/FileDownload");
        /*设置文件下载路径*/
        constants.setBaseDownloadPath("/FileUploading");

    }

    public void configRoute(Routes routes) {
        routes.add(new FrontRoute());
        routes.add(new InterceptRoute());
    }

    public void configEngine(Engine engine) {

    }

    public void configPlugin(Plugins plugins) {
        DruidPlugin druidPlugin =new DruidPlugin(PropKit.get("jdbcUrl"),
                PropKit.get("jdbcUsername"),PropKit.get("jdbcPassword").trim());
        plugins.add(druidPlugin);
        ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
        arp.addMapping("user", User.class);
        plugins.add(arp);
    }

    /*全局*/
    public void configInterceptor(Interceptors interceptors) {
        interceptors.add(new GlobalInterceptor());
        //同上原理
        //interceptors.addGlobalActionInterceptor(new GlobalInterceptor());
        /*业务拦截器*/
        /*interceptors.addGlobalServiceInterceptor(new InjectorInterceptor());*/
    }

    public void configHandler(Handlers handlers) {
        handlers.add(new ContextPathHandler("ctx"));
    }

    @Override
    public void afterJFinalStart() {
        System.out.println("开始启动");
        super.afterJFinalStart();
    }

    public static void main(String[] args) {
        JFinal.start("JFinal02/src/main/webapp",8080,"/");
    }
}
