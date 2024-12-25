package Map;


import Collection.ArrayList;
import Collection.Collection;
import Collection.LinkedList;
import Collection.List;

import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.Set;

public class HashMap<K, V> extends AbstractMap<K, V> {

    private LinkedList<SimpleEntry<K, V>>[] data;
    private int size;
    private int capacity;

    public HashMap() {
        data = (LinkedList<SimpleEntry<K,V>>[]) Array.newInstance(LinkedList.class, 200);
        size = 0;
        capacity = 200;
    }

    public HashMap(int capacity) {
        if(capacity < 1) throw new IllegalArgumentException();
        data = (LinkedList<SimpleEntry<K,V>>[]) Array.newInstance(LinkedList.class, capacity);
        size = 0;
        this.capacity = capacity;
    }



    @Override
    public V get(K key) {
        V ret = null;
        var bucket = data[key.hashCode()%capacity];
        if(bucket == null) return null;
        for(var entry : bucket) {
            if(entry.getKey().equals(key)) {
                ret = entry.getValue();
                break;
            }
        }
        return ret;
    }

    @Override
    public void clear() {
        data = (LinkedList<SimpleEntry<K,V>>[]) new Object[capacity];
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        var bucket = data[key.hashCode()%capacity];
        if(bucket == null) return false;
        for(var entry : bucket) {
            if(entry.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        for(var bucket : data) {
            if(bucket != null) {
                for (var entry : bucket) {
                    if (entry.getValue().equals(value)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> ret = new HashSet<>();
        for(var bucket : data) {
            if(bucket != null) {
                for(var entry : bucket) {
                    ret.add(entry);
                }
            }
        }
        return ret;
    }

    @Override
    public Set<K> keySet() {
        Set<K> ret = new HashSet<>();
        for(var bucket : data) {
            if(bucket != null) {
                for (var entry : bucket) {
                    ret.add(entry.getKey());
                }
            }
        }
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

    @Override
    public V put(K key, V value) {
        V tmp = get(key);
        if(tmp != null) {
            for(var entry : data[key.hashCode()%capacity]) {
                if(entry.getKey().equals(key)) {
                    entry.setValue(value);
                    break;
                }
            }
        }
        else {
            if(data[key.hashCode()%capacity] == null) {
                data[key.hashCode()%capacity] = new LinkedList<SimpleEntry<K, V>>();
            }
            data[key.hashCode()%capacity].add(new SimpleEntry<K, V>(key, value));
            size++;
        }
        return tmp;
    }

    @Override
    public V remove(K key) {
        V ret = null;
        var bucket = data[key.hashCode()%capacity];
        if(bucket != null) {
            for (var entry : bucket) {
                if (entry.getKey().equals(key)) {
                    ret = entry.getValue();
                    bucket.remove(entry);
                    size--;
                    break;
                }
            }
        }
        return ret;
    }

    @Override
    public boolean replace(K key, V value) {
        if(data[key.hashCode()%capacity] != null) {
            for (var entry : data[key.hashCode() % capacity]) {
                if (entry.getKey().equals(key)) {
                    entry.setValue(value);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Collection<V> values() {
        Collection<V> ret = new ArrayList<V>(capacity);
        for(var bucket : data) {
            if(bucket != null) {
                for (var entry : bucket) {
                    ret.add(entry.getValue());
                }
            }
        }
        return ret;
    }
}
