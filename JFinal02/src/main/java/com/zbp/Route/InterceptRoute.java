package com.zbp.Route;

import com.jfinal.config.Routes;
import com.zbp.Inerceptor.RoutesInerceptor;
import com.zbp.controller.IterceptController;

/**
 * Created by Administrator on 2017/6/21.
 */
public class InterceptRoute extends Routes{

    public void config() {
        setBaseViewPath("/WEB-INF");
        addInterceptor(new RoutesInerceptor());
        add("/Intercept", IterceptController.class);
    }
}
