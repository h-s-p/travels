package com.baizhi.travels.dao;

import com.baizhi.travels.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDAO {

//    更具用户名查询用户
    User findByUsername(String username);
    void save(User user);
}
