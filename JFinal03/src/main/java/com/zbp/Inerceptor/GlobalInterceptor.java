package com.zbp.Inerceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

/**
 * Created by Administrator on 2017/7/3.
 */
public class GlobalInterceptor implements Interceptor {

    public void intercept(Invocation invocation) {
        System.out.println("全局拦截器！");
        invocation.invoke();
    }
}
