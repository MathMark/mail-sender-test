package com.pet.mailSender.dao.imp;

import com.pet.mailSender.model.PeopleList;
import org.springframework.stereotype.Component;

@Component
public class PeopleListDao extends AbstractDao<PeopleList> {

    public PeopleListDao(){
        setClazz(PeopleList.class);
    }
}
