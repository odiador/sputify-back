package co.edu.uniquindio.sputify.structures.lists;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularList<T extends Comparable<T>> implements LinkedList<T> {

    private Node<T> head;
    private int size;

    public CircularList() {
        size = 0;
    }

    private class Node<T> {
        T element;
        Node<T> next;

        Node(T element) {
            this.element = element;
        }
    }

    private class CircularListIterator implements Iterator<T> {

        private Node<T> current;
        private int count;

        CircularListIterator(Node<T> start) {
            this.current = start;
            this.count = 0;
        }

        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            T value = current.element;
            current = current.next;
            count++;
            return value;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new CircularListIterator(head);
    }

    @Override
    public void addHead(T element) {
        var node = new Node<>(element);
        if(isEmpty()) node.next = node;
        else{
            node.next = head;
            Node<T> current = head;
            while (current.next != head) {
                current = current.next;
            }
            current.next = node;
        }
        head = node;
        size++;
    }

    @Override
    public void addTail(T element) {
        var node = new Node<>(element);
        if (isEmpty()) {
            head = node;
            node.next = node;
        } else {
            Node<T> current = head;
            while (current.next != head) {
                current = current.next;
            }
            current.next = node;
            node.next = head;
        }
        size++;
    }

    @Override
    public void add(int index, T element) {
        throw new UnsupportedOperationException("Este método no está implementado para listas circulares");
    }

    @Override
    public boolean validIndex(int index) {
        throw new UnsupportedOperationException("Este método no está implementado para listas circulares");
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void removeHead() {
        if (isEmpty()) throw new NoSuchElementException();
        Node<T> current = head;
        while (current.next != head) {
            current = current.next;
        }
        if (current == head) {
            head = null;
        } else {
            head = head.next;
            current.next = head;
        }
        size--;
    }

    @Override
    public void removeTail() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node<T> current = head;
        Node<T> previous = null;
        while (current.next != head) {
            previous = current;
            current = current.next;
        }
        if (previous == null) {
            head = null;
        } else {
            previous.next = head;
        }
        size--;
    }

    @Override
    public void remove(int index) {
        if (isEmpty()) throw new NoSuchElementException();
        if (!validIndex(index)) throw new IndexOutOfBoundsException();
        if (index == 0) removeHead();
        else{
            Node<T> current = head;
            Node<T> previous = null;
            int currentIndex = 0;
            while (currentIndex < index) {
                previous = current;
                current = current.next;
                currentIndex++;
            }
            previous.next = current.next;
        }
        size--;
    }


    @Override
    public void remove(T element) {
        if (isEmpty()) throw new NoSuchElementException("La lista está vacía");
        
        if (head.element.equals(element)) removeHead();
        else{
            Node<T> current = head;
            Node<T> previous = null;

            while (current != head && !current.element.equals(element)) {
                previous = current;
                current = current.next;
            }

            if (current != head && current.element.equals(element)) previous.next = current.next;
            else throw new NoSuchElementException("El elemento no está presente en la lista");
        }
        size--;
    }


    @Override
    public void sort() {
        if (isEmpty() || size == 1) return;
        head = quickSort(head, null);
    }

    private Node<T> quickSort(Node<T> start, Node<T> end) {
        if (start == end || start == null || start == start.next) return start;

        Node<T> pivot = partition(start, end);

        pivot.next = quickSort(pivot.next, end);
        head = quickSort(head, pivot);

        return head;
    }

    private Node<T> partition(Node<T> start, Node<T> end) {
        if (start == end || start == null || start == start.next) return start;

        Node<T> pivot = end;
        Node<T> prev = null;
        Node<T> current = start;
        Node<T> tail = pivot;

        while (current != pivot) {
            if (current.element.compareTo(pivot.element) < 0) {
                if (head == pivot) head = current;
                prev = current;
                current = current.next;
            } else {
                if (prev != null) prev.next = current.next;
                Node<T> temp = current.next;
                current.next = null;
                tail.next = current;
                tail = current;
                current = temp;
            }
        }
        
        if (head == pivot) head = pivot.next;
        tail.next = pivot;
        
        return pivot;
    }


    @Override
    public void print() {
        if (isEmpty()) {
            System.out.println("La lista está vacía");
            return;
        }

        Node<T> current = head;
        do {
            System.out.print(current.element);
            if (current.next != head) {
                System.out.print(" -> ");
            }
            current = current.next;
        } while (current != head);
        System.out.println();
    }

    @Override
    public void clean() {
        head = null;
        size = 0;
    }
}
