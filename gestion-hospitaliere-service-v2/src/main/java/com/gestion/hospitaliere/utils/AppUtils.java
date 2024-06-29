package com.gestion.hospitaliere.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

public class AppUtils {

    public static String removeLastChar(String s) {
        return (s == null || s.isEmpty()) ? null : (s.substring(0, s.length() - 1));
    }

    public static LocalDateTime convertToLocalDateTimeViaInstant(String dateToConvert) throws ParseException {
        Date date = AppUtils.convertStringToDate(dateToConvert);
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zoneId);
    }


    public static Date convertStringToDate(String time) throws ParseException {
       return new SimpleDateFormat("yyyy-MM-dd", Locale.UK).parse(time);
    }
}
