package com.zpj.thread.blogTest.cooperation;

import scala.Console;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by PerkinsZhu on 2017/8/21 10:07.
 */
public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        test.testWait();
    }
    private void testWait(){
        Object lock = new Object();
        AtomicInteger num = new AtomicInteger(0);
        Thread th01 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    Console.println("I am  Hello ");
                    if(num.getAndIncrement() == 3){
                        synchronized(lock){
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
        th01.start();
        AtomicInteger num01 = new AtomicInteger(0);
        Thread th02 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    if(num01.getAndIncrement() ==5){
                        synchronized (lock){
                            lock.notify();
                        }
                    }
                    Console.println(" I am world ");
                    sleep(1000);
                }
            }
        });
        th02.start();
    }

    private void sleep(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
