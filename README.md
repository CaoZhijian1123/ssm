# ssm
这是一个SSM框架整合的演示项目

SSM框架（Spring+Spring MVC + MyBatis）框架是当前后端开发较为流行的框架集合，在整合时应该按照Spring整合其他框架的原则进行，在整合测试时，先测试每个框架单独是否配置成功，再测试整合是否成功。

# 整合示例

> 示例使用c3p0作为数据源
>
> 日志使用log4j

## pom依赖

```xml
<dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>3.1.0</version>
    </dependency>
<!--    mybatis-->
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis</artifactId>
      <version>3.5.5</version>
    </dependency>


    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis-spring</artifactId>
      <version>2.0.5</version>
    </dependency>

<!--    log4j-->
    <dependency>
      <groupId>log4j</groupId>
      <artifactId>log4j</artifactId>
      <version>1.2.17</version>
    </dependency>

    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>8.0.15</version>
    </dependency>

<!--jsp页面中的jstl库依赖，项目中使用 forEach标签-->
    <dependency>
      <groupId>jstl</groupId>
      <artifactId>jstl</artifactId>
      <version>1.2</version>
    </dependency>

    <dependency>
      <groupId>javax.servlet.jsp</groupId>
      <artifactId>jsp-api</artifactId>
      <version>2.0</version>
    </dependency>
<!--    注解-->
    <dependency>
      <groupId>javax.annotation</groupId>
      <artifactId>javax.annotation-api</artifactId>
      <version>1.3.1</version>
    </dependency>


<!--Spring framework-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>${spring-version}</version>
    </dependency>

    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-core</artifactId>
      <version>${spring-version}</version>
    </dependency>

    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-beans</artifactId>
      <version>${spring-version}</version>
    </dependency>

    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-aop</artifactId>
      <version>${spring-version}</version>
    </dependency>

    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-expression</artifactId>
      <version>${spring-version}</version>
    </dependency>

    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-jdbc</artifactId>
      <version>${spring-version}</version>
    </dependency>

    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-tx</artifactId>
      <version>${spring-version}</version>
    </dependency>

    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-test</artifactId>
      <version>${spring-version}</version>
    </dependency>

<!--    切面表达式-->
    <dependency>
      <groupId>org.aspectj</groupId>
      <artifactId>aspectjweaver</artifactId>
      <version>1.8.7</version>
    </dependency>

<!--Spring mvc-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-webmvc</artifactId>
      <version>${spring-version}</version>
    </dependency>

    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-web</artifactId>
      <version>${spring-version}</version>
    </dependency>

<!--    数据源-->
    <dependency>
      <groupId>com.mchange</groupId>
      <artifactId>c3p0</artifactId>
      <version>0.9.5.2</version>
    </dependency>


    <!-- 文件上传使用的jar包 -->

    <dependency>
      <groupId>commons-fileupload</groupId>
      <artifactId>commons-fileupload</artifactId>
      <version>1.3.1</version>
    </dependency>

    <dependency>
      <groupId>commons-io</groupId>
      <artifactId>commons-io</artifactId>
      <version>2.5</version>
    </dependency>

  <!--单元测试-->
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.12</version>
      <scope>test</scope>
    </dependency>
```



## 配置文件

### log4j

log4j.properties

```properties
### #配置根Logger ###
log4j.rootLogger=debug,stdout

### 输出到控制台 ###
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.Target=System.out
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=[%d{yyy-MM-dd HH\:mm\:ss}] [%5p] %c{1}\:%L - %m%n
```

### mybatis

MyBatis的很多配置都在Spring配置文件中进行，只剩下不常用的配置在单独配置文件中进行

mybatis-config.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">

<configuration>

    <settings>
        <setting name="mapUnderscoreToCamelCase" value="true"/>
        <setting name="logImpl" value="STDOUT_LOGGING"/>
        <setting name="lazyLoadingEnabled" value="true"/>
        <setting name="aggressiveLazyLoading" value="false"/>
        <setting name="cacheEnabled" value="true"/>
    </settings>

