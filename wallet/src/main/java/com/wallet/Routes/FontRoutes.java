package com.wallet.Routes;

import com.jfinal.config.Routes;
import com.wallet.Controller.WalletController;

/**
 * Created by Administrator on 2017/8/9.
 */
public class FontRoutes extends Routes {

    public void config() {
        add("/", WalletController.class);
    }
}
