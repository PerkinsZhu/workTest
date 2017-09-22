package com.zpj.dataStructure.stack;

import java.util.Stack;

import org.junit.Test;

public class StackTest {

	
	@Test
	public void testStack(){
		Stack stack = new Stack();
		stack.add(1);
		stack.add(2);
		stack.add(3);
		System.out.println(stack.pop());
		System.out.println(stack.pop());
	}
	
}
