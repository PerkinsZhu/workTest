package com.zpj.thread.blogTest.lock;

/**
 * Created by PerkinsZhu on 2017/8/16 14:35.
 */

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 公平锁 谁先到谁先执行
 */
public class TicketLock implements Lock{
    private AtomicInteger serviceNum = new AtomicInteger();
    private AtomicInteger ticketNum = new AtomicInteger();
    private static final ThreadLocal<Integer> LOCAL = new ThreadLocal<Integer>();
    public void lock() {
        int myticket = ticketNum.getAndIncrement();
        LOCAL.set(myticket);
        while (myticket != serviceNum.get()) {
        }
    }
    public void unlock() {
        int myticket = LOCAL.get();
        serviceNum.compareAndSet(myticket, myticket + 1);
    }
}