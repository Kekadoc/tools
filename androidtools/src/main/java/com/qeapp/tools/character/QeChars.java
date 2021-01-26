package com.qeapp.tools.character;

public final class QeChars {
    private static final String TAG = "QeChars-TAG";

    private QeChars() {}

    public static final char[] NUMBERS = new char[] {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public static final char INFINITY = '\u221E';
    public static final char DOLLAR = '\u0024';
    public static final char PERCENT = '\u0025';
    public static final char ELLIPSIS = '\u2026';

    public static boolean isNumber(char c) {
        for (char number : NUMBERS) if (c == number) return true;
        return false;
    }
    public static int parseNumber(CharSequence chars) {
        return parseNumber(chars, 0, chars.length() - 1);
    }
    public static int parseNumber(CharSequence chars, int start) {
        return parseNumber(chars, start, chars.length());
    }
    public static int parseNumber(CharSequence chars, int start, int end) {
        if (end > chars.length()) throw new RuntimeException("length = " + chars.length() + "; start = " + start + "; end = " + end);
        char c;
        boolean started = false;
        StringBuilder n = new StringBuilder();
        for (int i = start; i < end; i++) {
            c = chars.charAt(i);
            if (isNumber(c)) {
                n.append(c);
                started = true;
            } else {
                if (started) break;
            }
        }
        return Integer.parseInt(n.toString());
    }
    public static boolean isNext(CharSequence sequence, int pos, CharSequence next) {
        if (pos + next.length() > sequence.length()) return false;
        CharSequence bef = sequence.subSequence(pos, pos + next.length());
        return bef.equals(next);
    }

}
