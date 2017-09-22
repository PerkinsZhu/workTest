package com.zpj.cmdTest;

public class Test {

	public static void main(String[] args) {
		Thread tr1 = new Thread(new MyThread("001"));
		Thread tr2 = new Thread(new MyThread("002"));
		tr1.start();
		tr2.start();
		boolean tip = true;
		try {
			tr1.wait();
			tr2.wait();
			while (true) {
				if (tip) {
					tr1.notify();
					tr2.notify();
					tip = false;
				} else {
					tr1.wait();
					tr2.wait();
					tip = true;
				}
			}

		} catch (InterruptedException e) {
			e.printStackTrace(); 
		}
	}

	static class MyThread implements Runnable {
		private String name;

		MyThread(String name) {
			this.name = name;
		}

		@Override
		public void run() {
			System.out.println("===" + name);
		}
	}
}
