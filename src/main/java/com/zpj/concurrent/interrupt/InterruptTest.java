package com.zpj.concurrent.interrupt;

import com.zpj.concurrent.MyRunner;

/***
 * @author Perkins Zhu
 * @date 2017年5月4日 上午11:28:53
 */
public class InterruptTest {

	static MyRunner run = new MyRunner();

	public static void main(String[] args) {
		testInterrupt();
		while(true){
			
		}
	}

	private static void testInterrupt() {
		Thread thread = new Thread(run,"子线程");
		thread.start();
//		thread.interrupt();
	}

}
