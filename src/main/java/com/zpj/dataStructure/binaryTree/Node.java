package com.zpj.dataStructure.binaryTree;

public class Node<T extends Comparable> {
	private Node left,right,father;
	private T data;
	private int balance = 0;//记录该节点的平衡度，balance = left.getBalance() - right.getBalance();
	
	public Node(Node left, Node right, Node father, T value) {
		super();
		this.left = left;
		this.right = right;
		this.father = father;
		this.data = value;
	}
	public Node getLeft() {
		return left;
	}
	public void setLeft(Node left) {
		this.left = left;
	}
	public Node getRight() {
		return right;
	}
	public void setRight(Node right) {
		this.right = right;
	}
	public Node getFather() {
		return father;
	}
	public void setFather(Node father) {
		this.father = father;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public void toLeaf() {
		this.setLeft(null);
		this.setRight(null);
		this.balance = 0;
	}
	public boolean isLeaf() {
		return this.getLeft() == null &&this.getRight() == null;
	}
	
}