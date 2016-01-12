package ua.artcode.utils.accounts;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by dexter on 31.12.15.
 */
public class Calculator {

    public static double getCost(double distance){
        return Math.round(distance / 100);
    }

    public static Date getTimeArrival(double distance){

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.add(Calendar.MINUTE, 3 * (((int)distance) / 1000));

        return calendar.getTime();
    }
}
