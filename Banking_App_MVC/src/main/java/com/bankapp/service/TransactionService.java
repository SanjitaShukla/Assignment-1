package com.bankapp.service;

import com.bankapp.dao.TransactionDao;
import com.bankapp.model.Transaction;

import java.util.List;

public class TransactionService {
    private TransactionDao transactionDao = new TransactionDao();

    public void transfer(int fromAccId, int toAccId, double amount) {
        transactionDao.saveTransaction(fromAccId, toAccId, amount, "DEBIT");
        transactionDao.saveTransaction(toAccId, fromAccId, amount, "CREDIT");
    }

    public List<Transaction> getPassbook(int userId, String start, String end) {
        return transactionDao.getPassbook(userId, start, end);
    }
}