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
    private static final String EMAIL_REGEX = "(\\w|\\d|\\.|_)+@(\\w|\\d|\\.|_)+\\.(\\w|\\d)+";

    /**
     * Special characters list. Used in constructing the following
     * regular expressions.
     */
    private static final String SPECIAL_CHARS = "@_\\$~\\.!-\\^%";

    /**
     * Regular expression for 'special characters.'
     */
    private static final String SPECIAL_CHARS_REGEX = "[" + SPECIAL_CHARS + "]+";

    /**
     * Regular expression for legal characters.
     */
    private static final String LEGAL_CHARS_REGEX = "[a-zA-Z0-9" + SPECIAL_CHARS + "]+";

    /**
     * Minimum required password length.
     */
    private static final int MIN_PASSWORD_LENGTH = 8;

    /**
     * Minimum number of uppercase characters needed in password.
     */
    private static final int MIN_UPPERCASE_CHARS = 2;

    /**
     * Minimum number of lowercase characters needed in password.
     */
    private static final int MIN_LOWERCASE_CHARS = 3;

    /**
     * Minimum number of special characters required in a password.
     */
    private static final int MIN_SPECIAL_CHARS = 1;

    /**
     * Minimum number of numeric characters required in a password.
     */
    private static final int MIN_NUMERIC_CHARS = 2;

    /**
     * Test if a String is non-empty, <i>including</i> ensuring the string
     * does not only contain whitespace.
     * @param s The String to validate.
     * @return True if the string is not all whitespace.  False otherwise.
     */
    private static boolean isStringNonEmpty(String s) {
        return (s != null) && !s.replaceAll("\\s", "").isEmpty();
    }

    /**
     * Test if the String contains illegal characters.
     * @param s The String to validate.
     * @return If the String contains illegal characters.
     */
    private static boolean containsIllegalCharacters(String s) {
        return (s != null) && !s.matches(LEGAL_CHARS_REGEX);
    }

    /**
     * Test if the String is a valid email address.
     * @param s The String to validate.
     * @return If the String is a valid email.
     */
    public static boolean isInvalidEmail(String s) {
        return (s == null) || !s.matches(EMAIL_REGEX);
    }

    /**
     * Test if the String is non-empty, does not contain illegal characters,
     * meets a minimum length requirement, and contains at least a specified
     * number of alphabetic (upper and lower case), numeric, and special
     * characters.
     * @param s The String to validate.
     * @return If the String is a valid password
     */
    public static boolean isInvalidPassword(String s) {
        return !isStringNonEmpty(s) || containsIllegalCharacters(s)
                || (s.length() < MIN_PASSWORD_LENGTH)
                || !hasMinChars(s);
    }

    private static int getNumUppercaseChars(CharSequence s) {
        int numUpper = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ((c >= 'A') && (c <= 'Z')) {
                numUpper++;
            }
        }

        return numUpper;
    }

    private static int getNumLowercaseChars(CharSequence s) {
        int numLower = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ((c >= 'a') && (c <= 'z')) {
                numLower++;
            }
        }

        return numLower;
    }

    private static int getNumSpecialChars(CharSequence s) {
        int numSpecial = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ((c + "").matches(SPECIAL_CHARS_REGEX)) {
                numSpecial++;
            }
        }

        return numSpecial;
    }

    private static int getNumNumericChars(CharSequence s) {
        int numNumeric = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ((c >= '0') && (c <= '9')) {
                numNumeric++;
            } // else should never happen
        }
        return numNumeric;
    }

    /**
     * Test if the String has the minimum number of chars needed for a valid password.
     *
     * @param s The String to validate.
     * @return If the String has the min character requirements.
     */
    private static boolean hasMinChars(CharSequence s) {
        return (getNumUppercaseChars(s) >= MIN_UPPERCASE_CHARS)
                && (getNumLowercaseChars(s) >= MIN_LOWERCASE_CHARS)
                && (getNumSpecialChars(s) >= MIN_SPECIAL_CHARS)
                && (getNumNumericChars(s) >= MIN_NUMERIC_CHARS);
    }
}
