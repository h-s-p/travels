package com.baizhi.travels.service;

import com.baizhi.travels.dao.UserDAO;
import com.baizhi.travels.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;


    @Override
    public User login(User user) {
        User userDB = userDAO.findByUsername(user.getUsername());
        if (userDB != null) {
            if (userDB.getPassword().equals(user.getPassword())) {
                return userDB;
            }
//            代码1：密码不正确
//            代码2：用户名不正确
            throw new RuntimeException("请确认用户名或密码是否正确，若正确请联系管理员 错误带代码 1~~~");
        } else {
            throw new RuntimeException("请确认用户名或密码是否正确，若正确请联系管理员错误带代码 2~~~");
        }
    }

    @Override
    public void register(User user) {
        if (userDAO.findByUsername(user.getUsername()) == null) {
            userDAO.save(user);
        } else {
            throw new RuntimeException("用户名已经存在~~~~");
        }
    }
}
