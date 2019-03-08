package pers.xx.edu.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 附件下载工具类
 * 
 * @author yang qi qi
 *
 */
public class DownloadUtil {
	/**
	 * 
	 * @param name
	 *            文件名.
	 * @param type
	 *            文件类型.
	 * @param path
	 *            文件路径.
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public static boolean downLoadFile(String path,
			HttpServletResponse response) throws Exception {

		path = path.substring(0, path.length());
		String paths[] = path.split("\\\\");
		String fileName = paths[paths.length - 1];

		File file = new File(path); // 根据文件路径获得File文件

		// 设置文件类型(这样设置就不止是下Excel文件了，一举多得)

		response.setContentType("multipart/form-data;charset=utf8");
		// 文件名
		response.setHeader("Content-Disposition", "attachment;filename=\""
				+ new String(fileName.getBytes(), "ISO8859-1") + "\"");
		System.out.println("文件长度   " + file.length());
		response.setContentLength((int) file.length());
		byte[] buffer = new byte[4096];// 缓冲区
		BufferedOutputStream output = null;
		BufferedInputStream input = null;
		try {
			output = new BufferedOutputStream(response.getOutputStream());
			input = new BufferedInputStream(new FileInputStream(file));
			int n = -1;
			// 遍历，开始下载
			while ((n = input.read(buffer, 0, 4096)) > -1) {
				output.write(buffer, 0, n);
			}
			output.flush(); // 不可少
			response.flushBuffer();// 不可少
		} catch (Exception e) {
			// 异常自己捕捉
		} finally {
			// 关闭流，不可少
			if (input != null)
				input.close();
			if (output != null)
				output.close();
		}
		return false;
	}

	/**
	 * 下载文件方法
	 * 
	 * @author xiexing
	 * @param path
	 *            文件路径
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public static boolean downLoad(String path, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		String userAgent = request.getHeader("User-Agent");
		String fileName = UploadUtils.getRealName(path);// 根据路径获取文件名称
		response.setContentType("multipart/form-data;charset=utf8");// 自动获得文件类型
																	// ,此处虽说是文件类型自动获取，但有一些文件上传下载后为0kb,这时就是文件类型出现了问题。
		File file = new File(path); // 根据文件路径获得File文件
		// 针对IE或者以IE为内核的浏览器：
		if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
			fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
		} else {
			// 非IE浏览器的处理：
			fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
		}
		response.setHeader("Content-disposition",
				String.format("attachment; filename=\"%s\"", fileName));
		response.setContentType("multipart/form-data");
		response.setCharacterEncoding("UTF-8");

		response.setContentLength((int) file.length());
		byte[] buffer = new byte[4096];// 缓冲区
		BufferedOutputStream output = null;
		BufferedInputStream input = null;
		try {
			output = new BufferedOutputStream(response.getOutputStream());
			input = new BufferedInputStream(new FileInputStream(file));
			int n = -1;
			// 遍历，开始下载
			while ((n = input.read(buffer, 0, 4096)) > -1) {
				output.write(buffer, 0, n);
			}
			output.flush(); // 不可少
			response.flushBuffer();// 不可少
		} catch (Exception e) {
			System.out.println("未找到文件路径");
		} finally {// 关闭流，不可少
			if (input != null)
				input.close();
			if (output != null)
				output.close();
		}
		return false;
	}
}