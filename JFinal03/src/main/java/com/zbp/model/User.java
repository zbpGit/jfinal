package com.zbp.model;

import com.jfinal.plugin.activerecord.Model;

/**
 * Created by Administrator on 2017/6/20.
 */
public class User extends Model<User> {

    private static final long seriaLVersionUID =1L;

    public static final  User dao = new User().dao();

}
