package Map;

import com.sun.source.tree.Tree;

import java.util.*;

public class TreeMap<K extends Comparable<? super K>, V> extends AbstractMap<K, V> {

    public static class TreeNode<K, V> extends AbstractMap.SimpleImmutableEntry<K, V> {
        private TreeNode<K, V> left;
        private TreeNode<K, V> right;
        private TreeNode<K, V> parent;

        public TreeNode(K key, V value) {
            super(key, value);
            left = right = parent = null;
        }

        public TreeNode<K, V> getLeft() {
            return left;
        }

        public void setLeft(TreeNode<K, V> left) {
            this.left = left;
        }

        public TreeNode<K, V> getRight() {
            return right;
        }

        public void setRight(TreeNode<K, V> right) {
            this.right = right;
        }

        public TreeNode<K, V> getParent() {
            return parent;
        }

        public void setParent(TreeNode<K, V> parent) {
            this.parent = parent;
        }
    }

    private TreeNode<K, V> root;
    private int size;

    public TreeMap() {
        root = null;
        size = 0;
    }

    private TreeNode<K, V> treeSearch(TreeNode<K, V> node, K key) {
        if(node == null) {
            return null;
        }
        else if(node.getKey().equals(key)) {
            return node;
        }
        else if(key.compareTo(node.getKey()) < 0) {
            return treeSearch(node.getLeft(), key);
        }
        else {
            return treeSearch(node.getRight(), key);
        }
    }

    @Override
    public V get(K key) {
        TreeNode<K, V> tmp = treeSearch(root, key);
        if(tmp == null) {
            return null;
        }
        else {
            return tmp.getValue();
        }
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        return treeSearch(root, key) != null;
    }


    private boolean containsValueHelper(TreeNode<K, V> node, V value) {
        if(node == null) {
            return false;
        }
        return containsValueHelper(node.getLeft(), value) || node.getValue().equals(value)
                || containsValueHelper(node.getRight(), value);
    }

    @Override
    public boolean containsValue(V value) {
        return containsValueHelper(root, value);
    }

    private void entrySetHelper(TreeNode<K, V> node, Set<Entry<K, V>> result) {
        if(node == null) {
            return;
        }
        else {
            entrySetHelper(node.getLeft(), result);
            result.add(node);
            entrySetHelper(node.getRight(), result);
        }
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> ret = new HashSet<>();
        entrySetHelper(root, ret);
        return ret;
    }

    private void keySetHelper(TreeNode<K, V> node, Set<K> result) {
        if(node == null) {
            return;
        }
        else {
            keySetHelper(node.getLeft(), result);
            result.add(node.getKey());
            keySetHelper(node.getRight(), result);
        }
    }

    @Override
    public Set<K> keySet() {
        Set<K> ret = new HashSet<>();
        keySetHelper(root, ret);
        return ret;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    private V treeInsert(TreeNode<K, V> node, K key, V val) {
        if(key.compareTo(node.getKey()) < 0) {
            if(node.getLeft() == null) {
                node.setLeft(new TreeNode<>(key, val));
                node.getLeft().setParent(node);
                size++;
                return null;
            }
            else {
                return treeInsert(node.getLeft(), key, val);
            }
        }
        else if(key.equals(node.getKey())) {
            V tmp = node.getValue();
            node.setValue(val);
            return tmp;
        }
        else {
            if(node.getRight() == null) {
                node.setRight(new TreeNode<K, V>(key, val));
                node.getRight().setParent(node);
                size++;
                return null;
            }
            else {
                return treeInsert(node.getRight(), key, val);
            }
        }
    }

    @Override
    public V put(K key, V value) {
        if(root == null) {
            root = new TreeNode<K, V>(key, value);
            return value;
        }
        else {
            return treeInsert(root, key, value);
        }
    }

    private void transplant(TreeNode<K, V> a, TreeNode<K, V> b) {
        if(a.getParent() == null) {
            root = b;
        }
        else if(a.getParent().getRight() == a) {
            a.getParent().setRight(b);
        }
        else {
            a.getParent().setLeft(a);
        }
        if(b != null) {
            b.setParent(a.getParent());
        }
    }

    private TreeNode<K, V> treeMin(TreeNode<K, V> node) {
        while(node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    //sistemare -> aggiungere ref a root nella firma in modo da poter concentrare la rimozione
    // in qualsiasi sottoalbero
    private V treeDelete(TreeNode<K, V> node) {
        if(node.getRight() == null) {
            transplant(node, node.getLeft());
        }
        else if(node.getLeft() == null) {
            transplant(node, node.getRight());
        }
        else {
            TreeNode<K, V> min = treeMin(node.getRight());
            if(!min.getParent().equals(node)) {
                transplant(min, min.getRight());
                min.setRight(node.getRight());
                min.getRight().setParent(min);
            }
            transplant(node, min);
            min.setLeft(node.getLeft());
            min.getLeft().setParent(min);
        }
        return node.getValue();
    }


    @Override
    public V remove(K key) {
        var tmp = treeSearch(root, key);
        if(tmp == null) {
            return null;
        }
        else {
            return treeDelete(tmp);
        }
    }

    @Override
    public boolean replace(K key, V value) {
        if(treeSearch(root, key) == null) {
            return false;
        }
        else {
            treeInsert(root, key, value);
            return true;
        }
    }

    private void valuesHelper(TreeNode<K, V> node, Collection<V> result) {
        if(node == null) {
            return;
        }
        else {
            valuesHelper(node.getLeft(), result);
            result.add(node.getValue());
            valuesHelper(node.getRight(), result);
        }
    }

    @Override
    public Collection<V> values() {
        Collection<V> ret = new ArrayList<>();
        valuesHelper(root, ret);
        return ret;
    }

    /////////////////////////////////////////

    private void printOrderRec(TreeNode<K, V> n) {
        if(n != null) {
            printOrderRec(n.getLeft());
            System.out.println(n.getValue());
            printOrderRec(n.getRight());
        }
    }

    public void printOrder() {
        printOrderRec(root);
    }



}
