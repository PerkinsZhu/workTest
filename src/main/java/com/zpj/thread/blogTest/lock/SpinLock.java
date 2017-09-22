package com.zpj.thread.blogTest.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by PerkinsZhu on 2017/8/16 14:01.
 */

/**
 * 非公平锁，在线程释放锁时候，下一个由哪个线程取得该锁是随机的
 */
public class SpinLock implements Lock{
    private AtomicReference<Thread> sign =new AtomicReference<>();
    public void lock(){
        Thread current = Thread.currentThread();
        while(!sign .compareAndSet(null, current)){
        }
    }
    public void unlock (){
        Thread current = Thread.currentThread();
        sign .compareAndSet(current, null);
    }
}