package com.pet.mailSender.service;

import com.pet.mailSender.dao.Dao;
import com.pet.mailSender.model.Campaign;
import com.pet.mailSender.model.viewModels.CampaignView;
import com.pet.mailSender.service.emailSender.EmailSender;
import com.pet.mailSender.service.mappers.campaignMapper.CampaignMapper;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.*;

public class CampaignService {

    private Dao<Campaign> campaignDao;

    private CampaignMapper campaignMapper;

    @Autowired
    private ObjectFactory<EmailSender> objectFactory;

    public CampaignService(Dao<Campaign> campaignDao, CampaignMapper campaignMapper) {
        this.campaignDao = campaignDao;
        this.campaignMapper = campaignMapper;
    }

    private Map<Integer, Thread> threadMap = new HashMap<>();

    public List<Campaign> getAll(){
        return campaignDao.getAll();
    }

    public Campaign getCampaignById(int id){
        return campaignDao.getById(id);
    }

    public void save(Campaign campaign){
        campaignDao.add(campaign);
    }

    public void deleteCampaign(Campaign campaign){
        campaignDao.delete(campaign);
    }

    public void saveAsCampaign(CampaignView campaignView){
        Campaign campaign = campaignMapper.getCampaign(campaignView);
        save(campaign);
    }

    public void runCampaignParallel(int campaignId){
        Campaign campaign = campaignDao.getById(campaignId);
        if(campaign != null){
            EmailSender e = objectFactory.getObject();
            e.setCampaign(campaign);
            Thread thread = new Thread(e);
            threadMap.put(e.getCampaign().getId(), thread);
            thread.start();
        }
    }

    public void stopCampaign(int campaignId){
        Campaign campaign = campaignDao.getById(campaignId);
        if(campaign != null){
            Thread thread = threadMap.remove(campaignId);
            if(thread != null && !thread.isInterrupted()){
                thread.interrupt();
            }
        }
    }

}
