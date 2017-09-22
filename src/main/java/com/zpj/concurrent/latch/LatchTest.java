package com.zpj.concurrent.latch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/***
 * @author Perkins Zhu
 * @date 2017年5月8日 下午12:17:31
 */
public class LatchTest {
	
	public static void main(String[] args) {
		ExecutorService excutor = Executors.newCachedThreadPool();
//		CountDownLatch latch = new CountDownLatch(6);//注意这里的参数值一定要等于预处理线程个数。大于则无法触发结果线程，小于则提前触发结果线程
		CountDownLatch latch = new CountDownLatch(3);
		Worker w1 = new Worker(latch,"work1");
		Worker w2 = new Worker(latch,"work2");
		Worker w3 = new Worker(latch,"work3");
		Boss bos = new Boss(latch,"boss");
		excutor.submit(w1);
		excutor.submit(w2);
		excutor.submit(w3);
		excutor.submit(bos);
	}

}

class Worker implements Runnable {

	private CountDownLatch latch;
	private String name;

	public Worker(CountDownLatch latch, String name) {
		super();
		this.latch = latch;
		this.name = name;
	}

	@Override
	public void run() {
		System.out.println(name + "----working…………");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		latch.countDown();
	}

}
class Boss implements Runnable{
	private CountDownLatch latch;
	private String name;
	
	public Boss(CountDownLatch latch, String name) {
		super();
		this.latch = latch;
		this.name = name;
	}

	@Override
	public void run() {
		System.out.println("===========Boss is Waiting…………");
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("===========Boss is chacking………………");
	}
}
