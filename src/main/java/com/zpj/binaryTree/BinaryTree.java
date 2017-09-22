package com.zpj.binaryTree;

public class BinaryTree {

	class Node{
		int data;
		Node right;
		Node left;
		Node father;
		Node(Node father,Node left,Node right,int data){
			this.father = father;
			this.left = left;
			this.right = right;
			this.data = data;
		}
	}
	
	Node root = null;
	
	public  boolean add(int data){
		boolean tip = false;
		if(root == null){
			root = new Node(null,null, null , data);
		}else{
			Node result = addSearch(data,root);
			 if( data< result.data){
				result.left = new Node(result,null,null,data);
			}else if(data> result.data){
				result.right = new Node(result,null,null,data);
			}
			 tip = true;
		}
		return tip;
	}
	
	private Node addSearch(int data,Node node){
		if(data == node.data){
			return node;
		}else if(data > node.data){
			if(node.right == null){
				return node;
			}else{
				return addSearch(data,node.right);
			}
		}else{
			if(node.left == null){
				return node;
			}else{
				return addSearch(data, node.left);
			}
		}
	}
	
	
	public Node search(int data){
			return dataSearch(data, this.root);
	}
	
	private Node dataSearch(int data,Node node){
		if(data == node.data){
			return node;
		}else if(data > node.data){
			if(node.right == null){
				return null;
			}else{
				return dataSearch(data,node.right);
			}
		}else{
			if(node.left == null){
				return null;
			}else{
				return dataSearch(data, node.left);
			}
		}
	}
	
	//先序遍历
	public void preorderTraverse(){
		preorderTraverseData(root);
	}
	
	private void preorderTraverseData(Node node){
		System.out.print(node.data+"、");
		if(node.left != null){
			preorderTraverseData(node.left);
		}
		if(node.right != null){
			preorderTraverseData(node.right);
		}
	}
//	中序遍历
	public void inorderTraverse(){
		inorderTraverseData(root);
	}
	private void inorderTraverseData(Node node){
		
		if(node.left != null){
			inorderTraverseData(node.left);
		}
		System.out.print(node.data+"、");
		if(node.right != null){
			inorderTraverseData(node.right);
		}
	}
	
//	中序遍历
	public void postTraverse(){
		postTraverseData(root);
	}
	private void postTraverseData(Node node){
		
		if(node.left != null){
			postTraverseData(node.left);
		}
		if(node.right != null){
			postTraverseData(node.right);
		}
		System.out.print(node.data+"、");
	}
	
	//节点删除
	public boolean delete(int data){
		boolean tip = false;
		Node node = search(data);
		System.out.println("查找到："+node.data);
		if(node != null){
			if(node.left == null || node.right == null ){
				if(isLeaf(node)){
					if(node.father.left == node){
						node.father.left = null;
					}else{
						node.father.right = null;
					}
				}else if(node.left != null){
					if(node.father.left == node){
						node.father.left = node.left;
					}else{
						node.father.right = node.left;
					}
				}else{
					if(node.father.left == node){
						node.father.left = node.right;
					}else{
						node.father.right = node.right;
					}
				}
			}else{
				Node delete = deleteNode(node);
				node.data = delete.data;
				if(delete.father.left == delete){
					delete.father.left = null;
				}else{
					delete.father.right = null;
				}
				tip  = true;
			}
		}
		return tip;
	}
	
	private  Node deleteNode(Node delete){
		Node node = delete.left;
		while(node.right != null){
			node = node.right;
		}
		return node;
	}
	
	
	
	private Node searchMinNode(Node node) {
		if(node.left != null){
			return searchMinNode(node.left);
		}else{
			return node;
		}
	}

	public boolean isLeaf(Node node){
		return node.left == null && node.right == null;
	}
	
	public void disPlayMax(){
		System.out.print(getMax(root).data+"、");
		/*inorderTraverse();*/
	}
	
	private Node getMax(Node node){
		if(node.right != null){
			return getMax(node.right);
		}else{
			if(node == root){
				root = root.left;
				return node;
			}
			node.father.right = node.left;
			if(node.left != null){
				node.left.father = node.father;
			}
			return node;
		}
	}
	
}
