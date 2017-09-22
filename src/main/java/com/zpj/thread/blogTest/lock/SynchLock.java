package com.zpj.thread.blogTest.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by PerkinsZhu on 2017/8/19 9:40.
 */
 public  class  SynchLock {
     public synchronized void showInfo01(){//这里synchronized锁的是this对象，也即synchronized(this)
     }
    public void showInfo02(){
        synchronized (this){//这里的this可以替换为任意Object对象。注意是Object对象，基本变量不行。java中字符串是String实例，所以字符串是可以的。
            //doSomething
        }
    }
}


