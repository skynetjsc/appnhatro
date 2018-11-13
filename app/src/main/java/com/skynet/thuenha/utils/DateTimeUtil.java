package com.skynet.thuenha.utils;

import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by thaopt on 7/4/17.
 */

public class DateTimeUtil {
    public static final SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
    public static final SimpleDateFormat formatDateFilter = new SimpleDateFormat("yyyy-MM-dd");

    public static final SimpleDateFormat formatDateCommon = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    public static final SimpleDateFormat formatDateExprre = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public static int convertTimeToInt(String sDate) {
        int ti = 0;
        Calendar cal = Calendar.getInstance();
        try {
            Date date = formatDate.parse(sDate);
            cal.setTime(date);
            long time = cal.getTimeInMillis();
            String t = String.valueOf(time).substring(0, 10);
            ti = Integer.parseInt(t);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ti;
    }

    public static String convertStrDateWithFormat(String date, String toFormat) {
        if (date == null || date.isEmpty()) return "";
        try {
            Date dateFrom = formatDateExprre.parse(date);
            String strDate = DateFormat.format(toFormat, dateFrom).toString();
            return strDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    public static String convertStrDateWithFormatMMYY(String date) {
        if (date == null || date.isEmpty()) return "";
        try {
            Date dateFrom = formatDateExprre.parse(date);
            String strDate = DateFormat.format(" TMM/yyyy", dateFrom).toString();
            return strDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    public static String convertStrDateTimeEvent(String date) {
        if (date == null || date.isEmpty()) return "";
        try {
            Date dateFrom = formatDateExprre.parse(date);
            String strDate = DateFormat.format("HH:mm dd/MM/yyyy", dateFrom).toString();
            return strDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String convertStrDateExpireWithFormat(String date) {
        if (date == null || date.isEmpty()) return "";
        try {
            Date dateFrom = formatDateExprre.parse(date);
            //2017-10-29 07:24:13
            String strDate = DateFormat.format("HH:mm dd/MM/yyyy", dateFrom).toString();
            return strDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String convertTimeToString(String time) {
        long millisecond = Long.parseLong(time);
        // or you already have long value of date, use this instead of milliseconds variable.
        String dateString = DateFormat.format("hh:mm dd/MM/yyyy", new Date(millisecond)).toString();
        return dateString;
    }


    public static String getTimeCreate(String time) {
        long millisecond = Long.parseLong(time);
        long millis = System.currentTimeMillis();

        String timeDay = DateFormat.format("HH:mm", new Date(millisecond)).toString();
        String timeCurrent = DateFormat.format("dd/MM/yyyy", new Date(millis)).toString();
        String timeCreate = DateFormat.format("dd/MM/yyyy", new Date(millisecond)).toString();

        if (timeCurrent.equals(timeCreate)) {
            return (timeDay + " hôm nay");
        } else {
            return (DateFormat.format("HH:mm dd/MM/yyyy", new Date(millisecond)).toString());
        }
    }

    public static Date convertToDate(String timeSource, SimpleDateFormat simpleDateFormat) {
        try {
            return simpleDateFormat.parse(timeSource);
        } catch (Exception e) {

        }
        return null;
    }

    public static String getEndDateFilter(String timeStartFilter) {
        try {
            StringBuilder strTimeEnd = new StringBuilder();
            Date date = formatDateFilter.parse(timeStartFilter);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            String timeEnd = strTimeEnd.append(calendar.get(Calendar.YEAR)).append("-").append((calendar.get(Calendar.MONTH) + 1)).append("-")
                    .append(calendar.getActualMaximum(Calendar.DAY_OF_MONTH)).append(" 23:59:59").toString();
            return timeEnd;
        } catch (Exception e) {

        }
        return "";
    }
}
