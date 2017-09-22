package com.zpj.concurrent.future;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/***
 * @author Perkins Zhu
 * @date 2017年5月4日 上午9:41:31
 */
public class FutureTest {
	static class MyTask implements Callable<Integer> {
		@Override
		public Integer call() throws Exception {
			for (int i = 0; i < 990000000; i++) {// 延时操作
				double num = 0.2 * ((i * 253) / 65865);
//				System.out.println(".......");
			}
			System.out.println("=====call 处理结束！==");
			return 12;
		}
	}

	public static void main(String[] args) {
		 testFuture();
//		testCompletionService();
	}

	private static void testCompletionService() {
		MyTask task = new MyTask();
		ExecutorService exe = Executors.newCachedThreadPool();
		CompletionService<Integer> service = new ExecutorCompletionService<Integer>(exe);
		service.submit(task);
		Future<Integer> future;
		try {
			System.out.println("------等待call处理结束！！");
			future = service.take();//该方法会阻塞到callable方法返回处理结果
			System.out.println("******");
			System.out.println(future.get());
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	/**
	 * future模式使用
	 */
	private static void testFuture() {
		MyTask task = new MyTask();
		ExecutorService exe = Executors.newCachedThreadPool();
		Future<Integer> future = exe.submit(task);
		try {
			System.out.println(future.get(1000, TimeUnit.MILLISECONDS));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
