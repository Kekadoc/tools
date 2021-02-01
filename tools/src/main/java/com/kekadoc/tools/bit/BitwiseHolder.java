package com.kekadoc.tools.bit;

import com.kekadoc.tools.exeption.ExceptionUtils;

interface BitwiseHolder {

    static String makeInf(int value) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 32; i++)
            str.append("[").append(i).append(": ").append(Index.getBit(value, i)).append("]\n");
        return str.toString();
    }

    /** Value */
    int getBitwiseValue();
    void setBitwiseValue(int value);

    void clear();
    void clear(int index);

    void flip(int index);

    boolean get(int index);

    void set(int index);
    void set(int index, boolean value);

    abstract class Index implements BitwiseHolder {

        public static int setBit(int value, int index, boolean b) {
            com.kekadoc.tools.exeption.ExceptionUtils.checkIfValueLess(0, index);
            return b ? setBit(value, index) : clearBit(value, index);
        }
        public static int clearBit(int value, int index) {
            com.kekadoc.tools.exeption.ExceptionUtils.checkIfValueLess(0, index);
            return value & ~(1 << index);
        }
        public static int setBit(int value, int index) {
            com.kekadoc.tools.exeption.ExceptionUtils.checkIfValueLess(0, index);
            return value | (1 << index);
        }
        public static int flipBit(int value, int index) {
            com.kekadoc.tools.exeption.ExceptionUtils.checkIfValueLess(0, index);
            return value ^ (1 << index);
        }
        public static boolean getBit(int value, int index) {
            ExceptionUtils.checkIfValueLess(0, index);
            return (value & (1 << index)) != 0;
        }

        @Override
        public void clear() {
            setBitwiseValue(0);
        }
        @Override
        public void clear(int bitIndex) {
            setBitwiseValue(clearBit(getBitwiseValue(), bitIndex));
        }
        @Override
        public void flip(int bitIndex) {
            setBitwiseValue(flipBit(getBitwiseValue(), bitIndex));
        }
        @Override
        public boolean get(int bitIndex) {
            return getBit(getBitwiseValue(), bitIndex);
        }
        @Override
        public void set(int bitIndex) {
            setBitwiseValue(setBit(getBitwiseValue(), bitIndex));
        }
        @Override
        public void set(int bitIndex, boolean value) {
            setBit(getBitwiseValue(), bitIndex, value);
        }

        @Override
        public String toString() {
            return super.toString() + "\n" + BitwiseHolder.makeInf(getBitwiseValue());
        }

    }
    abstract class Flag implements BitwiseHolder {

        public static int setBit(int value, int flag, boolean b) {
            return b ? setBit(value, flag) : clearBit(value, flag);
        }
        public static int clearBit(int value, int flag) {
            return value & ~flag;
        }
        public static int setBit(int value, int flag) {
            return value | flag;
        }
        public static int flipBit(int value, int flag) {
            return value ^ flag;
        }
        public static boolean getBit(int value, int flag) {
            return (value & flag) != 0;
        }

        @Override
        public void clear() {
            setBitwiseValue(0);
        }
        @Override
        public void clear(int flag) {
            setBitwiseValue(clearBit(getBitwiseValue(), flag));
        }
        @Override
        public void flip(int flag) {
            setBitwiseValue(flipBit(getBitwiseValue(), flag));
        }
        @Override
        public boolean get(int flag) {
            return getBit(getBitwiseValue(), flag);
        }
        @Override
        public void set(int flag) {
            setBitwiseValue(setBit(getBitwiseValue(), flag));
        }
        @Override
        public void set(int flag, boolean value) {
            if (value) set(flag);
            else clear(flag);
        }

        @Override
        public String toString() {
            return super.toString() + "\n" + BitwiseHolder.makeInf(getBitwiseValue());
        }

    }

}
