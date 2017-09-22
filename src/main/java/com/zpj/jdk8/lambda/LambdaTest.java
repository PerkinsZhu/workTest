package com.zpj.jdk8.lambda;

import org.junit.Test;

/***
 @author  Perkins Zhu
 @date  2017年5月4日 下午2:45:22
 */
public class LambdaTest {

	public static void main(String[] args) {
		 new Thread(() -> System.out.println("Hello World!")).start();
	}

}
