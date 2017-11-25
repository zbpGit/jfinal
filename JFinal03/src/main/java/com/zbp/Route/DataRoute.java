package com.zbp.Route;

import com.jfinal.config.Routes;
import com.zbp.Inerceptor.RoutesInerceptor;
import com.zbp.controller.DataController;
import com.zbp.controller.FileController;
import com.zbp.controller.SessController;

/**
 * Created by Administrator on 2017/6/26.
 */
public class DataRoute extends Routes {
    public void config() {
        setBaseViewPath("/Data");
        addInterceptor(new RoutesInerceptor());
        add("/", DataController.class);
        add("/Sess", SessController.class);
        add("/File", FileController.class);
    }
}
