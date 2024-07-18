package com.javatutorialshub.mailsender.sender;

import com.javatutorialshub.mailsender.composer.MailComposer;
import com.javatutorialshub.mailsender.composer.MailComposerException;
import com.javatutorialshub.mailsender.composer.TextMailComposer;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MailSenderImpl implements MailSender {
    private static final Logger logger = Logger.getLogger(MailSenderImpl.class.getName());

    private final Session session;

    public MailSenderImpl(Session session) {
        this.session = session;
    }

    @Override
    public void send(MailComposer mailComposer) throws MailSenderException {
        try {
            Message message = mailComposer.compose(session);
            Transport.send(message);
        } catch (MailComposerException e) {
            throw new MailSenderException(e);
        } catch (MessagingException e) {
            logger.log(Level.SEVERE, "Error occurred when send the mail message to the recipients");
            throw new MailSenderException(e);
        }
    }
}
