package com.zpj.socket.pingpang.mina;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/***
 @author  Perkins Zhu
 @date  2017年4月13日 上午9:38:21
 */
public class ClientHandler implements IoHandler{
	@Override
	public void sessionCreated(IoSession session) throws Exception {
		System.out.println("--------------sessionCreated-------------");
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		System.out.println("--------------sessionOpened-------------");
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		System.out.println("--------------sessionClosed-------------");
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		System.out.println("--------------sessionIdle-------------");
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		System.out.println("--------------exceptionCaught-------------");
		cause.printStackTrace();
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		System.out.println("--------------Received-------------"+message.toString());
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		System.out.println("--------------messageSent-------------"+message);
	}

}
