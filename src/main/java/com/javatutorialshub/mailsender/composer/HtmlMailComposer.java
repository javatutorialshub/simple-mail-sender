package com.javatutorialshub.mailsender.composer;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMultipart;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HtmlMailComposer extends MailComposer {

    private static final Logger logger = Logger.getLogger(HtmlMailComposer.class.getName());
    public static final String TEXT_HTML_CHARSET_UTF_8 = "text/html; charset=utf-8";

    public HtmlMailComposer(String fromEmail, Collection<String> toEmails) {
        super(fromEmail, toEmails);
    }

    @Override
    public Message doCompleteCompose(Message message) throws MailComposerException {
        try {
            Multipart multipart = new MimeMultipart();
            addBodyPart(multipart);
            message.setSubject(subject);
            message.setContent(multipart);
            return message;
        } catch (MessagingException e) {
            logger.log(Level.SEVERE, "Error occurred when composing the text message");
            throw new MailComposerException(e);
        }
    }

    protected void addBodyPart(Multipart multipart) throws MessagingException {
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(content, TEXT_HTML_CHARSET_UTF_8);
        multipart.addBodyPart(mimeBodyPart);
    }
}
