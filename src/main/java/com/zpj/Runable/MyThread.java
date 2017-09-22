package com.zpj.Runable;

public class MyThread extends Thread {
	public MyThread(String name,String str) {
		super(name);
		this.obj = str;
	}

	private String obj;
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized(obj){
				num++;
				System.out.println(this.getName() + "------" + num);
			}
//			count();
		}
	}

	private int num;

	private synchronized void count() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		num++;
		System.out.println(this.getName() + "------" + num);
	}
}
