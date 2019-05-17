package pers.xx.edu.utils;

/**
 * @author XieXing
 * @createDate 2019年4月17日 下午5:18:20
 * @description 个人信息工具类
 */
public class PersonUtils {
	/**
	 * @author XieXing
	 * @createDate 2019年4月17日 下午5:19:29
	 * @description 根据角色获取跳转路径
	 * @param roleId
	 * @return
	 */
	public static String getPah(Integer roleId) {
		if(roleId==1) {
			return "user";
		}
		if(roleId==2) {
			return "student";
		}
		if(roleId==3) {
			return "teacher";
		}
		if(roleId==4) {
			return "instructor";
		}
		if(roleId==5) {
			return "leader";
		}
		if(roleId==6) {
			return "manager";
		}
		return null;
	}

}
