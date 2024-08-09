package org.example;

import org.example.reflect.CtrlProxyHandler;
import org.example.reflect.IUserController;
import org.example.reflect.UserController;
import org.junit.Test;
import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;

public class JDKProxyTest {


    @Test
    public void testJDKProxy() {
        IUserController userController = new UserController();
        CtrlProxyHandler handler = new CtrlProxyHandler(userController);
        IUserController userControllerProxy = (IUserController) Proxy.newProxyInstance(
                handler.getClass().getClassLoader(), UserController.class.getInterfaces(), handler);
        userControllerProxy.login("139********", "******");
    }

    @Test
    public void testJDKProxyGenerator() {

        byte[] byteCodes = ProxyGenerator.generateProxyClass("$Proxy", new Class[]{IUserController.class});

        try (FileOutputStream out = new FileOutputStream("D:\\workstation\\tmp\\$Proxy.class")) {
            out.write(byteCodes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
