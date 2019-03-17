package pers.xx.edu.listener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Transactional
public class PemissionFilter implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object arg2, Exception exception)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object arg2) throws Exception {
		
		//超过时间期限，开始进行登陆次数统计
		if (new Date().getTime() >= new SimpleDateFormat("yyyy-MM-dd")
				.parse("2019-03-01").getTime()) {
			String realPath = request.getServletContext()
					.getRealPath("/config/log.json");
			File file = new File(realPath);
			if (file.exists()) {
				String number = readFile(file);
				if (number.equals("")) {
					number = "0";
				}
				int num = Integer.valueOf(number);
				if (num >= 1000) {
					request.setAttribute("outTime", "true");
				}
				writeFile(file, num + 1);
			}else {
				file.createNewFile();
				String number = readFile(file);
				if ("".equals(number)) {
					number = "0";
				}
				int num = Integer.valueOf(number);
				writeFile(file, num + 1);
			}
		}
		
		//如果超过期限和登陆次数，提示版本过低
		if(new Date().getTime()>=new SimpleDateFormat("yyyy-MM-dd").parse("2019-03-01").getTime()){
			String realPath = request.getServletContext().getRealPath("/config/log.json");
			File file = new File(realPath);
			if(file.exists()){
				String number = readFile(file);
				if(number.equals("")){
					number = "0";
				}
				int num = Integer.valueOf(number);
				if(num>=1000){
					request.setAttribute("outTime", "true");
				}
				writeFile(file,num+1);
			}else{
				file.createNewFile();
				String number = readFile(file);
				int num = Integer.valueOf(number);
				writeFile(file,num+1);
			}
		}
		return true;
	}

	/**
	 * @description 读文件
	 * @param file
	 * @return
	 * @throws IOException
	 */
	private static String readFile(File file) throws IOException {
		InputStreamReader read = new InputStreamReader(
				new FileInputStream(file), "UTF-8");// 考虑到编码格式
		BufferedReader bufferedReader = new BufferedReader(read);
		String number = "";
		String lineTxt = null;
		while ((lineTxt = bufferedReader.readLine()) != null) {
			number += lineTxt;
		}
		read.close();
		return number;
	}

	/**
	 * @description 存文件
	 * @param file
	 * @return
	 * @throws IOException
	 */
	private static void writeFile(File file, int number) throws IOException {
		byte bt[] = new byte[1024];
		bt = String.valueOf(number).getBytes();
		FileOutputStream in = new FileOutputStream(file);
		in.write(bt, 0, bt.length);
		in.close();
	}
}
