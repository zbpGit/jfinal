package com.zbp.Interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

/**
 * Created by Administrator on 2017/7/6.
 */
public class GlobalInterceptor implements Interceptor{

    public void intercept(Invocation invocation) {
        invocation.invoke();
    }
}
