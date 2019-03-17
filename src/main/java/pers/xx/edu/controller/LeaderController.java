package pers.xx.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author XieXing
 * @description
 * @create 2019年3月17日 下午6:07:58
 */
@Controller
@RequestMapping("/leader")
public class LeaderController {
	/**
	 * @author XieXing
	 * @create 2019年3月17日
	 * @description 跳转到学生信息新增页面
	 * @return
	 */
	@RequestMapping("/toAdd")
	public String toAdd() {
		return "leader/add";
	}
	
	
	/**
	 * @author XieXing
	 * @create 2019年3月17日
	 * @description 跳转到学生信息列表页面
	 * @return
	 */
	@RequestMapping("/toList")
	public String toList() {
		return "leader/list";
	}

}
