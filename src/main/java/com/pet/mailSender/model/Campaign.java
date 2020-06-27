package com.pet.mailSender.model;

import com.pet.mailSender.model.enums.CampaignStatus;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "campaign")
public class Campaign implements Serializable {
    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private Long delay;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "people_list_id")
    @Getter
    @Setter
    private PeopleList peopleList;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "template_id")
    @Getter
    @Setter
    private Template template;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "email_statistics_id")
    @Getter
    @Setter
    private EmailStatistics emailStatistics;

    @Override
    public String toString() {
        return "Campaign{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", delay=" + delay +
                ", peopleList=" + peopleList +
                ", template=" + template +
                ", emailStatistics=" + emailStatistics +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Campaign campaign = (Campaign) o;
        return id.equals(campaign.id) &&
                title.equals(campaign.title) &&
                delay.equals(campaign.delay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, delay);
    }
}
