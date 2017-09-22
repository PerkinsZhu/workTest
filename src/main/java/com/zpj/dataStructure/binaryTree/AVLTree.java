package com.zpj.dataStructure.binaryTree;

public class AVLTree<T extends Comparable> {

	private Node root;

	public boolean insert(T value) {
		boolean tip = false;
		if (value == null) {
			return tip;
		}
		if (root == null) {
			root = new Node(null, null, null, value);
			tip = true;
		} else {
			try {
				tip = insertToTree02(root, value);
			} catch (Exception e) {
			}
		}
		return tip;
	}

	boolean isRotateRight = false;// 标识是否进行旋转，如果进行旋转在进行递归回溯的时候对balance进行重新计算
	boolean isRotateLeft = false;// 标识是否进行旋转，如果进行旋转在进行递归回溯的时候对balance进行重新计算

	private boolean insertToTree(Node node, T value) throws Exception {
		isRotateLeft = false;
		isRotateRight = false;
		boolean tip = false;
		int age = value.compareTo(node.getData());
		// System.out.println(age);
		/******************** 执行插入操作 *************************************/
		if (value.compareTo(node.getData()) > 0) {
			if (node.getRight() != null) {
				tip = insertToTree(node.getRight(), value);
			} else {
				Node insertNode = new Node(null, null, node, value);
				node.setRight(insertNode);
				tip = true;
			}
			if (tip) {
				if ((isRotateLeft || isRotateRight) && node.getBalance() == 0) {
					throw new Exception();
				} else if (isRotateLeft || isRotateRight) {
					node.setBalance(node.getBalance() + 1);
				} else {
					node.setBalance(node.getBalance() - 1);
				}
			}
		} else if (value.compareTo(node.getData()) < 0) {
			if (node.getLeft() != null) {
				tip = insertToTree(node.getLeft(), value);
			} else {
				Node insertNode = new Node(null, null, node, value);
				node.setLeft(insertNode);
				tip = true;
			}
			if (tip) {
				if ((isRotateLeft || isRotateRight) && node.getBalance() == 0) {
					throw new Exception();
				} else if (isRotateLeft || isRotateRight) {
					node.setBalance(node.getBalance() - 1);
				} else {
					node.setBalance(node.getBalance() + 1);
				}
			}
		} else {
			tip = false;
		}
		/********************* 查找不平衡节点 ************************************/

		/*
		 * if(isRotateLeft){ node.setBalance(node.getBalance()+1); }
		 * if(isRotateRight){ node.setBalance(node.getBalance()-1); }
		 */
		if (node.getBalance() == 0) {
			throw new Exception();
		}

		// while (node != null) {
		if (node.getBalance() == 2) { // 左子树高于右子树
			rotateRight(node);
		} else if (node.getBalance() == -2) {// 右子树高于左子树
			rotateLeft(node);
		}

		// node = node.getFather();

		/*
		 * int balance = node.getData().compareTo(node.getFather().getData());
		 * if(balance < 0){ //插入节点在parent的左子树中
		 * node.getFather().setBalance(node.getBalance() - 1); }else if(balance
		 * > 0){ //插入节点在parent的右子树中
		 * node.getFather().setBalance(node.getBalance() - 1); }else{
		 * //此节点的balance为0，不再向上调整BF值，且不需要旋转 break; }
		 * 
		 * if (node.getFather().getBalance() == 2) { // 左子树高于右子树
		 * rotateRitht(node); } else if (node.getFather().getBalance() == -2)
		 * {// 右子树高于左子树 rotateLeft(node); } node = node.getFather();
		 */

		// }
		return tip;
	}

