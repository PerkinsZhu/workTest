package com.zpj.socket.pingpang.mina;

import java.util.Date;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/***
 @author  Perkins Zhu
 @date  2017年4月13日 上午8:31:47
 */
public class ServerHandler implements IoHandler{
	MessageCollection mc  ;
	Long startTime ; 
	Long endTime ; 
	
	
	public ServerHandler(MessageCollection mc) {
		super();
		this.mc = mc;
	}
	@Override
	public void sessionCreated(IoSession session) throws Exception {
		System.out.println("--------------sessionCreated-------------");
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		startTime	= new Date().getTime();
		System.out.println("--------------sessionOpened-------------");
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		
		System.out.println("--------------sessionClosed-------------"+(new Date().getTime())+"---"+(startTime));
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
		//通过TCP工具发送时需要末尾添加回车符 发送时换行符才能触发该事件
//		mc.getWriteBuffer().append(message);
		System.out.println(message.getClass());
//		System.out.println("--------------Received-------------"+message.toString());
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		System.out.println("--------------messageSent-------------"+message);
	}

}
