package pers.xx.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author XieXing
 * @description 项目管理Controller
 * @create 2019年2月25日 下午6:12:11
 */
@Controller
@RequestMapping("/project")
public class ProjectController {
	/**
	 * @author XieXing
	 * @create 2019年2月25日
	 * @description 跳转到项目管理主页面 
	 * @return
	 */
	@RequestMapping("/toMain")
	public String toMainJsp() {
		return "projectmanage/main";
	}

}
