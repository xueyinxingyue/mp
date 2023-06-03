package com.xueyin.mp;

import com.xueyin.mp.entity.Employee;
import com.xueyin.mp.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MpApplicationTests {
    //从Spring容器中获取Mapper对象
    @Autowired
    private EmployeeMapper employeeMapper;

    //测试增加方法
    @Test
    void testInsert() {
        Employee employee = new Employee(null, "张三", "zhangsan", "zhangsan@wolfcode.cn", 30, 1, 1L);
        int num = employeeMapper.insert(employee);
        System.out.println(num);
    }

    @Test
    void testUpdate() {
        Employee employee = new Employee(1660704438384087041L, "李四", "lisi", "lisi@wolfcode.cn", 30, 1, 1L);
        employeeMapper.updateById(employee);
    }

    @Test
    void testDelete() {
        employeeMapper.deleteById(1660704438384087041L);
    }

    @Test
    void testSelectById() {
        Employee employee = employeeMapper.selectById(1);
        System.out.println(employee);
    }

    @Test
    void testSelectList() {
        //selectList:根据wrapper设置条件查询，如果设置为null，表示没有条件
        List<Employee> employees = employeeMapper.selectList(null);

        //第一种查询方法
        employees.forEach(System.out::println);
//        //第二种查询方法
//        employees.forEach(emp -> System.out.println(emp));
//        //第三种查询方法
//        for (Employee employee : employees){
//            System.out.println(employee);
//        }

    }
}
