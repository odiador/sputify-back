package co.edu.uniquindio.sputify.structures.trees;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

import co.edu.uniquindio.sputify.structures.lists.Stack;

public class BinaryTree<T extends Comparable<T>> implements Tree<T> {
	private TreeNode<T> root;

	public BinaryTree() {

	}

	public BinaryTree(TreeNode<T> root) {
		this.root = root;
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public void insert(T value) {
		Objects.requireNonNull(value);
		if (isEmpty())
			this.root = new TreeNode<T>(value);
		insert(root, value);
	}

	public void put(T value) {
		Objects.requireNonNull(value);
		if (isEmpty())
			throw new NoSuchElementException("The element doesn't exists in the list");
		put(root, value);
	}

	private void insert(TreeNode<T> node, T value) {
		if (value.compareTo(node.getValue()) > 0) {
			if (node.getRight() == null) {
				node.setRight(new TreeNode<T>(value));return;}
			insert(node.getRight(), value);
		} else if (value.compareTo(node.getValue()) < 0) {
			if (node.getLeft() == null) {
				node.setLeft(new TreeNode<T>(value));
				return;
			}
			insert(node.getLeft(), value);
		}
	}

	private void put(TreeNode<T> node, T value) {
		if (value.compareTo(node.getValue()) > 0) {
			if (node.getRight() == null) throw new NoSuchElementException("The element doesn't exists in the list");
			put(node.getRight(), value);
		} else if (value.compareTo(node.getValue()) < 0) {
			if (node.getLeft() == null) throw new NoSuchElementException("The element doesn't exists in the list");
			put(node.getLeft(), value);
		} else
			node.setValue(value);
	}

	@Override
	public boolean exists(T value) {
		return exists(root, value);
	}

	private boolean exists(TreeNode<T> node, T value) {
		if (node == null)
			return false;
		if (value.compareTo(node.getValue()) == 0)
			return true;
		if (value.compareTo(node.getValue()) < 0)
			return exists(node.getLeft(), value);
		else
			return exists(node.getRight(), value);
	}

	public void printPreOrder() {
		printPreOrder(root);
	}

	private void printPreOrder(TreeNode<T> node) {
		if (node == null)
			return;
		System.out.println(node.getValue());
		printPreOrder(node.getLeft());
		printPreOrder(node.getRight());
	}

	public void printInOrder() {
		printInOrder(root);
	}

	private void printInOrder(TreeNode<T> node) {
		if (node == null)
			return;
		printInOrder(node.getLeft());
		System.out.println(node.getValue());
		printInOrder(node.getRight());
	}

	public void printPostOrder() {
		printPostOrder(root);
	}

	private void printPostOrder(TreeNode<T> node) {
		if (node == null)
			return;
		printPostOrder(node.getLeft());
		printPostOrder(node.getRight());
		System.out.println(node.getValue());
	}

	@Override
	public Iterator<T> iterator() {
		return new BinaryTreeIterator(root);
	}

	private class BinaryTreeIterator implements Iterator<T> {

		private Stack<TreeNode<T>> stack = new Stack<TreeNode<T>>();

		public BinaryTreeIterator(TreeNode<T> root) {
			addAllLeft(root);
		}

		private void addAllLeft(TreeNode<T> node) {
			while (node != null) {
				stack.push(node);
				node = node.getLeft();
			}
		}

		@Override
		public boolean hasNext() {
			return !stack.isEmpty();
		}

		@Override
		public T next() {
			if (stack.isEmpty()) throw new NoSuchElementException();
			TreeNode<T> node = stack.pop();
			addAllLeft(node.getRight());
			return node.getValue();
		}

	}
}