</configuration>
```

### spring

spring配置文件中配置spring和mybatis相关配置

spring.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context" xmlns:tx="http://www.springframework.org/schema/tx"
       xmlns:mybatis-spring="http://mybatis.org/schema/mybatis-spring"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd http://mybatis.org/schema/mybatis-spring http://mybatis.org/schema/mybatis-spring.xsd">

    <!--组件扫描-->
    <context:component-scan base-package="com.console" >
        <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
    </context:component-scan>

    <context:annotation-config></context:annotation-config>

    <!--配置文件-->
    <context:property-placeholder location="classpath:dbconfig.properties"/>


    <!--配置数据源-->
    <bean class="com.mchange.v2.c3p0.ComboPooledDataSource" id="dataSource">
        <property name="driverClass" value="${jdbc.driver}"/>
        <property name="jdbcUrl" value="${jdbc.url}"/>
        <property name="user" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>

        <property name="initialPoolSize" value="3"/>
        <property name="minPoolSize" value="2"/>
        <property name="maxPoolSize" value="5"/>
        <property name="acquireIncrement"  value="3" />
        <property name="maxStatements" value="8"/>
        <property name="maxStatementsPerConnection" value="5"/>
        <property name="maxIdleTime" value="1800"/>

    </bean>

    <!--事务管理器-->
    <bean class="org.springframework.jdbc.datasource.DataSourceTransactionManager" id="transactionManager">
        <property name="dataSource" ref="dataSource"></property>
    </bean>

<!--    开启基于注解的事务-->
    <tx:annotation-driven transaction-manager="transactionManager" ></tx:annotation-driven>

<!--    mybatis-->

<!--mybatis配置文件位置-->
    <bean class="org.springframework.core.io.ClassPathResource" id="resource">
        <constructor-arg name="path" value="mybatis-config.xml"></constructor-arg>
    </bean>


	<!--SqlSessionFactoryBean-->
    <bean class="org.mybatis.spring.SqlSessionFactoryBean" id="sqlSessionFactory">
        <property name="dataSource" ref="dataSource"></property>
        <property name="configLocation" value="classpath:mybatis-config.xml"/>
        <property name="mapperLocations" value="classpath:mapper/*.xml"/>
    </bean>

<!--    下面两种方式都可以实现Mapper自动扫描注入-->
    <!--方式1-->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer" id="mapperScannerConfigurer">
        <property name="basePackage" value="com.console.mybatis.mapper"></property>
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"></property>
    </bean>
    
	<!--方式2-->
<!--    mapper接口扫描-->
<!--    <mybatis-spring:scan base-package="com.console.mybatis.mapper"></mybatis-spring:scan>-->


</beans>
```



其中，和数据库有关的配置单独抽取出来

dbconfig.properties

```properties
jdbc.driver=com.mysql.cj.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/test?serverTimezone=GMT%2B8
jdbc.username=root
jdbc.password=root
```



### Spring MVC

`spring-servlet.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/mvc https://www.springframework.org/schema/mvc/spring-mvc.xsd">

	<!--    只扫描控制器-->
    <context:component-scan base-package="com.console" use-default-filters="false">
        <context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
    </context:component-scan>


    <!--视图解析器-->
    <bean  id="internalResourceViewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/pages/"></property>
        <property name="suffix" value=".jsp"></property>
    </bean>

	<!--    Spring mvc注解支持-->
    <mvc:annotation-driven ></mvc:annotation-driven>
    <mvc:default-servlet-handler/>

	<!--    静态资源过滤器-->
    <mvc:resources mapping="/css/**" location="/css/"/>
    <mvc:resources mapping="/images/**" location="/images"/>
    <mvc:resources mapping="/js/**" location="/js/"/>

</beans>
```



### web.xml

为了让web服务启时Spring容器可以跟随启动，需要在web.xml中配置

```xml
<!DOCTYPE web-app PUBLIC
 "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
 "http://java.sun.com/dtd/web-app_2_3.dtd" >

<web-app>
  <display-name>Archetype Created Web Application</display-name>


<!--  Spring配置文件-->
  <context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>classpath:spring.xml</param-value>
  </context-param>

<!--  Spring跟随Web一起启动-->
  <listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
  </listener>

    
  <servlet>
    <servlet-name>spring</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>classpath:spring-servlet.xml</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
  </servlet>

  <servlet-mapping>
    <servlet-name>spring</servlet-name>
    <url-pattern>/</url-pattern>
  </servlet-mapping>
  
<!-- 编码过滤器-->
  
  <filter>
    <filter-name>characterEncodingFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
      <param-name>encoding</param-name>
      <param-value>UTF-8</param-value>
    </init-param>
  </filter>

  <filter-mapping>
    <filter-name>characterEncodingFilter</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>
  
  <welcome-file-list>
    <welcome-file>/index.jsp</welcome-file>
  </welcome-file-list>
</web-app>

```



## 事务控制

在Spring配置文件中已经开启了Spring的事务控制器和基于注解的事务管理

在Service类中进行事务控制即可

`AccountService.java`

```java
package com.console.service;

import com.console.mybatis.bean.Account;
import com.console.mybatis.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.management.RuntimeErrorException;


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

        //假定这里发生异常，测试事务是否成功
        int i = 1 / 0;
        accountMapper.update(to);


    }


    public Account getById(Integer id){
        Account account = accountMapper.queryById(id);
        return account;

    }

}
```

`AccountServiceTest.java`

```java
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
```



事务控制时，在业务层获取到的异常事务控制之后，调用业务方法的方法内同样可以获取到