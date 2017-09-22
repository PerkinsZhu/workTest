package com.zpj.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.zpj.concurrent.executor.MyExecutor;

/***
 * @author Perkins Zhu
 * @date 2017年5月4日 上午8:52:26
 */
public class MyTest {
	Runnable run = new Runnable() {
		@Override
		public void run() {
			System.out.println("===============");
		}

	};
	@Test
	public void testExecutor() {
		MyExecutor exe = new MyExecutor();
		// 通过该方法可以让函数立即返回，无法阻塞等待run方法的执行
		exe.execute(run);

	}

	@Test
	public void testExecutors() {
		// 使用线程池来执行
		ExecutorService exe = Executors.newFixedThreadPool(5);

		exe.submit(run);
	}
	
	@Test
	public void testScheduledThreadPoolExecutor(){
		new ScheduledThreadPoolExecutor(2).scheduleAtFixedRate(run, 1000, 1000,TimeUnit.MILLISECONDS );
	}
	
	public static void main(String[] args){
		MyTest test = new MyTest();
		test.testScheduledThreadPoolExecutor();
	}

}
