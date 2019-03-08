package pers.xx.edu.utils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @Author XWZ
 * @Description 生成随机文件名
 * @Date 2018/9/4 14:00
 **/
public class UploadUtils {
	/** 允许的文件类型 */
	private static final String[] extensionPermit = {"txt", "doc", "docx",
			"jpg", "png", "xls", "xlsx", "pdf", "zip", "ppt", "csv"};
	/**
	 * 获取随机名称
	 * 
	 * @param realName
	 *            真实名称
	 * @return uuid
	 */
	public static String getUUIDName(String realName) {
		// realname 可能是 1.jpg 也可能是 1
		// 获取后缀名
		int index = realName.lastIndexOf(".");
		if (index == -1) {
			return UUID.randomUUID().toString().replace("-", "").toUpperCase();
		} else {
			return UUIDUtils.getId().substring(1, 6)
					+ realName.substring(index);
		}
	}

	/**
	 * 获取文件真实名称
	 * 
	 * @param name
	 * @return
	 */
	public static String getRealName(String name) {
		// c:/upload/1.jpg 1.jpg
		// 获取最后一个"/"
		int index;

		if (name.contains("\\"))
			index = name.lastIndexOf("\\");
		else {
			index = name.lastIndexOf("/");
		}
		return name.substring(index + 1);
	}
	/**
	 * 获取文件目录
	 * 
	 * @param name
	 *            文件名称
	 * @return 目录
	 */
	public static String getDir(String name) {
		// 任意一个对象都有一个hash码 131313213
		int i = name.hashCode();
		// 将hash码转成16禁止的字符串
		String hex = Integer.toHexString(i);
		System.out.println(hex);
		int j = hex.length();
		for (int k = 0; k < 8 - j; k++) {
			hex = "0" + hex;
		}
		return "/" + hex.charAt(0) + "/" + hex.charAt(1) + "/" + hex.charAt(2)
				+ "/" + hex.charAt(3) + "/" + hex.charAt(4) + "/"
				+ hex.charAt(5) + "/" + hex.charAt(6) + "/" + hex.charAt(7);
	}
	/**
	 * 保存文件到本地并返回文件保存路径.
	 * 
	 * @author XieXing
	 * @param file
	 *            需要保存的文件.
	 * @param session
	 *            当前会话.
	 * @param id
	 *            文件所在数据库中的id.
	 * @return
	 * @throws Exception
	 */
	public static String saveFile(CommonsMultipartFile file,
			HttpSession session, String id) {
		String curProjectPath = session.getServletContext().getRealPath("/");
		String saveDirectoryPath = FileUtils.getSavePath(curProjectPath);// 文件保存路径
		if (!file.isEmpty()) {// 判断文件是否存在
			String fileName = file.getOriginalFilename();// 文件原名称
			String fileExtension = FilenameUtils.getExtension(fileName);// 文件扩展名

			String savePath = saveDirectoryPath + fileExtension + "\\" + id
					+ "/";// id是固定的所以保留的是最后更改的,所以文件夹id应该是惟一的
			File saveFile = new File(savePath);
			if (!saveFile.exists()) {
				saveFile.mkdirs();
			}
			try {
				file.transferTo(new File(saveFile, fileName));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			String attachment = saveFile.getPath() + "\\" + fileName;
			return attachment.replaceAll("\\\\", "/");// 将所有\转换为/,否则js传参会报错
		}else {
			System.out.println("未上传文件 ，无法保存");
			return "";
		}

	}

	/**
	 * 保存文件到本地并返回文件保存路径.
	 * 
	 * @param file
	 *            需要保存的文件.
	 * @param session
	 *            当前会话.
	 * @param id
	 *            文件所在数据库中的id.
	 * @return
	 * @throws Exception
	 */
	public static String saveFileBy(CommonsMultipartFile file,
			HttpSession session, String id, String foreFile) {
		String curProjectPath = session.getServletContext().getRealPath("/");
		System.out.println(curProjectPath);
		String path[] = curProjectPath.split("\\\\");// 把路径按\分割
		String saveDirectoryPath = "";// 文件保存路径
		for (int i = 0; i < path.length; i++) {// 在webapp之外创建文件夹
			if (path[i].equals("wtpwebapps")) {
				path[i] = foreFile;
			}
			saveDirectoryPath = saveDirectoryPath + path[i] + "\\";
		}
		saveDirectoryPath = saveDirectoryPath + "upload" + "\\"; // 创建upload文件家
		if (!file.isEmpty()) {// 判断文件是否存在
			String fileName = file.getOriginalFilename();// 文件原名称
			String fileExtension = FilenameUtils.getExtension(fileName);// 文件扩展名
			if (!ArrayUtils.contains(extensionPermit, fileExtension)) {// 允许的后缀名
				throw new IllegalArgumentException("不合法的文件参数");
			} else {
				String savePath;
				if (id != null) {
					savePath = saveDirectoryPath + fileExtension + "\\";
					// id是固定的所以保留的是最后更改的,所以文件夹id应该是惟一的
				} else {
					savePath = saveDirectoryPath + fileExtension + "\\" + id
							+ "/";// id是固定的所以保留的是最后更改的,所以文件夹id应该是惟一的
				}
				File saveFile = new File(savePath);
				if (!saveFile.exists()) {
					saveFile.mkdirs();
				}
				try {
					file.transferTo(new File(saveFile, fileName));
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				String attachment = saveFile.getPath() + "\\" + fileName;
				System.out.println(attachment + "mmmm");
				return attachment.replaceAll("\\\\", "/");// 将所有\转换为/,否则js传参会报错
			}
		} else {
			throw new IllegalArgumentException("参数不合法！");
		}

	}
}
