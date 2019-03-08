package pers.xx.edu.utils;

import java.util.Random;
import java.util.UUID;

public class UUIDUtils {
	/**
	 * 随机生成id
	 *
	 * @return
	 */
	public static String getId() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}

	public static String getUUID64() {
		return getId() + getId();
	}

	/**
	 * 生成随机码
	 *
	 * @return
	 */
	public static String getCode() {
		return getId();
	}
	
	public static String getRandomId() {
		Random random = new Random();
		String result="";
		for (int i=0;i<6;i++)
		{
			result+=random.nextInt(10);
		}
		return result;
	}

	public static void main(String[] args) {
		for(int i=0 ;i<100;i++)
			System.out.println(getRandomId());
	}
}
