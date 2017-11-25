package com.zbp.controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.aop.Duang;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.zbp.Inerceptor.ClassInerceptor;
import com.zbp.Inerceptor.InjectorInterceptor;
import com.zbp.Inerceptor.MethodIntreceptor;
import com.zbp.Inerceptor.RoutesInerceptor;
import com.zbp.InerceptorInn.AInerceptor;
import com.zbp.InerceptorInn.BInerceptor;
import com.zbp.ServiceImpl.ServceImpl2;
import com.zbp.ServiceImpl.ServiceImpl;

/**
 * Created by Administrator on 2017/6/21.
 */
@Before(ClassInerceptor.class)
public class IterceptController extends Controller{

    /**
     * 只能清除本级别以上的拦截器
     *@Clear
     *
     * 只会清楚指定的拦截器
     * @Clear（{ClassInerceptor.class}）
     */
    @Clear
    public void index(){
        renderTemplate("index.jsp");
    }

    @Before(MethodIntreceptor.class)
    @Clear({ClassInerceptor.class, RoutesInerceptor.class})
    public void add(){
        renderTemplate("admin.jsp");
    }

    public void all(){
        /*目标加强*/
        System.out.println("=======可以指定方法也可以制定类=======");
        ServiceImpl service = Duang.duang(ServiceImpl.class);
        service.testInject();
        service.commInject();
        /*制定加强*/
        System.out.println("=======指定类加强=======");
        ServceImpl2 impl2 = Duang.duang(ServceImpl2.class,InjectorInterceptor.class);
        impl2.ServceImpl2Test();
        renderTemplate("add.jsp");
    }

    @Before({BInerceptor.class,AInerceptor.class})
    public void inn(){
        renderTemplate("add.jsp");
    }
}
