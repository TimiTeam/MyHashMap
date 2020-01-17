package com.gmail.timurworkspace;

import com.gmail.timurworkspace.HashMap.MyHashMap;

import java.util.*;

public class App {


    public static List<Integer> createKeys(int len){
        List<Integer> ret = new ArrayList<>(len);
        for(int i = 0; i < len; ++i){
            ret.add(new Random().nextInt() + 43);
        }
        return ret;
    }

    public static void main(String[] args) {

        Map<Integer, String> originalMap = new HashMap<>(12);
        MyHashMap <Integer, String> customMap = new MyHashMap<>(12);

        int containerLen = 67;

        List<Integer> key = createKeys(containerLen);


        for(int i = 0; i < containerLen; ++i){
            originalMap.put(key.get(i), "Value: "+i);
            customMap.put(key.get(i), "Value: "+i);
        }

        System.out.println("original size: "+originalMap.size()+", custom size: "+customMap.size());

        for(int i = 0; i < containerLen; ++i){
            System.out.println("key = "+key.get(i)+". original: "+originalMap.get(key.get(i))+", custom: "+customMap.get(key.get(i)));
        }
    }
}
