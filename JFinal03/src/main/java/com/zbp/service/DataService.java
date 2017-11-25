package com.zbp.service;

import com.jfinal.plugin.activerecord.Db;
import com.zbp.Util.UnifyThrowEcxp;
import org.apache.log4j.Logger;

/**
 * Created by Administrator on 2017/9/20.
 */
public class DataService {

    private static final Logger logger = Logger.getLogger(DataService.class);

    public String sw(){
        logger.info("事物层");
        try {
            Db.update("Update cargo set moeny = moeny + ? where id = ?",200,1);
            Db.update("Update cargo set moeny = moeny- ? where id = ?",300,1);
            return "success";
        }catch (Exception e){
            System.out.println(e.toString());
            throw new RuntimeException(UnifyThrowEcxp.throwExcp(e));
        }
    }
}
