// src/main/java/com/bankapp/util/PasswordUtil.java
package com.bankapp.util;

import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;

public class PasswordUtil {

    /**
     * Hashes a password using SHA-256
     * @param password plain text password
     * @return SHA-256 hash as hex string
     */
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    /**
     * Verifies a plain text password against a stored hash
     * @param inputPassword plain text password (from login form)
     * @param storedHash SHA-256 hash from database
     * @return true if match, false otherwise
     */
    public static boolean verifyPassword(String inputPassword, String storedHash) {
        return hashPassword(inputPassword).equals(storedHash);
    }
}