package com.console.controller;

import com.console.mybatis.bean.Employee;
import com.console.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * @author caozj
 * @version 1.0
 * @className EmpController
 * @date 2020/9/22 11:57
 */
@Controller
@RequestMapping("/emp")
public class EmpController {

//    @Resource(name="employeeService")
//    @Autowired
//    @Qualifier("employeeService")


    @Autowired
    private EmployeeService employeeService;

    @RequestMapping( "all" )
    public String allEmployees(Map<String,Object> params){

        List<Employee> employees = employeeService.queryAll();
        System.out.println("查询结果数量 ："+employees.size());
        params.put("employees",employees);
        return "allEmpList";
    }

    @RequestMapping("/test")
    public String test(){
        return "test";
    }

}
