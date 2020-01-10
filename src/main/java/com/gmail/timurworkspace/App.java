package com.gmail.timurworkspace;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class App
{
    public static void main( String[] args )
    {
        MyHashMap hashMap = new MyHashMap(38);
        int keys[] = new int[155];
        Set<Integer> set = new HashSet<>();

        System.out.println("//\tGenerated a random keys("+keys.length+") with range from 1 to 5000. And put them to MyHashMap with key index as a value");

        for (int i = 0; i < keys.length; ++i){
            int key = new Random().nextInt(5000) + 1;
            keys[i] = key;
            set.add(key);
        }

        for (int i = 0; i < keys.length; ++i){
            hashMap.put(keys[i], i);
        }

        System.out.println("//\tList all "+hashMap.size()+" elements of MyHashMap");

        for(Integer i : set){
            System.out.println("key = "+i+" val = "+hashMap.get(i));
        }

        System.out.println("//\tEnd. ");
        System.out.println("//\tPutting elements with the same key thrice: put(99999, 55); put(99999, 100); put(99999, 256);\n//\tand getting value get(99999)");

        hashMap.put(99999, 55);
        hashMap.put(99999, 100);
        hashMap.put(99999, 256);

        System.out.println("key = "+99999+" val = "+hashMap.get(99999));

        System.out.println("//\tGetting value by nonexistent key");

        System.out.println("key = "+77777777+" val = "+hashMap.get(77777777));
        System.out.println("Map size = "+hashMap.size());
    }
}
