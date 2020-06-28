package com.pet.mailSender.service;

import com.pet.mailSender.dao.Dao;
import com.pet.mailSender.model.Campaign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CampaignService {

    @Autowired
    @Qualifier("campaignDao")
    private Dao<Campaign> campaignDao;

    public List<Campaign> getAll(){
        return campaignDao.getAll();
    }

    public void save(Campaign campaign){
        campaignDao.add(campaign);
    }
}
