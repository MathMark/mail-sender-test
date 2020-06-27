package com.pet.mailSender.model;

import com.pet.mailSender.model.enums.CampaignStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "email_statistics")
public class EmailStatistics {

    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private int sentEmailsCount;

    @Getter
    @Setter
    private int rejectedEmailsCount;

    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private CampaignStatus campaignStatus;

    @OneToOne(mappedBy = "emailStatistics", cascade = CascadeType.MERGE)
    @Getter
    @Setter
    private Campaign campaign;

    @Override
    public String toString() {
        return "EmailStatistics{" +
                "id=" + id +
                ", sentEmailsCount=" + sentEmailsCount +
                ", rejectedEmailsCount=" + rejectedEmailsCount +
                ", campaignStatus=" + campaignStatus +
                '}';
    }
}
