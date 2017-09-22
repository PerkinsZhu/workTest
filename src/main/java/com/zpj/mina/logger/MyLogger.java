package com.zpj.mina.logger;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.WriteRequest;
import org.apache.mina.filter.logging.LoggingFilter;

/***
 @author  Perkins Zhu
 @date  2017年3月31日 上午9:49:42
 */
public class MyLogger extends LoggingFilter{

	@Override
	public void messageReceived(NextFilter nextFilter, IoSession session, Object message) throws Exception {
		System.out.println("----messageReceived"+message);
		super.messageReceived(nextFilter, session, message);
	}

	@Override
	public void messageSent(NextFilter nextFilter, IoSession session, WriteRequest writeRequest) throws Exception {
		System.out.println("----messageSent");
//		super.messageSent(nextFilter, session, writeRequest);
	}

	
}
