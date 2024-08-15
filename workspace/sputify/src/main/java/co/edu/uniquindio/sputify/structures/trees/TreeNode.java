package co.edu.uniquindio.sputify.structures.trees;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TreeNode<T extends Comparable<T>> {
    private T value;
    private TreeNode<T> left;
    private TreeNode<T> right;

    public TreeNode(T value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    @Override
    public String toString() {
        return "TreeNode [value=" + value + "]";
    }
}
