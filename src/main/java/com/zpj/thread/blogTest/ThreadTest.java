package com.zpj.thread.blogTest;


import org.junit.Test;
import scala.Console;

/**
 * Created by PerkinsZhu on 2017/8/11 16:42.
 */
public class ThreadTest {

    public static void main(String[] args) {
        ThreadTest threadTest = new ThreadTest();
        //所有的这些启动方式的执行体都是在run方法中完成的
        /*threadTest.testLambda();
        threadTest.testRunnableWithAnonymousRunnable();
        threadTest.testRunnableWithAnonymousThread();
        threadTest.testRunnable();
        threadTest.testMyThread();*/
//        threadTest.testStop();
//        threadTest.testWait();
    }



    public void testLambda() {//lambda表达式开启线程
        new Thread(() -> System.out.println("i am lambda Thread....")).start();
    }

    public void testRunnableWithAnonymousThread() {//匿名Thread类开启线程
        new Thread() {
            @Override
            public void run() {
                System.out.println("i am ThreadWithAnoymous");
            }
        }.start();
    }

    public void testRunnableWithAnonymousRunnable() {//匿名Runnable类开启线程
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("i am RunableWithAnoymous");
            }
        });
        thread.start();
    }

    @Test
    public void testRunnable() {//实现Runnable接口
        MyRunnable runable = new MyRunnable();
        Thread thread = new Thread(runable);
        thread.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void testMyThread() {//继承自Thread
        MyThread thread = new MyThread();
        thread.setName("MyThread");
        thread.start();
    }

    @Test
    public void testStartAndRun(){
        MyThread thread = new MyThread();
        thread.setName("MyThread");
        thread.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.stop();
    }

    int account01 = 10;
    int account02= 0;
    Object lock = new Object();




    public void testStop() {
        class StopRunnable implements Runnable {
            @Override
            public void run() {
                //要求 account01 + account02 =10  恒成立
                for(int i =0;i<5;i++){
                    synchronized (lock) {//加锁保证操作的原子性
                        account01--;
                        System.out.println("....."+Thread.currentThread().getName());//为了看到线程停止添加输出线程名称操作
                        sleep(200);//睡眠200ms
                        account02++;
                    }
                }
            }
        }
        Thread thread01 = new Thread(new StopRunnable());
        thread01.setName("thread01");
        Thread thread02 = new Thread(new StopRunnable());
        thread02.setName("thread02");

        thread01.start();
        thread02.start();

        sleep(500);
        thread01.suspend();
        while (true){
            sleep(1000);
            System.out.println("account01: " + account01 + " account02: " + account02+" thread01 isAlive:"+thread01.isAlive()+" thread02 isAlive:"+thread02.isAlive());
        }
    }
    private void sleep(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    int time = 0;
    public void testWait(){
        MyRunnable runner = new MyRunnable();
        Thread thread01 = new Thread(runner);
        thread01.start();

        while(true){
            time ++;
            System.out.println(thread01.isAlive());
            sleep(1000);
            if(time ==2){
                try {
                    synchronized (lock){
                        lock.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    class MyRunnable implements Runnable {
        @Override
        public void run() {
            int time = 0;
            while(true){
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                time++;
                if(time == 7){
                    synchronized (lock){
                        lock.notify();
                }
            }
        }
    }
}


class MyThread extends Thread {//继承Thread
    @Override
    public void run() {
        while(true){
            System.out.println("==== "+Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
}

