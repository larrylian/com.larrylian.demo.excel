package com.larrylian.boot.demo.service.impl;

import com.larrylian.boot.demo.dao.UserDao;
import com.larrylian.boot.demo.model.User;
import com.larrylian.boot.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Larry on 2017/9/4
 */
@Service
public class UserServceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Override
    public List<User> findAll() {
       return userDao.findAll();
    }
}
