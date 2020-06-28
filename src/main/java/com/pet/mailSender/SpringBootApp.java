package com.pet.mailSender;

import com.pet.mailSender.dao.Dao;
import com.pet.mailSender.model.*;
import com.pet.mailSender.model.enums.CampaignStatus;
import com.pet.mailSender.model.enums.EmailStatus;
import com.pet.mailSender.service.emailSender.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;


@SpringBootApplication
public class SpringBootApp {

    @Autowired
    @Qualifier("campaignDao")
    private Dao<Campaign> campaignDao;

    @Autowired
    private EmailSender emailSender;

    private ResourceBundle resourceBundle = ResourceBundle.getBundle("gmailAuth");

    public static void main(String...args) throws IOException {

        ConfigurableApplicationContext appContext = SpringApplication.run(SpringBootApp.class, args);
        SpringBootApp springBootApp = (SpringBootApp) appContext.getBean("springBootApp");
        springBootApp.test();

    }

    private void test() throws IOException {
        Person vadim = new Person();
        vadim.setFirstName("Vadim");
        vadim.setLastName("Martsun");
        vadim.setEmail("markgre");
        vadim.setEmailStatus(EmailStatus.NEW);

        Person mark = new Person();
        mark.setFirstName("Mark");
        mark.setLastName("Martsun");
        mark.setEmail("mark.netpeak@gmail.com");
        mark.setEmailStatus(EmailStatus.NEW);

        PeopleList peopleList = new PeopleList();
        peopleList.setTitle("Test");
        peopleList.getPeople().add(vadim);
        peopleList.getPeople().add(mark);

        Template template = new Template();
        template.setSubject("Hello!");
        template.setBody("<p>It's a test of my new application!</p><p>How are you?</p>");
        template.setTitle("Test template");

        Campaign campaign = new Campaign();
        campaign.setTitle("Test campaign");
        campaign.setDelay(100L);
        campaign.setTemplate(template);

        Account account = new Account();
        account.setEmail(resourceBundle.getString("username"));
        account.setFirstName("Vadim");
        account.setLastName("Martsun");
        account.setPassword(resourceBundle.getString("password"));

        campaign.setAccount(account);

        campaign.setPeopleList(peopleList);

        EmailStatistics emailStatistics = new EmailStatistics();
        emailStatistics.setCampaignStatus(CampaignStatus.NEW);
        campaign.setEmailStatistics(emailStatistics);


        FileWriter fileWriter = new FileWriter("output.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);

        campaignDao.add(campaign);

        List<Campaign> campaignList = campaignDao.getAll();
        campaignList.forEach(f -> printWriter.println(f));

        emailSender.sendEmails(campaign);

        List<Campaign> campaignList2 = campaignDao.getAll();
        campaignList2.forEach(f -> printWriter.println(f));

        printWriter.close();
    }
}
