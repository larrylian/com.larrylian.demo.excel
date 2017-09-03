package com.larrylian.boot.demo.dao;

import com.larrylian.boot.demo.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Larry on 2017/9/4
 */
@Repository
public interface UserDao {
    public List<User> findAll();
}
