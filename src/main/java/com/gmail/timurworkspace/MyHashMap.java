package com.gmail.timurworkspace;

import java.util.Arrays;

public class MyHashMap {
    private static final int minSize = 128;
    private static final int maxSize = 128 * 1000;
    private Element[] elements;
    private int arraySize;
    private int size;


    public MyHashMap() {
        this.arraySize = minSize;
        elements = new Element[arraySize];
    }

    public MyHashMap(int size) {
        this.arraySize = size >= minSize ? size : minSize;
        this.elements = new Element[this.arraySize];
    }

    public long put(int key, long value){
        if (size + 1 == arraySize)
            elements = resizeArray();
        if (size < arraySize) {
            int index = getIndex(key);
            Element e;
            for (; index < arraySize; ++index){
                if ((e = elements[index]) == null || e.getKey() == key)
                  break;
                if (index + 1 == arraySize)
                    index = 0;
            }
            if (elements[index] == null) {
                elements[index] = new Element(key, value);
                ++size;
            }
            else {
                long ret = elements[index].getValue();
                elements[index].setValue(value);
                return ret;
            }
        }
        return value;
    }

    public int size() {
        return size;
    }

    public long get(int key){
        boolean circle = false;
        Element e;
        for(int i = getIndex(key); i < arraySize; ++i){
            if ((e = elements[i]) != null && e.getKey() == key){
                return e.getValue();
            }
            if (i + 1 == arraySize){
                if(circle)
                    break;
                circle = true;
                i = 0;
            }
        }
        return (-0);
    }

    private int hash(int key)
    {
        key ^= (key >>> 20) ^ (key >>> 10);
        return key ^ (key >>> 8) ^ (key >>> 4);
    }

    private int getIndex(int key){
        int hash = hash(key);
        return hash & (this.arraySize - 1);
    }

    private Element[] resizeArray(){
        if (arraySize + minSize >= maxSize)
            return elements;
        arraySize += minSize;
        return Arrays.copyOf(elements, arraySize);
    }

    private class Element{
        private int key;
        private long value;

        public Element(int key, long value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public long getValue() {
            return value;
        }

        public void setValue(long value) {
            this.value = value;
        }
    }
}
