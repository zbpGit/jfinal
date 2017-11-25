package com.zbp.controllor;

import com.jfinal.core.Controller;
import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;

/**
 * Created by Administrator on 2017/7/1.
 */
public class TestControllor extends Controller {

    public void index(){
        Cache cache = Redis.use("ppx");
        cache.set("zbp","123");
        renderText("reids,ok!");
    }


}
