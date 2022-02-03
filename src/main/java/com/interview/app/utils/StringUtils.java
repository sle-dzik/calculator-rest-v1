package com.interview.app.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    private static Pattern numberPattern = Pattern.compile("^\\d+$");

    public static boolean isNumber(String input) {
        Matcher m = numberPattern.matcher(input);
        return m.find();
    }
}
