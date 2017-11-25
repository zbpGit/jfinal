package com.wallet.configuration;

import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.log.Log4jLogFactory;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.SqlReporter;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.template.Engine;
import com.wallet.RenderFactory.MyBeetlRenderFactory;
import com.wallet.Routes.FontRoutes;

/**
 * Created by Administrator on 2017/8/9.
 */
public class Mybogl extends JFinalConfig {

    public void configConstant(Constants constants) {
        constants.setDevMode(true);
         /*导入数据库连接*/
        PropKit.use("jdbc.properties");
        //开启日志
        constants.setLogFactory(new Log4jLogFactory());
        SqlReporter.setLog(true);
        constants.setRenderFactory(new MyBeetlRenderFactory());


    }

    public void configRoute(Routes routes) {
        routes.add(new FontRoutes());
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
        arp.addSqlTemplate("/sql/wallet.sql");
        plugins.add(arp);
        /*缓存*/
        plugins.add(new EhCachePlugin());
    }

    public void configInterceptor(Interceptors interceptors) {

    }

    public void configHandler(Handlers handlers) {
        handlers.add(new ContextPathHandler("ctx"));
    }

    public static void main(String[] args) {
        JFinal.start("wallet/src/main/webapp",8080,"/");
    }
}
