package pers.xx.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author XieXing
 * @createDate 2019年4月9日 上午9:57:23
 * @description 专业Controller
 */
@Controller
@RequestMapping("/major")
public class MajorController {

	/**
	 * @author XieXing
	 * @createDate 2019年4月9日 下午2:22:22
	 * @description 跳转到专业列表页面
	 */
	@RequestMapping("/toList")
	public String toList() {
		return "major/list";
	}
}
