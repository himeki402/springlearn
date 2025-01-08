package com.study.identity_service.exception;

public enum ErrorCode {
    USER_EXISTED(1001,"User existed"),
    UNCATEGORIZED_EXCEPTION(9999, "Uncategoried Error"),
    USERNAME_INVALID(1003,"Username must be at least 4 characters"),
    PASSWORD_INVALID(1004, "Password must be at least 6 characters"),
    INVALID_KEY(1005, "Invalid message key"),
    USER_NOT_EXISTED(1006, "User not existed")
    ;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
