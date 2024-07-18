package com.javatutorialshub.mailsender.properties;

public class PropertiesLoaderException extends Exception {
    public PropertiesLoaderException() {
    }

    public PropertiesLoaderException(String message) {
        super(message);
    }

    public PropertiesLoaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public PropertiesLoaderException(Throwable cause) {
        super(cause);
    }

    public PropertiesLoaderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
