package com.qeapp.tools.text;

public final class QeString {
    private QeString() {}

    public static final String NULL = "Null";
    public static final String EMPTY = "";
    public static final String ELLIPSIS = "…";

    public static CharSequence specificSize(CharSequence sequence, int maxSize) {
        if (sequence == null) return null;
        if (sequence.length() <= maxSize) return sequence;
        return sequence.subSequence(0, maxSize - 3) + ELLIPSIS;
    }

    public static CharSequence empty(CharSequence str) {
        if (str != null) return str;
        return EMPTY;
    }
    public static CharSequence notNull(CharSequence str) {
        if (str != null) return str;
        return NULL;
    }

    public static StringBuilder deleteLast(StringBuilder string, int count) {
        return string.delete(string.length() - count, string.length());
    }

}
