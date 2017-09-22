package com.zpj.dataStructure.LinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements Iterable<T> {

	private static class Node<T> {
		Node<T> previous;
		Node<T> next;
		T data;

		public Node(Node<T> previous, Node<T> next, T data) {
			super();
			this.previous = previous;
			this.next = next;
			this.data = data;
		}
	}

	private Node<T> head = new Node<T>(null, null, null);
	private int size = 0;

	public LinkedList() {
		head.previous = head.next = head;
	}

	public boolean add(T data) {
		return addData(data, head);
	}

	private boolean addData(T data, Node<T> node) {
		if (data == null) {
			return false;
		}
		Node<T> tempNode = new Node<T>(node, node.next, data);
		node.next.previous = tempNode;
		node.next = tempNode;
		size++;
		return true;
	}

	public T get(int index) {
		Node<T> node = head.next;
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException("");
		if (index < (size >> 1)) {
			for (int i = 0; i < index; i++) {
				node = node.next;
			}
		} else {
			for (int i = size; i > index; i--) {
				node = node.previous;
			}
		}
		return node.data;
	}

	private class ListItr implements Iterator<T> {
		private Node<T> lastReturnNode;
		private Node<T> nextNode;
		private int nextIndex;

		public ListItr(int index) {
			if (index < 0 || index > size)
				throw new IndexOutOfBoundsException("Index:" + index + ";size:" + size);
			if (index < (size >> 1)) {
				nextNode = head.next;
				for (nextIndex = 0; nextIndex < index; nextIndex++) {
					nextNode = nextNode.next;
				}
			} else {
				nextNode = head.previous;
				for (nextIndex = size; nextIndex > index; nextIndex--) {
					nextNode = nextNode.previous;
				}
			}
		}

		@Override
		public boolean hasNext() {
			return nextIndex != size;
		}

		@Override
		public T next() {
			if (nextIndex == size)
				throw new NoSuchElementException();
			lastReturnNode = nextNode;
			nextNode = nextNode.next;
			nextIndex++;
			return lastReturnNode.data;
		}

		@Override
		public void remove() {
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new ListItr(0);
	}

}
