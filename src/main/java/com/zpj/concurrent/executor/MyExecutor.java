package com.zpj.concurrent.executor;

import java.util.concurrent.Executor;

/***
 @author  Perkins Zhu
 @date  2017年5月4日 上午8:49:50
 */
public class MyExecutor implements Executor{

	@Override
	public void execute(Runnable task) {
		new Thread(task).start();
	}

}
