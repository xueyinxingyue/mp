package com.xueyin.mp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xueyin.mp.entity.Employee;
import com.xueyin.mp.qo.EmployeeQueryObject;
import com.xueyin.mp.service.IEmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
public class TestService {
    @Autowired
    private IEmployeeService employeeService;

    @Test
    void testSave(){
        Employee employee = new Employee();
        employeeService.save(employee);
    }

    //模拟条件分页
    //条件数据      name
    //分页数据      currentPage     pageSize
    @Test
    void testPage(){
        //假设已经获取到请求参数
        EmployeeQueryObject qo = new EmployeeQueryObject();
        qo.setName("a");
        qo.setCurrentPage(1);
        qo.setPageSize(5);

        //调用service
        IPage<Employee> page = employeeService.page(qo);

        System.out.println("当前页码" + page.getCurrent());
        System.out.println("总数据量" + page.getTotal());
        System.out.println("总页数" + page.getPages());
        System.out.println("每页数据量" + page.getSize());
        System.out.println("当前页数据" + page.getRecords());
    }

    @Test
    void testDelete(){
        employeeService.removeByIds(Arrays.asList(1,2,3,4,5));
    }
}
