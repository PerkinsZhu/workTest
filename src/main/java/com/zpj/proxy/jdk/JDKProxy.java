package com.zpj.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Perkins on 2017/4/2.
 */
public class JDKProxy implements InvocationHandler {
    private Object person;
    public Object getInstance(Object person) {
        this.person = person;
        return Proxy.newProxyInstance(person.getClass().getClassLoader(), person.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("doSomething---------start");
        method.invoke(person, args);
        System.out.println("doSomething---------end");
        return null;
    }
}