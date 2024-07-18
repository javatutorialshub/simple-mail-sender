package com.javatutorialshub.mailsender.composer;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class MailComposer {
    private static final Logger logger = Logger.getLogger(MailComposer.class.getName());

    protected String fromEmail;
    protected Collection<String> toEmails;
    protected Collection<String> ccEmails;
    protected Collection<String> bccEmails;
    protected String subject;
    protected String content;

    public MailComposer(String fromEmail, Collection<String> toEmails) {
        this.fromEmail = fromEmail;
        this.toEmails = toEmails;
    }

    public Message compose(Session session) throws MailComposerException{
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(
                    Message.RecipientType.TO,
                    toEmails.stream().map(t -> {
                        try {
                            return new InternetAddress(t);
                        } catch (AddressException e) {
                            logger.log(Level.SEVERE, "Error occurred when converting '" + t + "' email");
                            throw new RuntimeException(e);
                        }
                    }).toList().toArray(new InternetAddress[0])
            );
            return doCompleteCompose(message);
        } catch (MessagingException e) {
            logger.log(Level.SEVERE, "Error convert email to internet addresses");
            throw new MailComposerException(e);
        }
    }

    public abstract Message doCompleteCompose(Message message) throws MailComposerException;

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public Collection<String> getToEmails() {
        return toEmails;
    }

    public void setToEmails(Collection<String> toEmails) {
        this.toEmails = toEmails;
    }

    public Collection<String> getCcEmails() {
        return ccEmails;
    }

    public void setCcEmails(Collection<String> ccEmails) {
        this.ccEmails = ccEmails;
    }

    public Collection<String> getBccEmails() {
        return bccEmails;
    }

    public void setBccEmails(Collection<String> bccEmails) {
        this.bccEmails = bccEmails;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
