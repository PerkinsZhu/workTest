package com.zpj.thread.testsynchronized;

public class SendCmd {
	private static SendCmd send= new SendCmd();
	private SendCmd() {
	}
	public static SendCmd getInstances(){
		return send;
	}
	
	private String name;
	public void setName(String name) {
		this.name = name;
	}
	
	public synchronized void startRun(String thName){
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(thName+"----------");
	}
	

}
