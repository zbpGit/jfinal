package com.zbp.Route;

import com.jfinal.config.Routes;
import com.zbp.Inerceptor.RoutesInerceptor;
import com.zbp.controller.IndexController;

/**
 * Created by Administrator on 2017/9/22.
 */
public class IndexRoute extends Routes {

    public void config() {
        setBaseViewPath("/front");
        addInterceptor(new RoutesInerceptor());
        add("/",IndexController.class);
    }
}
