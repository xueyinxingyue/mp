package com.xueyin.mp;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xueyin.mp.entity.Employee;
import com.xueyin.mp.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestUpdateWrapper {
    @Autowired
    private EmployeeMapper employeeMapper;

    //需求：将id=1的员工name改为zhao
    @Test
    void testUpdate(){
        UpdateWrapper<Employee> wrapper = new UpdateWrapper<>();
        //条件
        wrapper.eq("id",1L);
        //修改的数据
        wrapper.set("name","zhao");
        employeeMapper.update(null,wrapper);
    }

    //需求：将id=1的员工age改为18， 如果传入ename变量值不等于null或者“”，修改为员工name为ename变量值
    @Test
    void testUpdate2(){
        String ename = "";

        UpdateWrapper<Employee> wrapper = new UpdateWrapper<>();
        wrapper.eq("id",1L);
        wrapper.set("age",18);
        wrapper.set(StringUtils.isNotBlank(ename),"name",ename);

        employeeMapper.update(null,wrapper);
    }

    //需求：将id=1的用户name改为zhao
    @Test
    void testUpdate3(){
        UpdateWrapper<Employee> wrapper = new UpdateWrapper<>();
        wrapper.eq("id",1L);
        wrapper.setSql("name='zhao'");

        employeeMapper.update(null,wrapper);
    }

    //需求：将id=1的用户name改为zhao
    @Test
    void testUpdate4(){
        //Employee::getId   表中列名，避免写列名时出现错误
        // 因为实体类中属性与表中列对应的，所以可以根据实体类中的属性映射到表中列
        LambdaUpdateWrapper<Employee> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Employee::getId,1L);
        wrapper.set(Employee::getName,"zhao");

        employeeMapper.update(null,wrapper);
    }
}
