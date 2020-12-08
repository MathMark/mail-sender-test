package com.pet.mailSender.config;

import com.pet.mailSender.dao.Dao;
import com.pet.mailSender.dao.imp.AccountDao;
import com.pet.mailSender.dao.imp.CampaignDao;
import com.pet.mailSender.dao.imp.PersonDao;
import com.pet.mailSender.dao.imp.TemplateDao;
import com.pet.mailSender.model.Account;
import com.pet.mailSender.model.Campaign;
import com.pet.mailSender.model.Person;
import com.pet.mailSender.model.Template;
import com.pet.mailSender.service.AccountService;
import com.pet.mailSender.service.CampaignService;
import com.pet.mailSender.service.TemplateService;
import com.pet.mailSender.service.emailSender.EmailSender;
import com.pet.mailSender.service.mappers.campaignMapper.CampaignMapper;
import com.pet.mailSender.service.mappers.campaignMapper.CampaignMapperImpl;
import com.pet.mailSender.service.parsers.csvParser.CsvParser;
import com.pet.mailSender.service.parsers.csvParser.Parser;
import com.pet.mailSender.service.utilities.ProgressCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public AccountDao accountDao() {
        return new AccountDao();
    }

    @Bean
    public PersonDao personDao() {
        return new PersonDao();
    }

    @Bean
    public TemplateDao templateDao() {
        return new TemplateDao();
    }

    @Bean
    public CampaignDao campaignDao() {
        return new CampaignDao();
    }

    @Bean
    public Parser<Person> personParser() {
        return new CsvParser<Person>();
    }

    @Bean
    public ProgressCalculator progressCalculator() {
        return new ProgressCalculator();
    }

    @Bean
    public CampaignMapper campaignMapper(Dao<Template> templateDao, Dao<Account> accountDao, Parser<Person> personParser) {
        return new CampaignMapperImpl(templateDao, accountDao, personParser);
    }


    @Bean
    public AccountService accountService(Dao<Account> accountDao) {
        return new AccountService(accountDao);
    }

    @Bean
    public TemplateService templateService(Dao<Template> templateDao) {
        return new TemplateService(templateDao);
    }

    @Bean
    public CampaignService campaignService(Dao<Campaign> campaignDao, CampaignMapper campaignMapper) {
        return new CampaignService(campaignDao, campaignMapper);
   }

    @Bean
    @Scope("prototype")
    public EmailSender emailSender(Dao<Campaign> campaignDao, ProgressCalculator progressCalculator) {
        return new EmailSender(campaignDao, progressCalculator);
    }
}
