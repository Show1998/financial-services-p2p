package com.cpp.pages.service.impl;

import com.cpp.pages.mapper.user.AccountMapper;
import com.cpp.pages.pojo.Account;
import com.cpp.pages.service.AccountService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    AccountMapper accountMapper;

    @Override
    public Double getBalance(Integer uid) {
        Account account = accountMapper.selectByUid(uid);
        return account.getAvailableMoney();
    }
}
