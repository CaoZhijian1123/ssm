package com.console.mybatis.mapper;

import com.console.mybatis.bean.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author caozj
 */
//@Mapper

//@Repository("employeeService")


public interface EmployeeMapper
{
    /**
     * 通过部门id查询
     * @param id
     * @return
     */
    public List<Employee> queryByDepId(int id);


    /**
     * 查询所有Employee对象
     * @return
     */
    public List<Employee> queryAll();

    /**
     * 通过id查询Employee对象
     * @param id
     * @return
     */
    public Employee queryById(int id);


    /**
     * 添加Employee对象
     * @param employee
     */
    public void addEmployee(Employee employee);

    /**
     * 更新Employee对象，使用id匹配
     * @param employee
     */
    public void updateEmployee(Employee employee);

    /**
     * 删除Employee对象
     * @param id
     */
    public void deleteEmployeeById(int id);

    /**
     * 测试多参数
     * @param name
     * @param email
     * @return
     */
    public Employee getEmployeeByNameAndEmail(@Param("name") String name, @Param("email") String email);

    /**
     * 通过id查询，结果封装为map,key为列名
     * @param id
     * @return
     */
    public Map getEmpToMap(int id);

    /**
     * name模糊查询
     * @param name
     * @return map  key为id
     */
    @MapKey("id")
    public Map<Integer,Employee> getNameLike(String name);

}
