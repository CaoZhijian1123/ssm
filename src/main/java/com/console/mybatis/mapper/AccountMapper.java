package com.console.mybatis.mapper;

import com.console.mybatis.bean.Account;
import org.springframework.transaction.annotation.Transactional;

/**
 * Account类对应的MyBatis映射文件
 * @author caozj
 */

public interface AccountMapper {

    /**
     * 通过id查询Account
     * @param id
     * @return
     */
    public Account queryById(Integer id);

    /**
     * 更新账户信息，id匹配
     * @param account
     */
    public void update(Account account);
}
