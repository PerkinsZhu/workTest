package com.zpj.javaNIO.SocketChannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketServer {

	public static void main(String[] args) {
		SocketChannel sc;
		try {
			sc = SocketChannel.open();
			sc.connect(new InetSocketAddress("192.168.3.195", 8990));
			ByteBuffer bb =  ByteBuffer.allocate(512);
			while(true){
				int i = sc.read(bb);
				System.out.println(new String(bb.array()));
				if(i == -1){
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
