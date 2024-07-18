package com.javatutorialshub.mailsender;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final Logger log = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        try {
            Properties prop = new Properties();
            prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.smtp.port", "465");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.socketFactory.port", "465");
            prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");


            String username = "javatutorialshub@gmail.com";
            String password = "xxxxxxxxx";

            Session session = Session.getInstance(prop,
            new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("myUserFrom@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("user1@gmail.com, user2@yahoo.com")
            );
            message.setSubject("Proverbe Chinois du jour");


            String msg = "Celui qui sait qu'il ne sait pas, éduque-le. Celui qui sait qu'il sait, " +
                    "écoute-le. Celui qui ne sait pas qu'il sait, éveille-le. Celui qui ne sait pas " +
                    "qu'il ne sait pas, fuis-le.";

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            Transport.send(message);
        } catch (MessagingException e) {
            log.log(Level.SEVERE, e.getMessage(), e);
        }

    }
}