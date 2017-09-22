package com.zpj.writeToFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.junit.Test;

public class TestFileTool {
	@Test
	public void testFileTool() {
		FileTool tool = new FileTool();
		tool.createFile("test.txt");
		
		FileTool tool02 = new FileTool();
		tool02.createFile("test.txt");
		
		tool02.printToFile("-----------tool02----");
		tool.printToFile("-------tool--------");
	}
	
	
	
	@Test
	public void testFileTool02() {
		Writer out;
		try {
			out = new FileWriter(new File("E:/test/outPuTtFile/test.txt"),true);
			while(true)
//			for(int i =0;i<1000;i++)
			{
			out.write("stt========1233==");
			out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
