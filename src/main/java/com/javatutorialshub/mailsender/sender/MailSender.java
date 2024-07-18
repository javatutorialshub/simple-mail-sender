package com.javatutorialshub.mailsender.sender;

import com.javatutorialshub.mailsender.composer.MailComposer;

public interface MailSender {
    void send(MailComposer mailComposer) throws MailSenderException;
}
