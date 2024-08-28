package com.nus.iss.tasktracker.util;

public class TaskTrackerConstant {

    public static final String JWT_MAGIC_WORD = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    public static final int JWT_EXPIRATION_MINUTES = 10;

    public static final String JWT_ISSUER = "TASK_TRACKER";

    public static final String SIGNUP_INVALID_INPUT = "%s - Please input value!";
    public static final String FIELD_INVALID_INPUT = "%s - Please input value!";

    public static final String SIGNUP_INVALID_INPUT_PASSWORD = "Invalid Password. Password must be at least 8 characters long\n" +
            " and contain a combination of letters, numbers, and special characters.";

    public static final String SIGNUP_INVALID_INPUT_USERNAME_UNAVAILABLE = "Username not available!";

    public static final String SIGNUP_INVALID_GROUP_NAME = "Invalid Group Name. Group Name must be at least 6 characters long\n" +
            " and contain a combination of letters, numbers, and space.";

    public static final String SIGNUP_GROUP_MEMBER_ADDITION_VALIDATION_FAILED = "User addition not allowed since group validation failed";

    public static final String TASK_ADMIN = "ADMIN";

    public static final String DELETE_FLAG_TRUE = "TRUE";

    public static final String DELETE_FLAG_FALSE = "FALSE";

    public static final String TASK_PWD_CHANGE_MANDATORY_TRUE = "TRUE";

    public static final String TASK_PWD_CHANGE_MANDATORY_FALSE = "FALSE";

    public static final String REGISTRATION_ROLE_ADMIN = "ROLE_ADMIN";

    public static final String REGISTRATION_ROLE_USER = "ROLE_USER";

    public static final String TASK_COMMENT_REGEX = "^[a-zA-Z0-9, @#$&. ]+$";

    public static final String TASK_COMMENT_INVALID_INPUT = "Invalid Task Comment Input";

    public  static final String TASK_STATUS_COMPLETE = "Completed";

    public static final String TASK_USER_ADMIN_DEFAULT_CRED = "NUSISS2024";

    public static final int TASK_GROUP_ADMIN_MAX_NO = 2;

    public static final int TASK_GROUP_MAX_NO = 10;
    public static final String USER_NOT_FOUND="User not found!";
    public static final String TASK_NOT_FOUND="Task not found!";
    public static final String SERVICE_ACCESS_WITHOUT_TOKEN ="Service Accessed Without Token";
    public static final String SERVICE_ACCESS_NOT_ALLOWED ="Access to the Service is Not Allowed";




}
