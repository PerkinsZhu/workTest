package com.zpj.Runable;

public class ThreadTest {
	public static void main(String[] args) {
		ThreadTest test = new ThreadTest();
//		test.myThread();
//		test.myRunnable();
		test.forWriteFile();
	}
	
	private void myRunnable02() {
		try {
			MyRunnable myRun = new MyRunnable("myRun");
			Thread thr= new Thread(myRun);
			thr.start();
//			Thread.sleep(5000);
			for(int i = 0;i<100000;i++){
				
			}
			thr.wait(5000);
		} catch (Exception e) {
		}
	}

	private void myThread() {
		String str = new String("123");
		for (int i = 0; i < 100; i++) {
			Thread thread = new MyThread("线程："+i,str);
			thread.start();
		}
	}
	private void myRunnable() {
			MyRunnable myThread01 = new MyRunnable("线程01");
//			MyRunnable myThread02 = new MyRunnable("线程02");
//			MyRunnable myThread03 = new MyRunnable("线程03");
//			MyRunnable myThread04 = new MyRunnable("线程04");
			Thread thread01 = new Thread(myThread01);
//			Thread thread02 = new Thread(myThread02);
//			Thread thread03 = new Thread(myThread03);
//			Thread thread04 = new Thread(myThread04);
			thread01.start();
//			thread02.start();
//			thread03.start();
//			thread04.start();
			try {
				Thread.sleep(5000);
//				stop(thread01, thread02, thread03, thread04);
//				wait(thread01, thread02, thread03, thread04);
//				sleep(thread01, thread02, thread03, thread04);
//				thread01.sleep(5000);
				thread01.stop();
				Thread.sleep(5000);
				thread01.start();
			} catch (InterruptedException e) {
			}
	}

	private void forWriteFile() {
		for (int i = 0; i < 10; i++) {
			MyRunnable myThread01 = new MyRunnable("线程:"+i);
			Thread thread01 = new Thread(myThread01);
			thread01.start();
		}
	}

	private void sleep(Thread thread01, Thread thread02, Thread thread03, Thread thread04) {
		try {
			thread01.sleep(5000);
			thread02.sleep(5000);
			thread03.sleep(5000);
			thread04.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void wait(Thread thread01, Thread thread02, Thread thread03, Thread thread04) throws InterruptedException {
		thread01.wait();
		thread02.wait();
		thread03.wait();
		thread04.wait();
	}

	private void stop(Thread thread01, Thread thread02, Thread thread03, Thread thread04) {
		thread01.stop();
		thread02.stop();
		thread03.stop();
		thread04.stop();
	}
	
}
