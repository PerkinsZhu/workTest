package com.zpj.thread.blogTest.lock;

/**
 * Created by PerkinsZhu on 2017/8/16 18:01.
 */

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class MCSLock {//这是通过链表实现对线程的控制的。每过来一个新的线程则把它添加到链表上阻塞进行while循环，当前一个线程结束之后，修改下一个线程的开关，开启下个线程持有锁。
    public static class MCSNode {
        volatile MCSNode next;
        volatile boolean isLocked = true;
    }
    private static final ThreadLocal<MCSNode> NODE = new ThreadLocal<MCSNode>();//这里保存的是当前线程的node，要理解ThreadLocal 的工作机制
    @SuppressWarnings("unused")
    private volatile MCSNode queue;
    private static final AtomicReferenceFieldUpdater<MCSLock, MCSNode> UPDATER = AtomicReferenceFieldUpdater.newUpdater(MCSLock.class, MCSNode.class, "queue");

    public void lock() {
        MCSNode currentNode = new MCSNode();//过来一个新线程创建一个node，同时防止在当前线程的NODE中进行保存。
        NODE.set(currentNode);//注意，这里的NODE存储的数据各个线程中是不共享的
        MCSNode preNode = UPDATER.getAndSet(this, currentNode);//获取前一个node节点，并更新当前节点
        if (preNode != null) {//前一个节点存在说明有线程正在操作临界区资源。则当前线程循环等待
            preNode.next = currentNode;//把当前节点加入到链表中，等待获取资源
            while (currentNode.isLocked) {}//循环等待，直至前一个线程释放资源，修改当前node的isLocked标志位
        }
    }

    public void unlock() {
        MCSNode currentNode = NODE.get();//取出当前线程的node节点
        if (currentNode.next == null) {//如果没有新的线程等待持锁
            if (UPDATER.compareAndSet(this, currentNode, null)) {//把当前node释放，如果成功则结束，如果失败进入else
            } else { //设置失败说明突然有线程在请求临界区资源进行等待。此时有新的线程更新了UPDATER数据。
                while (currentNode.next == null) {}//等待新加入的线程把节点加入链表
                // 此时currentNode.next ！= null 这里理应使用锁资源，而不应该直接结束，不然等待的线程无法获取“钥匙”访问临界区资源。所以添加以下两行代码释放锁资源
                currentNode.next.isLocked = false;//释放新添加线程的等待
                currentNode.next = null;
            }
        } else {
            currentNode.next.isLocked = false;//释放下一个等待锁的线程
            currentNode.next = null;
        }
    }
}
