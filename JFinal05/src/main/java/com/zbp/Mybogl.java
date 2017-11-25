package com.zbp;

import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;
import com.zbp.Interceptor.GlobalInterceptor;
import com.zbp.Route.IndexRoute;
import com.zbp.model.Volunteer;

/**
 * Created by Administrator on 2017/7/6.
 */
public class Mybogl extends JFinalConfig {

    public void configConstant(Constants constants) {
        constants.setDevMode(true);
        constants.setEncoding("UTF-8");
        /*导入数据库连接*/
        PropKit.use("jdbc.properties");
        /*设置文件上传路径*/
        constants.setBaseUploadPath("/FileDownload");
        /*设置文件下载路径*/
        constants.setBaseDownloadPath("/FileUploading");
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
        arp.addSqlTemplate("/sql/volunteer.sql");
        /*表映射*/
        arp.addMapping("volunteer", Volunteer.class);
        plugins.add(arp);
    }

    public void configInterceptor(Interceptors interceptors) {
        interceptors.addGlobalActionInterceptor(new GlobalInterceptor());
    }

    public void configHandler(Handlers handlers) {
        handlers.add(new ContextPathHandler("ctx"));
    }


    public static void main(String[] args) {
        JFinal.start("JFinal05/src/main/webapp",8080,"/");
    }
}
