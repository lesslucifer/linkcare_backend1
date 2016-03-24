/**==============================================================================
 * MAPER JSC (www.maper.vn) Proprietary.
 * Copyright 2015 MAPER JSC.
 * UNPUBLISHED WORK
 * ALL RIGHTS RESERVED
 *
 * This software is the confidential and proprietary information of 
 * MAPER J.S.C ("Proprietary Information").  Any use, reproduction,
 * distribution or disclosure of the software or Proprietary Information,
 * in whole or in part, must comply with the terms of the license  
 * agreement, nondisclosure agreement or contract entered into with 
 * MAPER providing access to this software.
 *
 * Project name  : maper-data<br>
 * File name     : TimeUtil.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		Dec 9, 2015				minh.nguyen				Initial<br>
 * </p>
 *
 * @author minh.nguyen
 *=============================================================================*/
package com.clinic.clinic.common.utils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * Describe functionality of this class here.
 * </p>
 *
 * @author Vuong Do<br>
 * @version 1.0<br>
 * @see TODO
 */
public class TimeUtil {
    /** Logging property. */
    private static final Logger LOGGER = LoggerFactory.getLogger(TimeUtil.class);
    
    /**
     * constants of time serials
     */
    public static final Long MILI_SEC_IN_ONE_HOUR = 60L * 60L * 1000L;
    public static final Long MILI_SEC_IN_ONE_DAY = MILI_SEC_IN_ONE_HOUR * 24L;
    public static final Long MILI_SEC_IN_ONE_WEEK = MILI_SEC_IN_ONE_DAY * 7L;
    
    /**
     * 
     * <p>compute Time Serial At Zero Hour of the date of given time.</p>
     *
     * @param timeSerial
     * @return
     *
     * @author Ngoc Anh
     */
    public static Long computeTimeSerialAtZeroHour(Long timeSerial) {
        Calendar now = Calendar.getInstance();

        Long zeroHourTimeSerial = timeSerial;
        // compute time serial at 0h of the day
        zeroHourTimeSerial = zeroHourTimeSerial - zeroHourTimeSerial % MILI_SEC_IN_ONE_DAY;
        // shift time zone
        zeroHourTimeSerial -= now.getTimeZone().getOffset(now.getTimeInMillis());

        return zeroHourTimeSerial;
    }
    /**
     * <p>compute Time Serial At 11:59 of the date of given time..</p>
     *
     * @param timeSerial
     * @return
     *
     * @author QuangMinh
     */
    public static Long computeTimeSerialAt_11h59m(Long timeSerial) {
        

        Long zeroHourTimeSerial = computeTimeSerialAtZeroHour(timeSerial);
        
        //compute time to 11:59
        zeroHourTimeSerial = zeroHourTimeSerial + MILI_SEC_IN_ONE_HOUR*12 - 60L*1000L;
       

        return zeroHourTimeSerial;
    }
    
    
    /**
     * 
     * <p>get Time Milestones Days In This Week.</p>
     *
     * @return
     *
     * @author anh
     */
    public static List<Long> getTimeMilestonesDaysInThisWeek() {
        List<Long> timeMilestones = new ArrayList<Long>();

        long beginTimeOfWeek = getTimeOfLatestMonday();

        LOGGER.debug("beginTimeOfWeek = {}; {}", beginTimeOfWeek, new Date(beginTimeOfWeek));

        // 8 = 7 days + 1 (for end of the last day)
        for (int i = 0; i <= 7; i++) {
            timeMilestones.add(beginTimeOfWeek + i * TimeUtil.MILI_SEC_IN_ONE_DAY);
        }

        return timeMilestones;
    }
    
