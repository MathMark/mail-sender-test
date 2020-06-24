package com.pet.mailSender.dao.imp;

import com.pet.mailSender.model.Campaign;

public class CampaignDao extends AbstractDao<Campaign> {

    public CampaignDao(){
        setClazz(Campaign.class);
    }
}
