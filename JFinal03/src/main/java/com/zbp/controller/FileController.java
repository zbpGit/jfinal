package com.zbp.controller;

import com.jfinal.core.Controller;
import com.jfinal.kit.PathKit;
import com.jfinal.upload.UploadFile;
import com.zbp.Util.FileUtil;
import com.zbp.Util.ZipUtil;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017/7/4.
 */
public class FileController extends Controller {

    public void index(){
        renderTemplate("/Data/ajax.jsp");
    }

    public void update(){
        UploadFile file = getFile("upload");
        Map map = FileUtil.FileUploading(file);
        renderJson(map);
    }

    public void download(){
        /*String path = getPara(0);
        String img = PathKit.getWebRootPath() + "/img/u/" + path.replaceAll("_", "/");
        ZipUtil.zip(img, PathKit.getWebRootPath() + "/img/temp/" + path);*/
        renderFile("");
    }


}
