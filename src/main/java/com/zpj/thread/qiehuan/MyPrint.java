package com.zpj.thread.qiehuan;

/***
 @author  Perkins Zhu
 @date  2017年4月10日 下午3:25:54
 */
public class MyPrint implements Runnable{
	private String name;
	
	
	public MyPrint(String name) {
		super();
		this.name = name;
	}

	@Override
	public void run() {
		for(int i = 0 ; i<100000;i++){
			System.out.println(name+"-----hello world!!!");
		}
	}
	

}
