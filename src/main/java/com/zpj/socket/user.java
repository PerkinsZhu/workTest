package com.zpj.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class user{
    public static void main(String[] args)  {
			String s = null;
			Socket mysocket;
			DataOutputStream out = null;
			DataInputStream in = null;
			int i =1;
			try {
			    mysocket= new Socket("172.16.100.63",12345);
			    in = new DataInputStream(mysocket.getInputStream());
			    out = new DataOutputStream(mysocket.getOutputStream());
			    out.write("0|127.0.0.1:7001:1".getBytes());
			    while(true){
			         i = (i+1)%128;
			         s = in.readUTF();
			         out.writeInt(i);
			         System.out.println("客户收到："+s);
			         Thread.sleep(500);
			    }
			}catch(Exception e) {
				e.printStackTrace();
			}
    }
}