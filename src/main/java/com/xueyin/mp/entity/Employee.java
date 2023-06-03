package com.xueyin.mp.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.*;

import java.lang.reflect.Type;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
//@Data

//当实体类名字与表名不一致时，需要用到TableName(表名)
//@TableName("t_employee")
public class Employee {
    //主键默认采用雪花算法生成唯一的主键
    //type：设置主键的生成策略    IdType.AUTO：数据库默认主键自增方式
    @TableId(type = IdType.AUTO)
    private Long id;

//    @TableField("name")
    private String name;
    private String password;
    private String email;
    private int age;
    private int admin;
    private Long deptId;

    //所属的部门信息
    @TableField(exist = false)
    private Department department;

    public Employee(Long id, String name, String password, String email, int age, int admin, Long deptId) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.age = age;
        this.admin = admin;
        this.deptId = deptId;
    }

    //exist = false ： 告诉mybatis-plus当前属性在表中没有对应的列，mybatis-plus在生成sql语句时会忽略该属性
    @TableField(exist = false)
    private String genter;

    //是否删除
    @TableLogic
    private int deleted;
}
