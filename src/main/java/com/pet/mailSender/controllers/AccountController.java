package com.pet.mailSender.controllers;

import com.pet.mailSender.dao.imp.AccountDao;
import com.pet.mailSender.model.Account;
import com.pet.mailSender.model.Campaign;
import com.pet.mailSender.model.viewModels.CampaignView;
import com.pet.mailSender.service.AccountService;
import com.pet.mailSender.service.emailSender.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private EmailSender emailSender;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getAllCampaigns(Model model) {
        List<Account> accounts = accountService.getAccounts();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("accounts", accounts);
        modelAndView.setViewName("accounts");

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/addAccount")
    public ModelAndView addAccountForm(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("account", new Account());
        modelAndView.setViewName("addAccount");

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveAccount")
    public ModelAndView testConnection(@ModelAttribute("account") @Validated Account account, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        if(bindingResult.hasErrors()){
            modelAndView.setViewName("addAccount");
        }else{
            boolean isValid = emailSender.validateCredentials(account);
            if(isValid){
                accountService.save(account);
                modelAndView.setViewName("redirect:/accounts");
            }else{
                modelAndView.addObject("connectionStatus", "Wrong credentials");
                modelAndView.addObject("account", account);
                modelAndView.setViewName("addAccount");
            }
        }

        return modelAndView;
    }
}
