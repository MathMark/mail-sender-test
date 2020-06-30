package com.pet.mailSender.service.emailSender;

import com.pet.mailSender.model.Campaign;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailSenderParallel extends Thread {

    @Autowired
    private EmailSender emailSender;

    @Getter
    @Setter
    private Campaign campaign;

    public EmailSenderParallel(Campaign campaign){
        this.campaign = campaign;
    }

    public EmailSenderParallel(){

    }

    @Override
    public void run() {
        emailSender.sendEmails(this.campaign);
    }

}
