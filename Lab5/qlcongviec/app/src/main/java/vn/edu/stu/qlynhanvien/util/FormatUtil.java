package vn.edu.stu.qlynhanvien.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatUtil {
    static SimpleDateFormat sdfDatetime = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
    static SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
    static SimpleDateFormat sdfTime = new SimpleDateFormat("hh:mm aa");

    public static String formatDatetime(Date date){
        return sdfDatetime.format(date);
    }

    public static String formatDate(Date date){
        return sdfDate.format(date);
    }

    public static String formatTime(Date date){
        return sdfTime.format(date);
    }
}