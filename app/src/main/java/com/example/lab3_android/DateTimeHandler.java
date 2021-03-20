package com.example.lab3_android;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateTimeHandler {

    private static final String DATE_FORMAT = "yyyy-mm-dd hh:mm:ss";

    public static String getDateFormat(Date dateTime){
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        return dateFormat.format(dateTime);
    }

    public static String localTimeToUtc(Date date) {
        String timeZone = Calendar.getInstance().getTimeZone().getID();
        Date utc = new Date(date.getTime() - TimeZone.getTimeZone(timeZone).getOffset(date.getTime()));
        String utcTime = getDateFormat(utc);
        return utcTime;
    }

    public static Date utcToLocalTime(String utcTime) throws ParseException {
        Date utcDateTime = new SimpleDateFormat(DATE_FORMAT).parse(utcTime);
        String timeZone = Calendar.getInstance().getTimeZone().getID();
        Date local = new Date(utcDateTime.getTime() + TimeZone.getTimeZone(timeZone).getOffset(utcDateTime.getTime()));

        return local;

    }

}
