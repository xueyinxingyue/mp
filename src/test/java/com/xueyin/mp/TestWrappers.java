package com.xueyin.mp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xueyin.mp.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Wrapper;

/*
 * 可以使用Wrappers获取所有的wrapper
 */
@SpringBootTest
public class TestWrappers {
    @Test
    void testWrappers(){
        //update
        UpdateWrapper<Employee> updateWrapper = Wrappers.<Employee>update();
        LambdaUpdateWrapper<Employee> wrapper = Wrappers.<Employee>lambdaUpdate();
        //updateWrapper  -->  LambdaUpdateWrapper
        LambdaUpdateWrapper<Employee> wrapper1 = updateWrapper.lambda();

        //query
        QueryWrapper<Employee> query = Wrappers.<Employee>query();
        LambdaUpdateWrapper<Employee> wrapper2 = Wrappers.<Employee>lambdaUpdate();

        LambdaQueryWrapper<Employee> lambda = query.lambda();
    }
}
