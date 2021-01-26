package com.qeapp.tools.exeption;

public final class ExceptionUtils {

    private ExceptionUtils() {}

    public static void checkIfValueInRange(int from, int to, int value) {
        if (value < from || value > to) throw new IndexOutOfBoundsException();
    }
    public static void checkIfValueMore(int start, int value) {
        if (value > start) throw new IndexOutOfBoundsException();
    }
    public static void checkIfValueLess(int start, int value) {
        if (value < start) throw new IndexOutOfBoundsException();
    }

    public static void checkIfValueInRange(float from, float to, float value) {
        if (value < from || value > to) throw new IndexOutOfBoundsException();
    }
    public static void checkIfValueMore(float start, float value) {
        if (value > start) throw new IndexOutOfBoundsException();
    }
    public static void checkIfValueLess(float start, float value) {
        if (value < start) throw new IndexOutOfBoundsException();
    }

}
