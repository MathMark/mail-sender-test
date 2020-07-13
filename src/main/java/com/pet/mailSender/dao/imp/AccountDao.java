package com.pet.mailSender.dao.imp;

import com.pet.mailSender.model.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountDao extends AbstractDao<Account> {

    public AccountDao(){
        setClazz(Account.class);
    }
}
