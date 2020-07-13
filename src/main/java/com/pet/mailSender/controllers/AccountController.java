package com.pet.mailSender.controllers;

import com.pet.mailSender.dao.imp.AccountDao;
import com.pet.mailSender.model.Account;
import com.pet.mailSender.model.Campaign;
import com.pet.mailSender.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getAllCampaigns(Model model) {
        List<Account> accounts = accountService.getAccounts();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("accounts", accounts);
        modelAndView.setViewName("accounts");

        return modelAndView;
    }
}
