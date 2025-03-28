package com.mahadiks.newsappjavaandxml.utils;

import java.util.regex.Pattern;

public class UserValidationUtil {

    private static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-z]{2,30}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private static final Pattern CONTACT_PATTERN = Pattern.compile("^[0-9]{10}$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,20}$");

    public static boolean isValidUser(String firstName, String lastName, String email, String contactNumber, String password, String confirmPassword) {
        return isValidName(firstName) &&
                isValidName(lastName) &&
                isValidEmail(email) &&
                isValidContactNumber(contactNumber) &&
                isValidPassword(password, confirmPassword);
    }

    public static boolean isValidName(String name) {
        return name != null && NAME_PATTERN.matcher(name).matches();
    }

    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isValidContactNumber(String contactNumber) {
        return contactNumber != null && CONTACT_PATTERN.matcher(contactNumber).matches();
    }

    public static boolean isValidPassword(String password, String confirmPassword) {
        return password != null && PASSWORD_PATTERN.matcher(password).matches() &&
                password.equals(confirmPassword);
    }
}