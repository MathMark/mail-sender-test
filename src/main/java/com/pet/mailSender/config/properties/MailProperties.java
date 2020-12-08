package com.pet.mailSender.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "mail-sender.mail-config")
@Data
public class MailProperties {
    private String host;
    private String port;
    private String auth;
    private String startTLS;
}
