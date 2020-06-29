package com.pet.mailSender.controllers;

import com.pet.mailSender.model.Campaign;
import com.pet.mailSender.model.viewModels.CampaignView;
import com.pet.mailSender.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.*;
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
        model.addAttribute("campaignAttribute" , new CampaignView());
        return "addCampaign";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveCampaign")
    public String saveCampaign(@ModelAttribute("campaignAttribute") CampaignView campaignView, @RequestParam("file") MultipartFile file) {
        try {
            System.out.println(file.getOriginalFilename());

            StringBuilder resultStringBuilder = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    resultStringBuilder.append(line).append("\n");
                }
            }
            System.out.println(resultStringBuilder.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
        campaignService.saveAsCampaign(campaignView);
        return "redirect:/campaigns";
    }
}
