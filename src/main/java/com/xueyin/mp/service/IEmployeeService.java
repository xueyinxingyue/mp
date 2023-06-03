package com.xueyin.mp.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xueyin.mp.entity.Employee;
import com.xueyin.mp.qo.EmployeeQueryObject;

public interface IEmployeeService extends IService<Employee> {
    //条件分页方法
    public IPage<Employee> page(EmployeeQueryObject qo);
}
