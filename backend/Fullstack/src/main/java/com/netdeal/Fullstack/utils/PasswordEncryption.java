package com.netdeal.Fullstack.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncryption  {

    public static String encode(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean checkPassword(String password, String passwordHash) {
        return BCrypt.checkpw(password, passwordHash);
    }

}
