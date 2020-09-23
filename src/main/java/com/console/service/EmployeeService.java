package com.console.service;

import com.console.mybatis.bean.Employee;
import com.console.mybatis.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author caozj
 * @version 1.0
 * @className EmployeeService
 * @date 2020/9/22 12:00
 */
@Service
public class EmployeeService {


    @Autowired
    private EmployeeMapper employeeMapper;


    /**
     * 查询所有员工信息
     * @return
     */
    public List<Employee> queryAll(){
        List<Employee> employees = employeeMapper.queryAll();
        return employees;
    }
}
