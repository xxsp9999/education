package pers.xx.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author XieXing
 * @description 渠道管理Controller
 * @create 2019年2月25日 下午6:13:22
 */
@Controller
@RequestMapping("/agent")
public class AgentController {
	/**
	 * @author XieXing
	 * @create 2019年2月25日
	 * @description 跳转到渠道管理主页面
	 * @return
	 */
	@RequestMapping("/toMain")
	public String toMainJsp() {
		return "agentmanage/main";
	}

}
