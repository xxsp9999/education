package pers.xx.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author XieXing
 * @createDate 2019年4月21日 上午9:16:02
 * @description 流程管理Controller
 */
@Controller
@RequestMapping("/process")
public class ProcessController {

	/**
	 * @author XieXing
	 * @createDate 2019年4月21日 上午9:21:06
	 * @description 跳转到流程部署管理页面
	 * @return
	 */
	@RequestMapping("/deployManage")
	public String toDepoly() {
		return "process/deployManage";
	}
	
	/**
	 * @author XieXing
	 * @createDate 2019年4月21日 上午9:23:37
	 * @description 跳转到流程定义管理页面
	 * @return
	 */
	@RequestMapping("/definitionManage")
	public String toDefinition() {
		return "process/definitionManage";
	}
}
