package com.zpj.exception;

import org.junit.Test;

public class RunTimeExceptionTest {
	
	@Test
	public void testRunTimeException(){
		sayHello();
	}

	public static void main(String[] args) {
		sayHello();
	}
	private static void sayHello() {

		System.out.println("-------------------");
		throw new RuntimeException("------主动抛出异常！");
		
	}
	
	

}
