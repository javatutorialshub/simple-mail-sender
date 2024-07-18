package com.javatutorialshub.mailsender.composer;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TextMailComposer extends MailComposer {

    private static final Logger logger = Logger.getLogger(TextMailComposer.class.getName());

    public TextMailComposer(String fromEmail, Collection<String> toEmails) {
        super(fromEmail, toEmails);
    }

    @Override
    public Message doCompleteCompose(Message message) throws MailComposerException {
        try {
            message.setSubject(subject);
            message.setText(content);
            return message;
        } catch (MessagingException e) {
            logger.log(Level.SEVERE, "Error occurred when composing the text message");
            throw new MailComposerException(e);
        }
    }
}
