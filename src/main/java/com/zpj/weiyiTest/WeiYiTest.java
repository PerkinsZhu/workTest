package com.zpj.weiyiTest;

import org.junit.Test;

public class WeiYiTest {

	
	@Test
	public void test01(){
	       int number = 30;
	        //原始数二进制
	        printInfo(number);
	        number = number << 2;
	        //左移一位
	        printInfo(number);
	        number = number >> 1;
	        //右移一位
	        printInfo(number);
	        number = number >>> 1;
	        //右移一位
	        printInfo(number);
	    }
	
    private static void printInfo(int num){
        System.out.println(num+"----"+Integer.toBinaryString(num));
    }
}
