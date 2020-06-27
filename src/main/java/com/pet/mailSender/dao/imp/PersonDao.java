package com.pet.mailSender.dao.imp;

import com.pet.mailSender.model.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonDao extends AbstractDao<Person> {

    public PersonDao(){
        setClazz(Person.class);
    }

}
