package com.gmail.timurworkspace;

import java.util.*;

public class App {

    private static final long secInNanos = 1_000_000_000;

    public static void main(String[] args) {

        MyHashMap hashMap = new MyHashMap(38);
        int keys[] = new int[998];
        int range = 530;
        Map<Integer, Long> map = new HashMap<>(38);

        System.out.println("//Generated a random keys(" + keys.length + ") with range from 1 to "+range+". And put them to custom MyHashMap and original HashMap with key index as a value");

        for (int i = 0; i < keys.length; ++i) {
            int key = new Random().nextInt(range) + 1;
            keys[i] = key;
        }

        speedTest(keys, hashMap, map);

        System.out.println("size of custom MyHashMap = " + hashMap.size() + ", size of original HashMap = " + map.size());

        int key = 2020;
        long value = 1996L;
        System.out.println("//Put value "+value+" with key "+key+"\n//Get value by key "+key);
        try {
            hashMap.put(key, value);
            System.out.println("Value by the key "+key+" is "+hashMap.get(key));
        }catch (KeyNotExistException | MapOverflowException e){
            System.out.println(e.getMessage());
        }

        int nonExistkey = 99999999;
        System.out.println("//Retrieving a value from a nonexistent key: " + nonExistkey);
        try {
            System.out.println("Key = " + nonExistkey + ", value = " + hashMap.get(nonExistkey));
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
        System.out.println("time to fill out the original HashMap: " + (double)duration / secInNanos + " sec");

        startTime = System.nanoTime();

        tester.fillMyHashMap(keys, myHashMap);
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("time to fill out the custom MyHashMap: " + (double)duration / secInNanos + " sec");

        tester.getAllValueHashMap(keys, originalMap);
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("time to get all values from original HashMap: " + (double)duration / secInNanos + " sec");

        startTime = System.nanoTime();

        tester.getAllValueMyHashMap(keys, myHashMap);
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("time to get all values from custom MyHashMap: " + (double)duration / secInNanos + " sec");

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
            } catch (MapOverflowException MapOverflowException) {
                MapOverflowException.getMessage();
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
