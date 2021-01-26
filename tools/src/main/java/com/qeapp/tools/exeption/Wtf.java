package com.qeapp.tools.exeption;

public class Wtf extends RuntimeException {

    public Wtf() {}
    public Wtf(String message) {
        super(message);
    }
    public Wtf(String message, Throwable cause) {
        super(message, cause);
    }
    public Wtf(Throwable cause) {
        super(cause);
    }

}
