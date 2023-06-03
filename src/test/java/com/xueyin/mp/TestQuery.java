package com.xueyin.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.xueyin.mp.entity.Department;
import com.xueyin.mp.entity.Employee;
import com.xueyin.mp.mapper.DepartmentMapper;
import com.xueyin.mp.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

@SpringBootTest
public class TestQuery {
    @Autowired
    private EmployeeMapper employeeMapper;

    //需求：查询所有员工，返回员工name，age列
    @Test
    void testQuery1() {
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        //查询的列
        wrapper.select("name", "age");

        List<Employee> employees = employeeMapper.selectList(wrapper);
        employees.forEach(System.out::println);
    }

    //需求：查询所有员工，返回员工以a字母开头的列
    @Test
    void testQuery2() {
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();

        //查询的列
        wrapper.select(
                Employee.class,
                tableFieldInfo -> tableFieldInfo.getProperty().startsWith("a")
        );
        System.out.println(employeeMapper.selectList(wrapper));
    }

    //需求：查询所有员工信息，按age排，如果age一样，按id排序
    //order by age asc,id asc
    @Test
    void testQuery4(){
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        //设置排序
//        wrapper.orderByAsc("age","id");
        wrapper.orderByAsc(false,"age","id");
        System.out.println(employeeMapper.selectList(wrapper));
    }

    //需求：查询所有员工信息，按age排，如果age一样，按id排序
    @Test
    void testQuery5() {
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        //参数1：是否要排序     参数2：是否升序排序      参数3：排序的列
        wrapper.orderBy(true,false,"age","id");
        System.out.println(employeeMapper.selectList(wrapper));
    }

    //需求：查询所有员工信息，按age排，如果age一样，按id倒序
    //order by age asc,id desc
    @Test
    void testQuery6() {
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();

        wrapper.orderByAsc("age").orderByDesc("id");
        System.out.println(employeeMapper.selectList(wrapper));
    }

    //需求： 以部门id进行分组查询，查每个部门员工个数
    @Test
    void testQuery7(){
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        //分组
        wrapper.groupBy("dept_id");
        //查询到的列
        wrapper.select("dept_id 部门","count(*) 人数");
        System.out.println(employeeMapper.selectMaps(wrapper));
    }

    //需求：以部门id进行分组查询，查每个部门员工个数， 将大于3人的部门过滤出来
    @Test
    void testQuery8(){
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        //分组
        wrapper.groupBy("dept_id");
        //查询到的列
        wrapper.select("dept_id 部门","count(*) 人数");

        wrapper.having("人数 > {0} and dept_id != {1}",3,3);
        System.out.println(employeeMapper.selectMaps(wrapper));
    }

    //需求：查询name=zhao， age=18的员工信息
    @Test
    void testQuery9(){
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        Map<String,Object> map = new HashMap<>();
        map.put("name","zhao");
        map.put("age",18);
        wrapper.allEq(map);

        List<Employee> employees = employeeMapper.selectList(wrapper);
        System.out.println(employees);
    }

    //需求：查询name=zhao的员工信息
    @Test
    void testQuery10(){
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        Map<String,Object> map = new HashMap<>();
        map.put("name","zhao");
        map.put("age",null);
        wrapper.allEq(map,false);

        List<Employee> employees = employeeMapper.selectList(wrapper);
        System.out.println(employees);
    }

    //需求：查询满足条件员工信息， 注意传入的map条件中， 包含a的列才参与条件查询
    @Test
    void testQuery11(){
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        Map<String,Object> map = new HashMap<>();
        map.put("name","zhao");
        map.put("age",null);
        map.put("dept_id",1);

        wrapper.allEq((s,o) -> s.contains("a"),map,false);
        System.out.println(employeeMapper.selectList(wrapper));
    }

    //需求：查询name = zhao员工信息
    @Test
    void testQuery12(){
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.eq("name","zhao");
        System.out.println(employeeMapper.selectList(wrapper));
    }

    //需求：查询name != zhao员工信息
    @Test
    void testQuery13(){
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.ne("name","zhao");
        System.out.println(employeeMapper.selectList(wrapper));
    }

    /*
    *   gt > 大于
    *   ge >= 大于等于
    *   lt < 小于
    *   le <= 小于等于
    */
    //需求：查询 age 大于 18 员工信息
    @Test
    void testQuery14() {
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.gt("age",18);
//        wrapper.ge("age",18);
        employeeMapper.selectList(wrapper);
    }

    //需求：查询年龄介于18~30岁的员工信息
    @Test
    void testQuery15(){
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.between("age",18,30);
        //需求：查询年龄小于18或者大于30岁的员工信息
//        wrapper.notBetween("age",18,30);
        employeeMapper.selectList(wrapper);
    }

    //需求： 查询dept_id 为null 员工信息
    @Test
    void testQuery16(){
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.isNull("dept_id");
        employeeMapper.selectList(wrapper);
    }

    //需求： 查询dept_id 不为null 员工信息
    @Test
    void testQuery17(){
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.isNotNull("dept_id");
        employeeMapper.selectList(wrapper);
    }

    //需求： 查询id为1， 2 的员工信息
    @Test
    void testQuery18() {
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.in("id",1,2);
//        wrapper.notIn("id",1,2);
        employeeMapper.selectList(wrapper);
    }

    //需求： 查询id为1， 2 的员工信息
    @Test
    public void testQuery19(){
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.inSql("id", "1,2");
//        wrapper.notInSql("id","1,2");
        employeeMapper.selectList(wrapper);
    }

    //多表连接用mybatis语句
    @Test
    void testQuery20(){
        List<Employee> employees = employeeMapper.listByXml();
        System.out.println(employees);
    }

    @Test
    void testQuery21(){
        List<Employee> employees = employeeMapper.listByXmlJoin();
        System.out.println(employees);
    }

    //用plus要发送额外的sql语句（不推荐）
    @Autowired
    private DepartmentMapper departmentMapper;
    @Test
    void testQuery22(){
        List<Employee> employees = employeeMapper.selectList(null);
        for (Employee employee : employees){
            Department department = departmentMapper.selectById(employee.getDeptId());
            employee.setDepartment(department);
        }
        System.out.println(employees);
    }
}