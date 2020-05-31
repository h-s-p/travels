package com.baizhi.travels.service;

import com.baizhi.travels.dao.UserDAO;
import com.baizhi.travels.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Override
    public User login(User user) {
        User userDB = userDAO.findByUsername(user.getUsername());
        if(userDB != null) {
            if(userDB.getPassword().equals(user.getPassword())) {
                return userDB;

            }  throw new RuntimeException("用户名或密码输入不正确");
        } else {
            throw new RuntimeException("用户名或密码输入不正确");
        }

    }

    @Autowired
    private UserDAO userDAO;

    @Override
    public void register(User user) {
        if (userDAO.findByUsername(user.getUsername()) == null) {
            userDAO.save(user);

        } else {
            throw new RuntimeException("用户名已存在");
        }
    }
}