	private void rotateLeft(Node node) {
		isRotateLeft = true;
		// TODO 判断node 为 root 的情况 如何处理balance还原的问题
		Node childNode = node.getRight();
		/**
		 * 1、判断是否是单链不平衡，如果是，则只能是单右节点树，则直接进行旋转。 2、如果不是单链不平衡树则有四种情况
		 * 
		 * 3、判断是否需要进行两次旋转 if(1){ }else if(2){ .... return; }
		 * 进行3、4情况旋转（1、3、4的第二次旋转操作时一样的，2操作直接完成return。）
		 */
		if (node.getLeft() == null) {// 既然是向左旋，单链的情况只能是左链为null，单链又分左右/右左两种情况
			if (childNode.getLeft() != null) {
				Node grandNode = childNode.getLeft();

				if (node != root) {
					if (node == node.getFather().getRight()) {
						node.getFather().setRight(grandNode);
					} else {
						node.getFather().setLeft(grandNode);
					}
					grandNode.setFather(node.getFather());
				} else {
					grandNode.setFather(null);
					root = grandNode;
				}

				grandNode.setRight(childNode);
				childNode.setFather(grandNode);
				childNode.toLeaf();

				grandNode.setLeft(node);
				node.setFather(grandNode);
				node.toLeaf();

				grandNode.setBalance(0);
			} else {
				if (node != root) {
					if (node == node.getFather().getLeft()) {
						node.getFather().setLeft(childNode);
					} else {
						node.getFather().setRight(childNode);
					}
					childNode.setFather(node.getFather());
				} else {
					childNode.setFather(null);
					root = childNode;
				}
				childNode.setLeft(node);

				node.setFather(childNode);
				node.toLeaf();

				childNode.setBalance(0);
			}
		} else {// 进行双链旋转
		// 双链旋转必有左孙子节点
			Node leftGrandSonNode = childNode.getLeft();
			if (childNode.getBalance() == 1 && leftGrandSonNode.getLeft() != null) {
				// 情况1进行第一次旋转

				node.setRight(leftGrandSonNode);
				leftGrandSonNode.setFather(node);

				childNode.setFather(leftGrandSonNode);
				leftGrandSonNode.setRight(childNode);
				childNode.setLeft(null);

			} else if (childNode.getBalance() == 1 && leftGrandSonNode.getRight() != null) {
				// 情况2进行旋转，两次旋转一起，直接结束
				Node rightGrandSon2Node = leftGrandSonNode.getRight();
				// 进行第一次旋转
				node.setRight(leftGrandSonNode);
				leftGrandSonNode.setFather(node);

				leftGrandSonNode.setRight(childNode);
				childNode.setFather(leftGrandSonNode);

				childNode.setRight(rightGrandSon2Node);
				rightGrandSon2Node.setFather(childNode);
				// 进行第二次旋转
				if (node != root) {
					if (node == node.getFather().getLeft()) {
						node.getFather().setLeft(leftGrandSonNode);
					} else {
						node.getFather().setRight(leftGrandSonNode);
					}
					leftGrandSonNode.setFather(node.getFather());
				} else {
					leftGrandSonNode.setFather(null);
					root = leftGrandSonNode;
				}

				leftGrandSonNode.setLeft(node);
				node.setFather(leftGrandSonNode);
				node.setRight(null);

				node.setBalance(1);
				leftGrandSonNode.setBalance(0);
				childNode.setBalance(0);

				// TODO 进行一步到位优化
				return;
			}
			// 如果没有结束则进行情况3/4初次旋转和情况1的二次旋转 ，此三种旋转操作相同
			childNode = node.getRight();// 更新childNode
			leftGrandSonNode = childNode.getLeft();
			if (node != root) {
				if (node == node.getFather().getLeft()) {
					node.getFather().setLeft(childNode);
				} else {
					node.getFather().setRight(childNode);
				}
				childNode.setFather(node.getFather());
			} else {
				childNode.setFather(null);
				root = childNode;
			}

			childNode.setLeft(node);
			node.setFather(childNode);
			node.setRight(leftGrandSonNode);

			node.setBalance(0);
			childNode.setBalance(0);
		}
	}

