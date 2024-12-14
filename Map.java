public interface Map<K, V> {
    /*
    interface Entry<K, V> {
        K getKey();
        V getValue();
        V setValue(V value);
    }
    */
    //Entry<K, V> entry(K key, V value);
    V get(K key);
    void clear();
    boolean containsKey(K key);
    boolean containsValue(V value);
    int size();
    V put(K key, V value);
    V remove(K key);
}
