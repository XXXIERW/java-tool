package cn.tool.core.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 关于日期转化以及比较使用的格式
 * 日期通用格式
 */
public final class DateUtil {
	//12星座常量定义
	private static final String[] constellationArr = { "魔羯座", "水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座",
			"射手座", "魔羯座" };
	//12星座日期边界
	private static final int[] constellationEdgeDay = { 20, 18, 20, 20, 20, 21, 22, 22, 22, 22, 21, 21 };

	// 日期转化使用的格式；
	/** 格式:yyyyMMddHHmmss **/
	public static final String DATE_TIME_14 = "yyyyMMddHHmmss";
	/** 格式:yyyy-MM-dd HH:mm:ss **/
	public static final String DATE_TIME_20 = "yyyy-MM-dd HH:mm:ss";
	/** 格式:yyyyMMdd **/
	public static final String DATE_8 = "yyyyMMdd";
	/** 格式:yyyy-MM-dd **/
	public static final String DATE_10 ="yyyy-MM-dd";
	/** 格式:yyyyMM **/
	public static final String MONTH_6 = "yyyyMM";
	/** 格式:MM.dd **/
	public static final String MONTH_DATE_5 = "MM.dd";
	/** 格式:yyyy-MM **/
	public static final String MONTH_7 = "yyyy-MM";
	/** 格式:yyyy-MM-dd HH:mm **/
	public static final String DATE_TIME_16 = "yyyy-MM-dd HH:mm";
	/** 格式:dd/MMM/yyyy:HH:mm **/
	public static final String US_DATE_TIME_17 = "dd/MMM/yyyy:HH:mm";

	public static final String US_DATE_TIME_20 = "dd/MMM/yyyy:HH:mm:ss";

	/**
	 *
	 * @Title: Str20ToStr14
	 * @Description: TODO(将yyyy-MM-dd HH:mm:ss格式化yyyyMMddHHmmss)
	 * @param str
	 * @return String 返回类型
	 * @throws
	 * @Description
	 *  将常见的格式（将yyyy-MM-dd HH:mm:ss格式化yyyyMMddHHmmss）这个一般使用在订单号里面
	 *  例如：
	 *  2021-01-16 16:10:51 -> 20210116161051********** 订单号的格式
	 */
	public static String Str20ToStr14(String str) {
		if ((str == null) || str.equals("")) {
			return null;
		}
		String a = str.replaceAll("-", "").replaceAll(" ", "")
				.replaceAll(":", "");
		return a;
	}

	/**
	 * @throws ParseException
	 * @Title: Str20ToStr14
	 * @Description: TODO(将yyyy-MM-dd HH:mm:ss格式化Date)
	 * @param str
	 * @return Date 返回类型
	 * @throws
	 * @Description:
	 * 将使用时间 中的String转化为 Date一般作用于 存储数据到数据库中；
	 */
	public static Date Str20ToDate(String str) throws ParseException {
		if ((str == null) || str.equals("")) {
			return null;
		}
		String a = str.replaceAll("-", "").replaceAll(" ", "")
				.replaceAll(":", "");
		// 作用于格式化时间 SimpleDateFormat 用于时间的处理
		Date d = new SimpleDateFormat(DATE_TIME_14).parse(a);
		return d;
	}

	/**
	 * @Title: getStringDate
	 * @Description: TODO(获取当前字符串的日期)
	 * @param format
	 * @return
	 * @throws Exception
	 * @Description 从字符串中获取里面的时间
	 */
	public final static String getStringDate(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}

