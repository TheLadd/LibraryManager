package com.learn.library.domain.ErrorMessages;

public class UserErrorMessage {
    public static final String NULL = "User cannot be null";
    public static final String FIRST_NAME_EMPTY = "User first name cannot be empty";
    public static final String LAST_NAME_EMPTY = "User last name cannot be empty";
    public static String ID_NOT_FOUND(int id) { return "Could not find User with ID: " + id; }
}
