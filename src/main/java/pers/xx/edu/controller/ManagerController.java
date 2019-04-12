package pers.xx.edu.controller;

import java.io.PrintWriter;
import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pers.xx.edu.entity.Manager;
import pers.xx.edu.service.ManagerService;
import pers.xx.edu.utils.DateTimeUtils;
import pers.xx.edu.utils.Page;
import pers.xx.edu.utils.StringUtils;
import pers.xx.edu.vo.ManagerVo;

/**
 * @author XieXing
 * @description 学生Controller
 * @create 2019年3月17日 下午5:53:26
 */
@Controller
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	private ManagerService managerService;
	/**
	 * @author XieXing
	 * @create 2019年3月17日
	 * @description 跳转到管理员信息新增页面
	 * @return
	 */
	@RequestMapping("/toAdd")
	public String toAdd(Integer id, String operate, Map<String, Object> map) {
		if (id != null) {
			Manager manager = managerService.getById(id);
			map.put("manager", manager);
		}
		map.put("operate", operate);
		return "manager/add";
	}

	/**
	 * @author XieXing
	 * @create 2019年3月17日
	 * @description 跳转到管理员信息列表页面
	 * @return
	 */
	@RequestMapping("/toList")
	public String toList() {
		return "manager/list";
	}
	
	/**
	 * @author XieXing
	 * @createDate 2019年4月12日 下午3:47:33
	 * @description 获取管理员信息
	 * @param page
	 * @param rows
	 * @param content
	 * @param start
	 * @param end
	 * @param request
	 * @param out
	 */
	@RequestMapping("/getList")
	public void getCpList(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int rows,
			String content, String start, String end, HttpServletRequest request, PrintWriter out) {
		Map<String, Object> params = new LinkedHashMap<>();// 值参数
		params.put("page", page);
		params.put("rows", rows);
		if (StringUtils.isNotEmpty(start)) {
			try {
				params.put("managerEntranceDate >= ?", pers.xx.edu.utils.DateTimeUtils.deal(start));
			} catch (ParseException e) {
				System.err.println("时间格式不正确！");
			}
		}
		if (StringUtils.isNotEmpty(end)) {
			try {
				params.put("managerEntranceDate < ?", DateTimeUtils.deal(end));
			} catch (ParseException e) {
				System.err.println("时间格式不正确！");
			}
		}
		if (StringUtils.isNotEmpty(content)) {
			params.put(
					"(managerName like :content or managerNumber like :content or managerAddr like :content or managerId like :content or managerPhone like :content or managerEmail like :content or managerRemark like :content)",
					"%" + content + "%");
		}
		Map<String, String> orderOrGroupBy = new HashMap<>();// 排序参数
		orderOrGroupBy.put("order by", "id desc");
		Page<Manager> pageBean = new Page<>();
		pageBean = managerService.getPageList(params, orderOrGroupBy);
		out.print(pageBean.toJqGridString());
		out.flush();
		out.close();
	}

	/**
	 * @author XieXing
	 * @createDate 2019年4月12日 下午3:54:33
	 * @description 管理员信息编辑
	 * @param managerVo
	 * @param managerEntranceDate
	 * @param managerBirth
	 * @return
	 */
	@RequestMapping("/edit")
	public String edit(ManagerVo managerVo, String managerEntranceDate, String managerBirth) {
		managerService.edit(managerVo, managerEntranceDate, managerBirth);
		return "redirect:/manager/toList";
	}
}
