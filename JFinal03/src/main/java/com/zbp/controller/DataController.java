package com.zbp.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.kit.JMap;
import com.jfinal.kit.Kv;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.*;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.zbp.Inerceptor.ErrorInterceptor;
import com.zbp.Util.UnifyThrowEcxp;
import com.zbp.dao.UserDao;
import com.zbp.model.User;
import com.zbp.model.cargo;
import com.zbp.service.DataService;
import org.apache.log4j.Logger;

import javax.xml.transform.sax.SAXResult;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/26.
 */
@Before(value = {ErrorInterceptor.class,Tx.class})
public class DataController extends Controller {

    static DataService dataService = new DataService();

    private static Logger logger = Logger.getLogger(DataController.class);

    /*public void index(){
        renderTemplate("/Data/index.jsp");
    }*/
    /*添加数据*/
    public void doAdd(){
        User user = getModel(User.class,"user");
        user.save();
        renderJson(user);
    }
    /*查询数据*/
    public void query(){
        /*查询全部数据*/
        List<User> userList = User.dao.find(UserDao.quary);
        /*查询id为1的数据*/
        User user = User.dao.findById(1);
        System.out.println(user.getLong("id"));

        /*分页*/
        Page<User> userPage = User.dao.paginate(1,10,UserDao.select,UserDao.page);

        /*只是查询第一条记录*/
        User user1 = User.dao.findFirst(UserDao.quary);
        renderJson(user1);
    }
    /*更新数据*/
    public void update(){
        /*User user = new User();
        user.set("id",1);
        user.set("name","皮皮虾");
        user.update();*/


        /*String sql = User.dao.getSql("user.userUpdate");
        Db.update(sql,"皮皮虾",7);*/

        renderText("修改成功");
    }

    /*删除*/
    public void delete(){
        Integer id =  getParaToInt();
        String sql = User.dao.getSql("user.userDelete");
        Db.update(sql,7);
        renderText("删除成功");
    }

    /*获取数据跳转*/
    public void Ud(){
        Integer id =  getParaToInt(0);
        String name =  getPara(1);
        User user = User.dao.findById(id);
        setAttr("user",user);
        renderTemplate("/Data/Update.jsp");
    }

    /*
    * Db添加
    * 不需要映射表
    * */
    public void Dbsave(){
        Record record = new Record();
        record.set("name","iphone");
        record.set("moeny",123);
        Db.save("cargo",record);
        renderText("Db添加成功");
    }

    /*Db修改*/
    public void Dbupdate(){
        Record record = new Record();
        record.set("id",1);
        record.set("moeny",100);
        Db.update("cargo",record);
        renderText("Db修改成功");
    }

    /*Db编程事物*/
    public void Dbthing(){
        boolean succeed = Db.tx(new IAtom() {
            public boolean run() throws SQLException {
                int count1 =Db.update("Update cargo set moeny = moeny + ? where id = ?",100,1);
                System.out.println(count1);
                int count = Db.update("Update cargo set moeny = moeny- ? where id = ?",300,1);
                System.out.println(count);
                return count == 1 && count1 == 1;
            }
        });
        System.out.println(succeed);
        renderJson("ok");
    }
    /*Db声明事物*/
    public void DbTx(){
        Db.update("Update cargo set moeny = moeny + ? where id = ?",100,1);
        Db.update("Update cargo set moeny = moeny- ? where id = ?",300,1);
        renderText("1");
    }
    /*关联查询*/
    public void OneQuery(){
        List<User> user = User.dao.find(UserDao.OneQuery,1);
        /*String name = user.getStr("interest");*/
        renderJson(user);
    }
    /*sql映射*/
    public void sqlManager(){
        /*直接sql查询*/
        //String sql = User.dao.getSql("user.userList");

        /*传参*/
        /*String sql1 =User.dao.getSql("user.useridAnd1");
        User user = User.dao.findFirst(sql1,1);*/

        /*String sql2 =User.dao.getSql("user.userid2And7");
        List<User> userList = User.dao.find(sql2,1,3);*/

        /*占位符*/
        Kv cond = new Kv().create().set("min_1",1).set("min_2",6);
        SqlPara sqlPara = User.dao.getSqlPara("user.userid2And7New",cond);
        List<User> users = User.dao.find(sqlPara);
        setAttr("list",users).render("/Data/iteration.jsp");

        /*String sql = User.dao.getSql("user.userDelete");
        Db.update(sql,6);*/
    }

    public void tiao(){
        forwardAction("/Data/index");
    }

    public void one(){
        String sql = Db.getSql("user.one");
        Record records = Db.findFirst(sql);
        System.out.println(records.get("uid"));
        String id = records.get("uid");
        setAttr("records",records);
        renderTemplate("/Data/iteration.jsp");
    }

    public void sw(){
        /*logger.info("事物测试");
        try {
            Db.update("Update cargo set moeny = moeny + ? where id = ?",200,1);
            Db.update("Update cargo set moeny = moeny- ? where id = ?",300,1);
            renderText("123");
        }catch (Exception e){
            logger.error("错误",new Exception(e));
            throw new RuntimeException(UnifyThrowEcxp.throwExcp(e));
        }*/
        String jj = dataService.sw();
        System.out.println(jj);
        renderText(jj);
    }

}
