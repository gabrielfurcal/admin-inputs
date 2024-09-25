package com.traincompany.management.admin_inputs.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public final class DateFormatter {
    private DateFormatter() {}

    public static String toString(Date date, String pattern) {
        
        LocalDateTime localDate = Instant.ofEpochMilli(date.getTime())
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime();

        return localDate.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static Date toDate(String date, String pattern) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        try {
            return formatter.parse(date);
        } catch(ParseException e) {
            e.printStackTrace();
            throw new Exception("Could not parse String: " + date + " to Date");
        }
    }
}
