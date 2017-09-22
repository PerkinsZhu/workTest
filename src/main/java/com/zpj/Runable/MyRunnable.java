package com.zpj.Runable;

import java.nio.ByteBuffer;

import com.zpj.writeToFile.FileTool;

public class MyRunnable implements Runnable {
	private String name;
	private static FileTool file = new FileTool();
	static{
		file.createFile("syso.txt");
	}
	public MyRunnable(String name) {
		super();
		this.name = name;
	}

	@Override
	public void run() {
		while (true) {
//			count();
//			showStr();
			count02();
		}
	}

	private void showStr() {
		try {
			this.wait(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("--------showStr"+num);
		num++;
	}

	private  static int num;
	public  Object obj = new Object();
	private  void count() {
		synchronized(obj){
		num++;
		/*try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
//		System.out.println(name+"-----"+num+"\n");
		file.printToFile(name+"-----"+num+"\n");
		}
	}
	ByteBuffer bb = ByteBuffer.allocate(512);
	private void count02(){
		synchronized(obj){
		num++;
		bb.clear();
		bb.put((name+"-----"+num+"\n").getBytes());
		file.printToFileByNIO(bb);
		}
		/*if(bb.capacity()==bb.limit()){
		}*/
	}
	
}
