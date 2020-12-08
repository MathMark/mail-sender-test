package com.pet.mailSender.service;

import com.pet.mailSender.dao.Dao;
import com.pet.mailSender.model.Template;
import java.util.List;

public class TemplateService {

    private Dao<Template> templateDao;

    public TemplateService(Dao<Template> templateDao) {
        this.templateDao = templateDao;
    }

    public List<Template> getAll(){
        return templateDao.getAll();
    }

    public void save(Template template){
        templateDao.add(template);
    }
}
