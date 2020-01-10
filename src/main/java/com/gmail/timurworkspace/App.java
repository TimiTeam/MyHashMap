package com.gmail.timurworkspace;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class App
{
    public static void main( String[] args )
    {
        MyHashMap hashMap = new MyHashMap(35);
        int keys[] = new int[50];
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < keys.length; ++i){
            int key = new Random().nextInt(5000);
            keys[i] = key;
            set.add(key);
        }

        for (int i = 0; i < keys.length; ++i){
            hashMap.put(keys[i], i);
        }

        System.out.println("//\tList all elements in MyHashMap");
        for(Integer i : set){
            System.out.println("key = "+i+" val = "+hashMap.get(i));
        }
        System.out.println("//\tEnd of listing elements");

        System.out.println("Map size = "+hashMap.size());

        hashMap.put(99999, 55);

        hashMap.put(99999, 100);

        hashMap.put(99999, 256);

        System.out.println("key = "+99999+" val = "+hashMap.get(99999));

        System.out.println("key = "+77777777+" val = "+hashMap.get(77777777));
        System.out.println("Map size = "+hashMap.size());
    }
}
