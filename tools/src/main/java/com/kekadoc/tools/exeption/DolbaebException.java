package com.kekadoc.tools.exeption;

public final class DolbaebException extends RuntimeException {

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
