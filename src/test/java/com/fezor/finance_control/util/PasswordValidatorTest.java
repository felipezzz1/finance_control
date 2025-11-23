package com.fezor.finance_control.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PasswordValidatorTest {

    @Test
    void testValidPassword() {
        Assertions.assertTrue(PasswordValidator.isValid("Abc123!"));
    }

    @Test
    void testInvalidShortPassword() {
        Assertions.assertFalse(PasswordValidator.isValid("Ab1!"));
    }

    @Test
    void testMissingUppercase() {
        Assertions.assertFalse(PasswordValidator.isValid("abc123!"));
    }

    @Test
    void testMissingLowercase() {
        Assertions.assertFalse(PasswordValidator.isValid("ABC123!"));
    }

    @Test
    void testMissingNumber() {
        Assertions.assertFalse(PasswordValidator.isValid("Abcdef!"));
    }

    @Test
    void testMissingSpecial() {
        Assertions.assertFalse(PasswordValidator.isValid("Abc1234"));
    }
}
