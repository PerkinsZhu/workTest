package com.zpj.socket.pingpang.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/***
 * @author Perkins Zhu
 * @date 2017年4月10日 下午3:37:29
 */
public class Server {

	public static void main(String[] args) {
		Selector selector;
		Tip tip = new Tip();
		try {
			ServerSocketChannel channel = ServerSocketChannel.open();
			selector = Selector.open();
			channel.configureBlocking(false);
			channel.socket().bind(new InetSocketAddress(12345));
			channel.register(selector, SelectionKey.OP_ACCEPT);
			new Thread(new writeHandle(selector, tip)).start();
			new Thread(new readHandle(tip)).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class readHandle implements Runnable {
	Tip tip;

	public readHandle(Tip tip) {
		super();
		this.tip = tip;
	}

	@Override
	public void run() {
		System.out.println("=======read ");
		while (true) {
			ByteBuffer readBuffer = getCanReadBuffer();
			String received = readBuffer.asCharBuffer().toString().trim();
			// String received = new String(readBuffer.array()).trim();
			readBuffer.flip();
			// readBuffer.clear();
			System.out.println("=====" + received);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			tip.writeToBufferOne = !tip.writeToBufferOne;
		}
	}

	private ByteBuffer getCanReadBuffer() {
		while (true) {
			if (!tip.isWritting) {
				return tip.writeToBufferOne ? tip.bufferTwo : tip.bufferOne;
			}
		}
	}
}

class writeHandle implements Runnable {
	int num = 0;
	ByteBuffer lastbuffer;
	Selector selector;
	Tip tip;
	int reu = 0;

	public writeHandle(Selector selector, Tip tip) {
		super();
		this.selector = selector;
		this.tip = tip;
		lastbuffer = tip.bufferOne;
	}

	@Override
	public void run() {
		while (true) {
			try {
				reu = selector.select();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Iterator<SelectionKey> ite = selector.selectedKeys().iterator();
			while (ite.hasNext()) {
				SelectionKey key = ite.next();
				ite.remove();
				if (key.isReadable()) {
					// 开始写的时候，不允许从正在写的buffer读取数据
					tip.isWritting = Boolean.TRUE;
					ByteBuffer writeBuffer = getCanWriteBuffer();
					SocketChannel channel = (SocketChannel) key.channel();
					try {
						channel.read(writeBuffer);
						System.out.println("写入次数：" + num++ + "=============" + lastbuffer.equals(writeBuffer));
						lastbuffer = writeBuffer;
					} catch (IOException e) {
						try {
							if (channel != null) {
								channel.close();
							}
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					tip.isWritting = Boolean.FALSE;
				} else if (key.isAcceptable()) {
					handleAccept(key, SelectionKey.OP_READ);
				}
			}
		}
	}

	public void handleAccept(SelectionKey key, int OPERATION) {
		ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
		SocketChannel socketChannel;
		try {
			socketChannel = serverSocketChannel.accept();
			socketChannel.configureBlocking(false);
			socketChannel.register(key.selector(), OPERATION);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private ByteBuffer getCanWriteBuffer() {
		return tip.writeToBufferOne ? tip.bufferOne : tip.bufferTwo;
	}

}

class Tip {
	ByteBuffer bufferOne = ByteBuffer.allocate(512);
	ByteBuffer bufferTwo = ByteBuffer.allocate(512);
	Boolean writeToBufferOne = Boolean.TRUE;
	Boolean isWritting = Boolean.FALSE;
}
