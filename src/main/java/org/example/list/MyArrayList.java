package org.example.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList<T> implements List<T>, Iterable<T> {
    private static final int DEFAULT_CAPACITY = 5;
    private T[] elements;
    private int size;


    public MyArrayList(int initCapacity) {
        if (initCapacity <= 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + initCapacity);
        }
        elements = (T[]) new Object[initCapacity];
    }


    public MyArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public static <T> List<T> of(T... elements) {
        MyArrayList<T> newList = new MyArrayList<>(elements.length);
        for (T element : elements) {
            newList.add(element);
        }
        return newList;
    }

    @Override
    public void add(T element) {
        resize();
        elements[size++] = element;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        resize();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return elements[index];
    }

    @Override
    public T getFirst() {
        if (size != 0) {
            return elements[0];
        }
        throw new NoSuchElementException();
    }

    @Override
    public T getLast() {
        if (size != 0) {
            return elements[size - 1];
        }
        throw new NoSuchElementException();
    }

    @Override
    public void set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        elements[index] = element;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        T removedElement = elements[index];

        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null;
        return removedElement;
    }

    @Override
    public boolean contains(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    private void resize() {
        if (size == elements.length) {
            int newCapacity = elements.length * 2;
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new IndexOutOfBoundsException();
                }
                return elements[index++];
            }
        };
    }
}
