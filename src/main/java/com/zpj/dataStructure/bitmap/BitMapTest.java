package com.zpj.dataStructure.bitmap;

import org.junit.Test;

public class BitMapTest {

	byte [] location = new byte[10000000];
	
	@Test
	public void testBitMap(){
		for(int i =0; i<100;i++){
			System.out.println(hashCode("fdfd"+i,1000000000));
		}
	}
	
	
	public int hashCode(String key, int prime){
	   int hash, i;
	   for (hash=key.length(), i=0; i<key.length(); ++i)
	     hash = (hash<<4)^(hash>>28)^key.charAt(i);
	   return (hash % prime);
	 }
}
