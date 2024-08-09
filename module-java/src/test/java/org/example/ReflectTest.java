package org.example;

import org.example.pojo.Person;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ReflectTest<T> {

    @Test
    public void testReflectionAttack() throws Exception {
        Class<?> clazz = Class.forName("org.example.pojo.Person");

        Constructor<?> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        Person person = (Person) constructor.newInstance();

        Field field = clazz.getDeclaredField("age");
        field.setAccessible(true);
        field.setInt(person, 20);

        Method method = clazz.getDeclaredMethod("print");
        method.setAccessible(true);
        method.invoke(person);
    }
}
