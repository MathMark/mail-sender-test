package com.pet.mailSender.service.mappers.campaignMapper;

import com.pet.mailSender.model.*;
import com.pet.mailSender.model.enums.CampaignStatus;
import com.pet.mailSender.model.viewModels.CampaignView;
import org.springframework.stereotype.Component;

@Component
public class CampaignMapperImpl implements CampaignMapper {
    @Override
    public Campaign getCampaign(CampaignView campaignView) {
        Account account = new Account();
        account.setFirstName(campaignView.getAccountFirstName());
        account.setLastName(campaignView.getAccountLastName());
        account.setEmail(campaignView.getAccountUsername());
        account.setPassword(campaignView.getAccountPassword());

        Template template = new Template();
        template.setTitle(campaignView.getTitle() + "-template");
        template.setSubject(campaignView.getTemplateSubject());
        template.setBody(campaignView.getTemplateBody());
        template.setSignature(campaignView.getTemplateSignature());

        EmailStatistics emailStatistics = new EmailStatistics();
        emailStatistics.setCampaignStatus(CampaignStatus.NEW);

        Campaign campaign = new Campaign();
        campaign.setTitle(campaignView.getTitle());
        campaign.setDelay(Long.parseLong(campaignView.getDelay())*1000);

        campaign.setAccount(account);
        campaign.setTemplate(template);
        campaign.setEmailStatistics(emailStatistics);

        return campaign;
    }

    @Override
    public CampaignView getCampaignView(Campaign campaign) {
        //TODO
        return null;
    }
}
