package com.zbp.Inerceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

/**
 * Created by Administrator on 2017/6/21.
 * 全局拦截器
 */
public class GlobalInterceptor implements Interceptor {

    public void intercept(Invocation invocation) {
        System.out.println("全局拦截器GlobalInterceptor...");
        invocation.invoke();
    }
}
