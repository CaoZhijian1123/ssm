package com.console.service;

import com.console.mybatis.bean.Employee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.*;



public class EmployeeServiceTest {

    EmployeeService employeeService;


    @Before
    public void setUp() throws Exception {

        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring.xml");
        employeeService = (EmployeeService) applicationContext.getBean("employeeService",EmployeeService.class);


        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void queryAll() {


        List<Employee> employees = employeeService.queryAll();
//        System.out.println(employees);

    }

}