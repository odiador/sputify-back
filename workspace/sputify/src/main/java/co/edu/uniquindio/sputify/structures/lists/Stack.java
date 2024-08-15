package co.edu.uniquindio.sputify.structures.lists;

import java.util.NoSuchElementException;

/**
 * @param <T>
 */
public class Stack<T> {

	private StackNode pointer;

	private class StackNode {
		StackNode prev;
		T value;

		StackNode(StackNode prev, T value) {
			this.prev = prev;
			this.value = value;
		}

	}

	public void push(T element) {
		if (pointer == null)
			pointer = new StackNode(null, element);
		else
			pointer = new StackNode(pointer, element);
	}

	public T pop() {
		if (pointer == null)
			throw new NoSuchElementException("");
		T t = pointer.value;
		pointer = pointer.prev;
		return t;
	}

	public boolean isEmpty() {
		return pointer == null;
	}

	public T peek() {
		if (pointer == null)
			throw new NoSuchElementException("");
		return pointer.value;
	}

	private synchronized String getValuesString() {
		StringBuilder sb = new StringBuilder("[");
		Stack<T> newStack = new Stack<T>();
		while (!this.isEmpty())
			newStack.push(this.pop());
		while (!newStack.isEmpty()) {
			T value = newStack.pop();
			sb.append(value.toString());
			if (!newStack.isEmpty())
				sb.append(", ");
			this.push(value);
		}
		sb.append("]");
		return sb.toString();
	}

	@Override
	public String toString() {
		return getValuesString();
	}
}
