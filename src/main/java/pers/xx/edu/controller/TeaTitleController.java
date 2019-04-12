package pers.xx.edu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pers.xx.edu.entity.TeaTitle;
import pers.xx.edu.service.TeaTitleService;

/**
 * @author XieXing
 * @createDate 2019年4月12日 上午9:32:00
 * @description 教师职称Controller
 */
@Controller
@RequestMapping("/teatitle")
public class TeaTitleController {
	@Autowired
	private TeaTitleService teaTitleService;

	/**
	 * @author XieXing
	 * @createDate 2019年4月12日 上午9:39:46
	 * @description 获取所有的职称
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getAllTitles")
	public List<TeaTitle> getAllTitles() {
		return teaTitleService.getAll();
	}

}
