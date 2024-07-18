package com.javatutorialshub.mailsender.composer;

import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.internet.MimeBodyPart;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MultiPartMailComposer extends HtmlMailComposer {

    private static final Logger logger = Logger.getLogger(MultiPartMailComposer.class.getName());

    private List<String> filePaths;

    public MultiPartMailComposer(String fromEmail, Collection<String> toEmails) {
        super(fromEmail, toEmails);
    }

    protected void addBodyPart(Multipart multipart) throws MessagingException {
        super.addBodyPart(multipart);
        if(filePaths != null && !filePaths.isEmpty()) {
            filePaths.forEach(filePath -> {
                MimeBodyPart attachmentBodyPart = new MimeBodyPart();
                try {
                    attachmentBodyPart.attachFile(new File(filePath));
                    multipart.addBodyPart(attachmentBodyPart);
                } catch (IOException | MessagingException e) {
                    logger.log(Level.SEVERE, "Error occurred when attaching file to mail body: '" + filePath);
                    throw new RuntimeException(e);
                }
            });
        }
    }

    public List<String> getFilePaths() {
        return filePaths;
    }

    public void setFilePaths(List<String> filePaths) {
        this.filePaths = filePaths;
    }
}
