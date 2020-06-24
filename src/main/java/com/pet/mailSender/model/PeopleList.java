package com.pet.mailSender.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "people_list")
public class PeopleList implements Serializable {
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
    @OneToMany(mappedBy = "peopleList", cascade = CascadeType.PERSIST)
    private Set<Person> people = new HashSet<>();

    @OneToOne(mappedBy = "peopleList", cascade = CascadeType.MERGE)
    @Getter
    @Setter
    private Campaign campaign;

    @Override
    public String toString() {
        return "PeopleList{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", people=" + people +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PeopleList that = (PeopleList) o;
        return id.equals(that.id) &&
                title.equals(that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
