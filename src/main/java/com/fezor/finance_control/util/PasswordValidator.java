package com.fezor.finance_control.util;

public class PasswordValidator {

    private static final String PASSWORD_PATTERN =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&.#_+=-])[A-Za-z\\d@$!%*?&.#_+=-]{6,20}$";

    public static boolean isValid(String password) {
        return password != null && password.matches(PASSWORD_PATTERN);
    }
}
