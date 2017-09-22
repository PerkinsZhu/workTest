package com.zpj.acm;

import java.math.BigInteger;
import java.util.Scanner;

public class Acm {

	public static void test1000(String args[]) {
		Scanner cin = new Scanner(System.in);
		while (cin.hasNextInt())
			System.out.println(cin.nextInt() + cin.nextInt());
	}
	public static void test1001(String args[]) {
		Scanner in = new Scanner(System.in);
		long num;
		while (in.hasNext()) {
			long result = 0;
			num = in.nextLong();
			while (num > 0) {
				result += num--;
			}
			System.out.println(result);
			System.out.println();
		}
	}
	public static void test1001_02(String args[]) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			double num = Double.parseDouble(in.next());
			System.out.println((long)((num / 2) * (num + 1)));
			System.out.println();
		}
	}
	public static void test1002(String args[]) {
		Scanner in = new Scanner(System.in);
		int m = in.nextInt();
		for (int i = 1; i <= m; i++) {
			BigInteger n1,n2;
			n1= in.nextBigInteger();
			n2= in.nextBigInteger();
			System.out.println("Case " + i + ":");
			System.out.println(n1 + " + " + n2 + " = " + n1.add(n2));
			if (i != m)
				System.out.println();
		}
	}
}