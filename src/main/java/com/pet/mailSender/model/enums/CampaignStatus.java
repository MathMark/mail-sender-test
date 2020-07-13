package com.pet.mailSender.model.enums;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum CampaignStatus {
    NEW("New"),
    RUNNING("Running"),
    STOPPED("Stopped"),
    FAILED("Failed"),
    FINISHED("Finished");

    private String title;

    CampaignStatus(String title){
        this.title = title;
    }

    private static Map<String, CampaignStatus> statusMap;

    static {
        Map<String, CampaignStatus> tempMap = new HashMap<>();
        for(CampaignStatus e : CampaignStatus.values()){
            tempMap.put(e.title, e);
        }

        statusMap = Collections.unmodifiableMap(tempMap);
    }

    public CampaignStatus fromString(String title){
        return statusMap.get(title);
    }

    public String getTitle(){
        return this.title;
    }
}
