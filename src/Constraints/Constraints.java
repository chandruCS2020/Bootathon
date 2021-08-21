/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Constraints;

/**
 *
 * @author chandru
 */

import java.util.regex.Pattern;

public class Constraints {

    static String email_regex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
            "[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
            "A-Z]{2,7}$";
    static String password_regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,}$";

    public static boolean checkEmail(String email) {
        return Pattern.matches(email_regex, email);
    }
    public static boolean checkPassword(String password) {
        return Pattern.matches(password_regex,password);
    }
}

