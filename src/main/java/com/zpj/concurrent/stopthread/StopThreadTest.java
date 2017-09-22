package com.zpj.concurrent.stopthread;

/***
 * @author Perkins Zhu
 * @date 2017年5月9日 上午9:26:46
 */
public class StopThreadTest {

	Thread thread = new Thread(new MyRunner());

	public static void main(String[] args) {
		StopThreadTest test = new StopThreadTest();
		test.stop02();
	}

	/**
	 * 测试多个wait一个notify的情况
	 * 测试notify和notifyAll的区别
	 * notify一次只能唤醒一个，notifyAll一次可以唤醒多个wait的线程，所以是可以运行多个线程通过同一个共享变量进行wait的。
	 */
	private void stop02() {
		String str = new String("tip");
		MyThread thread = new MyThread(str,"01");
		thread.start();
		MyThread thread02 = new MyThread(str,"02");
		thread02.start();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		synchronized (str) {
			str.notifyAll();//这里可以区分notify和notifyAll（）的区别
			//str.notify();
			System.out.println("--main ----");
		}
		System.out.println("--------main close");
	}

	/**
	 * 测试wait和notify的使用
	 */
	private void stop01() {
		String str = new String("tip");
		String str2 = new String("tip2");
		MyThread thread = new MyThread(str,"01");
		thread.start();
		synchronized (str) {
			try {
				str.wait();// 注意这里wait的对象和notify的对象，必须是同一个对象才有作用。包括synchronized的锁也要和wait的锁相同
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("--main ----");
		}
		System.out.println("--------main close");
	}
}

class MyRunner implements Runnable {
	@Override
	public void run() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class MyThread extends Thread {
	private String str;
	private String name;

	public MyThread(String str, String name) {
		super();
		this.str = str;
		this.name = name;
	}

	@Override
	public void run() {
		System.out.println(name + "--------start run");
		try {
			synchronized (str) {
				System.out.println(name + "--------wait run");
				str.wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(name + "-------close  run");
	}
}
