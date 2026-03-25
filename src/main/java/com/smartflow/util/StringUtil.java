package com.smartflow.util;

import java.util.regex.Pattern;

public class StringUtil {

    private static final Pattern SIXTEEN_DIGITS = Pattern.compile("(?<!\\d)(\\d{12})(\\d{4})(?!\\d)");

    public static String nullToEmpty(String str) {

        return str == null ? "" : str;
    }

    public static String mask16Digits(String str) {

        if (isEmpty(str)) {
            return str; // Return original string if it's null or not 16 characters long
        }
        return SIXTEEN_DIGITS.matcher(str).replaceAll("***$2"); // Mask the first 12 digits
    }

    public static boolean isEmpty(String str) {

        return str == null || str.isBlank();
    }

}
