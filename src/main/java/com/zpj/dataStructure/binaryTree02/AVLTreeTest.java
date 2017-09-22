package com.zpj.dataStructure.binaryTree02;

import org.junit.Test;

import com.zpj.dataStructure.binaryTree.util.AvlTree;


public class AVLTreeTest {

	@Test
	public void testInsert() {
		int temp = 0;
		AVLTree<Integer> tree = new AVLTree<Integer> ();
		 int array[]={19,15,2,19,0,12,15,2,0,9};
		for (int i = 0; i < 10; i++) {
			int num = (int) (Math.random() * 100);
			System.out.print(num+ ",");
			tree.insert(num);
			temp = num;
		}
		System.out.println();
		tree.remove(temp);
		System.out.println();
	}
	
	
	@Test
	public void testdelete() {
		AVLTree tree = new AVLTree();
		//TODO  左旋的时候旋转出来一个不平衡树18
		int array[]={28,35,5,35,26,30,1,21,18,35,7,30,25,1,7,};
		for (int i = 0; i <array.length; i++) {
			int num = (int) (Math.random() * 20);
			System.out.print(array[i] + ",");
			tree.insert(array[i]);
		}
		System.out.println();
		tree.inorderTraverse();
		tree.remove(12);
		System.out.println();
		tree.inorderTraverse();
	}
	
	@Test
	public void testdelete02() {
		AVLTree tree = new AVLTree();
		int temp =0;
		for (int i = 0; i <15; i++) {
			int num = (int) (Math.random() * 40);
			System.out.print(num+ ",");
			tree.insert(num);
			temp = num;
		}
		System.out.println();
		tree.inorderTraverse();
		tree.remove(temp);
		System.out.println();
		tree.inorderTraverse();
	}
	
	
	
	@Test
	public void testInsert02() {
		AVLTree tree = new AVLTree();
		 int array[]={69,74,81,49,98,81,71,29,19,31};
//		 int array[]={10,11,7,5,6};
//		 int array[]={10,9,11,13,12};
		for (int i = 0; i <array.length; i++) {
			System.out.print(array[i] + ",");
			tree.insert(array[i]);
			System.out.println();
			System.out.println("--------");
		}
		System.out.println();
		tree.inorderTraverse();
	}
	
	
	@Test
	public void testInsert03() {
		AvlTree tree = new AvlTree();
		int array[]={69,74,81,49,98,81,71,29,19,31};
		for (int i = 0; i <array.length; i++) {
			int num = (int) (Math.random() * 20);
			System.out.print(array[i] + ",");
			tree.insert(array[i]);
		}
		System.out.println();
		tree.preorderTraverse();
	}
}
