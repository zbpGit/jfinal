package com.zbp.Route;

import com.jfinal.config.Routes;
import com.zbp.Inerceptor.RoutesInerceptor;
import com.zbp.controller.FrontController;

/**
 * Created by Administrator on 2017/6/19.
 */
public class FrontRoute extends Routes {

    public void config() {
        setBaseViewPath("/Front");
        addInterceptor(new RoutesInerceptor());
        add("/", FrontController.class);
    }
}
