package com.zpj.concurrent.latch;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/***
 * @author Perkins Zhu
 * @date 2017年5月8日 下午1:07:15
 */
public class CyclicBarrierTest {
	public static void main(String[] args) {
		ExecutorService excutor = Executors.newCachedThreadPool();
		CyclicBarrier barrier = new CyclicBarrier(3);

		CycWork w1 = new CycWork(barrier, "w1");
		CycWork w2 = new CycWork(barrier, "w2");
		CycWork w3 = new CycWork(barrier, "w3");
		
		excutor.execute(w1);
		excutor.execute(w2);
		excutor.execute(w3);
		
		excutor.shutdown();
	}
}

class CycWork implements Runnable {
	static int num =1;
	private CyclicBarrier cyclicBarrier;
	private String name;

	public CycWork(CyclicBarrier cyclicBarrier, String name) {
		this.name = name;
		this.cyclicBarrier = cyclicBarrier;
	}
	@Override
	public void run() {
		System.out.println(name + "正在打桩，毕竟不轻松。。。。。");
		try {
			Thread.sleep(2000*num++);
			System.out.println(name + "不容易，终于把桩打完了。。。。");
			cyclicBarrier.await();//当指定的所有线程都执行到这一步的时候才会进行下面的操作
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(name + "：其他逗b把桩都打完了，又得忙活了。。。");
	}
}