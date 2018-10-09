package com.scrumbums.donationboi.model.util;

/**
 * Validates account credentials.
 *
 * @author jdierberger3
 */
public final class AccountValidation {

    /**
     * do not use.
     */
    private AccountValidation () { }

    /**
     * Regular expression for a valid email address.
     */
    private static final String EMAILREGEX = "(\\w|\\d|\\.|_)+@(\\w|\\d|\\.|_)+.(\\w|\\d)+";

    /**
     * Regular expression for 'special characters.'
     */
    private static final String SPECIALCHARSREGEX = "@_\\$~\\.";

    /**
     * Regular expression for legal characters.
     */
    private static final String LEGALCHARSREGEX = "[a-zA-Z0-9" + SPECIALCHARSREGEX + "]+";

    /**
     * Minimum required password length.
     */
    private static final int MINPASSWORDLENGTH = 8;

    /**
     * Minimum number of uppercase characters needed in password.
     */
    private static final int MINUPPERCASECHARS = 2;

    /**
     * Minimum number of lowercase characters needed in password.
     */
    private static final int MINLOWERCASECHARS = 3;

    /**
     * Minimum number of special characters required in a password.
     */
    private static final int MINSPECIALCHARS = 1;

    /**
     * Minimum number of numeric characters required in a password.
     */
    private static final int MINNUMERICCHARS = 2;

    /**
     * Test if a String is non-empty, <i>including</i> ensuring the string
     * does not only contain whitespace.
     * @param s The String to validate.
     * @return
     */
    public static final boolean isStringNonEmpty(String s) {
        return s != null && s.replaceAll("\\s", "").length() > 0;
    }

    /**
     * Test if the String contains illegal characters.
     * @param s The String to validate.
     * @return If the String contains illegal characters.
     */
    public static final boolean containsIllegalCharacters(String s) {
        return s != null && !s.matches(LEGALCHARSREGEX);
    }

    /**
     * Test if the String is a valid email address.
     * @param s The String to validate.
     * @return If the String is a valid email.
     */
    public static final boolean isValidEmail(String s) {
        return s != null && s.matches(EMAILREGEX);
    }

    /**
     * Test if the String is non-empty, does not contain illegal characters,
     * meets a minimum length requirement, and contains at least a specified
     * number of alphabetic (upper and lower case), numeric, and special
     * charcaters.
     * @param s The String to validate.
     * @return If the String is a valid password
     */
    public static final boolean isValidPassword(String s) {
        return isStringNonEmpty(s) && !containsIllegalCharacters(s)
                && s.length() > MINPASSWORDLENGTH && hasMinChars(s);
    }

    /**
     * Test if the String has the minimum number of chars needed for a valid
     * password.
     * @param s The String to validate.
     * @return If the String has the min character requirements.
     */
    private static final boolean hasMinChars(String s) {
        int numUpper = 0;
        int numLower = 0;
        int numSpecial = 0;
        int numNum = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ((c + "").matches(SPECIALCHARSREGEX)) {
                numSpecial++;
            } else if (c >= 'a' && c <= 'z') {
                numLower++;
            } else if (c >= 'A' && c <= 'Z') {
                numUpper++;
            } else if (c >= '0' && c <= '9') {
                numNum++;
            } // else should never happen
        }
        return numUpper >= MINUPPERCASECHARS && numLower >= MINLOWERCASECHARS
                && numSpecial >= MINSPECIALCHARS && numNum >= MINNUMERICCHARS;
    }
}
