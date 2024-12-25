package Map;

import Collection.Collection;
import java.util.Set;

public interface Map<K, V> {

    static interface Entry<K, V> {
        K getKey();
        V getValue();
        V setValue(V value);
    }

    V get(K key);
    void clear();
    boolean containsKey(K key);
    boolean containsValue(V value);
    Set<Entry<K, V>> entrySet();
    Set<K> keySet();
    int size();
    boolean isEmpty();
    V put(K key, V value);
    V remove(K key);
    boolean replace(K key, V value);
    Collection<V> values();

}
