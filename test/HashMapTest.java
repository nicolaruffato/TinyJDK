package test;

import Collection.ArrayList;
import Collection.LinkedList;
import Map.HashMap;
import Map.TreeMap;
import org.junit.Test;

import java.util.Random;

public class HashMapTest {

    @Test
    public void test() {
        Integer[] test = new Integer[10];
        assert test[0] == null;

    }


    @Test
    public void InsertTest() {
        HashMap<Integer, Integer> t = new HashMap<Integer, Integer>();
        Random rand = new Random();
        for(int i = 0; i < 10; i++) {
            int tmp = rand.nextInt(1, 100);
            t.put(tmp, tmp+1);
        }
        assert t.size() == 10;
    }

    @Test
    public void RemoveTest() {
        HashMap<Integer, Integer> t = new HashMap<Integer, Integer>();
        for(int i = 50; i < 100; i++) {
            t.put(i, i+1);
        }
        for (int i = 0; i < 50; i++) {
            t.put(i, i+1);
        }
        for(int i = 20; i < 80; i++) {
            t.remove(i);
        }
        assert t.size() == 40;
    }
}
