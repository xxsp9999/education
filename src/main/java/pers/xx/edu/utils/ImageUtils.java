package pers.xx.edu.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 生成验证码图片工具类
 */
public final class ImageUtils {

	// 验证码字符集
	private static final char[] chars = { '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
			'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
	// 字符数量
	private static final int SIZE = 4;
	// 干扰线数量
	private static final int LINES = 5;
	// 宽度
	private static final int WIDTH = 100;
	// 高度
	private static final int HEIGHT = 30;
	// 字体大小
	private static final int FONT_SIZE = 24;


	/**
	 * @description 生产随机验证码图片
	 * @return
	 */
	public static Object[] createImage() {
		StringBuffer sb = new StringBuffer();
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		Graphics graphic = image.getGraphics();
		// 设置画布背景色
		graphic.setColor(Color.LIGHT_GRAY);

		graphic.fillRect(0, 0, WIDTH, HEIGHT);
		Random ran = new Random();
		for (int i = 0; i < SIZE; i++) {
			int n = ran.nextInt(chars.length);
			graphic.setColor(Color.BLACK);
			graphic.setFont(new Font(null, Font.BOLD + Font.ITALIC, FONT_SIZE));
			graphic.drawString(chars[n] + "", i * WIDTH / SIZE, 23);
			sb.append(chars[n]);
		}
		for (int i = 0; i < LINES; i++) {
			graphic.setColor(getRandomColor());
			graphic.drawLine(ran.nextInt(WIDTH), ran.nextInt(HEIGHT),
					ran.nextInt(WIDTH), ran.nextInt(HEIGHT));
		}
		return new Object[] { sb.toString(), image };
	}

	/**
	 * 随机取色
	 */
	public static Color getRandomColor() {
		Random ran = new Random();
		Color color = new Color(ran.nextInt(256), ran.nextInt(256),
				ran.nextInt(256));
		return color;
	}

}
