package com.netdeal.Fullstack.utils;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PasswordHandlerTest {

    @ParameterizedTest
    @ValueSource(strings = {"1467ereefgS#1@", "1467fg1@"})
    void valid_password(String password) {
        PasswordHandler passwordHandler = new PasswordHandler();
        assertTrue(passwordHandler.isValidPassword(password));
    }

    @ParameterizedTest
    @ValueSource(strings = {"ABCDEFG", "", "      ", "Abcd123@", "!@#$%^&^^&@"})
    void invalid_password(String password) {
        PasswordHandler passwordHandler = new PasswordHandler();
        assertFalse(passwordHandler.isValidPassword(password));
    }

    @ParameterizedTest
    @CsvSource({
            "Word@1993,100",
            "RandomWord@%Quwry1234UUUabc!@#,205",
            "Harry@$Hagrid,125",
            "12345678,4",
            "asdfghjk,7",
    })
    void password_score(String password, int expectedScore) {
        PasswordHandler passwordHandler = new PasswordHandler();
        int actualScore = passwordHandler.getScore(password);
        assertEquals(expectedScore, actualScore);
    }

}