package com.pet.mailSender.model.viewModels;

import lombok.Data;

@Data
public class CampaignView {
    private String title;
    private String accountUsername;
    private String accountPassword;
    private String accountFirstName;
    private String accountLastName;
    private String templateSubject;
    private String templateBody;
    private String templateSignature;
    private String peopleListFileName;
    private String delay;
}
