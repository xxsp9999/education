package pers.xx.edu.utils;



import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 工具类
 * @author DengMingChao
 *
 */
public class Tools {
	

/**
 * 将字符串中的旧字符串替换成新字符
 * @param str
 * @param oldChar
 * @param newChar
 * @return
 */
	public static String replaceStr(String str ,String oldChar ,String newChar) {
		if(!isEmpty(str) && !isEmpty(oldChar) && !isEmpty(newChar)) {
			return str.replace(oldChar, newChar)  ;
		}else {
			return str ;
		}
	}
	
	
	public static String getFileNameByPath(String filePath) {
		if(isEmpty(filePath)) {
			return null  ;
		}
		String s1 = Tools.getFileName(filePath) ;
		String s2 = filePath.substring(filePath.lastIndexOf("."));
		String s3 = s1+s2 ;
		return s3  ;
	}
	
	
	/**
	 * 根据路径获取文件名称
	 * @param s
	 * @return
	 */
	public static String  getFileName(String s) {
		if(isEmpty(s)) {
			return null ;
		}
	String s1 = 	s.substring(0,s.lastIndexOf(".")) ;
	int i1 = s1.lastIndexOf("\\") ;
	String s2 =  s1.substring(i1+1,s1.length()) ;	
	return s2 ;
	}



	/*=====================================================*/
	/**
	 * 判断对象是否为空对象。
	 * 及有对象，但所有属性的值都为 NULL， "" 不为空！
	 * @author DengMingChao
	 * @param o
	 */
	@SuppressWarnings("unused")
	public static boolean isEmptyObject(Object obj) {
		if(isEmpty(obj)) {
			return true ;
		}
	  Class<? extends Object> clazz = 	obj.getClass() ;
	  Field[] fileds =	clazz.getDeclaredFields(); //通过反射获取所有的对象属性
	  int flag1 = 0 ;
	  for (Field field : fileds) {
		  try {
			  field.setAccessible(true);
			Object  result = field.get(obj) ;
			if(result == null) {
				flag1 ++ ;
			}
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	  if(flag1 == fileds.length) {
		  return true ;
	  }else {
		  return false ;
	  }
	  
	}
	
	
	
	
	/*====================字符串工具===========================*/
	
	
	/**
	 * @description 判断是否为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(Object str) {
		boolean flag = false;
		if (str != null && ! str.equals("")) {
			if (str.toString().trim().length() > 0) {
				flag = false;
			}else {
				flag = true ;
			}
		} else {
			flag = true;
		}
		return flag;
	}
	
	
	/**
	 * @description 判断输入的是电话还是非电话
	 * 原理：判断是否是11位数的数字！
	 * @param param
	 * @return
	 */
	public static boolean isPhoneNum(String param){
		if(isEmpty(param)) {
			return false;
		}
		if(param.length()>11){
			return false;
		}
		char[] chars = param.toCharArray();
		int length = 0;
		for(char c:chars){
			if(!Character.isDigit(c)){ // 判断是否为数字
				break;
			}
			length++;
		}
		if(length!=chars.length){
			return false;
		}
		return true;
	}
	
	/**
	 * @desctiption 是不是数字 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){ 
	   if(isEmpty(str)){
		   return false; 
	   }
	   Pattern pattern = Pattern.compile("[0-9]*"); 
	   Matcher isNum = pattern.matcher(str);
	   if( !isNum.matches() ){
	       return false; 
	   } 
	   return true; 
	}
	/**
	 * 字符串转double类型
	 * @param str
	 * @return
	 */
	public static double idDouble(String str){ 
		double d=0;
		if(!isEmpty(str)) {
			if(!"".equals(str)){
				d=Double.parseDouble(str);
			}
		}
		return d;
	}
	/**
	 * @description 格式化数字
	 * @createDate 2017年11月3日14:21:12
	 * @return
	 */
	public static String formatNum(float dou){
		DecimalFormat d = new DecimalFormat("0.00");
		if(dou == 0.0f){
			return "0";
		}else{
			return d.format(dou);
		}
	}
	
	
	
	/*====================时间工具===========================*/
	/**
	 * 获取当前时间
	 * @return
	 */
	public static Date getNowDate() {
		try {
			Date nowDate =  new Date() ;
			return nowDate ;
		}catch(Exception e) {
			e.printStackTrace();
			return null ;
		}
	}
	
	/**
	 * 获取当前时间Long类型
	 * @return
	 */
	public static long  getNowDateInt(String t) {
		long s = 0L ;
		if(isEmpty(t)) {
			return s ;
		}
		
		try {
			Calendar ca =  Calendar.getInstance();
			if("yyyy".equals(t)) {
				s =  ca.get(Calendar.YEAR);
			}else
			if("MM".equals(t)) {
				s = ca.get(Calendar.MONTH)+1;
			}else
			if("dd".equals(t)) {
				s =  ca.get(Calendar.DATE);
			}else
			if("hh".equals(t)) {
				s = ca.get(Calendar.HOUR);
			}else
			if("mm".equals(t)) {
				s = ca.get(Calendar.MINUTE);
			}else
			if("ss".equals(t)) {
				s = ca.get(Calendar.SECOND);
			}else
			{
				String ss = (ca.get(Calendar.YEAR)+""+(ca.get(Calendar.MONTH)+1)+""+ca.get(Calendar.DATE)+""+ca.get(Calendar.HOUR)+
						ca.get(Calendar.MINUTE)+""+ca.get(Calendar.SECOND)) ;
				s =Long.parseLong(ss);
			}
				return s  ;
		}catch(Exception e) {
			e.printStackTrace();
			return 0 ;
		}
	}
	/**
	 * 获取当前时间字符串格式
	 * @return
	 */
	public static String  getNowDateStr() {
		try {
		Date nowDate =  new Date() ;
		String nowDateStr =  new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(nowDate);
		return nowDateStr ;
		}catch(Exception e) {
			e.printStackTrace();
			return null ;
		}
	}
	
	
	/**
	 * 获取当前年月日
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static StringBuffer  getNowYMD() {
		StringBuffer nowDateStr = new StringBuffer() ;
		try {
		Date nowDate =  new Date() ;
		nowDateStr.append(new SimpleDateFormat("yyyy").format(nowDate)+"年");
		nowDateStr.append(nowDate.getMonth()+"月");
		nowDateStr.append(nowDate.getDay()+"日");
		
		return nowDateStr ;
		}catch(Exception e) {
			e.printStackTrace();
			return null ;
		}
	}
	
	
	
	/**
	 * 将时间转换成字符串
	 * @param date
	 * @return
	 */
	public static String DateParseStr(Date date) {
		try {
			String nowDateStr =  new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
			return nowDateStr ;
			}catch(Exception e) {
				e.printStackTrace();
				return null ;
			}
	}
	
	
	/**
	 * 将时间字符串转换成时间对象
	 * @param date
	 * @return
	 */
	public static Date StrParseDate(String dateStr) {
		try {
			Date date =  new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(dateStr);
			return date ;
			}catch(Exception e) {
				e.printStackTrace();
				return null ;
			}
	}
	
	
	public static  boolean compareDate(Date startDate , Date endDate) {
		boolean bool =  true ;
		if(startDate.after(endDate)) {
			bool =  false ;
		}
		return bool ;
	}
	
	
	
	

}
