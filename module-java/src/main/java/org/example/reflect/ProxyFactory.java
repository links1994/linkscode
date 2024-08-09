package org.example.reflect;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class ProxyFactory implements MethodInterceptor {
    private Object origBean;

    public ProxyFactory(Object origBean) {
        this.origBean = origBean;
    }


    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = method.invoke(origBean, args);
        long costTime = System.currentTimeMillis() - startTime;
        System.out.println(origBean.getClass().getSimpleName() + "#"
                + method.getName() + " cost time: " + costTime);
        return result;
    }
}
