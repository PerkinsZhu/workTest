package com.zpj.thread;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MyThread implements Runnable {

	private String name;
	private int count = 0;
	private String file;

	public MyThread(String file,String name) {
		super();
		this.file = file;
		this.name = name;
	}

	@Override
	public void run() {
		FileOutputStream out = getFileOutputStream(this.file);
		while (true) {
			String str = name + "----" + count++;
			System.out.println(str);
			try {
				out.write(str.getBytes());
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private FileOutputStream getFileOutputStream(String path) {
		try {
			File file = new File(path);
			if (file.exists()) {
				file.delete();
			}
			file.createNewFile();
			FileOutputStream out = new FileOutputStream(file, true);
			return out;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
