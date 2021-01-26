package com.qeapp.tools.text;

public interface StringWrapped extends CharSequence {

    String getString();

    @Override
    default int length() {
        return getString().length();
    }
    @Override
    default char charAt(int index) {
        return getString().charAt(index);
    }
    @Override
    default CharSequence subSequence(int start, int end) {
        return getString().subSequence(start, end);
    }

}
