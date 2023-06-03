package com.xueyin.mp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xueyin.mp.entity.Employee;
import com.xueyin.mp.mapper.EmployeeMapper;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class TestQueryWrapper {
    @Autowired
    private EmployeeMapper employeeMapper;

    //需求：查询name=zjap,age=18的用户
    @Test
    void testQuery1(){
        Map<String,Object> map = new HashMap<>();
        map.put("name","zhao");
        map.put("age",18);

        List<Employee> employees = employeeMapper.selectByMap(map);
        employees.forEach(System.out::println);
    }

    //修改、删除     UpdateWrapper
    //查询            QueryWrapper
    //需求：查询name=zjap,age=18的用户
    @Test
    void testQuery2(){
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.eq("name","zhao")
                .eq("age",18);

        //设置要查询的列
        wrapper.select("name","age","email");

        List<Employee> employees = employeeMapper.selectList(wrapper);
        employees.forEach(System.out::println);
    }

    //需求：查询name=zjap,age=18的用户
    @Test
    void testQuery3(){
        LambdaQueryWrapper<Employee> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Employee::getName,"zhao")
                .eq(Employee::getAge,"18");

        List<Employee> employees = employeeMapper.selectList(wrapper);
        employees.forEach(System.out::println);
    }
}
