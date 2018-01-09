package com.gt.instruct.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期操作工具类
 */
public class DateTimeKit {

    /**
     * 年
     */
    public static final String YEAR_FROMAT = "yyyy";
    /**
     * 月
     */
    public static final String MONTH_FROMAT = "yyyy-MM";
    /**
     * 日
     */
    public static final String DAY_FROMAT = "yyyy-MM-dd";
    /**
     * 时
     */
    public static final String HOUR_FROMAT = "yyyy-MM-dd HH";
    /**
     * 分
     */
    public static final String MINUTE_FROMAT = "yyyy-MM-dd HH:mm";
    /**
     * 秒
     */
    public static final String SECOND_FROMAT = "yyyy-MM-dd HH:mm:ss";


    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @exception Exception
     * @exception ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(DAY_FROMAT);
        try {
            smdate = sdf.parse(sdf.format(smdate));
            bdate = sdf.parse(sdf.format(bdate));
        } catch (ParseException e) {
            throw new Exception("计算两个日期之间相差的天数  ");
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 格式化时间
     *
     * @param formatString 时间格式 例：（yyyy-MM-dd HH:mm:ss）
     * @param date
     * @return
     */
    public static String formatTime(String formatString, Date date) {
        SimpleDateFormat format = new SimpleDateFormat(formatString);
        return format.format(date);
    }

    /**
     * 格式化时间，到月（yyyy-MM）
     *
     * @param date
     * @return
     */
    public static String formatTimeUntilMonth(Date date) {
        return formatTime(MONTH_FROMAT, date);
    }

    /**
     * 格式化时间，到日（yyyy-MM-dd）
     *
     * @param date
     * @return
     */
    public static String formatTimeUntilDay(Date date) {
        return formatTime(DAY_FROMAT, date);
    }

    /**
     * 格式化时间，到小时（yyyy-MM-dd HH）
     *
     * @param date
     * @return
     */
    public static String formatTimeUntilHour(Date date) {
        return formatTime(HOUR_FROMAT, date);
    }

    /**
     * 格式化时间，到分钟（yyyy-MM-dd HH:mm）
     *
     * @param date
     * @return
     */
    public static String formatTimeUntilMinute(Date date) {
        return formatTime(MINUTE_FROMAT, date);
    }

    /**
     * 格式化时间，到秒（yyyy-MM-dd HH:mm:ss）
     *
     * @param date
     * @return
     */
    public static String formatTimeUntilSecond(Date date) {
        return formatTime(SECOND_FROMAT, date);
    }

    /**
     * 格式化时间，从String类型转Date类型
     *
     * @param formatString
     * @param date
     * @return
     * @exception ParseException
     */
    public static Date formatTimeByString(String formatString, String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(formatString);
        return format.parse(date);
    }

    /**
     * 获取本月第一天
     *
     * @return
     */
    public static Date getThisMonthFirstDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获取本月第一天
     *
     * @return
     */
    public static Date getThisMonthLastDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        return calendar.getTime();
    }

    /**
     * 获取本周第一天
     *
     * @return
     */
    public static Date getThisWeekFirstDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, 1);
        return calendar.getTime();
    }

    /**
     * 获取本周最后一天
     *
     * @return
     */
    public static Date getThisWeekLastDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, 7);
        return calendar.getTime();
    }

    /**
     * 获取指定周第一天
     *
     * @param amount 指定周数
     * @return
     */
    public static Date getWeekFirstDay(int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.WEEK_OF_YEAR, amount);
        calendar.set(Calendar.DAY_OF_WEEK, 1);
        return calendar.getTime();
    }

    /**
     * 获取指定周最后一天
     *
     * @param amount 指定周数
     * @return
     */
    public static Date getWeekLastDay(int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.WEEK_OF_YEAR, amount);
        calendar.set(Calendar.DAY_OF_WEEK, 7);
        return calendar.getTime();
    }

    /**
     * 判断指定日期是否在特定日期直接
     *
     * @param firtDate
     * @param lastDate
     * @param thisDate
     * @return 在返回true，不在返回false
     */
    public static boolean between2Date(Date firtDate, Date lastDate, Date thisDate) {
        if (thisDate.getTime() >= firtDate.getTime() && thisDate.getTime() <= lastDate.getTime()) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws ParseException {
        System.out.println(formatTimeUntilSecond(getWeekFirstDay(-1)));
        System.out.println(formatTimeUntilSecond(getWeekLastDay(-1)));

//        String fromat = DAY_FROMAT;
//        System.out.println();
//        System.out.println(formatTimeByString(fromat, formatTime(fromat, getThisWeekLastDay())));
//        System.out.println(between2Date(formatTimeByString(fromat, formatTime(fromat, getThisWeekFirstDay())), formatTimeByString(fromat, formatTime(fromat, getThisMonthLastDay())), getThisMonthFirstDay()));
    }

}
