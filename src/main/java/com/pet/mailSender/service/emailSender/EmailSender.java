package com.pet.mailSender.service.emailSender;

import com.pet.mailSender.dao.Dao;
import com.pet.mailSender.model.Campaign;
import com.pet.mailSender.model.enums.CampaignStatus;
import com.pet.mailSender.model.enums.EmailStatus;
import com.pet.mailSender.model.Person;
import com.pet.mailSender.service.utilities.ProgressCalculator;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
@Scope("prototype")
public class EmailSender implements Runnable {

    private Properties properties;

    @Autowired
    @Qualifier("campaignDao")
    private Dao<Campaign> campaignDao;

    @Autowired
    private ProgressCalculator progressCalculator;

    public EmailSender() {
        properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true"); //TLS
    }


    @Getter
    @Setter
    private Campaign campaign;

    private void sendEmails() {

        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(campaign.getAccount().getEmail(), campaign.getAccount().getPassword());
                    }
                });

        int sentCount = 0;
        int rejectedCount = 0;

        campaign.getEmailStatistics().setCampaignStatus(CampaignStatus.RUNNING);

        for (Person person : campaign.getPeople()) {
            try {
                EmailStatus emailStatus = person.getEmailStatus();

                if(emailStatus == null || (!emailStatus.equals(EmailStatus.SENT))){
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(campaign.getAccount().getEmail()));
                    message.setRecipients(
                            Message.RecipientType.TO,
                            InternetAddress.parse(person.getEmail())
                    );
                    message.setSubject(campaign.getTemplate().getSubject());
                    message.setContent(campaign.getTemplate().getBody(), "text/html");

                    //Transport.send(message);
                    System.out.println("Sending message");
                    person.setEmailStatus(EmailStatus.SENT);
                    sentCount ++;
                    campaign.getEmailStatistics().setSentEmailsCount(sentCount);

                    campaign.getEmailStatistics().setProgress(progressCalculator.getProgress(campaign.getPeople().size(),
                            campaign.getEmailStatistics().getSentEmailsCount()));

                    campaignDao.update(campaign);
                    Thread.sleep(campaign.getDelay());
                }

            } catch (MessagingException e) {
                e.printStackTrace();
                rejectedCount ++;
                person.setEmailStatus(EmailStatus.REJECTED);
                campaign.getEmailStatistics().setRejectedEmailsCount(rejectedCount);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }
        campaign.getEmailStatistics().setCampaignStatus(CampaignStatus.FINISHED);
    }

    @Override
    public void run() {
        sendEmails();
    }
}
