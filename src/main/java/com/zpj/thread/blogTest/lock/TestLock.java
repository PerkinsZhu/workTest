package com.zpj.thread.blogTest.lock;

import com.zpj.proxy.jdk.Run;
import scala.Console;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.lang.Thread.sleep;

/**
 * Created by PerkinsZhu on 2017/8/16 14:01.
 */
public class TestLock {
    public static void main(String[] args) {
        TestLock test = new TestLock();
        //        test.testSpinLock();
//        test.testReentrantRWLock();
//        test.testSimple();
//        test.testReentrantLock();
        test.testSemaphore();
        test.shutDown();
    }

    Semaphore semaphore = new Semaphore(1);//同时只允许一个线程可以访问临界区资源
    private void testSemaphore(){
        for(int i = 0; i<5;i++){//开启5个线程竞争资源
            pool.execute(new SemapRunner());
            
        }
    }
    class SemapRunner implements Runnable{
        @Override
        public void run() {
            try {
                Console.println(Thread.currentThread().getName()+"  请求资源");
                semaphore.acquire();//请求资源
                Console.println(Thread.currentThread().getName()+"  获取到资源");
                sleep(2000);
                Console.println(Thread.currentThread().getName()+"  释放资源");
                semaphore.release();//释放资源
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private void testSimple(){
        SimpleRunner runner = new SimpleRunner();
        pool.execute(runner);
        pool.execute(runner);
    }
    int account01 =10;
    int account02 = 0;
    class SimpleRunner implements Runnable{
        @Override
        public void run() {
            while(true){
                synchronized ("lcok"){
                    account01 = account01 -1;
                    account02 = account02 +1;
                    Console.println("account01:"+account01+"  account02:"+account02);
                    sleep(1000);
                }
            }
        }
    }
    private void sleep(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void testReentrantRWLock() {
        MyRunnerForReentrantRWLock run = new MyRunnerForReentrantRWLock();
        for (int i = 0; i < 10; i++) {//开启10个线程测试+
            sleep(10);//睡眠10ms保证线程开启的顺序由1-10依次执行
            pool.execute(run);
        }
    }

    AtomicInteger num = new AtomicInteger(1);//用来切换读写锁测试方法
    ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock(true);//公平读写锁
    private class MyRunnerForReentrantRWLock implements Runnable {
        @Override
        public void run() {
                if(num.getAndIncrement() == 3){
                    lockTest.write();//调用写锁测试
                }else{
                    lockTest.read();//调用读锁测试
                }
        }
    }
    private void shutDown() {
        pool.shutdown();
    }

    private void testReentrantLock() {
        MyRunnerForReentrantLock run = new MyRunnerForReentrantLock();
        for (int i = 0; i < 10; i++) {//开启10个线程进行测试
            pool.execute(run);
        }
    }


    LockTest lockTest = new LockTest();
    class MyRunnerForReentrantLock implements Runnable {
        @Override
        public void run() {
            lockTest.reEnterLock(new AtomicInteger(3));//在run方法中调用reEnterLock()方法测试重入测试
        }
    }

    private void testSynchronized() {
        Thread thread01 = new Thread(new Runnable() {
            LockTest lockTest = new LockTest();

            @Override
            public void run() {
                lockTest.method01();
            }
        });
        Thread thread02 = new Thread(new Runnable() {
            LockTest lockTest = new LockTest();

            @Override
            public void run() {
                lockTest.method02();
            }
        });
        Thread thread03 = new Thread(new Runnable() {
            LockTest lockTest = new LockTest();

            @Override
            public void run() {
                lockTest.method03();
            }
        });
        Thread thread04 = new Thread(new Runnable() {
            LockTest lockTest = new LockTest();

            @Override
            public void run() {
                lockTest.method04();
            }
        });
        thread01.start();
        thread02.start();
        thread03.start();
        thread04.start();
    }

    private void testSpinLock() {

        for (int i = 0; i < 3; i++) {
            MyRunner runner = new MyRunner();
            pool.execute(runner);
        }
        pool.shutdown();
    }

    ExecutorService pool = Executors.newFixedThreadPool(10);
    volatile int num01 = 0;
    volatile int num02 = 20;

    //    volatile Lock lock = new SpinLock();
    volatile Lock lock = new TicketLock();



    class MyRunner implements Runnable {
        @Override
        public void run() {
            lock.lock();
            num01++;
            num02--;
            System.out.println(Thread.currentThread().getName() + "======" + num01 + "==" + num02);
            lock.unlock();
        }
    }

    class LockTest {

        private synchronized void method01() {//这里默认锁的是this对象
            System.out.println("method01");
            sleep(5000);
        }

        private void method02() {
            synchronized (this) {
                System.out.println("method02");
            }
        }

        private void method03() {
            synchronized ("hello") {
                System.out.println("method03");
                sleep(5000);
            }
        }

        private void method04() {
            synchronized ("hello") {
                System.out.println("method04");
            }
        }


        ReentrantLock reentrantLock = new ReentrantLock(true);//使用公平锁ReentrantLock
        private void reEnterLock(AtomicInteger time) {
            reentrantLock.lock();//加锁
            Console.println(Thread.currentThread().getName() + "--" + time);
            try {
                if (time.get() == 0) {
                    return;
                } else {
                    time.getAndDecrement();
                    reEnterLock(time);//这里使用递归来测试重入
                }
            } finally {
                reentrantLock.unlock();//释放锁。注意这里在finally中释放锁避免加锁代码抛出异常导致锁无法释放造成阻塞
            }
        }

        public void read() {//使用读锁
            rwlock.readLock().lock();
            try {
                Console.println(Thread.currentThread().getName()+"------read");
                sleep(2000);
            } finally {
                rwlock.readLock().unlock();
            }
        }
        public void write() {//使用写锁
            rwlock.writeLock().lock();
            try {
                sleep(2000);//模拟写操作
                Console.println(Thread.currentThread().getName()+"------write");
            }finally {
                rwlock.writeLock().unlock();
            }
        }
    }

    private synchronized void addData() {
        num01++;
        num02--;
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + "======" + num01 + "==" + num02);
        }
    }

}
