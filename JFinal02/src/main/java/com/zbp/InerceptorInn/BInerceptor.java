package com.zbp.InerceptorInn;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

/**
 * Created by Administrator on 2017/6/24.
 */
public class BInerceptor implements Interceptor {
    public void intercept(Invocation invocation) {
        System.out.println("BInerceptor");
        invocation.invoke();
    }
}
