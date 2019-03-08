package pers.xx.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author XieXing
 * @description 客户管理Controller
 * @create 2019年2月25日 下午6:11:34
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {
	/**
	 * @author XieXing
	 * @create 2019年2月25日
	 * @description 跳转到跳转到客户管理主页面
	 * @return
	 */
	@RequestMapping("/toMain")
	public String toMainJsp() {
		return "customermanage/main";
	}

}
