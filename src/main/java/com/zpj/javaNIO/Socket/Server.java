package com.zpj.javaNIO.Socket;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		try {
			ServerSocket serverSocket =new ServerSocket(10086);//1024-65535的某个端口
			while(true){
			Socket socket = serverSocket.accept(); 
			OutputStream os = socket.getOutputStream();
			               // 封装输出流
			                 DataOutputStream dos = new DataOutputStream(os);
			                dos.writeUTF("你好,客户端地址信息: " + socket.getInetAddress()
			                         + "\t客户端通信端口号: " + socket.getPort());
			                dos.writeUTF("i'm a server ,my name is hongten！");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
