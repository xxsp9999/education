package pers.xx.edu.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pers.xx.edu.utils.ImageUtils;

/**
 * @author XieXing
 * @description 系统路径跳转
 * @create 2019年2月25日 上午10:37:50
 */
@Controller
@RequestMapping("/system")
public class SystemController {
	/**
	 * @author XieXing
	 * @create 2019年2月25日
	 * @description 获取验证码
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/code")
	public void getCode(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Object[] objs = ImageUtils.createImage();
		request.getSession().removeAttribute("imgCode");
		request.getSession().setAttribute("imgCode", objs[0]);
		// TODO 输出生成的验证码字符串
		BufferedImage img = (BufferedImage) objs[1];
		response.setContentType("image/png");
		OutputStream os = response.getOutputStream();
		ImageIO.write(img, "png", os);
		os.close();
	}
	
	/**
	 * @author XieXing
	 * @create 2019年2月25日
	 * @description 跳转至登录
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/toLogin")
	public String toLogin(){
		return "system/login";
	}

}
