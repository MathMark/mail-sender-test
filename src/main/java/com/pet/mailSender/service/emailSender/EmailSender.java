package com.pet.mailSender.service.emailSender;

import com.pet.mailSender.model.Campaign;

public interface EmailSender {
    void sendEmails(Campaign campaign);
}