    /**
     * 
     * <p>getTimeMilestonesWeekInPeriod.</p>
     * TODO finish this function
     * @param beginTime
     * @param endTime
     * @return
     *
     * @author Ngoc Anh
     */
    public static List<Long> getTimeMilestonesWeekInPeriod(Long beginTime, Long endTime) {
        if (beginTime == null) {
            throw new NullPointerException("getTimeMilestonesWeekInPeriod() got beginTime null");
        }
        if (endTime == null) {
            throw new NullPointerException("getTimeMilestonesWeekInPeriod() got endTime null");
        }
        
        List<Long> timeMilestones = new ArrayList<Long>();

        if (endTime < beginTime) {
            return timeMilestones;
        }
        
        Long timeOfLatestMonday = getTimeOfLatestMonday();
        Long timeFirstMondayInPeriod = timeOfLatestMonday;
        
        /**
         * sure that timeFirstMondayInPeriod smallest but still >= beginTime
         */
        while (beginTime < timeFirstMondayInPeriod) {
            timeFirstMondayInPeriod -= MILI_SEC_IN_ONE_WEEK;
        }
        
        /**
         * sure that timeFirstMondayInPeriod >= beginTime
         */
        while (beginTime > timeFirstMondayInPeriod) {
            timeFirstMondayInPeriod += MILI_SEC_IN_ONE_WEEK;
        }
        
        // TODO finish this function
        
        /**
         * after 2 above loops, timeFirstMondayInPeriod <= endTime
         */
        

        return timeMilestones;
    }
    
    
    /**
     * 
     * <p>get Time Of Latest Monday.</p>
     *
     * @return
     *
     * @author Ngoc Anh
     */
    public static Long getTimeOfLatestMonday() {
    
        Calendar calendar = Calendar.getInstance();
        
//        /**
//         * add 2 if your week start on Monday
//         */
//        int delta = -calendar.get(GregorianCalendar.DAY_OF_WEEK) + 2;
//
//        calendar.add(Calendar.DAY_OF_MONTH, delta);
        
        LOGGER.debug("calendar.getTimeZone() = {}", calendar.getTimeZone().getDisplayName());
        
        Long beginTodayTimeSerial = TimeUtil.computeTimeSerialAtZeroHour(System.currentTimeMillis());
        LOGGER.debug("beginTodayTimeSerial = {}; {}", beginTodayTimeSerial, new Date(beginTodayTimeSerial));
        
        int thisDayInWeek = calendar.get(Calendar.DAY_OF_WEEK);
        
        LOGGER.debug("thisDayInWeek = {}", thisDayInWeek);
        
        Long beginThisWeekTimeSerial =  null;
        switch (thisDayInWeek) {
            case Calendar.MONDAY :
                beginThisWeekTimeSerial = beginTodayTimeSerial;
                LOGGER.debug("MONDAY");
                break;
            case Calendar.TUESDAY :
                beginThisWeekTimeSerial = beginTodayTimeSerial - TimeUtil.MILI_SEC_IN_ONE_DAY;
                LOGGER.debug("TUESDAY");
                break;
            case Calendar.WEDNESDAY :
                beginThisWeekTimeSerial = beginTodayTimeSerial - 2 * TimeUtil.MILI_SEC_IN_ONE_DAY;
                LOGGER.debug("WEDNESDAY");
                break;
            case Calendar.THURSDAY :
                beginThisWeekTimeSerial = beginTodayTimeSerial - 3 * TimeUtil.MILI_SEC_IN_ONE_DAY;
                LOGGER.debug("THURSDAY");
                break;
            case Calendar.FRIDAY :
                beginThisWeekTimeSerial = beginTodayTimeSerial - 4 * TimeUtil.MILI_SEC_IN_ONE_DAY;
                LOGGER.debug("FRIDAY");
                break;
            case Calendar.SATURDAY :
                beginThisWeekTimeSerial = beginTodayTimeSerial - 5 * TimeUtil.MILI_SEC_IN_ONE_DAY;
                LOGGER.debug("SATURDAY");
                break;
            case Calendar.SUNDAY :
                beginThisWeekTimeSerial = beginTodayTimeSerial - 6 * TimeUtil.MILI_SEC_IN_ONE_DAY;
                LOGGER.debug("SUNDAY");
                break;
            
            default :
                LOGGER.debug("ERROR DAY DONT KNOW IN WEEK");
                break;
        }
        
        LOGGER.debug("beginThisWeekTimeSerial = {}", beginThisWeekTimeSerial);
        return beginThisWeekTimeSerial;
    }
    
    /**
     * 
     * <p>get Time Milestones Week In This Month.</p>
     *
     * @return
     *
     * @author anh
     */
    public static List<Long> getTimeMilestonesWeekInThisMonth() {
        List<Long> timeMilestones = new ArrayList<Long>();
    
        Calendar calendar = Calendar.getInstance();
        
        /**
         * add 2 if your week start on Monday
         */
        int delta = -calendar.get(GregorianCalendar.DAY_OF_WEEK) + 2;

        calendar.add(Calendar.DAY_OF_MONTH, delta);
        
        int month = calendar.get(Calendar.MONTH);
  
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        /**
         * begin of the month is also the begin of the period
         */
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        
        Long beginDayTimeSerial = TimeUtil.computeTimeSerialAtZeroHour(calendar.getTime().getTime());
        timeMilestones.add(beginDayTimeSerial);
        
        for (int day = 2; day <= daysInMonth; day++) {
            /**
             * Monday days are internal milestones
             */
            calendar.set(Calendar.DAY_OF_MONTH, day);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek == Calendar.MONDAY) {
                beginDayTimeSerial = TimeUtil.computeTimeSerialAtZeroHour(calendar.getTime().getTime());
                timeMilestones.add(beginDayTimeSerial);
            }
        }
        
