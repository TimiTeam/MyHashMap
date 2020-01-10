package com.gmail.timurworkspace;

import java.util.*;

public class App {

    public static void main(String[] args) {

        MyHashMap hashMap = new MyHashMap(38);
        int keys[] = new int[122];
        Map<Integer, Long> map = new HashMap<>(38);

        System.out.println("//Generated a random keys(" + keys.length + ") with range from 1 to 5000. And put them to custom MyHashMap and original HashMap with key index as a value");

        for (int i = 0; i < keys.length; ++i) {
            int key = new Random().nextInt(5000) + 1;
            keys[i] = key;
        }

        speedTest(keys, hashMap, map);

        System.out.println("size of custom MyHash = " + hashMap.size() + ", size of original HashMap = " + map.size());

        int key = 99999999;
        System.out.println("//Retrieving a value from a nonexistent key: " + key);
        try {
            System.out.println("Key = " + key + ", value = " + hashMap.get(key));
        } catch (KeyNotExistException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void speedTest(int keys[], MyHashMap myHashMap, Map<Integer, Long> originalMap){
        App tester = new App();
        long endTime;
        long duration;

        long startTime = System.nanoTime();

        tester.fillHashMap(keys, originalMap);
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("time to feel the   HashMap: " + duration);

        startTime = System.nanoTime();

        tester.fillMyHashMap(keys, myHashMap);
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("time to feel the MyHashMap: " + duration);

        tester.getAllValueHashMap(keys, originalMap);
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("time to get all values from   HashMap: " + duration);

        startTime = System.nanoTime();

        tester.getAllValueMyHashMap(keys, myHashMap);
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("time to get all values from MyHashMap: " + duration);

    }

    private  void fillHashMap(int keys[], Map<Integer, Long> map) {
        for (int i = 0; i < keys.length; ++i) {
            map.put(keys[i], i + 0L);
        }
    }

    private  void fillMyHashMap(int keys[], MyHashMap map) {
        for (int i = 0; i < keys.length; ++i) {
            try {
                map.put(keys[i], i + 0L);
            } catch (MapOverflowException mapOverflowExceprion) {
                mapOverflowExceprion.getMessage();
            }
        }
    }

    private  void getAllValueMyHashMap(int keys[], MyHashMap map) {
        for (int i = 0; i < keys.length; ++i) {
            try {
                map.get(keys[i]);
            }
            catch (KeyNotExistException e){
            }
        }
    }

    private  void getAllValueHashMap(int keys[], Map<Integer, Long> map) {
        for (int i = 0; i < keys.length; ++i) {
            map.get(keys[i]);
        }
    }


}
