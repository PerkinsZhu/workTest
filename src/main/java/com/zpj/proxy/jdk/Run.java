package com.zpj.proxy.jdk;
/**
 * Created by Perkins on 2017/4/2.
 */
import java.lang.reflect.Method;
public class Run {

    private Method method;

    public static void main(String[] args) {
//        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
       Person person = (Person) new JDKProxy().getInstance(new MrLi());
       Method [] methods = person.getClass().getMethods();
       for(int i =0; i<methods.length;i++){
           System.out.println(methods[i].getName());
       }
        person.doWork();
    }
}