package com.qeapp.tools.exeption;

public class DolbaebException extends RuntimeException {

    public DolbaebException() {
        this("Dolbaeb!");
    }
    public DolbaebException(String message) {
        super(message);
    }
    public DolbaebException(String message, Throwable cause) {
        super(message, cause);
    }
    public DolbaebException(Throwable cause) {
        super(cause);
    }

}
