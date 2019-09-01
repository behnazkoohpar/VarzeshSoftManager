package com.noor.payment.utils;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDifferent {

    @SuppressLint("SimpleDateFormat")
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date d1 = null;
    Date d2 = null;

    public long DateDiffrent(String dateStart, String dateStop) {
        long diffSeconds = 0;
        long diffMinutes = 0;
        long diffHours = 0;
        long diffDays = 0;
        try {
            d1 = format.parse(dateStart);
            d2 = format.parse(dateStop);


            //in milliseconds
            long diff = d2.getTime() - d1.getTime();

            diffSeconds = diff / 1000 % 60;
            diffMinutes = diff / (60 * 1000) % 60;
            diffHours = diff / (60 * 60 * 1000) % 24;
            diffDays = diff / (24 * 60 * 60 * 1000);

            System.out.print(diffDays + " days, ");
            System.out.print(diffHours + " hours, ");
            System.out.print(diffMinutes + " minutes, ");
            System.out.print(diffSeconds + " seconds.");

            return diffMinutes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return diffMinutes;
    }
}
