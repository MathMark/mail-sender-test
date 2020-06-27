package com.pet.mailSender.dao.imp;

import com.pet.mailSender.model.Campaign;
import org.springframework.stereotype.Component;

@Component
public class CampaignDao extends AbstractDao<Campaign> {

    public CampaignDao(){
        setClazz(Campaign.class);
    }
}