	private void rotateRight(Node node) {
		isRotateRight = true;
		Node childNode = node.getLeft();

		if (node.getRight() == null) {// 单链进行右旋
			Node grandNode = childNode.getRight();
			if (grandNode != null) {
				if (node != root) {
					if (node == node.getFather().getLeft()) {
						node.getFather().setLeft(grandNode);
					} else {
						node.getFather().setRight(grandNode);
					}
					grandNode.setFather(node.getFather());
				} else {
					grandNode.setFather(null);
					root = grandNode;
				}

				grandNode.setLeft(childNode);
				childNode.setFather(grandNode);
				childNode.toLeaf();

				node.setFather(grandNode);
				grandNode.setRight(node);
				node.toLeaf();

				grandNode.setBalance(0);
			} else {
				if (node != root) {
					if (node == node.getFather().getLeft()) {
						node.getFather().setLeft(childNode);
					} else {
						node.getFather().setRight(childNode);
					}
					childNode.setFather(node.getFather());
				} else {
					childNode.setFather(null);
					root = childNode;
				}

				node.setFather(childNode);
				childNode.setRight(node);
				node.toLeaf();

				childNode.setBalance(0);
			}
		} else {// 进行双链右旋
			Node rightGrandSonNode = childNode.getRight();
			if (childNode.getBalance() == -1 && rightGrandSonNode.getRight() != null) {// 情况1
				Node rightGrandSon2Node = rightGrandSonNode.getRight();
				// 现在是一次性旋转结束，左旋情况1不同
				if (node != root) {
					if (node == node.getFather().getLeft()) {
						node.getFather().setLeft(rightGrandSonNode);
					} else {
						node.getFather().setRight(rightGrandSonNode);
					}
					rightGrandSonNode.setFather(node.getFather());
				} else {
					rightGrandSonNode.setFather(null);
				}

				rightGrandSonNode.setLeft(childNode);
				rightGrandSonNode.setRight(node);

				childNode.setFather(rightGrandSonNode);
				childNode.setRight(null);

				node.setFather(rightGrandSonNode);
				node.setLeft(rightGrandSon2Node);

				rightGrandSon2Node.setFather(node);

				node.setBalance(0);
				childNode.setBalance(1);
				rightGrandSonNode.setBalance(0);

			} else if (childNode.getBalance() == -1 && rightGrandSonNode.getLeft() != null) {
				Node rightGrandSon2Node = rightGrandSonNode.getLeft();
				if (node != root) {
					if (node == node.getFather().getLeft()) {
						node.getFather().setLeft(rightGrandSonNode);
					} else {
						node.getFather().setRight(rightGrandSonNode);
					}

					rightGrandSonNode.setFather(node.getFather());
				} else {
					rightGrandSonNode.setFather(null);
					root = rightGrandSonNode;
				}
				node.setFather(rightGrandSonNode);
				childNode.setFather(rightGrandSonNode);
				rightGrandSon2Node.setFather(childNode);

				node.setLeft(null);
				rightGrandSonNode.setLeft(childNode);

				childNode.setRight(rightGrandSon2Node);
				rightGrandSonNode.setRight(node);

				node.setBalance(1);
				childNode.setBalance(0);
				rightGrandSonNode.setBalance(0);

			} else {// 执行3、4情况旋转
				if (node != root) {
					if (node == node.getFather().getLeft()) {
						node.getFather().setLeft(childNode);
					} else {
						node.getFather().setRight(childNode);
					}

					childNode.setFather(node.getFather());
				} else {
					childNode.setFather(null);
					root = childNode;
				}
				node.setFather(childNode);
				rightGrandSonNode.setFather(node);

				node.setLeft(rightGrandSonNode);

				childNode.setRight(node);

				node.setBalance(0);
				childNode.setBalance(0);
			}
		}
	}

	public void preorderTraverse() {
		preorderTraverseData(root);
	}

	private void preorderTraverseData(Node node) {
		System.out.print(((Student) node.getData()).toString() + "、");
		if (node.getLeft() != null) {
			preorderTraverseData(node.getLeft());
		}
		if (node.getRight() != null) {
			preorderTraverseData(node.getRight());
		}
	}

	public boolean insertToTree02(Node node, T value) {
		boolean tip = false;
		// 找出插入点
		while (node != null) {
				if (value.compareTo(node.getData()) > 0) {
					if(node.getRight() != null){
						node = node.getRight();
					}else{
						break;
					}
				} else if (value.compareTo(node.getData()) < 0) {
					if(node.getLeft() != null){
						node = node.getLeft();
					}else{
						break;
					}
				}else{
					node = null;
				}
		}
		
		
		// 进行插入操作
		if (node != null && value.compareTo(node.getData()) > 0) {
			node.setRight(new Node(null, null, node, value));
			node = node.getRight();
			tip = true;
		} else if (node != null && value.compareTo(node.getData()) < 0) {
			node.setLeft(new Node(null, null, node, value));
			node = node.getLeft();
			tip = true;
		} else {
			tip = false;
		}
		// 找出最小不平衡点进行旋转
		while (node != null && node != root) {
			Node father = node.getFather();
			if (father.getData().compareTo(node.getData()) > 0) {
				father.setBalance(node.getFather().getBalance() + 1);
			} else if (father.getData().compareTo(node.getData()) < 0) {
				father.setBalance(node.getFather().getBalance() - 1);
			}

			if (father.getBalance() == 0) {
				break;
			}

			if (father.getBalance() == 2) {
				rotateRight(father);
				break;
			} else if (father.getBalance() == -2) {
				rotateLeft(father);
				break;
			}

			node = node.getFather();
		}

		return tip;
	}

}
