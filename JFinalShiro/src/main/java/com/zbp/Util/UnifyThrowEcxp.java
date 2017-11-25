package com.zbp.Util;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/5.
 */
public class UnifyThrowEcxp {
    /**
     * 统一抛异常- maps里面可自定义异常参数
     */
    public static String throwExcp(Exception e) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("msg", e.getMessage());
        map.put("text", e.getMessage());
        return   JSONObject.toJSONString(map);
    }
}
