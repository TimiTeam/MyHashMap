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

    public long put(int key, long value) {
        if (size + 1 == arraySize)
            elements = resizeArray();
        if (size < arraySize) {
            int index = getIndex(key);
            int empty = -1;
            boolean find = false;
            boolean find2 = false;
            int empty2 = -1;
            Element e;
            for (int i = index; i < arraySize; ++i) {
                e = elements[i];
                if (!find && e == null) {
                    find = true;
                    empty = i;
                }
                if (e != null && e.getKey() == key) {
                    long ret = e.getValue();
                    e.setValue(value);
                    return ret;
                }
            }

            for (int i = 0; i < index; ++i) {
                if ((e = elements[i]) != null && e.getKey() == key) {
                    long ret = e.getValue();
                    e.setValue(value);
                    return ret;
                } else if (e == null && !find2) {
                    find2 = true;
                    empty2 = i;
                }
            }
            if (find)
                elements[empty] = new Element(key, value);
            else if (find2)
                elements[empty2] = new Element(key, value);
        }
        ++size;
        return value;
}

    public int size() {
        return size;
    }

    public long get(int key) throws KeyNotExistException {
        boolean circle = false;
        Element e;
        for (int i = getIndex(key); i < arraySize; ++i) {
            if ((e = elements[i]) != null && e.getKey() == key) {
                return e.getValue();
            }
            if (i + 1 == arraySize) {
                if (circle)
                    break;
                circle = true;
                i = 0;
            }
        }
        throw new KeyNotExistException();
    }

    private int hash(int key) {
        key ^= (key >>> 20) ^ (key >>> 10);
        return key ^ (key >>> 8) ^ (key >>> 4);
    }

    private int getIndex(int key) {
        return hash(key) & (this.arraySize - 1);
    }

    private Element[] resizeArray() {
        if (arraySize + minSize >= maxSize)
            return elements;
        arraySize += minSize;
        return Arrays.copyOf(elements, arraySize);
    }

private class Element {
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
