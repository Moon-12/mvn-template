package com.ashwija.mvn.common;

public enum LoginStatus {
    FAILURE(0, "User not found."),
    ERROR(1, "Password is incorrect! Try again. "),
    SUCCESS(3, "Login Successful !!!");

    private final int code;
    private final String message;

    // Constructor
    LoginStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    // Getter for code
    public int getCode() {
        return code;
    }

    // Getter for message
    public String getMessage() {
        return message;
    }

    // Method to get enum from code
    public static LoginStatus fromCode(int code) {
        for (LoginStatus result : LoginStatus.values()) {
            if (result.getCode() == code) {
                return result;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }
}

