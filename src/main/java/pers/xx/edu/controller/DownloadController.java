package pers.xx.edu.controller;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pers.xx.edu.utils.DownloadUtil;
/**
 * 附件下载
 * 
 * @author yang qi qi
 *
 */
@Controller
public class DownloadController implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 获取路径，处理方式不要写在Controller里
	 * @author XieXing
	 * @param path
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/downLoad")
	public void downLoadExcelModel(@RequestParam(value = "path") String path,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DownloadUtil.downLoad(path, response,request);// 依次传入需要下载的文件名，文件格式，路径，response参数
	}
	/**
	 * yangqiqi
	 * 
	 * @param path
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/downLoading")
	public void downLoad(@RequestParam(value = "path") String path,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DownloadUtil.downLoadFile(path, response);// 依次传入需要下载的文件名，文件格式，路径，response参数
	}

	/**
	 * @author XieXing
	 * @create 2019年1月3日
	 * @description 下载WebRoot文件下的文件
	 * @param fileName
	 *            文件名
	 * @throws Exception 
	 */
	@RequestMapping("/downloadInnerFile")
	public void downloadInnerFile(String fileName, HttpServletRequest request,HttpServletResponse response) throws Exception {
		if (fileName != null && !fileName.equals("")) {
			String path = request.getSession().getServletContext().getRealPath("/files");
			path+="\\"+fileName;
			DownloadUtil.downLoad(path, response,request);
		}else {
			System.out.println("文件名为空");
		}
	}
}
