package com.zpj.unicode;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;

import org.junit.Test;

public class UnicodeTest {

	@Test
	public void testUTF8Code() throws UnsupportedEncodingException {
		char [] chars={'伾','b'};
		for(int i = 0;i<chars.length;i++){
			byte[] array = (chars[i]+"").getBytes("UTF-8");
		System.out.println(binary(array,2));
			String str = binary(array,10);
			int code = Integer.parseInt(str);
			System.out.println("code:"+code);
			byte [] temp=longToBytes(code);
			for(int j = 0; j<temp.length;j++){
				System.out.print(temp[j]);
			}
			System.out.println("---------------------");
			testToChar(temp);
		}
	}
    public static byte[] longToBytes(long n) {  
        byte[] b = new byte[8];  
        b[7] = (byte) (n & 0xff);  
        b[6] = (byte) (n >> 8 & 0xff);  
        b[5] = (byte) (n >> 16 & 0xff);  
        b[4] = (byte) (n >> 24 & 0xff);  
        b[3] = (byte) (n >> 32 & 0xff);  
        b[2] = (byte) (n >> 40 & 0xff);  
        b[1] = (byte) (n >> 48 & 0xff);  
        b[0] = (byte) (n >> 56 & 0xff);  
        return b;  
    }  
    public static byte[] int2byte(int res) {  
    byte[] targets = new byte[4];  
      
    targets[0] = (byte) (res & 0xff);// 最低位   
    targets[1] = (byte) ((res >> 8) & 0xff);// 次低位   
    targets[2] = (byte) ((res >> 16) & 0xff);// 次高位   
    targets[3] = (byte) (res >>> 24);// 最高位,无符号右移。   
    return targets;   
    }   
	  /** 
     * 将byte[]转为各种进制的字符串 
     * @param bytes byte[] 
     * @param radix 基数可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制 
     * @return 转换后的字符串 
     */  
    public  String binary(byte[] bytes, int radix){  
        return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数  
    }  
    
//    @Test
    public void testToChar(byte[] array) throws UnsupportedEncodingException{
    	System.out.println(new String(array,"utf-8"));
    }
    
}
