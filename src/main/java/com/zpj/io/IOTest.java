package com.zpj.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.Buffer;
import java.nio.CharBuffer;

import org.junit.Test;

/***
 * @author Perkins Zhu
 * @date 2017年3月2日 下午4:54:30
 */
public class IOTest {

	@Test
	public void testInputStream() {
		File file = new File("E:/test/outPuTtFile/test.txt");
		try {
			InputStream input = new FileInputStream(file);
			int i = -1;
			byte[] bbuf = new byte[1024];
			while ((i = input.read(bbuf)) != -1) {
				System.out.println(new String(bbuf));
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testOutputStream() {
		File file = new File("E:/test/outPuTtFile/test.txt");
		try {
			OutputStream out = new FileOutputStream(file, true);
			out.write("hhhhhh".getBytes());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testReader() {
		File file = new File("E:/test/outPuTtFile/test.txt");
		try {
			Reader in = new FileReader(file);
			CharBuffer buffer = CharBuffer.allocate(1024);
			in.read(buffer);
			System.out.println(new String(buffer.array()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testBufferedReader() {
		File file = new File("E:/test/outPuTtFile/test.txt");
		try {
			Reader in = new FileReader(file);
			BufferedReader br = new BufferedReader(in);
			String s;
			while ((s = br.readLine()) != null) {
				System.out.println(s);
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
