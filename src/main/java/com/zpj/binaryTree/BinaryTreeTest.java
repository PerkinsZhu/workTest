package com.zpj.binaryTree;

import org.junit.Test;

public class BinaryTreeTest {

	int temp;
	@Test
	public void testAdd(){
		BinaryTree bt= new BinaryTree();
		System.out.println("初始数据：");
		int  [] array ={26,4,98,55,49,50,27,64,59,91};
		for(int i = 0 ; i<array.length; i++){
		/*	int num = (int) (Math.random()*100);*/
			System.out.print(array[i]+"、");
			bt.add(array[i]);
		}
		temp = 55;
		System.out.println("\n先序遍历");
		bt.preorderTraverse();
		System.out.println("\n中序遍历");
		bt.inorderTraverse();
		System.out.println("\n后序遍历");
		bt.postTraverse();
	/*	System.out.println("\n删除数据："+temp);
		bt.delete(temp);*/
		System.out.println("\n中序遍历");
		bt.inorderTraverse();
		System.out.println("\n循环取出最大值");
		for(int i = 0 ;i<array.length;i++){
			bt.disPlayMax();
		}
		
	}
}
