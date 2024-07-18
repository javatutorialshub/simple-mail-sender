package com.javatutorialshub.mailsender;

import com.javatutorialshub.mailsender.composer.*;
import com.javatutorialshub.mailsender.properties.PropertiesLoader;
import com.javatutorialshub.mailsender.properties.PropertiesLoaderException;
import com.javatutorialshub.mailsender.sender.MailSender;
import com.javatutorialshub.mailsender.sender.MailSenderImpl;
import com.javatutorialshub.mailsender.session.SessionFactory;

import java.util.List;
import java.util.Properties;

public class ApplicationUtils {
    public static final String PASSWORD = "password";
    public static final String USERNAME = "username";

    private ApplicationUtils(){}

    public static MailSender getMailSender() throws PropertiesLoaderException {
        Properties props = PropertiesLoader.getApplicationProperties();
        String username = props.getProperty(USERNAME);
        String password = props.getProperty(PASSWORD);

        return new MailSenderImpl(SessionFactory.getSession(props, username, password));
    }

    public static MailComposer buildMailComposer(MailComposerType mailComposerType, String emails, String paths) throws PropertiesLoaderException {
        Properties props = PropertiesLoader.getApplicationProperties();
        String from = props.getProperty(USERNAME);
        List<String> toEmails = List.of(emails.split(","));
        return switch(mailComposerType) {
            case TEXT -> new TextMailComposer(from, toEmails);
            case HTML -> new HtmlMailComposer(from, toEmails);
            case WITH_ATTACHMENT -> {
                MultiPartMailComposer multiPartMailComposer = new MultiPartMailComposer(from, toEmails);
                List<String> filePaths = List.of(paths.split(","));
                multiPartMailComposer.setFilePaths(filePaths);
                yield multiPartMailComposer;
            }
        };
    }
}
