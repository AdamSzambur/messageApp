package org.example.exceptions;

public class DataNotFoundException extends RuntimeException {
    static final long serialVersionUID = -7034897190745766939L;

    public DataNotFoundException(String message) {
        super(message);
    }
}
