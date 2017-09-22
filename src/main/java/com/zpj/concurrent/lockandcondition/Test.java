package com.zpj.concurrent.lockandcondition;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/***
 * @author Perkins Zhu
 * @date 2017年5月8日 上午10:06:41
 */
public class Test {
	public static void main(String[] args) {
		Count count = new Count();
		new Thread(new Add(count)).start();
		new Thread(new Draw(count)).start();
	}
}

class Add implements Runnable {
	Count count;
	public Add(Count count) {
		super();
		this.count = count;
	}

	@Override
	public void run() {
		try {
			while (true) {
				Thread.sleep(100);
				count.add(100);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
class Draw implements Runnable {
	Count count;
	public Draw(Count count) {
		super();
		this.count = count;
	}
	
	@Override
	public void run() {
		try {
			while (true) {
				System.out.println("取款：" +200+"  当前余额："+count.get(200));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

class Count {
	private Lock lock = new ReentrantLock();
	private Condition save = lock.newCondition();
	private Condition draw = lock.newCondition();
	private AtomicInteger num1 = new AtomicInteger(0);

	public void add(int num) {
		lock.lock();
		System.out.println("存款：" +num+"  当前余额："+ num1.addAndGet(num));
		draw.signalAll();
		lock.unlock();
	}

	public int get(int num) {
		lock.lock();
		while (num1.get() < num) {
			try {
				draw.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return num1.addAndGet(-num);
	}
}
