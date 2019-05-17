package pers.xx.edu.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author xiexing
 * @description 日期时间处理
 * @create 2018年9月27日 下午4:45:08
 */
public class DateTimeUtils {
	/**
	 * @description 时间字符串转时间类型
	 * @param str
	 * @return
	 * @throws ParseException
	 */
	public static Date deal(String str) throws ParseException {
		if (str == null || "".equals(str)) {
			return null;
		} else {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			return format.parse(str);
		}
	}
	/**
	 * @description 返回当前时间的前一个月和后一个月
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	public static List<Date> dateChange(String time) throws ParseException {
		List<Date> dates = new ArrayList<>();
		Date start = new Date();
		Date end = new Date();
		Date tmp = new Date();
		tmp = deal(time);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(tmp);
		calendar.add(Calendar.MONTH, -1);
		start = calendar.getTime();
		dates.add(start);
		calendar.add(Calendar.MONTH, +2);
		end = calendar.getTime();
		dates.add(end);
		return dates;
	}

	/**
	 * @description 格式化时间
	 * @return
	 */
	public static String formateDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		String dateString = sdf.format(new Date());
		return dateString;
	}
}
