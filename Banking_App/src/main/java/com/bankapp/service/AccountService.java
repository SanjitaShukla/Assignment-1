package com.bankapp.service;

import com.bankapp.dao.AccountDao;
import com.bankapp.model.Account;


public class AccountService {
    private AccountDao accountDao = new AccountDao();

    public Account getAccountByUserId(int userId) {
        return accountDao.getAccountByUserId(userId);
    }

    public Account getAccountByNumber(String accountNumber) {
        return accountDao.getAccountByNumber(accountNumber);
    }

    public void updateBalance(String accountNumber, double newBalance) {
        accountDao.updateBalance(accountNumber, newBalance);
    }
}