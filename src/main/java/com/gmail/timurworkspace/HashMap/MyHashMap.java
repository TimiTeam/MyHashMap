package com.gmail.timurworkspace.HashMap;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MyHashMap<K,V> {
    private static final int maxSize = 100000;
    private Element[] elements;
    private int arraySize;
    private int currentSize;

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        this.arraySize = 8;
        currentSize = 0;
        elements = (Element[]) Array.newInstance(Element.class, arraySize);
    }

    @SuppressWarnings("unchecked")
    public MyHashMap(int size) {
        currentSize = 0;
        arraySize = size > 0 ? size : 8;
        elements = (Element[]) Array.newInstance(Element.class, arraySize);
    }

    public V put(K key, V value){

        V ret = value;

        if(key == null || value == null)
            throw new NullPointerException();

        if (currentSize + 1 == arraySize)
            resizeArray();

        int index = getIndex(key);
        int max = arraySize;

        for (int i = index; i < max; ++i){
            Element e;
            if ((e = elements[i]) == null){
                elements[i] = new Element(key, value);
                ++currentSize;
                break;
            }
            else if (e.getKey() == key){
                ret = e.getValue();
                e.setValue(value);
                break;
            }
            if (i + 1 == arraySize){
                i = 0;
                max = index;
            }
        }

        return ret;
    }

    public int size() {
        return currentSize;
    }

    public V get(K key){
        if (key == null)
            throw new NullPointerException();
        V ret = null;
        int index = getIndex(key);
        int max = arraySize;
        for(int i = index; i < max; ++ i){
            Element e = elements[i];
            if (e != null && e.getKey() == key){
                ret = e.getValue();
                break;
            }
            if (i + 1 == arraySize){
                i = 0;
                max = index;
            }
        }
        return ret;
    }

    private int hash(int key) {
        key ^= (key >>> 20) ^ (key >>> 12);
        return key ^ (key >>> 7) ^ (key >>> 4);
    }

    private int getIndex(K key) {
        return hash(key.hashCode()) & (this.arraySize - 1);
    }

    @SuppressWarnings("unchecked")
    private void resizeArray(){

        int newArraySize = arraySize + arraySize;
        if (newArraySize >= maxSize)
            throw new IllegalArgumentException();

        Element[] tmp = Arrays.copyOf(elements, arraySize);
        int oldArraySize = arraySize;
        arraySize = newArraySize;
        elements = (Element[]) Array.newInstance(Element.class, arraySize);
        currentSize = 0;
        for (int i = 0; i < oldArraySize; ++i){
            Element e = tmp[i];
            if (e != null)
                put(e.getKey(), e.getValue());
        }
    }

    private class Element{
        private final K key;
        private V value;

        private Element(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }


        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }
}
