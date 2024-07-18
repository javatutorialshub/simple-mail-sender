package com.javatutorialshub.mailsender;

import com.javatutorialshub.mailsender.composer.MailComposer;
import com.javatutorialshub.mailsender.composer.MailComposerType;
import com.javatutorialshub.mailsender.sender.MailSender;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MailSenderApplication {

    private static final Logger logger = Logger.getLogger(MailSenderApplication.class.getName());

    public static void main(String[] args){
        try {

            MailSender mailSender = ApplicationUtils.getMailSender();

            String state = "";
            Scanner scanner = new Scanner(System.in);

            while(!state.equals("no")) {
                System.out.println("Which kind of mail would you like to send (TEXT, HTML, WITH_ATTACHMENT)?");
                MailComposerType mailComposerType = MailComposerType.valueOf(scanner.nextLine().trim());

                System.out.println("Which emails to send to (comma separated)?");
                String toEmails = scanner.nextLine().trim();

                System.out.println("Mail Subject ?");
                String subject = scanner.nextLine().trim();

                System.out.println("Mail Content ?");
                String content = scanner.nextLine();

                String filePaths = null;

                if(mailComposerType == MailComposerType.WITH_ATTACHMENT) {
                    System.out.println("Attachment file paths (comma separated)?");
                    filePaths = scanner.nextLine().trim();
                }

                MailComposer mailComposer = ApplicationUtils.buildMailComposer(mailComposerType, toEmails, filePaths);
                mailComposer.setSubject(subject);
                mailComposer.setContent(content);


                mailSender.send(mailComposer);

                System.out.println("New Mail (yes, no)?");

                state = scanner.nextLine();
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
