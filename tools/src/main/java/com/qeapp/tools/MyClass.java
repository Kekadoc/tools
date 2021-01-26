package com.qeapp.tools;

public class MyClass {
    public interface FractionStateEventF {
        default void onChange(float change) {}
        default void onMax() {}
        default void onMin() {}
    }
    public interface FractionStateEventD {
        default void onChange(double change) {}
        default void onMax() {}
        default void onMin() {}
    }

}