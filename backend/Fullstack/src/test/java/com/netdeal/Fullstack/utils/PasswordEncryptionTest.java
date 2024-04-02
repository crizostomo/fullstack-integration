package com.netdeal.Fullstack.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordEncryptionTest {
        @Test
        public void encode_password() {
            String password = "pass123";
            String encodedPassword = PasswordEncryption.encode(password);
            assertNotNull(encodedPassword);
            assertNotEquals(password, encodedPassword);
        }

        @Test
        public void check_password() {
            String password = "pass123";
            String encodedPassword = PasswordEncryption.encode(password);
            assertTrue(PasswordEncryption.checkPassword(password, encodedPassword));
        }

        @Test
        public void incorrect_password() {
            String password = "pass123";
            String encodedPassword = PasswordEncryption.encode(password);
            assertFalse(PasswordEncryption.checkPassword("incorrect", encodedPassword));
        }

        @Test
        public void incorrect_password_hash() {
            String password = "pass123";
            assertThrows(IllegalArgumentException.class, () -> PasswordEncryption.checkPassword(password, "incorrectHash"));
        }

}