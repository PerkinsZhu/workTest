package com.zpj.thread.blogTest.lock;

/**
 * Created by PerkinsZhu on 2017/8/16 14:34.
 */

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class CLHLock implements Lock {
    public static class CLHNode {
        private volatile boolean isLocked = true;
    }

    @SuppressWarnings("unused")
    private volatile CLHNode tail;
    private static final ThreadLocal<CLHNode> LOCAL = new ThreadLocal<CLHNode>();
    private static final AtomicReferenceFieldUpdater<CLHLock, CLHNode> UPDATER = AtomicReferenceFieldUpdater.newUpdater(CLHLock.class, CLHNode.class, "tail");

    public void lock() {
        CLHNode node = new CLHNode();
        LOCAL.set(node);
        CLHNode preNode = UPDATER.getAndSet(this, node);
        if (preNode != null) {
            while (preNode.isLocked) {//每个线程使用前一个的控制器
            }
            preNode = null;
            LOCAL.set(node);
        }
    }

    public void unlock() {
        CLHNode node = LOCAL.get();//这里的node是线程中私有的node，因其存储在ThreadLocal中。所以在这里取出的是lock时存入的node
        if (!UPDATER.compareAndSet(this, node, null)) {//如果有新的线程执行，lock则这里必定为true，因为UPDATER中存储的是最新的node。所以这里将会把老的node.isLocker设置为false释放锁
            node.isLocked = false;
        }
        node = null;
    }
}
