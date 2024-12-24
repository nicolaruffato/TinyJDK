package Map;

import com.sun.source.tree.Tree;

import java.util.Collection;
import java.util.List;
import java.util.Set;

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

    @Override
    public boolean containsValue(V value) {
        return false;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return Set.of();
    }

    @Override
    public Set<K> keySet() {
        return Set.of();
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
        return treeInsert(root, key, value);
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
        if(node.getRight() == null && node.getLeft() == null) {
            transplant(node, null);
        }
        else if(node.getRight() == null) {
            transplant(node, node.getLeft());
        }
        else if(node.getLeft() == null) {
            transplant(node, node.getRight());
        }
        else {
            TreeNode<K, V> min = treeMin(node.getRight());
            V tmp = min.getValue();
            min.setValue(node.getValue());
            node.setValue(tmp);
            return treeDelete(min);
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

    @Override
    public Collection<V> values() {
        return List.of();
    }
}
