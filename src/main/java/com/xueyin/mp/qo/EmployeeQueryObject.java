package com.xueyin.mp.qo;

import lombok.Data;

/**
* 封装员工查询条件
 * */
@Data
public class EmployeeQueryObject extends QueryObject{
    private String name;
}
