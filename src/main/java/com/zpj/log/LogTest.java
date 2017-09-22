package com.zpj.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * @author Perkins Zhu
 * @date 2017年2月24日 下午2:15:08
 */
public class LogTest {
	private Logger logger = LoggerFactory.getLogger(LogTest.class);

	public static void main(String[] args) {
		new LogTest().testLog();
	}

	public void testLog() {
		logger.debug("----------debug");
		logger.info("-----------info");
		logger.warn("-----------warn");
		logger.error("--------error");
		new Log02().logToFile();
		 Thread th = new Thread(new Runnable(){
			@Override
			public void run() {
				logger.error("--------error");
			}
		 } );
		 th.start();
	}

}
