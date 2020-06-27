package com.pet.mailSender;

import com.pet.mailSender.dao.Dao;
import com.pet.mailSender.dao.imp.CampaignDao;
import com.pet.mailSender.dao.imp.PeopleListDao;
import com.pet.mailSender.model.*;
import com.pet.mailSender.service.emailSender.EmailSender;
import com.pet.mailSender.service.emailSender.EmailSenderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@SpringBootApplication
public class SpringBootApp {

    @Autowired
    @Qualifier("campaignDao")
    private Dao<Campaign> campaignDao;

    @Autowired
    private EmailSender emailSender;

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
        campaign.setDelay(10000L);
        campaign.setTemplate(template);

        campaign.setPeopleList(peopleList);


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
