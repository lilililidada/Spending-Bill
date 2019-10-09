package util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	static long millisecondsOfOneDay = 1000*60*60*24;
	
	public static java.sql.Date util2sql(java.util.Date d){
		return new java.sql.Date(d.getTime());
	}
	/*
	 * 获取今天，并把时、分、秒和毫秒都置0，因为通过日期控件获取到的日期，就是没有
	 * 分秒和毫秒的。
	 * 
	 */
	public static Date today() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	/*
	 * 获取月初。使用Calendar获取本月第一天。在统计消费一览信息的时候，查看本月的消费数据，其实
	 * 就是从数据库中把本月第一天到最后一天的数据
	 * */
	public static Date monthBegin() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.DATE,1);
		
		c.set(Calendar.HOUR_OF_DAY,0);
		c.set(Calendar.MINUTE,0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		
		return c.getTime();
	}
	/**
	 * 获取月末
	 * @return
	 */
	public static Date monthEnd() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		
		c.set(Calendar.DATE, 1);
		c.add(Calendar.MONTH, 1);
		return c.getTime();
	}
	/**
	 * 获取本月一共有多少天
	 * @return
	 * */
	public static int thisMonthTotalDay() {
		long lastDayMilliSeconds = monthEnd().getTime();//  思考：为什么要调用俩次getTime()(答：两种不同的方法）
		long firstDayMilliSeconds = monthBegin().getTime();
		return (int) ((lastDayMilliSeconds-firstDayMilliSeconds)/millisecondsOfOneDay)+1;
	}
	public static int thisMonthLeftDay() {
		long lastDayMilliSeconds = monthEnd().getTime();
		long toDayMilliSeconds = today().getTime();
		return (int) ((lastDayMilliSeconds-toDayMilliSeconds)/millisecondsOfOneDay);
	}
	//截至到今天，过了多少天
	public static int thisMonthToday() {
		long todayMilliSeconds = today().getTime();
		return (int) ((todayMilliSeconds/millisecondsOfOneDay)+1);
	}
//	public static void main(String[] args) {
//		System.out.println(today());
//		System.out.println(monthBegin());
//		System.out.println(monthEnd());
//		System.out.println(thisMonthLeftDay());
//		System.out.println(thisMonthTotalDay());
//	}
}
