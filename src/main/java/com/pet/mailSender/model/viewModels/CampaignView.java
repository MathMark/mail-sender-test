package com.pet.mailSender.model.viewModels;

import com.pet.mailSender.model.Account;
import com.pet.mailSender.model.Template;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CampaignView {
    private String title;
    private Account account;
    private Template template;
    private MultipartFile peopleList;
    private String delay;
}
