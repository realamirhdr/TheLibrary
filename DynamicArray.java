package com.company;

public class DynamicArray implements IDynamicArray {
    private Object[] elements = new Object[5];
    private int index = 0;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Object getElement(int i) {
        Object a = elements[i];
        return a;
    }

    public void add(Object a) {
        if (index == elements.length) {
            Object[] dynamicArray = new Object[2 * index];
            for (int i = 0; i < index; i++) {
                dynamicArray[i] = elements[i];
            }
            elements = dynamicArray;
        }
        elements[index] = a;
        index++;
    }

    public void remove(Object a) {
         for (int i = 0; i < index; i++ ) {
             if (a.equals(elements[i])) {
                 elements[i] = null;
                 for (int j = i; j < index; j++) {
                     elements[j] = elements[j+1];
                 }
                 index--;
                 break;
             }
         }
    }
}
