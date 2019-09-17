package com.eram.manager.utils.fdate;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.util.ULocale;
import com.mojtaba.materialdatetimepicker.utils.PersianCalendar;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.StringTokenizer;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Behpardaz Jahan</p>
 *
 * @author Reza Asadollahi
 * @version 1.0
 */

public class DateUtil {

    public static String getCurrentDate() {
        FDate curDate = new FDate(System.currentTimeMillis());
        return curDate.toString();
    }

    public static String getCurrentTime() {
        FDate curDate = new FDate(System.currentTimeMillis());
//    String time = curDate.getHour() + ":"+curDate.getMinute()+":"+curDate.getSecond();
        return getCompleteTimeString(curDate);
    }

    public static String getCurrentTimeString() {
        return getCompleteTimeString(new FDate(System.currentTimeMillis()));
    }

    public static String getCompleteTimeString(FDate fdate) {
        StringBuffer b = new StringBuffer();
        b.append((fdate.getHour() < 10) ? "0" + (fdate.getHour()) :
                String.valueOf(fdate.getHour()));
        b.append(":");
        b.append((fdate.getMinute() < 10) ? "0" + (fdate.getMinute()) :
                String.valueOf(fdate.getMinute()));
        b.append(":");
        b.append((fdate.getSecond() < 10) ? "0" + (fdate.getSecond()) :
                String.valueOf(fdate.getSecond()));
        return b.toString();
    }

    public static int getCurrentYear() {
        FDate curDate = new FDate(System.currentTimeMillis());
        return curDate.getYear();
    }

    public static int getCurrentMonth() {
        FDate curDate = new FDate(System.currentTimeMillis());
        return curDate.getMonth();
    }

    public static int getCurrentDay() {
        FDate curDate = new FDate(System.currentTimeMillis());
        return curDate.getDate();
    }

    public static String gatMiladiDate(int year, int month, int day) {

        GregorianCalendar gc = new GregorianCalendar(year, month, day);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return dateFormat.format(gc.getTime());
    }

    public static Date toDate(String formattedDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        ParsePosition pos = new ParsePosition(0);
        Date d = dateFormat.parse(formattedDate, pos);
        return d;
    }

    //reverse the order of dd and yyyy in a farsi date string just for fixing farsi presentation problem
    //suppose that input date is in the form of yyyy/mm/dd
    //the output would be as dd/mm/yyyy
    public static String invertDate(String fdate) {
        String yyyy = null;
        String mm = null;
        String dd = null;

        if (fdate == null || fdate.length() == 0)
            return "";
        StringTokenizer strTokenizer = new StringTokenizer(fdate, "/");
        if (strTokenizer.hasMoreTokens()) {
            yyyy = strTokenizer.nextToken();
            if (strTokenizer.hasMoreTokens()) {
                mm = strTokenizer.nextToken();
                if (strTokenizer.hasMoreTokens()) {
                    dd = strTokenizer.nextToken();
                    return dd + "/" + mm + "/" + yyyy;
                }
            }
        }
        return fdate;
    }

    public static String changeFarsiToMiladi(String farsiDate) {
        Date miladiDate = ShamsiCalendar.shamsiToMiladi(farsiDate);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return dateFormat.format(miladiDate);
    }

    public static String changeMiladiToFarsi(String miladiDate) {
        return ShamsiCalendar.miladiToShamsi(toDate(miladiDate));
    }

    public static int getTotalDays(FDate startDate, int durationMonth, int durationDay) {
        FDate endDate = new FDate(startDate.toMiladi());
        int i;

        for (i = 0; i < durationMonth; i++)
            endDate.nextMonth();

        for (i = 0; i < durationDay; i++)
            endDate.nextDay();

        int totalDays = endDate.minusDate(startDate.toString());

        return totalDays;
    }

