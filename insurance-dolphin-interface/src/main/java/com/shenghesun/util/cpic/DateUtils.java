 /* 文档密级：秘密 */
package com.shenghesun.util.cpic;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class DateUtils {
	private static final Logger log = Logger.getLogger(DateUtils.class);
	/** 格式到日期(yyyy-MM-dd) */
	public static final String DATE_DAYONLY = "yyyy-MM-dd";
	/** 格式到秒(yyyy-MM-dd HH:mm:ss) */
	public static final String DATE_DAYFULL = "yyyy-MM-dd HH:mm:ss";
	/** 格式到日期 */
	public static final String DATE_STRING_DAYONLY="yyyyMMdd";
	/** 格式到秒 */
	public static final String DATE_STRING_DAYFULL="yyyyMMddHHmmss";
	/** 格式为时间 */
	public static final String DATE_TIMEONLY = "HH:mm:ss";
	
	public static final long DAY_MILLI = 24 * 60 * 60 * 1000;

	/** 格式成 年-月-日 * */
	public static final String DATE_CH_DAYONLY = "yyyy年MM月dd日";
	public static final String DATE_CH_MINUTEONLY = "yyyy/MM/dd HH:mm";
	private DateUtils() {

	}

	/**
	 * 将string类型的字符数据转换为Date类型
	 * 
	 * @author zhouwei
	 */
	public static Date stringToDate(String date, String format) {
		if (StringUtils.isBlank(date)) {
			return null;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	/**
	 * 以某种格式格式化Date成字符串
	 * 
	 * @author zhouwei
	 */
	public static String dateToString(Date date, String format) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * String 转换为 Timestamp
	 * 
	 * @param date
	 * @param format yyyy-MM-dd HH:mm:ss或yyyy-MM-dd
	 * @return
	 */
	public static Timestamp stringToTimestamp(String date, String format) {
		if (StringUtils.isBlank(date)) {
			return null;
		}
		String temp = null;
		
		if (format.equals(DATE_DAYFULL)) {
			temp = date.replace('/', '-');
			temp = temp + ".000000000";
		} else if (format.equals(DATE_DAYONLY)) {
			temp = date.replace('/', '-');
			temp = temp + " 00:00:00.000000000";
		} else {
			return null;
		}
		// java.sql.Timestamp.value() 要求的格式必须为yyyy-mm-dd hh:mm:ss.fffffffff
		return Timestamp.valueOf(temp);
	}
	
	/**
	 * Timestamp转换为String
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String timestampToString(Timestamp date, String format) {
		if(date == null) {
			return null;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	/**
	 * 转换Date到Calendar
	 * 
	 * @param date
	 * @return
	 */
	public static Calendar dateToCalendar(Date date) {
		if(date == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c;
	}
	
	/**
	 * 
	 * @param c
	 * @return
	 */
	public static Date calendarToDate(Calendar c) {
		if(c == null) {
			return null;
		}
		Date date = new Date();
		date.setTime(c.getTimeInMillis());
		return date;
	}
	
	
	/**
	 * 比较两个日期是否同年同月同日
	 * @param      D1
	 * @param      D2
	 * @return     0否  1是
	 * */
	public static int verifyDateIsYMD(Date d1 ,Date d2,String formate){
		String yeard1 = DateUtils.dateToString(d1, formate);
		String yeard2 = DateUtils.dateToString(d2, formate);
		if (!yeard1.equals(yeard2)) {
			return 0;
		}
		return 1;
	}
	
	/**
	 * 日期比较(比较到毫秒）
	 * 
	 * @param d1
	 * @param d2
	 * @return d1等于d2返回0，d1小于d2返回小于0的值，d1大于d2返回大于0的值
	 */
	public static int compare(Date d1, Date d2) {
		if (d1 == null || d2 == null) {
			return 111111;
		}
		return d1.compareTo(d2);
	}

	/**
	 * 日期比较(比较到毫秒）
	 * 
	 * @param d1
	 * @param d2
	 * @return cal1等于cal2返回0，cal1小于cal2返回小于0的值，cal1大于cal2返回大于0的值
	 */
	public static int compare(Calendar cal1, Calendar cal2) {
		return cal1.compareTo(cal2);
	}

	/**
	 * 日期比较(比较到日期）
	 * 
	 * @param d1
	 * @param d2
	 * @return d1等于d2返回0，d1小于d2返回小于0的值，d1大于d2返回大于0的值
	 */
	public static int compareToDay(Date d1, Date d2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(d1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(d2);
		return compareToDay(cal1, cal2);
	}

	/**
	 * 日期比较(比较到日期）
	 * 
	 * @param cal1
	 * @param cal2
	 * @return cal1等于cal2返回0，cal1小于cal2返回小于0的值，cal1大于cal2返回大于0的值
	 */
	public static int compareToDay(Calendar cal1, Calendar cal2) {
		DateFormat df = new SimpleDateFormat(DATE_DAYONLY);
		Date d1 = null;
		try {
			d1 = df.parse(df.format(cal1.getTime()));
		} catch (ParseException e) {
			
		}
		Date d2 = null;
		try {
			d2 = df.parse(df.format(cal2.getTime()));
		} catch (ParseException e) {
			
		}
//		if(d1 != null){
//			d1.compareTo(d2);
//		}
		return d1.compareTo(d2);
	}

	public static Date addYears(Date date, int years) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return addYears(cal, years).getTime();
	}

	public static Calendar addYears(Calendar cal, int years) {
		cal.add(Calendar.YEAR, years);
		return cal;
	}

	public static Date addMonths(Date date, int months) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return addMonths(cal, months).getTime();
	}

	public static Calendar addMonths(Calendar cal, int months) {
		cal.add(Calendar.MONTH, months);
		return cal;
	}

	public static Date addDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return addDays(cal, days).getTime();
	}

	public static Calendar addDays(Calendar cal, int days) {
		cal.add(Calendar.DAY_OF_MONTH, days);
		return cal;
	}

	public static Date addHours(Date date, int hours) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return addHours(cal, hours).getTime();
	}

	public static Calendar addHours(Calendar cal, int hours) {
		cal.add(Calendar.HOUR, hours);
		return cal;
	}

	/**
	 * 取得两个日期之间的日数。如：beginDate＝"2005-08-26",endDate="2005-08-29",则返回3;反之返回-3
	 * 如果beginDate或endDate为null值，则取当前时间值。
	 * 
	 * @param t1
	 *            日期
	 * @param t2
	 *            日期
	 * @return t1到t2间的日数，如果t2 在 t1之后，返回正数，否则返回负数
	 */
	public static long daysBetweenByDate(java.util.Date beginDate,
			java.util.Date endDate) {
		if (beginDate == null) {
			beginDate = new java.util.Date();
		}
		if (endDate == null) {
			endDate = new java.util.Date();
		}
		long beginTime = beginDate.getTime();
		long endTime = endDate.getTime();
		long betweenDays = (long) ((endTime - beginTime) / (1000 * 60 * 60 * 24));

		return betweenDays;
	}

	/**
	 * 计算两个月份的差值
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static int getDateMonths(java.util.Date beginDate,
			java.util.Date endDate) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(endDate);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(beginDate);
		if (cal2.equals(cal1)) {
			return 0;
		}

		if (cal1.after(cal2)) {
			Calendar temp = cal1;
			cal1 = cal2;
			cal2 = temp;
		}

		int flag = 0;
		if (cal2.get(Calendar.DAY_OF_MONTH) > cal1.get(Calendar.DAY_OF_MONTH)) {
			flag = 1;
		}

		int iMonth = 0;
		if (cal2.get(Calendar.YEAR) > cal1.get(Calendar.YEAR)) {
			iMonth = (cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR)) * 12
					+ cal2.get(Calendar.MONTH) - cal1.get(Calendar.MONTH)
					+ flag;
		} else {
			iMonth = cal2.get(Calendar.MONTH) - cal1.get(Calendar.MONTH) + flag;
		}
		return iMonth;
	}

	/**
	 * 从身份证号码中获取生日日期
	 * 
	 * @param cardID
	 * @return
	 */
	public static Date getBirthday(String cardID) {
		if (StringUtils.isBlank(cardID)) {
			return null;
		}
		
		if (cardID.length() == 15) {
			Pattern p = Pattern.compile("^[1-9]\\d{5}(\\d{2}((((0[13578])|(1[02]))(([0-2][0-9])|(3[01])))|(((0[469])|(11))(([0-2][0-9])|(30)))|(02[0-2][0-9])))\\d{3}$");
			Matcher m = p.matcher(cardID);
			if (m.matches()) {
				String s = m.group(1);
				try {
					return new SimpleDateFormat("yyyyMMdd").parse("19" + s);
				} catch (ParseException e) {
				}
			}
		} else if (cardID.length() == 18) {
			Pattern p = Pattern.compile("^[1-9]\\d{5}(\\d{4}((((0[13578])|(1[02]))(([0-2][0-9])|(3[01])))|(((0[469])|(11))(([0-2][0-9])|(30)))|(02[0-2][0-9])))\\d{3}[\\dX]$");
			Matcher m = p.matcher(cardID);
			if (m.matches()) {
				String s = m.group(1);
				try {
					return new SimpleDateFormat("yyyyMMdd").parse(s);
				} catch (ParseException e) {
				}
			}
		}
		
		return null;
	}
	/**得到当前日期的前一天
	 * */
	public static Date getOneDayBefore(Date date)
	{
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DAY_OF_MONTH, -1);
		return calendar.getTime();
	}
	
	
	/**
	 * 判断后一个时间是否大于前一个时间24小时
	 * @param args
	 */

	public static boolean pd24(java.util.Date date1,java.util.Date date2){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date1);
		calendar.add(Calendar.HOUR_OF_DAY, 24);
		return DateUtils.compare(DateUtils.calendarToDate(calendar), date2)<=0;
	}
	
	public static boolean pd72(java.util.Date date1,java.util.Date date2){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date1);
		calendar.add(Calendar.HOUR_OF_DAY, 72);
		return DateUtils.compare(DateUtils.calendarToDate(calendar), date2)<=0;
	}
	
	/**
	 * 比较当前日期是否在上午11点30至1点之间或者在下午6点之后
	 * **/
	public static boolean compareDateBetwwenNowDate(){
	    Calendar c = Calendar.getInstance();
	    c.setTime(new Date());
	    
	    Calendar c1 = Calendar.getInstance();
	   
	    c1.setTime(new Date());
	    c1.set(Calendar.HOUR_OF_DAY, 11);
	    c1.set(Calendar.MINUTE, 30);

	   
	    Calendar c2 = Calendar.getInstance();
	    c2.setTime(new Date());
	    c2.set(Calendar.HOUR_OF_DAY, 13);
	    c2.set(Calendar.MINUTE, 0);
	    c2.set(Calendar.SECOND, 0);
	    
	    	    
	    Long l = c.getTimeInMillis();

	    Long l1 = c1.getTimeInMillis();
        Long l2 = c2.getTimeInMillis();
        if (l > l1 && l < l2) {
        	return true;
        }
        
        Calendar c3 = Calendar.getInstance();
        c3.setTime(new Date());
        c3.set(Calendar.HOUR_OF_DAY, 18);
        c3.set(Calendar.MINUTE, 0);
        c3.set(Calendar.SECOND, 0);
        
        Long l3 = c3.getTimeInMillis();
        if (l > l3) {
        	return true;
        }
        
		return false;
	}
	
	//得到一天中大于等于当前的小时数集合
	public static List<String> getDayCompareHoursList(){
		//设置为24时
		Date date = new Date();
		String dateStr = DateUtils.dateToString(date, "yyyy-MM-dd HH");
		date = DateUtils.stringToDate(dateStr, "yyyy-MM-dd HH");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		//当前hour
		int currencyHour = c.get(Calendar.HOUR_OF_DAY);;
        int maxHour = 24;
        List<String> dayHourList = new ArrayList<String>();
        for (int i = currencyHour; i <= maxHour; i++){
        	dayHourList.add(String.valueOf(i));
        }		 
		 return dayHourList;
	}
	
	//得到一天中的小时数
	public static List<String> getDayHoursList(){
		List<String> dayHourList = new ArrayList<String>();
		for (int i = 0; i < 24; i++){
			if (i <= 9) {
				dayHourList.add("0"+String.valueOf(i));
			} else {
				dayHourList.add(String.valueOf(i));
			}
		}
		return dayHourList;
	}
	
	//的到当前时间的小时
	public static String getHourOfDay(){
		//格式化为24小时
		String currencyDateStr = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH");
		Date currecyDate = DateUtils.stringToDate(currencyDateStr, "yyyy-MM-dd HH");
		Calendar c = Calendar.getInstance();
		c.setTime(currecyDate);
		int reHour = c.get(Calendar.HOUR_OF_DAY);
		String hourStr = String.valueOf(reHour);
		if (reHour < 10 && hourStr.length() == 1) {
			hourStr = "0"+hourStr;
		}
		return hourStr;
	}
	//获取当前的分钟，00、10、20、30、40、50
	public static String getMinuteOfDayNum(){
		String currencyDateStr = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm");
		Date currecyDate = DateUtils.stringToDate(currencyDateStr, "yyyy-MM-dd HH:mm");
		Calendar c = Calendar.getInstance();
		c.setTime(currecyDate);
		int miunte = c.get(Calendar.MINUTE);
		if (miunte < 10){
			return "10";
		} else if (miunte < 20) {
			return "20";
		} else if (miunte < 30) {
			return "30";
		} else if (miunte < 40) {
			return "40";
		} else if (miunte < 50) {
			return "50";
		} else if (miunte < 60) {
			return "00";
		} 
		return "00";
	}
	
	//获取当前的分钟，00、10、20、30、40、50
	public static Calendar getMinuteOfDay(){
		//String currencyDateStr = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm");
		//Date currecyDate = DateUtils.stringToDate(currencyDateStr, "yyyy-MM-dd HH:mm");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		int miunte = c.get(Calendar.MINUTE);
		if (miunte < 10){
			c.set(Calendar.MINUTE,10);
		} else if (miunte < 20) {
			c.set(Calendar.MINUTE,20);
		} else if (miunte < 30) {
			c.set(Calendar.MINUTE,30);
		} else if (miunte < 40) {
			c.set(Calendar.MINUTE,40);
		} else if (miunte < 50) {
			c.set(Calendar.MINUTE,50);
		} else if (miunte < 60) {
			c.set(Calendar.MINUTE,00);
			c.add(Calendar.HOUR_OF_DAY, 1);
		} 
		return c;
	}
	
	public static String getHourOfDay(Calendar c){
		//格式化为24小时
		String currencyDateStr = DateUtils.dateToString(c.getTime(), "yyyy-MM-dd HH");
		Date currecyDate = DateUtils.stringToDate(currencyDateStr, "yyyy-MM-dd HH");
		c.setTime(currecyDate);
		int reHour = c.get(Calendar.HOUR_OF_DAY);
		String hourStr = String.valueOf(reHour);
		if (reHour < 10 && hourStr.length() == 1) {
			hourStr = "0"+hourStr;
		}
		return hourStr;
	}
	
	
	public static void main(String[] args) {
       List<String> listDayHour = DateUtils.getDayCompareHoursList();
       System.out.println(listDayHour.size());
   
	}
}
