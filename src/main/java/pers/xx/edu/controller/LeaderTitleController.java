package pers.xx.edu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pers.xx.edu.entity.LeaderTitle;
import pers.xx.edu.service.LeaderTitleService;

/**
 * @author XieXing
 * @createDate 2019年4月12日 下午2:48:32
 * @description 领导职称Controller
 */
@Controller
@RequestMapping("/leadertitle")
public class LeaderTitleController {
	@Autowired
	private LeaderTitleService leaderTitleService;
	
	/**
	 * @author XieXing
	 * @createDate 2019年4月12日 下午2:50:50
	 * @description 获取所有领导职称
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getAllTitles")
	public List<LeaderTitle> getAllTitles(){
		return leaderTitleService.getAll();
	}

}
