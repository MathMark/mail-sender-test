package com.pet.mailSender.service;

import com.pet.mailSender.dao.Dao;
import com.pet.mailSender.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    @Qualifier("accountDao")
    private Dao<Account> accountDao;

    public List<Account> getAccounts(){
        return accountDao.getAll();
    }

    public Account getAccount(int id){
        return accountDao.getById(id);
    }

    public void save(Account account){
        accountDao.add(account);
    }

}
