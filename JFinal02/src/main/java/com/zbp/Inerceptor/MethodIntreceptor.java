package com.zbp.Inerceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

/**
 * Created by Administrator on 2017/6/21.
 * 方法级别拦截器
 */
public class MethodIntreceptor implements Interceptor{

    public void intercept(Invocation invocation) {
        System.out.println("方法级别拦截器MethodIntreceptor.......");
        invocation.invoke();
    }
}
