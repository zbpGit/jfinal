package com.zbp.manage;

import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.cron4j.Cron4jPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.plugin.redis.RedisPlugin;
import com.jfinal.render.JspRender;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import com.zbp.Inerceptor.GlobalInterceptor;
import com.zbp.Route.DataRoute;
import com.zbp.Util.AllUtil;
import com.zbp.Util.StaticUtil;
import com.zbp.model.User;
import com.zbp.model.cargo;

import javax.swing.text.View;

/**
 * Created by Administrator on 2017/6/26.
 */
public class MyBlog extends JFinalConfig {

    /*JFinal常量值*/
    public void configConstant(Constants constants) {
        constants.setDevMode(true);
        constants.setEncoding("UTF-8");
        /*导入数据库连接*/
        PropKit.use("jdbc.properties");
        /*设置错误页面*/
        constants.setError404View("/Data/404.jsp");
        constants.setError500View("/Data/500.jsp");

        /*设置文件上传路径*/
        constants.setBaseUploadPath("/FileDownload");
        /*设置文件下载路径*/
        constants.setBaseDownloadPath("/FileUploading");
    }

    /*访问路由*/
    public void configRoute(Routes routes) {
        routes.add(new DataRoute());
    }

    /*模板引擎*/
    public void configEngine(Engine engine) {
        /*所有导入的路径*/
        engine.setBaseTemplatePath(PathKit.getWebRootPath());

        /*导入函数页面*/
        engine.addSharedFunction("/Data/common.html");
        /*只能显示静态方法*/
        engine.addSharedStaticMethod(StaticUtil.class);
        /*可以显示任意的方法*/
        engine.addSharedObject("all",new AllUtil());
    }

    /*插件比如数据连接*/
    public void configPlugin(Plugins plugins) {
        DruidPlugin druidPlugin =new DruidPlugin(PropKit.get("jdbcUrl"),
                PropKit.get("jdbcUsername"),PropKit.get("jdbcPassword").trim());
        plugins.add(druidPlugin);
        ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
        /*sql的起始文件夹*/
        arp.setBaseSqlTemplatePath(PathKit.getWebRootPath());
        arp.addSqlTemplate("/sql/demo.sql");
        arp.addSqlTemplate("/sql/cargo.sql");
        /*表映射*/
        arp.addMapping("user", User.class);
        arp.addMapping("cargo", cargo.class);
        plugins.add(arp);

        /*缓存*/
        plugins.add(new EhCachePlugin());

        /*配置redis缓存*/
        RedisPlugin plugin = new RedisPlugin("zbp","127.0.0.1",6379);
        plugins.add(plugin);

        /*使用任务调度函数*/
        Cron4jPlugin jPlugin = new Cron4jPlugin(PropKit.use("cron4j"),"cron4j");
        plugins.add(jPlugin);
    }


    /*全局拦截器*/
    public void configInterceptor(Interceptors interceptors) {
        interceptors.addGlobalActionInterceptor(new GlobalInterceptor());
    }

    /*接管所有的请求*/
    public void configHandler(Handlers handlers) {
        handlers.add(new ContextPathHandler("ctx"));
    }

    /*JFinal启动的时候给自己启动一些东西*/
    @Override
    public void afterJFinalStart() {
        System.out.println("afterJFinalStart...");
        super.afterJFinalStart();
    }

    public static void main(String[] args) {
        JFinal.start("JFinal03/src/main/webapp",8080,"/");
    }
}
