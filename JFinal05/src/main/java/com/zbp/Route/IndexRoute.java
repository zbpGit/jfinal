package com.zbp.Route;

import com.jfinal.config.Routes;
import com.zbp.Controller.IndexController;

/**
 * Created by Administrator on 2017/7/6.
 */
public class IndexRoute extends Routes {

    public void config() {
        setBaseViewPath("/index");
        add("/", IndexController.class);
    }

}
