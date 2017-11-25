package com.wallet.RenderFactory;

import com.jfinal.render.HtmlRender;
import com.jfinal.render.Render;
import com.jfinal.render.RenderFactory;

/**
 * Created by Administrator on 2017/8/19.
 */
public class MyBeetlRenderFactory extends RenderFactory {

    @Override
    public Render getRender(String view) {
        return new HtmlRender(view);
    }


}
