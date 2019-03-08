package pers.xx.edu.utils;
/**
 * @author xiexing
 * @description id字符串处理类
 * @create 2018-9-7下午10:36:14
 */
public class IdSpilt {
	public static Integer[] IdsSpilt(String ids) {
		String[] id = ids.split(",");
		Integer[] idss = new Integer[id.length];
		for(int i = 0;i<id.length;i++) {
			idss[i]=Integer.valueOf(id[i]);
		}
		return idss;
	}
}
