package com.pet.mailSender.controllers;

import com.pet.mailSender.model.Template;
import com.pet.mailSender.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/templates")
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    private static final int GRID_COLUMN_COUNT = 3;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getAllTemplates(Model model) {
        List<Template> templates = templateService.getAll();

        List<List<Template>> templatesTable = templateGrid(templates);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("templatesTable", templatesTable);
        modelAndView.setViewName("templates");

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/addTemplate")
    public String viewCampaignForm(Model model) {
        model.addAttribute("templateAttribute", new Template());
        return "addTemplate";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveTemplate")
    public String saveCampaign(@ModelAttribute("templateAttribute") @Validated Template template,
                               BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "addTemplate";
        }
        templateService.save(template);
        return "redirect:/templates";
    }

    private List<List<Template>> templateGrid(List<Template> templates){
        List<List<Template>> result = new ArrayList<>();

        List<Template> colTemplates = new ArrayList<>();

        for(int i = 0; i < templates.size(); i++){
            colTemplates.add(templates.get(i));
            if((i + 1) % GRID_COLUMN_COUNT == 0){
                result.add(colTemplates);
                colTemplates = new ArrayList<>();
            }
        }
        result.add(colTemplates);
        return result;
    }
}
