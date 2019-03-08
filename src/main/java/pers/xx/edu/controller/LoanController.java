package pers.xx.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author XieXing
 * @description 借货管理Controller
 * @create 2019年2月25日 下午6:12:42
 */
@Controller
@RequestMapping("/loan")
public class LoanController {
	/**
	 * @author XieXing
	 * @create 2019年2月25日
	 * @description 跳转到借货管理主页面
	 * @return
	 */
	@RequestMapping("/toMain")
	public String toMainJsp() {
		return "loanmanage/main";
	}

}
