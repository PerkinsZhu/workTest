package com.zpj.javaNIO.Socket;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;

public class Client {

	public static void main(String[] args) {
		try {
			for (int i = 0; i < 10; i++) {
				Socket s1 = new Socket("127.0.0.1", 10086);
				InputStream is = s1.getInputStream();
				// 封装输入流
				DataInputStream dis = new DataInputStream(is);
				// 打印服务器端发送过来的信息
				System.out.println(dis.readUTF());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
