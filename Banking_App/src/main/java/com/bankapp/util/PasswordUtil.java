package com.bankapp.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets; // Make sure this import is present

public class PasswordUtil {
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // This line is CRITICAL: ensures consistent hashing with UTF-8
            byte[] hashedBytes = md.digest(password.getBytes(StandardCharsets.UTF_8)); 
            
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // Log this exception properly in a production environment
            throw new RuntimeException("Error hashing password: SHA-256 algorithm not found.", e);
        }
    }

    public static boolean verifyPassword(String inputPassword, String storedHash) {
        // Hash the input password and compare it with the stored hash from the database
        return hashPassword(inputPassword).equals(storedHash);
    }
}