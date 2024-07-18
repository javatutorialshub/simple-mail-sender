package com.javatutorialshub.mailsender.session;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;

import java.util.Properties;

public class SessionFactory {
    private static Session session;

    public static Session getSession(Properties props, String username, String password){
        if(session == null){
            session = Session.getInstance(props,
            new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
        }
        return session;
    }
}
