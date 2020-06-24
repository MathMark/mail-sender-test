package com.pet.mailSender.dao.imp;

import com.pet.mailSender.model.Template;

public class TemplateDao extends AbstractDao<Template> {

    public TemplateDao(){
        setClazz(Template.class);
    }
}
