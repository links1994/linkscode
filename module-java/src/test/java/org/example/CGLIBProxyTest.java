package org.example;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.core.DefaultGeneratorStrategy;
import net.sf.cglib.core.DefaultNamingPolicy;
import net.sf.cglib.proxy.Enhancer;
import org.example.reflect.ProxyFactory;
import org.example.reflect.UserController;
import org.junit.Test;

import java.io.FileOutputStream;

public class CGLIBProxyTest {

    @Test
    public void testCGLIBProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserController.class);
        enhancer.setCallback(new ProxyFactory(new UserController()));
        UserController userControllerProxy = (UserController) enhancer.create();
        userControllerProxy.login("", "");
    }

    @Test
    public void testCGLIBProxyGenerator() throws Exception {

        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\workstation\\tmp");

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserController.class);
        enhancer.setCallback(new ProxyFactory(new UserController()));
        UserController userControllerProxy = (UserController) enhancer.create();
        userControllerProxy.login("", "");

    }
}
