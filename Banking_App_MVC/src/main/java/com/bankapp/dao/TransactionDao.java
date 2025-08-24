package com.bankapp.dao;

import com.bankapp.config.DatabaseConfig;
import com.bankapp.model.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDao {
    public void saveTransaction(int fromAccId, int toAccId, double amount, String type) {
        String sql = "INSERT INTO transactions (from_account_id, to_account_id, amount, type, timestamp) VALUES (?, ?, ?, ?, NOW())";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, fromAccId);
            stmt.setInt(2, toAccId);
            stmt.setDouble(3, amount);
            stmt.setString(4, type);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Transaction> getPassbook(int userId, String start, String end) {
        List<Transaction> list = new ArrayList<>();
        String sql = "SELECT t.*, a.account_number as from_acc, a2.account_number as to_acc " +
                     "FROM transactions t " +
                     "JOIN accounts a ON t.from_account_id = a.account_id " +
                     "JOIN accounts a2 ON t.to_account_id = a2.account_id " +
                     "WHERE (a.user_id = ? OR a2.user_id = ?) " +
                     "AND t.timestamp BETWEEN ? AND ? " +
                     "ORDER BY t.timestamp DESC";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, userId);
            stmt.setString(3, start + " 00:00:00");
            stmt.setString(4, end + " 23:59:59");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Transaction tx = new Transaction();
                tx.setTransactionId(rs.getInt("transaction_id"));
                tx.setFromAccountId(rs.getInt("from_account_id"));
                tx.setToAccountId(rs.getInt("to_account_id"));
                tx.setAmount(rs.getDouble("amount"));
                tx.setType(rs.getString("type"));
                tx.setTimestamp(rs.getTimestamp("timestamp").toString());
                tx.setFromAccountNum(rs.getString("from_acc"));
                tx.setToAccountNum(rs.getString("to_acc"));
                list.add(tx);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}