	/**
	 *
	 * @Title: dateToString8
	 * @Description: TODO(将如期格式化为8位的字符串形式，如：20110415)
	 * @param @param date
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String dateToString8(Date date) {
		if (date == null) {
			return null;
		}
		return new SimpleDateFormat(DATE_8).format(date);
	}

	/**
	 *
	 * @Title: dateToString14
	 * @Description: TODO(将如期格式化为14位的字符串形式，如：20110415101112)
	 * @param @param date
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String dateToString14(Date date) {
		if (date == null) {
			return null;
		}
		return new SimpleDateFormat(DATE_TIME_14).format(date);
	}

	/**
	 *
	 * @Title: dateToString20
	 * @Description: TODO(将如期格式化为14位的字符串形式，如：2011-04-15 10:11:12)
	 * @param @param date
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String dateToString20(Date date) {
		if (date == null) {
			return null;
		}
		return new SimpleDateFormat(DATE_TIME_20).format(date);
	}

	/**
	 *
	 * @Title: dateToString10
	 * @Description: TODO(将如期格式化为10位的字符串形式，如：2011-04-15)
	 * @param @param date
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String dateToString10(Date date) {
		if (date == null) {
			return null;
		}
		return new SimpleDateFormat(DATE_10).format(date);
	}

	/**
	 *
	 * @Title: dateToString6
	 * @Description: TODO(将如期格式化为6位的字符串形式，如：201104)
	 * @param @param date
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String dateToString6(Date date) {
		if (date == null) {
			return null;
		}
		return new SimpleDateFormat(MONTH_6).format(date);
	}

	/**
	 *
	 * @Title: dateToString6
	 * @Description: TODO(将如期格式化为6位的字符串形式，如：201104)
	 * @param @param date
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	/**
	 *
	 * 将如期格式化为6位的字符串形式，如：09.21)
	 * @param date
	 * @return
	 * @author qiuyuhua
	 * 2016年9月21日 下午2:48:40
	 */
	public static String dateToString5(Date date) {
		if (date == null) {
			return null;
		}
		return new SimpleDateFormat(MONTH_DATE_5).format(date);
	}

