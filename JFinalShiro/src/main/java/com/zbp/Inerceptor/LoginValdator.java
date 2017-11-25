package com.zbp.Inerceptor;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

/**
 * Created by Administrator on 2017/7/1.
 * 校验拦截器Validator
 */
public class LoginValdator extends Validator {
    protected void validate(Controller controller) {
        validateString("str",3,10,"errorMsgKey","请输入最小3个字符，最大10个字符！");
    }

    protected void handleError(Controller controller) {
        controller.keepPara("str");
        controller.renderTemplate("/Data/login.jsp");
    }
}