        /**
         * 1 of next month is the end of period
         */
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.MONTH, month + 1);
        beginDayTimeSerial = TimeUtil.computeTimeSerialAtZeroHour(calendar.getTime().getTime());
        timeMilestones.add(beginDayTimeSerial);
        
        return timeMilestones;
    }
    
    
    
    /**
     * 
     * <p>get Time Milestones 3 LatestMonths.</p>
     *
     * @return
     *
     * @author anh
     */
    public static List<Long> getTimeMilestones3LatestMonths() {
        List<Long> timeMilestones = new ArrayList<Long>();
        
        Calendar calendar = Calendar.getInstance();
        
        LOGGER.debug("calendar = {}", calendar.toString());
        LOGGER.debug("calendar.day = {}", calendar.get(Calendar.DAY_OF_MONTH));
        LOGGER.debug("calendar.month = {}", calendar.get(Calendar.MONTH));
        LOGGER.debug("calendar.year = {}", calendar.get(Calendar.YEAR));
        LOGGER.debug("calendar.DAY_OF_WEEK = {}", calendar.get(Calendar.DAY_OF_WEEK));

        /**
         * add 2 if your week start on Monday
         */
        int delta = -calendar.get(GregorianCalendar.DAY_OF_WEEK) + 2;

        calendar.add(Calendar.DAY_OF_MONTH, delta);
        
        int month = calendar.get(Calendar.MONTH);
  
        /**
         * month - 2 is the begin of period
         */
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.MONTH, month - 2);
        Long beginDayTimeSerial = TimeUtil.computeTimeSerialAtZeroHour(calendar.getTime().getTime());
        timeMilestones.add(beginDayTimeSerial);
        
        /**
         * month - 1
         */
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.MONTH, month - 1);
        beginDayTimeSerial = TimeUtil.computeTimeSerialAtZeroHour(calendar.getTime().getTime());
        timeMilestones.add(beginDayTimeSerial);
        
        /**
         * this month
         */
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.MONTH, month);
        beginDayTimeSerial = TimeUtil.computeTimeSerialAtZeroHour(calendar.getTime().getTime());
        timeMilestones.add(beginDayTimeSerial);
        
        /**
         * 1 of next month is the end of period
         */
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.MONTH, month + 1);
        beginDayTimeSerial = TimeUtil.computeTimeSerialAtZeroHour(calendar.getTime().getTime());
        timeMilestones.add(beginDayTimeSerial);
        
        return timeMilestones;
    }    
    
    /**
     * <p>Caculate execution time of function.</p>
     *
     * @param beginTime
     * @param endTime
     * @return
     *
     * @author minh.nguyen
     */
    public static Double caculateExecutionTime(Long beginTime, Long endTime) {
        Long result = endTime - beginTime;
        double value = result / 1000.0;
        DecimalFormat df = new DecimalFormat("#.##");    //This should print a number which is rounded to 2 decimal places.
        Double str = Double.parseDouble(df.format(value));
        return str;
    }
    
    /**
     * <p>Convert Long time to String date formatted.</p>
     *
     * @param longTime
     * @param dateFormat
     * @return
     *
     * @author minh.nguyen
     */
    public static String convertLongToDate(Long longTime, String dateFormat) {
        String dateText = null;
        if (longTime != null) {
            try {
                Date date = new Date(longTime);
                SimpleDateFormat df2 = new SimpleDateFormat(dateFormat);
                dateText = df2.format(date);
            } catch (Exception e) {
                LOGGER.error("Err " + e);
            }
        }
        return dateText;
    }
    
    /**
     * <p>Util convert String Datetime to Long time.</p>
     *
     * @param dateTime
     * @param dateFormat
     * @return
     *
     * @author minh.nguyen
     */
    public static Long convertDateTimeToLong(String dateTime, String dateFormat) {       
        Long longTime = null;
        if(dateTime != null) {
                SimpleDateFormat df = new SimpleDateFormat(dateFormat);
                Date d;
                try {
                    d = df.parse(dateTime);
                    longTime = d.getTime();
                } catch (ParseException e) {                    
                    LOGGER.error("Exception", e);
                }
                
        }
        return longTime;
    }
    
    public static Long convertTimeToLong(String time, String dateFormat) {       
        Long longTime = null;
        if(time != null) {
                time = "01-01-1970 " + time;
                SimpleDateFormat df = new SimpleDateFormat(dateFormat);
                Date d;
                try {
                    d = df.parse(time);
                    longTime = d.getTime();
                } catch (ParseException e) {                    
                    LOGGER.error("Exception", e);
                }
                
        }
        return longTime;
    }
    
    

}
