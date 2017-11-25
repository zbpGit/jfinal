package com.zbp.ServiceImpl;

import com.jfinal.aop.Before;
import com.zbp.Inerceptor.InjectorInterceptor;

/**
 * serviceImpl使我们需要加强的目标类
 * Created by Administrator on 2017/6/22.
 */
@Before(InjectorInterceptor.class)
public class ServiceImpl {

    public void testInject(){
        System.out.println("testInject");
    }

    public void commInject(){
        System.out.println("commInject");
    }



}
