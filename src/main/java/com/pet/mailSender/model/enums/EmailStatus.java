package com.pet.mailSender.model.enums;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum EmailStatus {
    NEW("New"),
    SENT("Sent"),
    REJECTED("Rejected");

    private String title;

    EmailStatus(String title){
        this.title = title;
    }

    private static Map<String, EmailStatus> statusMap = new HashMap<>();

    static {
        Map<String, EmailStatus> tempMap = new HashMap<>();
        for(EmailStatus e : EmailStatus.values()){
            tempMap.put(e.title, e);
        }

        statusMap = Collections.unmodifiableMap(tempMap);
    }

    public EmailStatus fromString(String title){
        return statusMap.get(title);
    }
}
