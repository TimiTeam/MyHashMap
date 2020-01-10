package com.gmail.timurworkspace;

import java.util.*;

public class App{

    public static void fillHashMap(int keys[], Map<Integer, Long> map){
        for (int i = 0; i < keys.length; ++i){
            map.put(keys[i], i + 0L);
        }
    }


    public static void fillMyHashMap(int keys[], MyHashMap map){
        for (int i = 0; i < keys.length; ++i){
            map.put(keys[i], i + 0L);
        }
    }

    public static void main(String[] args )
    {

        MyHashMap hashMap = new MyHashMap(38);
        int keys[] = new int[1505];
        Map<Integer, Long> map = new HashMap<>(38);

        System.out.println("//\tGenerated a random keys("+keys.length+") with range from 1 to 5000. And put them to custom MyHashMap and original HashMap with key index as a value");

       for (int i = 0; i < keys.length; ++i){
           int key = new Random().nextInt(5000) + 1;
           keys[i] = key;
       }


        long endTime;
        long duration;

        long startTime = System.nanoTime();

        fillHashMap(keys,map);
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("time to feel the   HashMap: "+duration);

        startTime = System.nanoTime();

        fillMyHashMap(keys,hashMap);
        endTime  = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("time to feel the MyHashMap: "+duration);

        System.out.println("size of custom MyHash = "+hashMap.size()+", size of original HashMap = "+map.size());
    }
}
    