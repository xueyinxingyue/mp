package com.xueyin.mp.qo;

import lombok.Data;

/**
* 封装分页数据
*/
@Data
public class QueryObject {
    //分页数据
    private int currentPage;    //当前页码
    private int pageSize;       //每页数据量
}
