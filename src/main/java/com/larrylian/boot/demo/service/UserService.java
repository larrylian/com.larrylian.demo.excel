package com.larrylian.boot.demo.service;

import com.larrylian.boot.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Larry on 2017/9/4
 */
public interface UserService {
    public List<User> findAll();
}
