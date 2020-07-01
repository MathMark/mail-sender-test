package com.pet.mailSender.service;

import com.pet.mailSender.dao.Dao;
import com.pet.mailSender.model.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TemplateService {

    @Autowired
    @Qualifier("templateDao")
    private Dao<Template> templateDao;

    public List<Template> getAll(){
        return templateDao.getAll();
    }

    public void save(Template template){
        templateDao.add(template);
    }
}
