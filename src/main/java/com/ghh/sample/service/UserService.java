package com.ghh.sample.service;

import com.ghh.sample.model.entity.User;

import java.io.IOException;
import java.io.InputStream;

public interface UserService {
    public void createUser(User user);

    InputStream genExcel() throws IOException;
}
