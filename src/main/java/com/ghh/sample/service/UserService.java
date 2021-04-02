package com.ghh.sample.service;

import com.ghh.sample.base.IBaseEntityService;
import com.ghh.sample.model.entity.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface UserService extends IBaseEntityService<User> {
    void createUser(User user);

    List<User> listUsers();

    InputStream genExcel() throws IOException;

    String getUserName(String id);
}
