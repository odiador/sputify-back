package co.edu.uniquindio.sputify.structures.trees;

public interface Tree<T> extends Iterable<T> {

    void insert(T value);
    boolean exists(T value);
    boolean isEmpty();
}
