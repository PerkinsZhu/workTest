package com.zpj.socket.pingpang.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/***
 * @author Perkins Zhu
 * @date 2017年4月11日 上午9:18:47
 */
public class Client {

	public static void main(String[] args) {
		try {
			SocketChannel channel = SocketChannel.open();
			channel.configureBlocking(false);
			Selector selector = Selector.open();
			channel.connect(new InetSocketAddress("192.168.3.195", 12345));
			channel.register(selector, SelectionKey.OP_CONNECT);
			new Thread(new WriteData(selector)).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class WriteData implements Runnable {
	Selector selector;
	   int num = 0;
	public WriteData(Selector selector) {
		this.selector = selector;
	}

	@Override
	public void run() {
		try {
			selector.select();
			Iterator<SelectionKey> keys = selector.keys().iterator();
			while (keys.hasNext()) {
				SelectionKey key = keys.next();
				if (key.isConnectable()) {
					SocketChannel channel = (SocketChannel) key.channel();
	                    //如果正在连接，则完成连接
	                    if(channel.isConnectionPending()){
	                        channel.finishConnect();
	                    }
	                    String str;
	                    while (true) {
	                    	str = "hello " + num+++"--";
                        	channel.write(ByteBuffer.wrap(str.getBytes()));
                        	System.out.println(str);
                        	Thread.sleep(500);
                        }
				}
			}
		} catch (IOException e2) {
			e2.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}