	/**
	 *
	 * @Title: dateToString
	 * @Description: TODO(根据指定的格式化串来格式化日期)
	 * @param @param format 格式化串
	 * @param @param date
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String dateToString(String format, Date date) {
		if (date == null) {
			return null;
		}

		SimpleDateFormat sdf = null;
		if (format == null) {
			sdf = new SimpleDateFormat(DATE_TIME_20);
		} else {
			sdf = new SimpleDateFormat(format);
		}

		return sdf.format(date);
	}

	/**
	 *
	 * @Title: string8ToDate
	 * @Description: TODO(将一个yyyyMMdd格式的字符串转行为日期)
	 * @param @param date
	 * @param @return 设定文件
	 * @return Date 返回类型
	 * @throws
	 */
	public static Date string8ToDate(String date) {
		if (date == null) {
			return null;
		}
		if (date.length() == 14) {
			return string14ToDate(date);
		}
		Calendar cal = Calendar.getInstance();
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(4, 6));
		int day = Integer.parseInt(date.substring(6));
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 *
	 * @Title: string14ToDate
	 * @Description: TODO(将一个yyyyMMddHHmmss格式的字符串转行为日期)
	 * @param @param date
	 * @param @return 设定文件
	 * @return Date 返回类型
	 * @throws
	 */
	public static Date string14ToDate(String date) {
		if (date == null) {
			return null;
		}
		if (date.length() == 8) {
			return string8ToDate(date);
		}
		Calendar cal = Calendar.getInstance();
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(4, 6));
		int day = Integer.parseInt(date.substring(6, 8));
		int house = Integer.parseInt(date.substring(8, 10));
		int minute = Integer.parseInt(date.substring(10, 12));
		int second = Integer.parseInt(date.substring(12));
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.HOUR_OF_DAY, house);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, second);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 *
	 * @Title: string19ToDate
	 * @Description: TODO(将一个yyyy-MM-dd HH:mm:ss格式的字符串转行为日期)
	 * @param @param date
	 * @param @return 设定文件
	 * @return Date 返回类型
	 * @throws
	 */
	public static Date string19ToDate(String date) {
		if (date == null) {
			return null;
		}
		if (date.length() != 19) {
			return null;
		}
		date = dateToString14(stringToDate(date, "yyyy-MM-dd HH:mm:ss",
				"yyyyMMddHHmmss"));
		Calendar cal = Calendar.getInstance();
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(4, 6));
		int day = Integer.parseInt(date.substring(6, 8));
		int house = Integer.parseInt(date.substring(8, 10));
		int minute = Integer.parseInt(date.substring(10, 12));
		int second = Integer.parseInt(date.substring(12));
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.HOUR_OF_DAY, house);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, second);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 *
	 * @Title: compareTo
	 * @Description: TODO(比较两个字符串(yyyyMMdd 或 yyyyMMddHHmmss)形似的日期)
	 * @param @param date
	 * @param @param compareDate
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @throws
	 */
	public static int compareTo(String date, String compareDate) {
		Date d1 = DateUtil.string14ToDate(date);
		Date d2 = DateUtil.string14ToDate(compareDate);
		if ((d1 == null) || (d2 == null)) {
			return -100;
		}
		return d1.compareTo(d2);
	}

	public static int compareTo(Date date) {
		Date now = new Date();
		return now.compareTo(date);
	}

	/**
	 * 比较日期字符串格式为 yyyy-MM-dd HH:mm:ss的大小比较
	 * @param date
	 * @param compareDate
	 * @return 0 ==  <0
	 * @Description compareTo
	 */
	public static int compareToString(String date, String compareDate) {
		return StringToDate20(date).compareTo(StringToDate20(compareDate));
	}

	/**
	 * 将String字符串转为 yyyy-MM-dd HH:mm:ss 格式的Date
	 * @param date
	 * @return
	 */
	public static Date  StringToDate20(String date) {
		try {
			return new SimpleDateFormat(DATE_TIME_20).parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取指定年份和月份对应的天数
	 *
	 * @param year
	 *            指定的年份
	 * @param month
	 *            指定的月份
	 * @return int 返回天数
	 */
	public static int getDaysInMonth(int year, int month) {
		if ((month == 1) || (month == 3) || (month == 5) || (month == 7)
				|| (month == 8) || (month == 10) || (month == 12)) {
			return 31;
		} else if ((month == 4) || (month == 6) || (month == 9)
				|| (month == 11)) {
			return 30;
		} else {
			if ((((year % 4) == 0) && ((year % 100) != 0))
					|| ((year % 400) == 0)) {
				return 29;
			} else {
				return 28;
			}
		}
	}

	/**
	 * 根据所给的起止时间来计算间隔的天数
	 *
	 * @param startDate
	 *            起始时间
	 * @param endDate
	 *            结束时间
	 * @return int 返回间隔天数
	 */
	public static int getIntervalDays(Date startDate, Date endDate) {
		long startdate = startDate.getTime();
		long enddate = endDate.getTime();
		long interval = enddate - startdate;
		int intervalday = (int) (interval / (1000 * 60 * 60 * 24));
		return intervalday;
	}

	/**
	 *
	 * @Title：chaDay
	 * @Description：比较2个时间相差天数
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws ParseException
	 *             long
	 */
	public static long chaDay(String startTime, String endTime)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_10);
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(startTime));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(endTime));
		long time2 = cal.getTimeInMillis();
		long between = (time2 - time1) / (1000 * 3600 * 24);
		return between;

	}

	/**
	 * 比较2个日期相差的天数，注：仅仅比较日期相差的天数
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static long chaDay(Date startDate, Date endDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getDateBegin(startDate));
		long time1 = cal.getTimeInMillis();
		cal.setTime(getDateBegin(endDate));
		long time2 = cal.getTimeInMillis();
		long between = (time2 - time1) / (1000 * 3600 * 24);
		return between;
	}

	/**
	 * 根据所给的起止时间来计算间隔的月数
	 *
	 * @param startDate
	 *            起始时间
	 * @param endDate
	 *            结束时间
	 * @return int 返回间隔月数
	 */
	@SuppressWarnings("static-access")
	public static int getIntervalMonths(Date startDate, Date endDate) {
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(startDate);
		Calendar endCal = Calendar.getInstance();
		endCal.setTime(endDate);
		int startDateM = startCal.get(startCal.MONTH);
		int startDateY = startCal.get(startCal.YEAR);
		int enddatem = endCal.get(endCal.MONTH);
		int enddatey = endCal.get(endCal.YEAR);
		int interval = ((enddatey * 12) + enddatem)
				- ((startDateY * 12) + startDateM);
		return interval;
	}

	/**
	 * 返回当前日期时间字符串<br>
	 * 默认格式:yyyy-mm-dd hh:mm:ss
	 *
	 * @return String 返回当前字符串型日期时间
	 */
	public static String getCurrentTime() {
		String returnStr = null;
		Date date = new Date();
		returnStr = new SimpleDateFormat(DATE_TIME_20).format(date);
		return returnStr;
	}

	/**
	 * 返回自定义格式的当前日期时间字符串
	 *
	 * @param format
	 *            格式规则
	 * @return String 返回当前字符串型日期时间
	 */
	public static String getCurrentTime(String format) {
		String returnStr = null;
		SimpleDateFormat f = new SimpleDateFormat(format);
		Date date = new Date();
		returnStr = f.format(date);
		return returnStr;
	}

	/**
	 * 返回当前字符串型日期
	 *
	 * @return String 返回的字符串型日期
	 */
	public static String getCurDate() {
		Calendar calendar = Calendar.getInstance();
		String strDate = new SimpleDateFormat(DATE_10).format(calendar.getTime());
		return strDate;
	}

	/**
	 * 将字符串型日期转换为日期型
	 *
	 * @param strDate
	 *            字符串型日期
	 * @param srcDateFormat
	 *            源日期格式
	 * @param dstDateFormat
	 *            目标日期格式
	 * @return Date 返回的util.Date型日期
	 */
	public static Date stringToDate(String strDate, String srcDateFormat,
									String dstDateFormat) {
		Date rtDate = null;
		Date tmpDate = (new SimpleDateFormat(srcDateFormat)).parse(strDate,
				new ParsePosition(0));
		String tmpString = null;
		if (tmpDate != null) {
			tmpString = (new SimpleDateFormat(dstDateFormat)).format(tmpDate);
		}
		if (tmpString != null) {
			rtDate = (new SimpleDateFormat(dstDateFormat)).parse(tmpString,
					new ParsePosition(0));
		}
		return rtDate;
	}

	/**
	 * 月份加减
	 * @Title: changeMonth
	 * @param date 日期
	 * @param offset 加减月数
	 * @return
	 */
	public static Date changeMonth(Date date, int offset) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH,
				(calendar.get(Calendar.MONTH) + offset));
		return calendar.getTime();
	}

	/**
	 * 天数加减
	 * @Title: changeDay
	 * @param date 日期
	 * @param offset 加减天数
	 * @return
	 */
	public static Date changeDay(Date date, int offset) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR,
				(calendar.get(Calendar.DAY_OF_YEAR) + offset));
		return calendar.getTime();
	}

	/**
	 * 分钟加减
	 * @Title: changeDay
	 * @param date 日期
	 * @param offset 加减分钟
	 * @return
	 */
	public static Date changeMin(Date date, int offset) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MINUTE, (calendar.get(Calendar.MINUTE) + offset));
		return calendar.getTime();
	}

	/**
	 *
	 * @Title：changeSecond
	 * @Description：秒数加减
	 * @param date
	 * @param offset
	 * @return Date
	 */
	public static Date changeSecond(Date date, int offset) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.SECOND, (calendar.get(Calendar.SECOND) + offset));
		return calendar.getTime();
	}

	/**
	 * @Title: formatTimeStampe
	 * @Description: TODO(将时间戳转换成普通日期)
	 * @param timestampString
	 * @return String
	 * @throws
	 */
	public static String formatTimeStampe(String timestampString) {
		Long timestamp = Long.parseLong(timestampString) * 1000;
		String date = new SimpleDateFormat(DATE_TIME_20).format(new java.util.Date(timestamp));
		return date;
	}

	/**
	 *
	 * @Title: get
	 * @Description: TODO(获取距离当前时间最近一个月的日期)
	 * @param now
	 * @return
	 */
	public static Date getOneMonth(Calendar now) {
		int[] months = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		if (isLeapYear(now.get(Calendar.YEAR))) {
			months[1] = 29;
		}
		int nowmonth = Calendar.MONTH;
		int month = nowmonth - 1;
		long time = now.getTimeInMillis()
				- ((long) (months[month]) * 24 * 3600 * 1000);
		return new Date(time);
	}

	/**
	 *
	 * @Title: get
	 * @Description: TODO(获取距离当前时间最近两个月的日期)
	 * @param now
	 * @return
	 */
	public static Date getTwoMonth(Calendar now) {
		int[] months = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		if (isLeapYear(now.get(Calendar.YEAR))) {
			months[1] = 29;
		}
		int nowmonth = Calendar.MONTH;
		int month1 = nowmonth - 1;
		int month2 = nowmonth;
		long time = now.getTimeInMillis()
				- ((long) ((months[month1]) + (months[month2])) * 24 * 3600 * 1000);
		return new Date(time);
	}

	/**
	 *
	 * @Title: isLeapYear
	 * @Description: TODO(判断是否是闰年)
	 * @param year
	 * @return
	 */
	public static boolean isLeapYear(int year) {
		boolean tags = false;
		if ((((year % 4) == 0) && ((year % 100) != 0)) || ((year % 400) == 0)) {
			tags = true;
		}
		return tags;
	}

	/**
	 *
	 * @Title: changeDate
	 * @Description: TODO(获取一段时间后的时间)
	 * @param source
	 * @param sourcFormat
	 * @param c
	 * @param interval
	 * @param returnFormat
	 * @return
	 */
	public static String changeDate(String source, String sourcFormat, int c,
									int interval, String returnFormat) {
		String str = "";
		try {
			SimpleDateFormat sd = new SimpleDateFormat(sourcFormat);
			Date sourceDate = sd.parse(source);
			Calendar cal = Calendar.getInstance();
			cal.setTime(sourceDate);
			cal.add(c, interval);
			Date retDate = cal.getTime();
			SimpleDateFormat sdf1 = new SimpleDateFormat(returnFormat);
			str = sdf1.format(retDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return str;

	}

	/**
	 *
	 * @Title: convertWeekByDate
	 * @Description: TODO(计算本周起始日期（礼拜一的日期）)
	 * @param time
	 */
	public static String convertWeekByDate(Date time) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		String imptimeBegin = new SimpleDateFormat(DATE_TIME_14).format(cal.getTime());
		return imptimeBegin;
	}

	/**
	 *
	 * @Title: addDate
	 * @Description: TODO(给日期增加指定天数)
	 * @param time
	 * @param day
	 * @throws Exception
	 */
	public static String addDate(String time, int day) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_14);
		Calendar fromCal = Calendar.getInstance();
		Date date = sdf.parse(time);
		fromCal.setTime(date);
		fromCal.add(Calendar.DATE, day);
		return sdf.format(fromCal.getTime());
	}

	/**
	 *
	 * @Title: addDate
	 * @Description: TODO(给日期增加指定天数)
	 * @param time
	 * @param day
	 * @throws Exception
	 */
	public static String addDate20(String time, int day) throws Exception {
		Calendar fromCal = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		Date date = dateFormat.parse(time);
		fromCal.setTime(date);
		fromCal.add(Calendar.DATE, day);
		return dateFormat.format(fromCal.getTime());
	}

	/**
	 *
	 * @Title: addDate
	 * @Description: TODO(给日期增加指定天数)
	 * @param time
	 * @param day
	 * @throws Exception
	 */
	public static String addDate10(String time, int day) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_10);
		Calendar fromCal = Calendar.getInstance();
		Date date = sdf.parse(time);
		fromCal.setTime(date);
		fromCal.add(Calendar.DATE, day);
		return sdf.format(fromCal.getTime());
	}

	/** 通过设置年月日时分秒来获得时间 **/
	public static Date getDate(int year, int month, int day, int hour,
							   int minute, int second) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month - 1);
		c.set(Calendar.DAY_OF_MONTH, day);
		c.set(Calendar.HOUR_OF_DAY, hour);
		c.set(Calendar.MINUTE, minute);
		c.set(Calendar.SECOND, second);
		return c.getTime();
	}

	/**
	 * 获取指定日期后n天的凌晨
	 *
	 * @param date
	 * @param n
	 * @return
	 */
	public static Date getDateNextDay(Date date, int n) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_YEAR, (c.get(Calendar.DAY_OF_YEAR) + n));
		return c.getTime();
	}

	/**
	 *
	 * @Title: bijiao
	 * @Description: TODO(比较日期大小，格式yyyy-MM-dd HH:mm:ss或yyyy-MM-dd)
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean bijiao(String str1, String str2) {
		boolean bo = false;
		str1 = str1.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
		str2 = str2.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
		long s1 = Long.parseLong(str1);
		long s2 = Long.parseLong(str2);
		if (s1 > s2) {
			bo = true;
		}
		return bo;
	}

	/**
	 * 获取倒计时 需要现在时间和结束时间
	 * @param smallTime
	 * @param bigTime
	 * @return
	 */
	public static String daojishi(Date smallTime, Date bigTime) {
		long time_distance = bigTime.getTime() - smallTime.getTime();
		long int_day = (long) Math.floor(time_distance / 86400000);
		time_distance -= int_day * 86400000;
		long int_hour = (long) Math.floor(time_distance / 3600000);
		time_distance -= int_hour * 3600000;
		long int_minute = (long) Math.floor(time_distance / 60000);
		time_distance -= int_minute * 60000;
		long int_second = (long) Math.floor(time_distance / 1000);
		String str_day = "";
		String str_hour = "";
		String str_minute = "";
		String str_second = "";
		str_day = int_day + "";
		if (int_hour < 10) {
			str_hour = "0" + int_hour;
		} else {
			str_hour = int_hour + "";
		}
		if (int_minute < 10) {
			str_minute = "0" + int_minute;
		} else {
			str_minute = int_minute + "";
		}
		if (int_second < 10) {
			str_second = "0" + int_second;
		} else {
			str_second = int_second + "";
		}
		String str_time = str_day + "天" + str_hour + "时" + str_minute + "分"
				+ str_second + "秒";
		// String str_time = str_day + "天" + str_hour + "小时" + str_minute + "分钟"
		// + str_second + "秒";
		return str_time;

	}

	/**
	 *
	 * @Title：ifOpTime
	 * @Description：开奖时间
	 * @return
	 * @throws Exception
	 *             String
	 */
	public static String getOpTime() throws Exception {
		String format = "HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String nowTime = sdf.format(new Date());
		boolean bo = bijiao(nowTime, "20:00:00");
		String optime = DateUtil.dateToString10(new Date());
		if (bo) {
			optime = DateUtil.addDate10(optime, 1);
		}
		return optime;
	}

	/**
	 *
	 * @Title：bj
	 * @Description：减去当前时间 得到的小时数
	 * @param str
	 * @return long
	 */
	public static long bj(String str) {
		long diff = 0;
		try {
			Date date = new Date();
			Date st = new SimpleDateFormat(DATE_TIME_20).parse(str);
			diff = st.getTime() - date.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return diff / (60000 * 60);
	}

	/**
	 *
	 * @Description：获取当前的年份
	 * @return Integer
	 */
	public static Integer currentYear() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		return c.get(Calendar.YEAR);
	}
	
	/**
	 *
	 * @Description：获取当前的年份
	 * @return Integer
	 */
	public static Integer getYear(Date date) {
		if(date == null) {
			return currentYear();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}

	/**
	 * @Description:根据日期获取时间日
	 * @param date
	 * @return
	 */
	public static int getDayByDate(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * @Description:根据日期获取时间月
	 * @param date
	 * @return
	 */
	public static int getMonthByDate(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MONTH);
	}

	/**
	 * @Description:根据日期获取时间年
	 * @param date
	 * @return
	 */
	public static int getYearByDate(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}

	/**
	 *
	 * @Description：获取当前月份的天数
	 * @return Integer
	 */
	public static Integer monthOfDays(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 *
	 * @Title：addHourBj20
	 * @Description：给日期增加指定小时数 如果大于当前时间返回true
	 * @param time
	 * @param hour
	 * @return
	 * @throws Exception
	 *             boolean
	 */
	public static boolean addHourBj20(String time, int hour) throws Exception {
		Calendar fromCal = Calendar.getInstance();
		Date date = new SimpleDateFormat(DATE_TIME_20).parse(time);
		fromCal.setTime(date);
		fromCal.add(Calendar.HOUR_OF_DAY, hour);
		Calendar nowTime = Calendar.getInstance();
		int re = fromCal.compareTo(nowTime);
		if (re == 1) {
			return true;
		}
		return false;
	}

	public static Date StringToDate(String dateStr,String formatStr){
		DateFormat dd=new SimpleDateFormat(formatStr);
		Date date=null;
		try {
			date = dd.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 获取秒时间戳
	 */
	public static Long currentTimeSeconds() {
		return System.currentTimeMillis()/1000;
	}

	/**
	 * 根据秒时间戳表示的日期获取该日期对应的星座
	 * @param seconds
	 */
	public static String getConstellationFromSeconds(Long seconds) {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		// 转毫秒
		cal.setTimeInMillis(seconds * 1000);

		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DATE);

		if (day <= constellationEdgeDay[month - 1]) {
			month = month - 1;
		}
		if (month >= 0) {
			return constellationArr[month];
		}
		// default to return 魔羯 从定义好的数组里面获取
		return constellationArr[11];

	}

	/**
	 * 获取指定日期的日终时间，即获取到指定日期的23点59分59秒999毫秒
	 * @param date 为空默认为当前日期
	 */
	public static Date getDateEnd(Date date) {
		Calendar cal = Calendar.getInstance();
		if(date!=null) {
			cal.setTime(date);
		}
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

	/**
	 * 获取当天的日终时间秒时间戳，即获取到当天的23点59分59秒999毫秒的秒时间戳如1468425599
	 */
	public static long getTodayEndSecond() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime().getTime() / 1000;
	}

	/**
	 * 获取当天的日始时间秒时间戳，即获取到当天的0点0分0秒0毫秒的秒时间戳如1468339200
	 */
	public static long getTodayBeginSecond() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime().getTime() / 1000;
	}

	/**
	 * 获取指定日期的日始时间，即获取到指定日期的0点0分0秒0毫秒
	 * @param date 为空默认为当前日期
	 */
	public static Date getDateBegin(Date date) {
		Calendar cal = Calendar.getInstance();
		if(date!=null) {
			cal.setTime(date);
		}
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 根据秒时间戳获取日期
	 * @param second
	 */
	public static Date fromSecond(long second) {
		Date date = new Date(second * 1000);
		return date;
	}

	/**
	 * 将日期转换成秒时间戳
	 * @param date
	 * @return
	 */
	public static long toSecond(Date date) {
		return date.getTime()/1000;
	}

	/**
	 * 将Jan这样的英文简写月转换成数字，英文简写不正确时返回当前月
	 * @param month
	 * @return
	 * @throws
	 */
	public static int parseEnMonth(String month) {
		int m = getMonthByDate(new Date());
		switch (month) {
			case "Jan":
				m = 1;
				break;
			case "Feb":
				m = 2;
				break;
			case "Mar":
				m = 3;
				break;
			case "Apr":
				m = 4;
				break;
			case "May":
				m = 5;
				break;
			case "Jun":
				m = 6;
				break;
			case "Jul":
				m = 7;
				break;
			case "Aug":
				m = 8;
				break;
			case "Sep":
			case "Sept":
				m = 9;
				break;
			case "Oct":
				m = 10;
				break;
			case "Nov":
				m = 11;
				break;
			case "Dec":
				m = 12;
				break;
			default:
				break;
		}
		return m;
	}

	/**
	 * 获取当前日期所在周的最后一天日期 （周一为第一天，周日为最后一天）
	 * @param time
	 * @return
	 * @author qiuyuhua
	 * 2016年9月21日 上午11:56:53
	 */
	public static Date getLastDateOfWeek(Date time) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);

		// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}

		cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day + 6);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值

		return cal.getTime();
	}

	/**
	 * 获取当前日期所在月的最后一天日期 
	 * @param time
	 * @return
	 * @author qiuyuhua
	 * 2016年9月21日 下午2:14:04
	 */
	public static Date getLastDateOfMonth(Date time) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH,0);//设置为1,当前日期既为本月第一天 
		return cal.getTime();
	}
	
	/**
	 * 获取当前日期所在年的最后一天日期 
	 * @param time
	 * @return
	 * @author qiuyuhua
	 * @time 2019年2月20日 上午11:58:09
	 */
	public static Date getLastDateOfYear(Date time) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		cal.add(Calendar.YEAR, 1);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.DAY_OF_MONTH,0);//设置为1,当前日期既为本月第一天 
		return cal.getTime();
	}
	
	/**
	 * 获取当前日期所在月的第一天日期 
	 * @param time
	 * @return
	 * @author qiuyuhua
	 * 2016年9月21日 下午2:14:04
	 */
	public static Date getFirstDateOfMonth(Date time) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		cal.set(Calendar.DAY_OF_MONTH,1);//设置为1,当前日期既为本月第一天 
		return cal.getTime();
	}

	/**
	 * 获取指定日期一年中所在的周
	 * @param date
	 * @return
	 */
	public static int getWeekByDate(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 *
	 * @Title: dateToString7
	 * @Description: TODO(将如期格式化为6位的字符串形式，如：2011-04)
	 * @param @param date
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String dateToString7(Date date) {
		if (date == null) {
			return null;
		}
		return new SimpleDateFormat(MONTH_7).format(date);
	}

	/**
	 *
	 * @Title: addDate
	 * @Description: TODO(给日期增加指定天数)
	 * @param time
	 * @param day
	 * @throws ParseException
	 * @throws Exception
	 */
	public static String addDate8(String time, int day) throws ParseException   {
		Calendar fromCal = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_8);
		Date date = dateFormat.parse(time);
		fromCal.setTime(date);
		fromCal.add(Calendar.DATE, day);
		return dateFormat.format(fromCal.getTime());
	}


	/**
	 * 转换格式04/Feb/2017:09:12的日期
	 * @param dateString
	 * @return
	 * @throws ParseException
	 */
	public static String usDateToString16(String dateString) throws ParseException {

		SimpleDateFormat us_sf = new SimpleDateFormat(US_DATE_TIME_20, Locale.US);
		SimpleDateFormat sf = new SimpleDateFormat(DATE_TIME_16);
		Date d = us_sf.parse(dateString);
		return sf.format(d);
	}

	public static String usDateToString10(String dateString) throws ParseException {

		SimpleDateFormat us_sf = new SimpleDateFormat(US_DATE_TIME_20, Locale.US);
		SimpleDateFormat sf = new SimpleDateFormat(DATE_10);
		Date d = us_sf.parse(dateString);
		return sf.format(d);
	}

	/**
	 * 获取两个日期间，每月第一天的日期
	 * @param startDate 最小时间  2015-01
	 * @param endDate 最大时间 2015-10
	 * @return
	 */
	public static List<Date> getMonthBetween(Date startDate, Date endDate){
		List<Date> result = new ArrayList<Date>();

		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();

		start.setTime(startDate);
		start.set(start.get(Calendar.YEAR), start.get(Calendar.MONTH), 1);

		end.setTime(endDate);
		end.set(end.get(Calendar.YEAR), end.get(Calendar.MONTH), 2);

		Calendar curr = start;
		while (curr.before(end)) {
			result.add(curr.getTime());
			curr.add(Calendar.MONTH, 1);
		}

		return result;
	}

	public static String changeSecondsToOneWords(long seconds) {
		if(seconds<=0) {
			return "";
		}

		if(seconds % 60 > 0 ) {
			return seconds + "秒";
		}

		if(seconds % 3600 > 0) {
			return (seconds/60) + "分钟";
		}

		if(seconds % 86400 > 0) {
			return (seconds/3600) + "小时";
		}

		if(seconds % 604800 > 0) {
			return (seconds/86400) + "天";
		}

		return (seconds/604800) + "周";
	}
}
