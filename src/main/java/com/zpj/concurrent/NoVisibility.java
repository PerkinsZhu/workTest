package com.zpj.concurrent;

/**
 * 测试线程重排序
 * 
 * @author Perkins Zhu
 * @date 2017年5月3日 上午8:27:51
 */
public class NoVisibility {

	private static boolean ready;
	private static int num;

	private static class TestThread extends Thread {
		@Override
		public void run() {
			while (!ready)
				Thread.yield();
			System.out.println(num);

		}
	}

	public static void main(String[] args) {
		new TestThread().start();
		num = 42;
		ready = true;
	}
}
