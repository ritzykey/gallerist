package com.frknozbek.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frknozbek.dto.DtoAccount;
import com.frknozbek.dto.DtoAccountIU;
import com.frknozbek.exception.BaseException;
import com.frknozbek.exception.ErrorMessage;
import com.frknozbek.exception.MessageType;
import com.frknozbek.model.Account;
import com.frknozbek.repository.AccountRepository;
import com.frknozbek.service.IAccountService;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;

    private Account createAccount(DtoAccountIU dtoAccountIU) {
        Account account = new Account();
        account.setCreateTime(new Date());
        BeanUtils.copyProperties(dtoAccountIU, account);
        return account;
    }

    @Override
    public DtoAccount saveAccount(DtoAccountIU dtoAccountIU) {

        DtoAccount dtoAccount = new DtoAccount();

        Account savedAccount = accountRepository.save(createAccount(dtoAccountIU));

        BeanUtils.copyProperties(savedAccount, dtoAccount);

        return dtoAccount;
    }

    @Override
    public String sayHello() {
        return "Hello";
    }

    @Override
    public List<DtoAccount> listAccount() {

        List<DtoAccount> listDtoAccount = new ArrayList<DtoAccount>();

        List<Account> allAccount = accountRepository.findAll();

        allAccount.forEach(acc -> {
            DtoAccount dtoAccount = new DtoAccount();
            dtoAccount.setCreateTime(new Date());
            BeanUtils.copyProperties(acc, dtoAccount);
            listDtoAccount.add(dtoAccount);
        });

        return listDtoAccount;
    }

    @Override
    public DtoAccount getAccountById(String accountNo) {
        DtoAccount dtoAccount = new DtoAccount();
        dtoAccount.setCreateTime(new Date());

        Optional<Account> optBbyAccountNo = accountRepository.findByAccountNo(accountNo);

        optBbyAccountNo.orElseThrow(() -> new BaseException(
                new ErrorMessage(MessageType.NO_RECORD_EXIST, accountNo)));

        BeanUtils.copyProperties(optBbyAccountNo.get(), dtoAccount);

        return dtoAccount;
    }

}
