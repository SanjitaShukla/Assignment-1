package com.bankapp.service;

import com.bankapp.dao.UserDao;
import com.bankapp.model.User;

public class AuthService {
    private UserDao userDao = new UserDao();

    public User login(String username, String password) {
        return userDao.authenticate(username, password);
    }
}
