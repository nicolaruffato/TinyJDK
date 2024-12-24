package test;

import Map.TreeMap;
import org.junit.Test;

import java.util.Random;

public class TreeMapTest {

    @Test
    public void InsertTest() {
        TreeMap<Integer, Integer> t = new TreeMap<Integer, Integer>();
        Random rand = new Random();
        for(int i = 0; i < 10; i++) {
            int tmp = rand.nextInt(1, 100);
            t.put(tmp, tmp+1);
        }
        t.printOrder();
    }

    @Test
    public void RemoveTest() {
        TreeMap<Integer, Integer> t = new TreeMap<Integer, Integer>();
        for(int i = 50; i < 100; i++) {
            t.put(i, i+1);
        }
        for (int i = 0; i < 50; i++) {
            t.put(i, i+1);
        }
        for(int i = 20; i < 80; i++) {
            t.remove(i);
        }
        t.printOrder();
    }


}
