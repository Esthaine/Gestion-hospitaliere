package com.gestion.hospitaliere.utils;

public class AppUtils {

    public static String removeLastChar(String s) {
        return (s == null || s.isEmpty()) ? null : (s.substring(0, s.length() - 1));
    }
}
