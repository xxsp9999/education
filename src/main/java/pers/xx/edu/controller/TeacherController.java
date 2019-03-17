package pers.xx.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author XieXing
 * @description 老师Controller
 * @create 2019年3月17日 下午6:05:01
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {
	
	/**
	 * @author XieXing
	 * @create 2019年3月17日
	 * @description 老师信息新增 
	 * @return
	 */
	@RequestMapping("/toAdd")
	public String toAdd() {
		return "teacher/add";
	}
	
	/**
	 * @author XieXing
	 * @create 2019年3月17日
	 * @description 老师信息列表
	 * @return
	 */
	@RequestMapping("/toList")
	public String toList() {
		return "teacher/list";
	}

}