    public static int getDurationMonth(FDate startDate, int totalDays) {

        FDate endDate = new FDate(startDate.toString());
        FDate curDate = new FDate(startDate.toString());
        int i;

//    endDate.plusDay(totalDays);

        for (i = 0; i < totalDays; i++)
            endDate.nextDay();

        System.out.println("END DATE IS " + endDate.toString());

        int durationMonthVal = 0;


        while (curDate.getYear() < endDate.getYear()
                || (curDate.getYear() == endDate.getYear() && curDate.getMonth() < endDate.getMonth())) {
            durationMonthVal++;
            curDate.nextMonth();
        }
        if (curDate.after(endDate)) {
            durationMonthVal--;
            curDate.prevMonth();
        }
        return durationMonthVal;
    }

    public static int getDurationMonth(FDate startDate, FDate endDate) {
        int durationMonth = 0;

        while (startDate.compareTo(endDate) < 0) {
            startDate.nextMonth();
            durationMonth++;
        }
        return durationMonth;
    }

    public static int getDurationMonth(String startDate, String endDate) {
        return getDurationMonth(new FDate(startDate), new FDate(endDate));
    }

    public static int getDurationDay(FDate startDate, int totalDays) {
        FDate endDate = new FDate(startDate.toString());
        FDate curDate = new FDate(startDate.toString());
        int i;

        int durationMonthVal = 0;

//    endDate.plusDay(totalDays);

        for (i = 0; i < totalDays; i++)
            endDate.nextDay();

        while (curDate.getYear() < endDate.getYear()
                || (curDate.getYear() == endDate.getYear() && curDate.getMonth() < endDate.getMonth())) {
            durationMonthVal++;
            curDate.nextMonth();
        }
        if (curDate.after(endDate)) {
            durationMonthVal--;
            curDate.prevMonth();
        }

        int durationDayVal = 0;
        while (curDate.before(endDate)) {
            durationDayVal++;
            curDate.nextDay();
        }

        return durationDayVal;
    }

    public static int getDurationMonth(String startDate, int totalDays) {
        FDate fDate = new FDate(startDate);
        return getDurationMonth(fDate, totalDays);
    }

    public static int getDurationDay(String startDate, int totalDays) {
        FDate fDate = new FDate(startDate);
        return getDurationDay(fDate, totalDays);
    }

    public static String stringDayMountYear() {
        return ShamsiCalendar.weekDayName(ShamsiCalendar.dayOfWeek(DateUtil.getCurrentDate())) + " " +
                ShamsiCalendar.monthDayName(DateUtil.getCurrentDay()) +
                ShamsiCalendar.monthName(DateUtil.getCurrentMonth()) + " ��� " +
                String.valueOf(DateUtil.getCurrentYear());
    }

    public static String decreaseYear(String tavalodDate, int cnt) {
        String year = tavalodDate.substring(0, 4);
        int ny = Integer.decode(year) - cnt;
        return String.valueOf(ny) + tavalodDate.substring(4);
    }

    public static String decreaseCurrentYear(int cnt) {
        String cur = getCurrentDate();
        String year = cur.substring(0, 4);
        int ny = Integer.decode(year) - cnt;
        return String.valueOf(ny) + cur.substring(4);
    }

    public static String increaseYear(String tavalodDate, int cnt) {
        String year = tavalodDate.substring(0, 4);
        int ny = Integer.decode(year) + cnt;
        return String.valueOf(ny) + tavalodDate.substring(4);
    }

    public static String increaseCurrentYear(int cnt) {
        String cur = getCurrentDate();
        String year = cur.substring(0, 4);
        int ny = Integer.decode(year) + cnt;
        return String.valueOf(ny) + cur.substring(4);
    }

    public static String[] HafteGhabl(String date) {

        String[] roozHayeHafteGhabl = new String[7];
        String dateShanbe = AddDate(date, -7);
        for (int i = 1; i <= 7; i++) {
            roozHayeHafteGhabl[i - 1] = AddDate(dateShanbe, i - 1);
        }
        return roozHayeHafteGhabl;
    }

