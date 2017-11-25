package com.zbp.Inerceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

/**
 * 路由级别拦截器
 */
public class RoutesInerceptor implements Interceptor {
    public void intercept(Invocation invocation) {
        System.out.println("路由级别拦截器RoutesInerceptor");
        invocation.invoke();
    }
}
