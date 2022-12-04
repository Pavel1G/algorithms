package org.example.algorithms2;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        SelfWrittenArrayListInteger service = new SelfWrittenArrayListInteger();
        int size = 10000;
        Integer[] integers = generateRandomArray(size);
        Integer[] integers1 = Arrays.copyOf(integers, size);
        Integer[] integers2 = Arrays.copyOf(integers, size);

        long start = System.currentTimeMillis();
        sortBubble(integers);
        System.out.println("Время выполнения - " + (System.currentTimeMillis() - start) + " мс.");

        long start1 = System.currentTimeMillis();
        sortSelection(integers1);
        System.out.println("Время выполнения - " + (System.currentTimeMillis() - start1) + " мс.");

        long start2 = System.currentTimeMillis();
        sortInsertion(integers2);
        System.out.println("Время выполнения - " + (System.currentTimeMillis() - start2) + " мс.");
    }

    private static void sortInsertion(Integer[] integers2) {
        for (int i = 1; i < integers2.length; i++) {
            int temp = integers2[i];
            int j = i;
            while (j > 0 && integers2[j - 1] >= temp) {
                integers2[j] = integers2[j - 1];
                j--;
            }
            integers2[j] = temp;
        }
    }

    private static void sortSelection(Integer[] integers1) {
        for (int i = 0; i < integers1.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < integers1.length; j++) {
                if (integers1[j] < integers1[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            int temp = integers1[minElementIndex];
            integers1[minElementIndex] = integers1[i];
            integers1[i] = temp;
        }
    }

    private static void sortBubble(Integer[] integers) {
        for (int i = 0; i < integers.length; i++) {
            for (int j = 0; j < integers.length - 1 - i; j++) {
                if (integers[j] > integers[j + 1]) {
                    int temp = integers[j];
                    integers[j] = integers[j + 1];
                    integers[j + 1] = temp;
                }
            }
        }
    }

    private static Integer[] generateRandomArray(int size) {
        Integer[] result = new Integer[size];
        for (int i = 0; i < size; i++) {
            result[i] = (int) (Math.random() * 999 + 1);
        }
        return result;
    }
}
