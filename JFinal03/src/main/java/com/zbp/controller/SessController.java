package com.zbp.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.jfinal.plugin.ehcache.CacheInterceptor;
import com.jfinal.plugin.ehcache.CacheName;
import com.jfinal.plugin.ehcache.EvictInterceptor;
import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;
import com.jfinal.upload.UploadFile;
import com.zbp.Inerceptor.LoginValdator;
import com.zbp.model.User;

import java.util.*;

/**
 * Created by Administrator on 2017/7/1.
 */
public class SessController extends Controller {

    public void index(){
        renderTemplate("/Data/ajax.jsp");
    }

    /*redis数据添加*/
    public void reSava(){
        Cache cache = Redis.use("zbp");
        //存储字符串
        cache.set("zzz","123");
        String zzz = cache.get("zzz");
        //存储列表数据
        cache.lpush("zxc","z");
        cache.lpush("zxc","x");
        cache.lpush("zxc","c");
        List<String> list = cache.lrange("zxc",0,5);
        //清除数据
        cache.del("zzz");
        //查询所有的
        Set<String> key = cache.keys("*");
        Iterator<String> iterator = key.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        


        renderText("reids,ok!");
    }

    @Before(LoginValdator.class)
    public void doValidator(){
        renderTemplate("/Data/index.jsp");
    }

    /**
     *@Before(CacheInterceptor.class)开启缓存
     * @Before(EvictInterceptor)清除缓存
     *@CacheName("userList")设置缓存的名字
     */
    @Before(CacheInterceptor.class)
    @CacheName("userList")
    public void SessList(){
        String sql = User.dao.getSql("user.userList");
        List<User> users = User.dao.find(sql);
        renderJson(users);
    }

    @Before(EvictInterceptor.class)
    @CacheName("userList")
    public void update(){
        String sql = User.dao.getSql("user.userUpdate");
        Db.update(sql,"lalal",6);
    }
    /*ajax*/
    public void ajax(){
        String name = getPara("name");
        String password = getPara("password");
        Map map = new TreeMap();
        map.put("name",name);
        map.put("password",password);
        renderJson(map);
    }

    public void AllName(){
        String sql = User.dao.getSql("user.UserName");
        List<Record> records = Db.find(sql,"%"+"皮"+"%");
        renderJson(records);
    }


}
