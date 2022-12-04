package org.example.algorithms1;

public class InvalidSizeException extends RuntimeException {
    public InvalidSizeException() {
    }

    public InvalidSizeException(String message) {
        super(message);
    }

    public InvalidSizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidSizeException(Throwable cause) {
        super(cause);
    }

    public InvalidSizeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
