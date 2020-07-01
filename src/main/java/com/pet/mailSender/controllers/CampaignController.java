package com.pet.mailSender.controllers;

import com.pet.mailSender.model.Campaign;
import com.pet.mailSender.model.viewModels.CampaignView;
import com.pet.mailSender.service.CampaignService;
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

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getAllCampaigns(Model model) {
        List<Campaign> campaigns = campaignService.getAll();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("campaigns", campaigns);
        modelAndView.setViewName("campaigns");

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/addCampaign")
    public String viewCampaignForm(Model model) {
        model.addAttribute("campaignAttribute", new CampaignView());
        return "addCampaign";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveCampaign")
    public String saveCampaign(@ModelAttribute("campaignAttribute") CampaignView campaignView, @RequestParam("file") MultipartFile file) {
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
