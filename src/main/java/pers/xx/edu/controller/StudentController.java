package pers.xx.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author XieXing
 * @description 学生Controller
 * @create 2019年3月17日 下午5:53:26
 */
@Controller
@RequestMapping("/student")
public class StudentController {
	
	/**
	 * @author XieXing
	 * @create 2019年3月17日
	 * @description 跳转到学生信息新增页面
	 * @return
	 */
	@RequestMapping("/toAdd")
	public String toAdd() {
		return "student/add";
	}
	
	
	/**
	 * @author XieXing
	 * @create 2019年3月17日
	 * @description 跳转到学生信息列表页面
	 * @return
	 */
	@RequestMapping("/toList")
	public String toList() {
		return "student/list";
	}

}
