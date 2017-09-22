package com.zpj.javaNIO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.junit.Test;

public class NioTest {
	@Test
	public void testRead() {
//		readFile();
		testByteBuffer();
//		testPositation();
	}

	private void readFile() {
		try {
			FileInputStream fis = new FileInputStream("E:/test/outPuTtFile/syso.txt");
			FileOutputStream fos = new FileOutputStream("E:/test/outPuTtFile/syso02.txt");
			FileChannel fc = fis.getChannel();
			FileChannel foc = fos.getChannel();
			ByteBuffer bb = ByteBuffer.allocate(1024);
			int i;
			while (true) {
				i = fc.read(bb);
				bb.flip();
				foc.write(bb);
				bb.clear();
				if (i == -1)
					break;
			}
			foc.close();
			fc.close();
			fis.close();
			fos.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private void testByteBuffer(){
		ByteBuffer bb = ByteBuffer.allocate(100);
		bb.put("hKhiJUiu我ihiuhohui 慧慧huhjljojh 1258，破zhen集合3".getBytes());
		for(int  i = 0;i<10;i++){
			bb.mark();
			bb.reset();
//			bb.flip();
			System.out.println(new String(bb.array()));
			
		}
	}
	
	private void testPositation(){
		ByteBuffer buff = ByteBuffer.wrap("helloworld".getBytes());
		print(buff);
		buff.get();
		buff.get();
		print(buff);
		buff.mark();
		buff.get();
		buff.get();
		buff.reset();
		print(buff);
		buff.get();
		print(buff);
		
	}

	private void print(ByteBuffer buff) {
		System.out.println("position:" + buff.position() + "\t limit:" + buff.limit());
	}
	

}