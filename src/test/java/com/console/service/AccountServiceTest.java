package com.console.service;

import com.console.mybatis.bean.Account;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void transfer() {
        Account account1 = accountService.getById(2);
        Account account2 = accountService.getById(3);

        try {
            double money = 500;

            System.out.println(account1);

            System.out.println(account2);
            accountService.transfer(account1, account2, money);

            System.out.println("------------");
            System.out.println(account1);
            System.out.println(account2);
        }catch (Exception e){
            account1=accountService.getById(2);
            account2=accountService.getById(3);

            System.out.println("发生错误，重新获取账户信息");

            System.out.println(account1);

            System.out.println(account2);
        }

    }
}