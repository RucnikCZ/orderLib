package cz.deepvision.pos.orderlibrary.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class TimeUtil {
    public static String formatDate(Date date, TimeZone zone) {
        if (date != null && zone != null) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
            df.setTimeZone(zone);
            String ret = df.format(date);
            ret = ret.replaceFirst("\\+([0-9][0-9])([0-9][0-9])$", "+$1:$2");
            return ret;
        } else {
            return null;
        }
    }

    public static String formatDate(Date date) {
        if (date == null) {
            return null;
        } else {
            String ret = (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")).format(date);
            ret = ret.replaceFirst("\\+([0-9][0-9])([0-9][0-9])$", "+$1:$2");
            return ret;
        }
    }

    public static Date parseDate(String date) {
        try {
            date = date.replaceFirst("\\+([0-9][0-9]):([0-9][0-9])$", "+$1$2");
            return (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault())).parse(date);
        } catch (ParseException var2) {
            throw new IllegalArgumentException("bad date", var2);
        }
    }

    public static TimeZone parseTimeZone(String date) {
        if (date == null) {
            return null;
        } else {
            String zoneStr = date.substring(date.lastIndexOf("+"));
            return TimeZone.getTimeZone("GMT" + zoneStr);
        }
    }

    public static String formatDateEurope(Date date){
        if (date == null) {
            return null;
        } else {
            String ret = (new SimpleDateFormat("HH:mm:ss dd.MM.yyyy")).format(date);
            return ret;
        }
    }
}
