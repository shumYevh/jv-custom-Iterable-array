package org.example;

import java.util.Iterator;
import org.example.list.MyArrayList;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> myList = new MyArrayList<>();
        myList.add(1);
        myList.add(2);
        myList.add(3);
        myList.add(4);

        System.out.println(" Custom iterable list");
        for (Integer num : myList) {
            System.out.println(num);
        }

        Iterator<Integer> iterator = new Iterator<>() {
            private final Integer[] array = {1, 2, 3, 4, 5};
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < array.length;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new IndexOutOfBoundsException();
                }
                return array[index++];
            }
        };


        System.out.println("\n Custom Iterator");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}