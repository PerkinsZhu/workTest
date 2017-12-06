package com.zpj.thread;

import com.zpj.bean.Student;
import com.zpj.thread.testsynchronized.SynchronizedTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class ThreadTest {

    public static void main(String[] args) {
        testAtomicReferenceFieldUpdater();
    }

    //使用原子更新器进行更新对象的字段
    private static void testAtomicReferenceFieldUpdater(){
        AtomicReferenceFieldUpdater updater=AtomicReferenceFieldUpdater.newUpdater(Dog.class, String.class, "name");
        AtomicReferenceFieldUpdater perUpdater=AtomicReferenceFieldUpdater.newUpdater(Per.class, String.class, "chineseName");
        Dog dog = new Dog();
        updater.compareAndSet(dog,dog.name,"jack") ;//当前的参数和第二个参数相等的时候才会把第三个参数更新为当前参数。
        System.out.println(dog.name);
        Per p = new Per();
        perUpdater.compareAndSet(p,p.chineseName,"LiSi");
        System.out.println(p.chineseName);
    }

    static  class Dog {
        volatile  String name = "dog1";
    }
    static class Per{volatile String chineseName="zhangSan";}

    //ThreadLocal 变量为每一个线程都保存了一个线程唯一的变量，该线程唯一变量在线程之间不共享，且每个线程调用set方法不会影响其他线程。但是对于ThreadLocal变量，每个线程都可以进行访问
    private static void testThreadLocal() {
        MyRunner run = new MyRunner();
        ExecutorService pool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            pool.execute(run);
        }
        System.out.println("------" + students.get());
        pool.shutdown();
    }
    static ThreadLocal<Student> students = new ThreadLocal<Student>() {
        Student stu = new Student();
        @Override
        protected Student initialValue() {
            return stu;
        }
    };
    static AtomicInteger num = new AtomicInteger(0);

    static class MyRunner implements Runnable {
        @Override
        public void run() {
            synchronized (num) {
                if (num.get() == 6 || num.get() == 7) {
                    System.out.println(num.get());
                    students.set(new Student());
                }
                num.getAndIncrement();
            }
            System.out.println(students.get());
        }
    }
    private static void testOther() {
        for (int i = 0; i < 100; i++) {
            SynchronizedTest thread01 = new SynchronizedTest();
            thread01.setName("ThreadName : " + i);
            Thread th01 = new Thread(thread01);
            th01.start();
        }
    }

}
