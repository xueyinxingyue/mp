package com.xueyin.mp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xueyin.mp.entity.Employee;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/*
* mybatis-plus生成sqL
*   1.怎么知道表名?   根据继承BaseMapper指定的实体类泛型找对应的表
*   2.怎么知道表中字段?     根据实体类中的属性生成宇段
*
*   IoC：控制反转    将对象创建交于Spring去做
*       IoC注解（代码作用一样，但可通过对应注解可以知道代码是三层架构中的哪一种代码）
*           //@Repository
*           //@Controller
*           //@Service
*           @Component
*
*   DI：依赖注入     从Spring容器获取对应的对象设置给某一个属性
*       DI注解：
*           @Autowired
*           @Resource   //作用一样，但有区别
* */
@Repository
public interface EmployeeMapper extends BaseMapper<Employee> {
    public List<Employee> listByXml();

    public List<Employee> listByXmlJoin();
}
