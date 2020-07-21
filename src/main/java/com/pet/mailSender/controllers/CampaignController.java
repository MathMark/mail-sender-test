package com.pet.mailSender.controllers;

import com.pet.mailSender.model.Campaign;
import com.pet.mailSender.model.Template;
import com.pet.mailSender.model.viewModels.CampaignView;
import com.pet.mailSender.service.CampaignService;
import com.pet.mailSender.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
@RequestMapping("/campaigns")
public class CampaignController {

    @Autowired
    private CampaignService campaignService;

    @Autowired
    private TemplateService templateService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getAllCampaigns(Model model) {
        List<Campaign> campaigns = campaignService.getAll();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("campaigns", campaigns);
        modelAndView.setViewName("campaigns");

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/addCampaign")
    public ModelAndView viewCampaignForm(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("campaignAttribute", new CampaignView());

        List<Template> templates = templateService.getAll();
        modelAndView.addObject("templates", templates);

        modelAndView.setViewName("addCampaign");

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveCampaign")
    public String saveCampaign(@ModelAttribute("campaignAttribute") CampaignView campaignView,  @RequestParam("file") MultipartFile file) {
        campaignView.setPeopleList(file);
        campaignService.saveAsCampaign(campaignView);
        return "redirect:/campaigns";
    }

    @RequestMapping(value = "/run/{campaignId}")
    public String runCampaign(@PathVariable("campaignId") int campaignId, Model model){
        System.out.println(campaignId);
        campaignService.runCampaignParallel(campaignId);
        return "redirect:/campaigns";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/view/{campaignId}")
    public ModelAndView showCampaign(@PathVariable("campaignId") int campaignId, Model model){
        Campaign campaign = campaignService.getCampaignById(campaignId);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("campaign", campaign);
        modelAndView.setViewName("viewCampaign");

        return modelAndView;
    }
}
