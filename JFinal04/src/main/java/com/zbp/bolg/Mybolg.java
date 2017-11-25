package com.zbp.bolg;

import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.core.JFinalFilter;
import com.jfinal.plugin.redis.RedisPlugin;
import com.jfinal.template.Engine;
import com.zbp.controllor.TestControllor;

/**
 * Created by Administrator on 2017/7/1.
 */
public class Mybolg extends JFinalConfig {

    public void configConstant(Constants constants) {
        constants.setDevMode(true);
    }

    public void configRoute(Routes routes) {
        routes.add("/", TestControllor.class,"/Test");
    }

    public void configEngine(Engine engine) {

    }

    public void configPlugin(Plugins plugins) {
        RedisPlugin plugin = new RedisPlugin("ppx","127.0.0.1",6379);
        plugins.add(plugin);
    }

    public void configInterceptor(Interceptors interceptors) {

    }

    public void configHandler(Handlers handlers) {

    }

    @Override
    public void afterJFinalStart() {
        System.out.println("afterJFinalStart...");
        super.afterJFinalStart();
    }

    public static void main(String[] args) {
        JFinal.start("JFinal04/src/main/webapp",8080,"/");
    }

}
