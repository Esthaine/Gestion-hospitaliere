package com.gestion.hospitaliere.servlets.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class WebUtils {

    public static LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public static Date convertToDateViaInstant(String dateToConvert) {
        String dateString = "01-01-2022";
        String pattern = "dd-MM-yyyy";
        Date date = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);

        try {
            date = dateFormat.parse(dateString);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
