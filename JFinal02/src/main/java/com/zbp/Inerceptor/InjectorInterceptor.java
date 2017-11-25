package com.zbp.Inerceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

/**
 * Created by Administrator on 2017/6/22.
 * 业务拦截器
 */
public class InjectorInterceptor implements Interceptor {

    public void intercept(Invocation invocation) {
        System.out.println("业务拦截器InjectorInterceptor...");
        invocation.invoke();
    }

}
