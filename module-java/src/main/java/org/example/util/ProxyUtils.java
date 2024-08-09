package org.example.util;

import org.example.reflect.IUserController;
import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ProxyUtils {

    public static void main(String[] args) {
        byte[] byteCodes = ProxyGenerator.generateProxyClass("$Proxy", new Class[]{IUserController.class});

        try (FileOutputStream out = new FileOutputStream("D:/workstation/$Proxy.class")) {
            out.write(byteCodes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
