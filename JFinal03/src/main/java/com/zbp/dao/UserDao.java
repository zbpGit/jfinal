package com.zbp.dao;

/**
 * Created by Administrator on 2017/6/29.
 */
public class UserDao {

    /*查询全部数据*/
    public static final String quary = "Select * from User order by id desc";
    /*分页*/
    public static final String select = "select *";
    public static final String page = "from User order by id desc";
    /*关联查询*/
    public static final String OneQuery = "select b.*,u.name from user u inner join hobby b on u.id = b.uid where u.id = ?";
}
