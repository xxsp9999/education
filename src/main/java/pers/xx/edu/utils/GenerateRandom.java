package pers.xx.edu.utils;

import java.util.Random;

/**
 * 生成随机数字，防止使用时间戳生成相同的数字
 * @author lichanghua
 *
 */
public class GenerateRandom {

	public static long random(){
		Random random = new Random(); 
		long num=random.nextInt();
		String str=String.valueOf(num);
		if(str.contains("-")){
			return Long.parseLong(str.replace("-", ""));
		}
		return num;
	}
	
}
