package org.nimshub.utils;

/**
 * Array element swapper
 */
public class Swapper {
    private Swapper() {
    }

    public static void swap(Integer a, Integer b, int[] array) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
