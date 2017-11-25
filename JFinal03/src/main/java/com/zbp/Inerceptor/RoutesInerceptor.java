package com.zbp.Inerceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

/**
 * Created by Administrator on 2017/6/26.
 */
public class RoutesInerceptor implements Interceptor {
    public void intercept(Invocation invocation) {
        System.out.println("路由拦截器！");
        invocation.invoke();
    }
}
