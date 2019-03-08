package pers.xx.edu.utils;
/**
 * @author xiexing
 * @description 公共状态返回
 * @create 2018年10月28日 下午3:07:09
 */
public class StatusCode {

	/**
	 * @decription 成功状态返回
	 * @return
	 */
	public static String success() {
		return "{\"status\":\"success\"}";
	}
	
	/**
	 * @decription 失败状态返回
	 * @return
	 */
	public static String fail() {
		return "{\"status\":\"fail\"}";
	}
}
