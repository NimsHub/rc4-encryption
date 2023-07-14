package org.nimshub.utils;

public class Printer {
    public static void print(String message) {
        System.out.println(message);
    }

    public static void print(String descriptor, String message) {
        System.out.println(descriptor + " --- " + message);
    }
}
