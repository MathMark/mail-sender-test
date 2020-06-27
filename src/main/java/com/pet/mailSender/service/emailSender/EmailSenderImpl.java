package com.pet.mailSender.service.emailSender;

import com.pet.mailSender.dao.Dao;
import com.pet.mailSender.dao.imp.CampaignDao;
import com.pet.mailSender.dao.imp.PersonDao;
import com.pet.mailSender.model.Campaign;
import com.pet.mailSender.model.EmailStatus;
import com.pet.mailSender.model.Person;
import com.pet.mailSender.model.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.ResourceBundle;

@Component
public class EmailSenderImpl implements EmailSender {
    private static ResourceBundle resource = ResourceBundle.getBundle("gmailAuth");

    private final String username = resource.getString("username");
    private final String password = resource.getString("password");

    private Properties properties;


    @Autowired
    @Qualifier("campaignDao")
    private Dao<Campaign> campaignDao;

    public EmailSenderImpl() {
        properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true"); //TLS
    }

    @Override
    public void sendEmails(Campaign campaign) {

        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        for (Person person : campaign.getPeopleList().getPeople()) {
            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(resource.getString("username")));
                message.setRecipients(
                        Message.RecipientType.TO,
                        InternetAddress.parse(person.getEmail())
                );
                message.setSubject(campaign.getTemplate().getSubject());
                message.setContent(campaign.getTemplate().getBody(), "text/html");

                Transport.send(message);
                person.setEmailStatus(EmailStatus.SENT);
                Thread.sleep(campaign.getDelay());
                campaignDao.update(campaign);

            } catch (MessagingException | InterruptedException e) {
                person.setEmailStatus(EmailStatus.REJECTED);
            }

        }

    }
}
