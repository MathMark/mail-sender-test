package com.pet.mailSender.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
public class Template implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String subject;

    @Getter
    @Setter
    @Column(columnDefinition = "varchar2")
    private String body;

    @Getter
    @Setter
    @Column(columnDefinition = "varchar2")
    private String signature;

    @OneToMany(mappedBy = "template")
    private Set<Campaign> campaigns;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Template template = (Template) o;
        return id.equals(template.id) &&
                title.equals(template.title) &&
                subject.equals(template.subject) &&
                body.equals(template.body) &&
                Objects.equals(signature, template.signature);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, subject, body, signature);
    }

    @Override
    public String toString() {
        return "Template{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", signature='" + signature + '\'' +
                '}';
    }
}
