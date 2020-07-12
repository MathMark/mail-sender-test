package com.pet.mailSender.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
public class Template implements Serializable {
    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Integer id;

    @NotEmpty(message = "{title.empty}")
    @Column(nullable = false, unique = true)
    @Getter @Setter private String title;

    @NotEmpty(message = "{subject.empty}")
    @Size(max = 80, message = "{subject.toolong}")
    @Column(nullable = false)
    @Getter
    @Setter
    private String subject;

    @NotEmpty(message = "{body.empty}")
    @Column(columnDefinition = "TEXT", nullable = false) //mysql type
    @Getter @Setter private String body;

    @Column(columnDefinition = "TEXT")//mysql type
    @Getter @Setter private String signature;

    @OneToMany(mappedBy = "template")
    private Set<Campaign> campaigns;

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Template template = (Template) o;
        return title.equals(template.title) &&
                subject.equals(template.subject) &&
                body.equals(template.body);
    }*/

    @Override
    public int hashCode() {
        return Objects.hash(id, title, subject, body);
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
