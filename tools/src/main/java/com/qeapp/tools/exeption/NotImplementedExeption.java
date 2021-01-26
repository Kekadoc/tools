package com.qeapp.tools.exeption;

public class NotImplementedExeption extends RuntimeException {

    public NotImplementedExeption() {
    }
    public NotImplementedExeption(String message) {
        super(message);
    }
    public NotImplementedExeption(String message, Throwable cause) {
        super(message, cause);
    }
    public NotImplementedExeption(Throwable cause) {
        super(cause);
    }

}
