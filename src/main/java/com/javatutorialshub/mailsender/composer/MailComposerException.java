package com.javatutorialshub.mailsender.composer;

public class MailComposerException extends Exception {
    public MailComposerException() {
    }

    public MailComposerException(String message) {
        super(message);
    }

    public MailComposerException(String message, Throwable cause) {
        super(message, cause);
    }

    public MailComposerException(Throwable cause) {
        super(cause);
    }

    public MailComposerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
