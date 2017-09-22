package com.zpj.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 @author  Perkins Zhu
 @date  2017年2月24日 下午3:22:47
 */
public class Log02 {

	final  Logger logger = LoggerFactory.getLogger(Log02.class);
	public  void logToFile(){
		logger.debug("============debug");
		logger.info("============info");
		logger.warn("============warn");
		logger.error("============error");
	}
	
}
