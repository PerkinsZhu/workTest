package com.zpj.socket.pingpang.mina;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.Date;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.zpj.mina.BaseConfig;

/***
 * @author Perkins Zhu
 * @date 2017年4月13日 上午9:38:09
 */
public class Client {

	public static void main(String[] args) {
		IoConnector connector = new NioSocketConnector();
		connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory()));
		connector.setHandler(new ClientHandler());
		ConnectFuture future = connector.connect(new InetSocketAddress("127.0.0.1", BaseConfig.PORT));
		future.awaitUninterruptibly();
		IoSession session = future.getSession(); 
		int num = 0;
		try {
			while (true) {
				num++;
				session.write(new Date());
				Thread.sleep(90000000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
