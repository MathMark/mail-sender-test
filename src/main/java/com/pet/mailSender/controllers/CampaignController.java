package com.pet.mailSender.controllers;

import com.pet.mailSender.model.Campaign;
import com.pet.mailSender.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/campaigns")
public class CampaignController {

    @Autowired
    private CampaignService campaignService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getAllCampaigns(Model model){
        List<Campaign> campaigns = campaignService.getAll();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("campaigns", campaigns);
        modelAndView.setViewName("campaigns");

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/addCampaign")
    public String viewCampaignForm(Model model) {
        model.addAttribute("campaignAttribute" , new Campaign());
        return "addCampaign";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public String addCampaign(@ModelAttribute("bookAttribute")  @Validated Campaign campaign) {
        System.out.println(campaign.getTitle());
        campaignService.save(campaign);
        return "redirect:/campaigns";
    }

}
