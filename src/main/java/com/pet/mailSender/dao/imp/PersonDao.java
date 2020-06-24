package com.pet.mailSender.dao.imp;

import com.pet.mailSender.model.Person;

public class PersonDao extends AbstractDao<Person> {

    public PersonDao(){
        setClazz(Person.class);
    }

}
