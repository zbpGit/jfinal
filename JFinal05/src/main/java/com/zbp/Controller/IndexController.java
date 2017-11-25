package com.zbp.Controller;

import com.jfinal.core.Controller;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;
import com.jfinal.upload.UploadFile;
import com.zbp.Util.FileUtil;
import com.zbp.model.Volunteer;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/6.
 */
public class IndexController extends Controller {

    public void index(){
        renderTemplate("/index/index.jsp");
    }

    public void save(){
        UploadFile uploadFile = getFile("print");
        Map all = FileUtil.FileUploading(uploadFile);
        System.out.println(all);
        String file  = (String) all.get("url");
        String title = getPara("title");
        String message = getPara("message");

        Volunteer record = new Volunteer();
        record.set("title",title).set("print",file).set("message",message);
        record.save();

        /*只负责跳转不能带参*/
        /*forwardAction("/all");*/
        /*跳转可以带参*/
        redirect("/all?name="+456);
    }

    public void all(){
        String name = getPara("name");
        String sqlPara = Db.getSql("volunteer.all");
        Volunteer records = Volunteer.dao.findFirst(sqlPara);
        renderJson(records);
    }

}
