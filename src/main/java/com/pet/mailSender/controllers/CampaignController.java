package com.pet.mailSender.controllers;

import com.pet.mailSender.model.Account;
import com.pet.mailSender.model.Campaign;
import com.pet.mailSender.model.Template;
import com.pet.mailSender.model.enums.CampaignStatus;
import com.pet.mailSender.model.viewModels.CampaignView;
import com.pet.mailSender.service.AccountService;
import com.pet.mailSender.service.CampaignService;
import com.pet.mailSender.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/campaigns")
public class CampaignController {

    @Autowired
    private CampaignService campaignService;

    @Autowired
    private TemplateService templateService;

    @Autowired
    private AccountService accountService;

    private int CAMPAIGNS_PER_PAGE = 5;

    private List<List<Campaign>> getCampaignsPages(){
        List<Campaign> allCampaigns = campaignService.getAll();
        List<List<Campaign>> pages = new ArrayList<>();

        int pagesCount = allCampaigns.size()/CAMPAIGNS_PER_PAGE;
        List<Campaign> page;
        for(int i = 0, offset = CAMPAIGNS_PER_PAGE; i < pagesCount; i++, offset += CAMPAIGNS_PER_PAGE){
            page = allCampaigns.subList(offset - CAMPAIGNS_PER_PAGE, offset);
            pages.add(page);
        }

        if(allCampaigns.size() % CAMPAIGNS_PER_PAGE > 0){
            int offset = pagesCount * CAMPAIGNS_PER_PAGE;
            page = allCampaigns.subList(offset, allCampaigns.size());
            pages.add(page);
        }

        return pages;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{pageId}")
    public ModelAndView getAllCampaigns(@PathVariable int pageId, Model model) {
        List<List<Campaign>> pages = getCampaignsPages();
        List<Campaign> campaigns = pages.get(pageId - 1);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("campaigns", campaigns);
        modelAndView.addObject("pageCount", pages.size());
        modelAndView.setViewName("campaigns");

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/addCampaign")
    public ModelAndView viewCampaignForm(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("campaignAttribute", new CampaignView());

        List<Template> templates = templateService.getAll();
        modelAndView.addObject("templates", templates);

        List<Account> accounts = accountService.getAccounts();
        modelAndView.addObject("accounts", accounts);

        modelAndView.setViewName("addCampaign");

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveCampaign")
    public String saveCampaign(@ModelAttribute("campaignAttribute") CampaignView campaignView,  @RequestParam("file") MultipartFile file) {
        campaignView.setPeopleList(file);
        campaignService.saveAsCampaign(campaignView);
        return "redirect:/campaigns/1";
    }

    @RequestMapping(value = "/run/{campaignId}")
    public String runCampaign(@PathVariable("campaignId") int campaignId, Model model){
        campaignService.runCampaignParallel(campaignId);
        return "redirect:/campaigns/1";
    }

    @RequestMapping(value = "/stop/{campaignId}")
    public String stopCampaign(@PathVariable("campaignId") int campaignId, Model model){
        campaignService.stopCampaign(campaignId);
        return "redirect:/campaigns/1";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/view/{campaignId}")
    public ModelAndView showCampaign(@PathVariable("campaignId") int campaignId, Model model){
        Campaign campaign = campaignService.getCampaignById(campaignId);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("campaign", campaign);
        modelAndView.setViewName("viewCampaign");

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/deleteCampaign/{campaignId}")
    public String deleteCampaign(@PathVariable("campaignId") int campaignId){
        Campaign campaign = campaignService.getCampaignById(campaignId);

        if(campaign.getEmailStatistics().getCampaignStatus() != CampaignStatus.RUNNING){
            campaignService.deleteCampaign(campaign);
        }

        return "redirect:/campaigns/1";
    }
}
