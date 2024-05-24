package com.gestion.hospitaliere.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class AppUtils {

    public static String removeLastChar(String s) {
        return (s == null || s.isEmpty()) ? null : (s.substring(0, s.length() - 1));
    }

    public static LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }


    public static Date convertToDateViaInstant(String dateToConvert) {


        Date date = null;


        try {
            String pattern = "dd-MM-yyyy";
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            date = dateFormat.parse(dateToConvert);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
