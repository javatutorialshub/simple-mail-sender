package com.javatutorialshub.mailsender.sender;

public class MailSenderException extends Exception {
    public MailSenderException() {
    }

    public MailSenderException(String message) {
        super(message);
    }

    public MailSenderException(String message, Throwable cause) {
        super(message, cause);
    }

    public MailSenderException(Throwable cause) {
        super(cause);
    }

    public MailSenderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
