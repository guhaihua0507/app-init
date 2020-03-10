package com.ghh.sample.service.impl;

import com.ghh.sample.mapper.UserMapper;
import com.ghh.sample.model.entity.User;
import com.ghh.sample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public void createUser(User user) {
        userMapper.insert(user);
    }
}
