package com.bankapp.service;

import com.bankapp.dao.UserDao;
import com.bankapp.model.User;

import java.util.List;

public class UserService {
    private UserDao userDao = new UserDao();

    public List<User> getAllCustomers() {
        return userDao.getAllCustomers();
    }

    public void updateUserStatus(int userId, boolean active) {
        userDao.updateUserStatus(userId, active);
    }

    public User getUserById(int userId) {
        return userDao.getUserById(userId);
    }

    public void updateUser(User user) {
        userDao.updateUser(user);
    }
}