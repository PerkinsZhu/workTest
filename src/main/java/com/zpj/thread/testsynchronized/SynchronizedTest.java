package com.zpj.thread.testsynchronized;

public class SynchronizedTest implements Runnable{
	private String name ;
	 SendCmd send=SendCmd.getInstances();
	public void setName(String name){
		this.name = name;
	}
	@Override
	public void run() {
			send.setName(this.name);
			System.out.println(this.name+"==");
			send.startRun(this.name);
	}
}
