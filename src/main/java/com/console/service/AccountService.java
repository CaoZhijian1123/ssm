package com.console.service;

import com.console.mybatis.bean.Account;
import com.console.mybatis.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.RuntimeErrorException;

/**
 * @author caozj
 * @version 1.0
 * @className AccountService
 * @date 2020/9/23 9:53
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AccountService {

    @Autowired
    private AccountMapper accountMapper;

    /**
     * 转账
     * @param from
     * @param to
     * @param money
     */
    public void transfer(Account from,Account to,Double money){
        if (from.getMoney() < money){
            throw new RuntimeException("余额不足，无法转账");
        }


        from.setMoney(from.getMoney() - money);
        to.setMoney(to.getMoney() + money);

        accountMapper.update(from);

        int i = 1 / 0;
        accountMapper.update(to);


    }


    public Account getById(Integer id){
        Account account = accountMapper.queryById(id);
        return account;

    }

}
