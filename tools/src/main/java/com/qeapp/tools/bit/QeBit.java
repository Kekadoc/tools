package com.qeapp.tools.bit;

public class QeBit {
    private static final String TAG = "QeBit-TAG";

    public static void logAllBits(int v) {
        System.out.println("logAllBits: \n" + BitwiseHolder.makeInf(v));
    }

    public static final int BIT_0 = 1;          // 1
    public static final int BIT_1 = 1 << 1;     // 2
    public static final int BIT_2 = 1 << 2;     // 4
    public static final int BIT_3 = 1 << 3;     // 8
    public static final int BIT_4 = 1 << 4;     // 16
    public static final int BIT_5 = 1 << 5;     // 32
    public static final int BIT_6 = 1 << 6;     // 64
    public static final int BIT_7 = 1 << 7;     // 128
    public static final int BIT_8 = 1 << 8;     // 256
    public static final int BIT_9 = 1 << 9;     // 512
    public static final int BIT_10 = 1 << 10;   // 1024
    public static final int BIT_11 = 1 << 11;   // 2048
    public static final int BIT_12 = 1 << 12;   // 4096
    public static final int BIT_13 = 1 << 13;   // 8192
    public static final int BIT_14 = 1 << 14;   // 16384
    public static final int BIT_15 = 1 << 15;   // 32768
    public static final int BIT_16 = 1 << 16;   // 65536
    public static final int BIT_17 = 1 << 17;   // 131072
    public static final int BIT_18 = 1 << 18;   // 262144
    public static final int BIT_19 = 1 << 19;   // 524288
    public static final int BIT_20 = 1 << 20;   // 1048576
    public static final int BIT_21 = 1 << 21;   // 2097152
    public static final int BIT_22 = 1 << 22;   // 4194304
    public static final int BIT_23 = 1 << 23;   // 8388608
    public static final int BIT_24 = 1 << 24;   // 16777216
    public static final int BIT_25 = 1 << 25;   // 33554432
    public static final int BIT_26 = 1 << 26;   // 67108864
    public static final int BIT_27 = 1 << 27;   // 134217728
    public static final int BIT_28 = 1 << 28;   // 268435456
    public static final int BIT_29 = 1 << 29;   // 536870912
    public static final int BIT_30 = 1 << 30;   // 1073741824
    public static final int BIT_31 = 1 << 31;   // -2147483648

}
