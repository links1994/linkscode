package org.example.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CtrlProxyHandler implements InvocationHandler {

    private Object origBean;

    public CtrlProxyHandler(Object origBean) {
        this.origBean = origBean;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = method.invoke(origBean, args);
        long endTime = System.currentTimeMillis();
        System.out.println(origBean.getClass().getSimpleName() + "#"
                + method.getName() + " cost time: " + (endTime - startTime));
        return result;
    }
}
