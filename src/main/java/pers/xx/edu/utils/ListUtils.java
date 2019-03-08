package pers.xx.edu.utils;

import java.util.List;

/**
 * 
 * @description List集合工具类 
 * @author 白贵才
 * @create 2017-11-21上午11:09:56
 *
 */
public class ListUtils {

	/**
	 * @description 整型list集合 总和
	 * @param list
	 * @return
	 */
	public static int getSum(List<Integer> list){
		int result = 0;
		if(list != null && list.size() > 0){
			for (Integer inte : list) {
				result += inte;
			}
		}
		
		return result;
	}
}
