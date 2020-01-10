package com.gmail.timurworkspace;

import java.util.Arrays;

public class MyHashMap {
    private static final int minSize = 128;
    private static final int maxSize = 128 * 1000 + 1;
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

    public long put(int key, long value) throws MapOverflowException {
        if (size + 1 == arraySize)
            elements = resizeArray();
        boolean find = false;
        int empty = 0;
        int index = getIndex(key);
        int max = arraySize;
        Element e;
        for (int i = index; i < max; ++i) {
            if ((e = elements[i]) != null && e.getKey() == key) {
                long ret = e.getValue();
                e.setValue(value);
                return ret;
            }else if (e == null && !find) {
                find = true;
                empty = i;
            }
            if (i + 1 == arraySize) {
                i = 0;
                max = index;
            }
        }
        if (find) {
            elements[empty] = new Element(key, value);
            ++size;
        }
        return value;
    }

    public int size() {
        return size;
    }

    public long get(int key) throws KeyNotExistException {
        int index = getIndex(key);
        int max = arraySize;
        Element e;
        for (int i = index; i < max; ++i) {
            if ((e = elements[i]) != null && e.getKey() == key) {
                return e.getValue();
            }
            if (i + 1 == arraySize) {
                i = 0;
                max = index;
            }
        }
        throw new KeyNotExistException();
    }

    private int hash(int key) {
        key ^= (key >>> 20) ^ (key >>> 12);
        return key ^ (key >>> 7) ^ (key >>> 4);
    }

    private int getIndex(int key) {
        return hash(key) & (this.arraySize - 1);
    }

    private Element[] resizeArray() throws MapOverflowException {
        if (arraySize + minSize >= maxSize)
            throw new MapOverflowException();
        arraySize += minSize;
        return Arrays.copyOf(elements, arraySize);
    }

    private class Element {
        private int key;
        private long value;

        private Element(int key, long value) {
            this.key = key;
            this.value = value;
        }

        private int getKey() {
            return key;
        }

        private long getValue() {
            return value;
        }

        private void setValue(long value) {
            this.value = value;
        }
    }
}
