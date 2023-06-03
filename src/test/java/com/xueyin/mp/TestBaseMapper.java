package com.xueyin.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xueyin.mp.entity.Employee;
import com.xueyin.mp.mapper.EmployeeMapper;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@SpringBootTest
public class TestBaseMapper {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    void testInsert() {
        Employee employee = new Employee(20L, "张三", "zhangsan", "zhangsan@wolfcode.cn", 30, 1, 1L);
        employeeMapper.insert(employee);
    }

    //修改
    // mp生成sql 判断字段的值是否为null，若是，则不生成对应的字段
    //解决问题
    //1、将属性的类型改为引用数据类型(如int改为integet)
    //2、先查询出要修改的对象，设置数据，修改
    @Test
    void testUpdate1(){
        //先查询
        Employee employee = employeeMapper.selectById(20L);
//        Employee employee = new Employee();
        //设置数据
        employee.setId(20L);
        employee.setName("李四");

        employeeMapper.updateById(employee);
    }

    @Test
    void testUpdate2(){
        UpdateWrapper<Employee> wrapper = new UpdateWrapper<>();
        //设置条件
        //参数1：表中列名      参数2：数据
        wrapper.eq("id",20L);
        //设置修改数据
//        wrapper.set("name","王五");
        //设置sql片段，mybatis-plus在生成sql时，直接将sql片段拼接在一起
        wrapper.setSql("name='zhao',age=20");

        employeeMapper.update(null,wrapper);
    }

    @Test
    void testDelete1(){
//        employeeMapper.deleteById(20L);

        //根据id批量删除
//        employeeMapper.deleteBatchIds(Arrays.asList(1,2,3));

//        Map<String,Object> map = new HashMap<>();
//        //设置删除条件
//        map.put("id",1L);
//        map.put("name","张三");
//        employeeMapper.deleteByMap(map);

        UpdateWrapper<Employee> wrapper = new UpdateWrapper<>();
        wrapper.eq("id",1L);
        employeeMapper.delete(wrapper);
    }

    @Test
    void testQuery(){
//        Employee employee = employeeMapper.selectById(4L);
//        System.out.println(employee);

//        List<Employee> employees = employeeMapper.selectBatchIds(Arrays.asList(1, 2, 3, 4));
//        employees.forEach(System.out::println);

//        Map<String,Object> map = new HashMap<>();
//        map.put("id",1L);
//        map.put("name","zhao");
//        List<Employee> employees = employeeMapper.selectByMap(map);


        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        //模糊查询 %a%  a%  %a
//        wrapper.like("name","a");

        //模拟用户输入条件
        String param = "a";
        //参数1：boolean   是否需要拼接该条件
//        wrapper.like(param != null && !param.equals(""),"name",param);
//        List<Employee> employees = employeeMapper.selectList(wrapper);
//        employees.forEach(System.out::println);

        //查询param列数据包含name或email中包含param
//        wrapper
//                .like(StringUtils.isNotBlank(param),"name",param)   //默认使用and，可改为or
//                .or()
//                .like(StringUtils.isNotBlank(param),"email",param)
//        ;
//        List<Employee> employees = employeeMapper.selectList(wrapper);
//        employees.forEach(System.out::println);

        //查询name列数据包含param或email中包含param，并且年龄为18的信息
//        wrapper
//                .like(StringUtils.isNotBlank(param),"name",param)   //默认使用and，可改为or
//                .or()
//                .like(StringUtils.isNotBlank(param),"email",param)
//                .eq("age",18)
//        ; //错误语句示范，因sql语句有优先

        wrapper
                .eq("age",18)
                .and(
                        StringUtils.isNotBlank(param),
                        p -> p.like("name",param).or().like("email",param)
                )
        ;
        List<Employee> employees = employeeMapper.selectList(wrapper);
        employees.forEach(System.out::println);
    }

    @Test
    void testQuery2(){
        //统计记录数
        Integer count = employeeMapper.selectCount(null);
        System.out.println(count);

        //将查询中的每一条数据作为一个map，将数据的列名和数据作为map集合中元素
        List<Map<String, Object>> maps = employeeMapper.selectMaps(null);
        maps.forEach(System.out::println);

        //若null，默认将查询中第一列的值获取
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        //设置要获取哪个列的值
        wrapper.select("name");
        List<Object> objects = employeeMapper.selectObjs(wrapper);
        objects.forEach(System.out::println);

        //查询一条数据
        QueryWrapper<Employee> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("name","admin").eq("password","1");
        Employee employee = employeeMapper.selectOne(wrapper1);
        System.out.println(employee);
    }

    @Test
    void testQuery3(){
        //分页查询
        //需求：分页查询员工信息，每页显示5条数据，查询第二页数据
        //参数1：当前页码      参数2：每页数据量
        IPage<Employee> page = new Page<>(2,5);
        employeeMapper.selectPage(page,null);
        System.out.println("当前页码" + page.getCurrent());
        System.out.println("总数据量" + page.getTotal());
        System.out.println("总页数" + page.getPages());
        System.out.println("每页数据量" + page.getSize());
        System.out.println("当前页数据" + page.getRecords());
    }
}
