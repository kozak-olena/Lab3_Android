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

    public static long localTimeToUtc(Date date) {
        String timeZone = Calendar.getInstance().getTimeZone().getID();
        Date utc = new Date(date.getTime() - TimeZone.getTimeZone(timeZone).getOffset(date.getTime()));
        long millis = utc.getTime();
        /*String utcTime = getDateFormat(utc);*/
        return millis;
    }

    public static Date utcToLocalTime(long utcTime) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        String dateString = formatter.format(new Date(utcTime)).toString();
        Date utcDateTime = new SimpleDateFormat(DATE_FORMAT).parse(dateString);
        String timeZone = Calendar.getInstance().getTimeZone().getID();
        Date local = new Date(utcDateTime.getTime() + TimeZone.getTimeZone(timeZone).getOffset(utcDateTime.getTime()));

        return local;
    }

   }
