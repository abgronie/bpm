package com.hotent.platform.day;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Day {
	private static Day day;
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private Day() {
		/**写死的假日(除周六日)和周六日补班*/
		/**2018年*/
		/**元旦*/
		addHoliday("2018-01-01");
		/**春节*/
		addHoliday("2018-02-15");addHoliday("2018-02-16");addHoliday("2018-02-19");addHoliday("2018-02-20");addHoliday("2018-02-21");
		addSupplement("2018-02-11");addSupplement("2018-02-24");
		/**清明*/
		addHoliday("2018-04-05");addHoliday("2018-04-06");
		addSupplement("2018-04-08");
		/**劳动节*/
		addSupplement("2018-04-28");addHoliday("2018-04-30");addHoliday("2018-05-01");
		/**端午节*/
		addHoliday("2018-06-18");
		/**中秋*/
		addHoliday("2018-09-24");
		/**国庆*/
		addSupplement("2018-09-29");addSupplement("2018-09-30");
		addHoliday("2018-10-01");addHoliday("2018-10-02");addHoliday("2018-10-03");addHoliday("2018-10-04");addHoliday("2018-10-05");
	}
	
	/** 节假日列表 */
	private List<Calendar> holidayList = new ArrayList<Calendar>();
	/**补班列表*/
	private List<Calendar> supplementList = new ArrayList<Calendar>();
	
	public synchronized static Day get() {
		if(day == null) {
			day = new Day();
		}
		return day;
	}
	
	/**
	 * Description: TODO 计算相加day天，并且排除节假日和周末后的日期
	 * @param calendar
	 *            当前的日期
	 * @param day
	 *            相加天数
	 * @return return Calendar 返回类型 返回相加day天，并且排除节假日和周末后的日期
	 */
	private Calendar addDateByWorkDay(Calendar calendar, int day) {
		try {
			for (int i = 0; i < day; i++) {
				calendar.add(Calendar.DAY_OF_MONTH, 1);
				if (checkHoliday(calendar)) {
					i--;
				}
			}
			return calendar;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return calendar;
	}

	/**
	 * Description: TODO 验证日期是否是节假日
	 * @param calendar
	 *            传入需要验证的日期
	 * @return return boolean 返回类型 返回true是节假日，返回false不是节假日
	 */
	private boolean checkHoliday(Calendar calendar) throws Exception {
		/** 判断日期是否是周六周日 */
		if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY
				|| calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			/**是否补班*/
			for (Calendar ca : supplementList) {
				if (ca.get(Calendar.MONTH) == calendar.get(Calendar.MONTH)
						&& ca.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH)
						&& ca.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)) {
					return false;
				}
			}
			return true;
		}
		/** 判断日期是否是节假日 */
		for (Calendar ca : holidayList) {
			if (ca.get(Calendar.MONTH) == calendar.get(Calendar.MONTH)
					&& ca.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH)
					&& ca.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 添加节假日
	 * @param date 格式yyyy-MM-dd
	 */
	private void addHoliday(String date) {
		String[] da = date.split("-");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, Integer.valueOf(da[0]));
		calendar.set(Calendar.MONTH, Integer.valueOf(da[1]) - 1);// 月份比正常小1,0代表一月
		calendar.set(Calendar.DAY_OF_MONTH, Integer.valueOf(da[2]));
		holidayList.add(calendar);
	}

	/**
	 * 添加补班日
	 * @param date 格式yyyy-MM-dd
	 */
	private void addSupplement(String date) {
		String[] da = date.split("-");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, Integer.valueOf(da[0]));
		calendar.set(Calendar.MONTH, Integer.valueOf(da[1]) - 1);// 月份比正常小1,0代表一月
		calendar.set(Calendar.DAY_OF_MONTH, Integer.valueOf(da[2]));
		supplementList.add(calendar);
	}
	
	/**
	 * @param acceptTime 受理时间
	 * @param promisevalue 承诺期限
	 * @return 办结日期
	 * @throws ParseException
	 */
	public Calendar calculate(String acceptTime,int promisevalue) throws ParseException {
		Calendar ca = Calendar.getInstance();
		Date d = df.parse(acceptTime);
		ca.setTime(d);
		return addDateByWorkDay(ca, promisevalue);
	}
	
	public static void main(String[] args) throws ParseException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Day.get().calculate("2018-02-08", 7);
		System.out.println(df.format(c.getTime()));
	}

}
