package com.zpj.acm;

import java.util.Scanner;

import org.junit.Test;

public class AcmTest {
	@Test
	public void testMap() {
		/*
		 * map中如果不存在对应key的value则返回null Map<String,Integer>result = new
		 * HashMap<String , Integer>(); System.out.println(result.get("sdf"));
		 */
		double D= 30;
		 System.out.println(100*(1-D/120.0)*(1-D/120.0)); 
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNextLine()) {
			String temp =in.nextLine();
			System.err.println("---"+temp);
		}
	}
}
