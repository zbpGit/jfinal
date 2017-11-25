package com.zbp.blog;

import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import com.zbp.Inerceptor.GlobalInterceptor;
import com.zbp.Route.IndexRoute;

/**
 * Created by Administrator on 2017/9/22.
 */
public class MyBlog extends JFinalConfig {

    public void configConstant(Constants constants) {
        constants.setDevMode(true);
        constants.setViewType(ViewType.JSP);
        constants.setEncoding("UTF-8");
        /*导入数据库连接*/
        PropKit.use("jdbc.properties");
    }

    public void configRoute(Routes routes) {
        routes.add(new IndexRoute());
    }

    public void configEngine(Engine engine) {
        /*导入函数路径*/
        engine.setBaseTemplatePath(PathKit.getWebRootPath());
    }

    public void configPlugin(Plugins plugins) {
        DruidPlugin druidPlugin =new DruidPlugin(PropKit.get("jdbcUrl"),
                PropKit.get("jdbcUsername"),PropKit.get("jdbcPassword").trim());
        plugins.add(druidPlugin);
        ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);

        /*sql的起始文件夹*/
        arp.setBaseSqlTemplatePath(PathKit.getWebRootPath());
        plugins.add(arp);
    }

    public void configInterceptor(Interceptors interceptors) {
        interceptors.addGlobalActionInterceptor(new GlobalInterceptor());
    }

    public void configHandler(Handlers handlers) {
        handlers.add(new ContextPathHandler("ctx"));
    }

    @Override
    public void afterJFinalStart() {
        System.out.println("jfinalShiro");
    }

    public static void main(String[] args) {
        JFinal.start("JFinalShiro/src/main/webapp",8080,"/");
    }

}
