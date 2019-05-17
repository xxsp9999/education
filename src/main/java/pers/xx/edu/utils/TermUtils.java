package pers.xx.edu.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author XieXing
 * @createDate 2019年5月14日 上午9:37:38
 * @description 学期工具类
 */
public class TermUtils {

	/**
	 * @author XieXing
	 * @createDate 2019年5月14日 上午11:43:07
	 * @description 获取学生选课的季节，学年，学期
	 * @param entranceDate
	 * @return
	 */
	public static List<String> getStuTermInfo(Date entranceDate) {
		List<String> list = new ArrayList<>();
		String season = "";// 季节
		String year = "";// 年份
		Date date = new Date();// 当前时间
		Calendar calendar = Calendar.getInstance();// 日历对象
		calendar.setTime(date);// 设置当前日期
		int yearStr = calendar.get(Calendar.YEAR);// 获取年份
		int month = calendar.get(Calendar.MONTH) + 1;// 获取月份
		// 获取春秋
		// 获取学年
		if (month >= 8 && month <= 12) {
			season = "秋";
			year = yearStr + "-" + (yearStr + 1);
		} else {
			season = "春";
			year = (yearStr - 1)+"-"+yearStr;
		}
		list.add(season);
		list.add(year);
		String term = "";
		// 获取学期
		int months = getMonthDiff(date,entranceDate);
		if (months < 7) {
			term = "第一学期";
		} else if (months < 14) {
			term = "第二学期";
		} else if (months < 21) {
			term = "第三学期";
		} else if (months < 28) {
			term = "第四学期";
		} else if (months < 35) {
			term = "第五学期";
		} else if (months < 42) {
			term = "第七学期";
		} else {
			term = "第八学期";
		}
		list.add(term);
		return list;
	}
	
	/**
	 * @author XieXing
	 * @createDate 2019年5月14日 上午11:45:04
	 * @description 获取老师选课事件记录
	 * @return
	 */
	public static String getTeaTermInfo() {
		String year = "";// 年份
		Date date = new Date();// 当前时间
		Calendar calendar = Calendar.getInstance();// 日历对象
		calendar.setTime(date);// 设置当前日期
		int yearStr = calendar.get(Calendar.YEAR);// 获取年份
		int month = calendar.get(Calendar.MONTH) + 1;// 获取月份
		// 获取学年
		if (month >= 8 && month <= 12) {
			year = yearStr + "-" + (yearStr + 1);
		} else {
			year = (yearStr - 1)+"-"+yearStr;
		}
		return year;
	}
	
	/**
	 * @author XieXing
	 * @createDate 2019年5月16日 下午12:03:31
	 * @description 获取当前季节
	 * @return
	 */
	public static String getSeasonInfo() {
		String season = "";// 季节
		Date date = new Date();// 当前时间
		Calendar calendar = Calendar.getInstance();// 日历对象
		calendar.setTime(date);// 设置当前日期
		int month = calendar.get(Calendar.MONTH) + 1;// 获取月份
		// 获取春秋
		// 获取学年
		if (month >= 8 && month <= 12) {
			season = "秋";
		} else {
			season = "春";
		}
		return season;
	}
	
	/**
     *  获取两个日期相差的月数
     * @param d1    较大的日期
     * @param d2    较小的日期
     * @return  如果d1>d2返回 月数差 否则返回0
     */
    public static int getMonthDiff(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        if(c1.getTimeInMillis() < c2.getTimeInMillis()) return 0;
        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH);
        int month2 = c2.get(Calendar.MONTH);
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        int day2 = c2.get(Calendar.DAY_OF_MONTH);
        // 获取年的差值 假设 d1 = 2015-8-16  d2 = 2011-9-30
        int yearInterval = year1 - year2;
        // 如果 d1的 月-日 小于 d2的 月-日 那么 yearInterval-- 这样就得到了相差的年数
        if(month1 < month2 || month1 == month2 && day1 < day2) yearInterval --;
        // 获取月数差值
        int monthInterval =  (month1 + 12) - month2  ;
        if(day1 < day2) monthInterval --;
        monthInterval %= 12;
        return yearInterval * 12 + monthInterval;
    }

}
