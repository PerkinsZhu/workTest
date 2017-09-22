package com.zpj.dataStructure.binaryTree;

import org.junit.Test;

public class UnitTest {

	@Test
	public void testInsert() {
		AVLTree<Student> tree = new AVLTree<Student>();
		// int array[]={9,7,12,11,10};
		for (int i = 0; i < 10; i++) {
			int num = (int) (Math.random() * 20);
			System.out.print(num + ",");
			tree.insert(new Student(num));
		}
		System.out.println();
		tree.preorderTraverse();
	}

	@Test
	public void testInsertArray() {
		AVLTree<Student> tree = new AVLTree<Student>();
//		int array[] = { 3, 15, 6, 15, 6, 9, 5, 12, 14, 7, }; 
		int array[] = {13,11,3,7,6,7,8,4,10,18};
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]+",");
			tree.insert(new Student(array[i]));
		}
		System.out.println();
		tree.preorderTraverse();
	}
}
