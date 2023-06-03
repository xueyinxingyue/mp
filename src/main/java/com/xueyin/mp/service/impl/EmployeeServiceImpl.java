package com.xueyin.mp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xueyin.mp.entity.Employee;
import com.xueyin.mp.mapper.EmployeeMapper;
import com.xueyin.mp.qo.EmployeeQueryObject;
import com.xueyin.mp.service.IEmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {
    @Override
    public IPage<Employee> page(EmployeeQueryObject qo){
        LambdaQueryWrapper<Employee> wrapper = new LambdaQueryWrapper<>();

        wrapper.like(StringUtils.isNotBlank(qo.getName()),Employee::getName,qo.getName());

        IPage<Employee> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());

        return baseMapper.selectPage(page,wrapper);
    }
}
