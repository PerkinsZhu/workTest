package com.zpj.writeToFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileTool {
	public File file;
	FileOutputStream fos;
	FileChannel fc;
	public  void printToFile(String str){
		try {
			Writer out = new FileWriter(file,true);
			out.write(str);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	private static Object obj = new Object();
	public void printToFileByNIO(ByteBuffer bb) {
		try {
				fc.write(bb);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createFile(String fileName) {
		file = new File("E:/test/outPuTtFile/");
		if(!file.exists()){
			file.mkdirs();
		}
		file = new File("E:/test/outPuTtFile/"+fileName);
			try {
				file.delete();
				file.createNewFile();
				fos = new FileOutputStream(file,true);
				fc = fos.getChannel();
			} catch (Exception e) {
		}
	}

}
