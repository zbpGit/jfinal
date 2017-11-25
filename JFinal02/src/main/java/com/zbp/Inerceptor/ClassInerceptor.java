package com.zbp.Inerceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

/**
 * Created by Administrator on 2017/6/21.
 * 类级别拦截器
 */
public class ClassInerceptor implements Interceptor {

    public void intercept(Invocation invocation) {
        System.out.println("类级别拦截器ClassInerceptor......");
        invocation.invoke();
    }

}
