package com.pet.mailSender.service;

import com.pet.mailSender.dao.Dao;
import com.pet.mailSender.model.Campaign;
import com.pet.mailSender.model.viewModels.CampaignView;
import com.pet.mailSender.service.emailSender.EmailSender;
import com.pet.mailSender.service.mappers.campaignMapper.CampaignMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CampaignService {

    @Autowired
    @Qualifier("campaignDao")
    private Dao<Campaign> campaignDao;

    @Autowired
    private CampaignMapper campaignMapper;

    @Autowired
    private EmailSender emailSender;

    public List<Campaign> getAll(){
        return campaignDao.getAll();
    }

    public Campaign getCampaignById(int id){
        return campaignDao.getById(id);
    }

    public void save(Campaign campaign){
        campaignDao.add(campaign);
    }

    public void saveAsCampaign(CampaignView campaignView){
        Campaign campaign = campaignMapper.getCampaign(campaignView);
        save(campaign);
    }


    public void runCampaignParallel(int campaignId){
        Campaign campaign = campaignDao.getById(campaignId);
        if(campaign != null){
            emailSender.setCampaign(campaign);
            Thread thread = new Thread(emailSender);
            thread.start();
        }
    }
}
