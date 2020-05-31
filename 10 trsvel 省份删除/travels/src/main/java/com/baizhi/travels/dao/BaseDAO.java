package com.baizhi.travels.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseDAO<T, k> {
    //分页
    void save(T t);

    void updata(T t);

    void delete(T t);

    List<T> findAll();//返回值

    List<T> findByPage(@Param("start") Integer start, @Param("rows") Integer rows);//分页查询

    Integer findTotals();
}
