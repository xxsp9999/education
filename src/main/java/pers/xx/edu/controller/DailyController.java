package pers.xx.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author XieXing
 * @description 日报管理Controller
 * @create 2019年2月25日 下午6:16:09
 */
@Controller
@RequestMapping("/daily")
public class DailyController {
	/**
	 * @author XieXing
	 * @create 2019年2月25日
	 * @description 跳转到日报管理主页面
	 * @return
	 */
	@RequestMapping("/toMain")
	public String toMainJsp() {
		return "dailymanage/main";
	}

}
