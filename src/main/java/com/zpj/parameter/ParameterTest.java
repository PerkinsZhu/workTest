package com.zpj.parameter;

import org.junit.Test;

public class ParameterTest {
	class Person {
		private String name;
	}

	@Test
	public void mainTest() {
		int num = 1;
		System.out.println("原值-----》" + num);
		print(num);
		Person p = new Person();
		print(p);
		System.out.println("原值-----》" + p);
	}

	public void print(Object obj) {
		System.out.println("参数-----》" + obj);
	}

	@Test
	public void testString() {
		String str = "asdf";
		System.out.println("原值-----》" + str);
		printString(str);
		System.out.println("原值---2--》" + str);
	}

	public void printString(String obj) {
		System.out.println("参数-----》" + obj + "-----");
	}

	@Test
	public void testPerson1() {
		Person p = new Person();
		System.out.println("原值-1----》" + p);
		printPerson1(p);
		System.out.println("原值---2--》" + p);
		System.out.println("原值---2--》" + p.name);
	}

	public void printPerson1(Person obj) {
		System.out.println("参数-----》" + obj + "-----");
		obj.name = "as";
		System.out.println("参数-2----》" + obj + "-----");
	}

	@Test
	public void testPerson() {
		Person p = new Person();
		changeName(p);
	}

	public void changeName(Person obj) {
		obj.name = "Tom";
	}

	class Node {
		private String name;
		private Node next;
		private Node father;

		public Node(String name) {
			super();
			this.name = name;
		}
	}

	@Test
	public void testNode() {
		Node node1 = new Node("node1");
		Node node2 = new Node("node2");
		node2.father = node1;
		node1.next = node2;
		printNode(node1.next);
		node2 = null;
	}

	private void printNode(Node node3) {
	/*	node3.father.next = null;*/
		node3 = null;
	}

}