    public static String hafteJari(String dateJari) {

        Calendar c = Calendar.getInstance();
        //c.setTime(yourDate);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 7)
            dayOfWeek = 0;
        else
            dayOfWeek = -1 * dayOfWeek;
        String[] roozHayeHafteJari = new String[7];
        String date = AddDate(dateJari, dayOfWeek);
        for (int i = 1; i <= 7; i++) {
            roozHayeHafteJari[i - 1] = AddDate(date, i - 1);
        }
        return roozHayeHafteJari[0];

    }

    public static String[] HafteBadi(String date) {
        String[] roozHayeHafteBadi = new String[7];
        String dateShanbe = AddDate(date, 7);
        for (int i = 1; i <= 7; i++) {
            roozHayeHafteBadi[i - 1] = AddDate(dateShanbe, i - 1);
        }

        return roozHayeHafteBadi;
    }

    public static String AddDate(String date_string, Integer n) {

        Integer y, m, d;
        String yst, mst, dst;
        Byte i;
        byte maxdays[] = new byte[13];

        maxdays[1] = 31;
        maxdays[2] = 31;
        maxdays[3] = 31;
        maxdays[4] = 31;
        maxdays[5] = 31;
        maxdays[6] = 31;
        maxdays[7] = 30;
        maxdays[8] = 30;
        maxdays[9] = 30;
        maxdays[10] = 30;
        maxdays[11] = 30;
        maxdays[12] = 29;


        y = Integer.parseInt(date_string.substring(0, 4));
        m = Integer.parseInt(date_string.substring(5, 7));
        d = Integer.parseInt(date_string.substring(8, 10));
        if (n >= 0) {
            d = d + n;
            if (y % 4 == 3)
                maxdays[12] = 30;
            while (d > maxdays[m]) {
                d = d - maxdays[m];

                m++;
                if (m > 12) {
                    m = 1;

                    y++;
                    if (y % 4 == 3)
                        maxdays[12] = 30;
                    else
                        maxdays[12] = 29;
                }
            }
        } else {
            d = d + n;
            while (d < 1) {
                m--;
                if (m < 1) {
                    m = 12;
                    y--;
                    if (y % 4 == 3)
                        maxdays[12] = 30;
                    else
                        maxdays[12] = 29;

                }
                d = d + maxdays[m];

            }
        }

        yst = String.format("%1$04d", y);
        mst = String.format("%1$02d", m);
        dst = String.format("%1$02d", d);
        date_string = yst + "/" + mst + "/" + dst;
        return date_string;
    }

