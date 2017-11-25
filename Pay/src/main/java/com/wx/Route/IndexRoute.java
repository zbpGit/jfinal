package com.wx.Route;

import com.jfinal.config.Routes;
import com.wx.Controller.AdminController;
import com.wx.Controller.IndexController;

/**
 * Created by Administrator on 2017/7/6.
 */
public class IndexRoute extends Routes {

    public void config() {
        add("/", IndexController.class);
        add("/Admin", AdminController.class);
    }

}