    public static String getDateCurrent() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", new Locale("fa_IR"));
        Calendar c = Calendar.getInstance();
        // number of days to add
        return sdf.format(c.getTime());
    }

    public static String OneDayNext(String date) {
        String dt = date;  // Start date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", new Locale("fa_IR"));
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dt));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 1);  // number of days to add
        dt = sdf.format(c.getTime());  // dt is now the new date
        return dt;
    }

    public static String OneDayBefor(String date) {
        String dt = date;  // Start date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", new Locale("fa_IR"));
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dt));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, -1);  // number of days to loss
        dt = sdf.format(c.getTime());  // dt is now the new date
        return dt;
    }

    public static String OneWeekNext(String date) {
        String dt = date;  // Start date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", new Locale("fa_IR"));
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dt));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.WEEK_OF_MONTH, 1);  // number of week to add
        dt = sdf.format(c.getTime());  // dt is now the new date
        return dt;
    }

    public static String OneWeekBefor(String date) {
        String dt = date;  // Start date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", new Locale("fa_IR"));
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dt));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.WEEK_OF_MONTH, -1);  // number of week to loss
        dt = sdf.format(c.getTime());  // dt is now the new date
        return dt;
    }

    public static String OneMonthNext(String date, String day) {
        String dt = date;  // Start date
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", new Locale("fa_IR"));
//        Calendar c = Calendar.getInstance();
//        try {
//            c.setTime(sdf.parse(dt));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        c.add(Calendar.MONTH, 1);  // number of month to add
//        dt = sdf.format(c.getTime());  // dt is now the new date
        String year = dt.substring(0, 4);
        String month = dt.substring(5, 7);
        if (Integer.parseInt(month) == 12) {
            year = String.valueOf(Integer.parseInt(year) + 1);
            month = "01";
            dt = year + "/" + month + "/" + day;
        } else {
            month = String.valueOf(Integer.parseInt(month) + 1);
            if (month.length() == 1)
                dt = year + "/0" + month + "/" + day;
            else
                dt = year + "/" + month + "/" + day;
        }
        return dt;
    }

    public static String OneMonthBefor(String date, String day) {
        String dt = date;  // Start date
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", new Locale("fa_IR"));
//        Calendar c = Calendar.getInstance();
//        try {
//            c.setTime(sdf.parse(dt));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        c.add(Calendar.MONTH, -1);  // number of month to loss
//        dt = sdf.format(c.getTime());  // dt is now the new date
        String year = dt.substring(0, 4);
        String month = dt.substring(5, 7);
        if (Integer.parseInt(month) == 1) {
            year = String.valueOf(Integer.parseInt(year) - 1);
            month = "12";
            dt = year + "/" + month + "/" + day;
        } else {
            month = String.valueOf(Integer.parseInt(month) - 1);
            if (month.length() == 1)
                dt = year + "/0" + month + "/" + day;
            else
                dt = year + "/" + month + "/" + day;
        }
        return dt;
    }

    public static String OneYearNext(String date, String monthday) {
        String dt = date;  // Start date
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", new Locale("fa_IR"));
//        Calendar c = Calendar.getInstance();
//        try {
//            c.setTime(sdf.parse(dt));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        c.add(Calendar.YEAR, 1);  // number of year to add
//        dt = sdf.format(c.getTime());  // dt is now the new date
        String year = dt.substring(0, 4);
        year = String.valueOf(Integer.parseInt(year) + 1);
        dt = year + monthday;
        return dt;
    }

    public static String OneYearBefor(String date, String monthDay) {
        String dt = date;  // Start date
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", new Locale("fa_IR"));
//        Calendar c = Calendar.getInstance();
//        try {
//            c.setTime(sdf.parse(dt));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        c.add(Calendar.YEAR, -1);  // number of year to loss
//        dt = sdf.format(c.getTime());  // dt is now the new date

        String year = dt.substring(0, 4);
        year = String.valueOf(Integer.parseInt(year) - 1);
        dt = year + monthDay;
        return dt;
    }

    public static String getNameDay(String date1) {
        String dateString = String.format("%d-%d-%d", Integer.parseInt(date1.substring(0, 4)),
                Integer.parseInt(date1.substring(5, 7)),
                Integer.parseInt(date1.substring(8, 10)));
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-M-d").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String dayOfWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date);
        System.out.println(dayOfWeek);
        String dayName = "";
        if (dayOfWeek.equalsIgnoreCase("FRIDAY"))
            return "جمعه";
       if (dayOfWeek.equalsIgnoreCase("SATURDAY"))
            return "شنبه";
       if (dayOfWeek.equalsIgnoreCase("SUNDAY"))
            return "یکشنبه";
       if (dayOfWeek.equalsIgnoreCase("MONDAY"))
            return "دوشنبه";
       if (dayOfWeek.equalsIgnoreCase("TUESDAY"))
            return "سه شنبه";
       if (dayOfWeek.equalsIgnoreCase("WEDNESDAY"))
            return "چهارشنبه";
       if (dayOfWeek.equalsIgnoreCase("THURSDAY"))
            return "پنجشنبه";

        return dayName;
    }


}