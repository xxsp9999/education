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

import pers.xx.edu.entity.Leader;
import pers.xx.edu.service.LeaderService;
import pers.xx.edu.utils.DateTimeUtils;
import pers.xx.edu.utils.Page;
import pers.xx.edu.utils.StringUtils;
import pers.xx.edu.vo.LeaderVo;

/**
 * @author XieXing
 * @description
 * @create 2019年3月17日 下午6:07:58
 */
@Controller
@RequestMapping("/leader")
public class LeaderController {
	@Autowired
	private LeaderService leaderService;

	/**
	 * @author XieXing
	 * @create 2019年3月17日
	 * @description 跳转到领导信息新增页面
	 * @return
	 */
	@RequestMapping("/toAdd")
	public String toAdd(Integer id, String operate, Map<String, Object> map) {
		if (id != null) {
			Leader leader = leaderService.getById(id);
			map.put("leader", leader);
		}
		map.put("operate", operate);
		return "leader/add";
	}

	/**
	 * @author XieXing
	 * @create 2019年3月17日
	 * @description 跳转到领导信息列表页面
	 * @return
	 */
	@RequestMapping("/toList")
	public String toList() {
		return "leader/list";
	}

	/**
	 * @author XieXing
	 * @createDate 2019年4月12日 下午2:11:35
	 * @description 获取领导信息
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
				params.put("leaderEntranceDate >= ?", pers.xx.edu.utils.DateTimeUtils.deal(start));
			} catch (ParseException e) {
				System.err.println("时间格式不正确！");
			}
		}
		if (StringUtils.isNotEmpty(end)) {
			try {
				params.put("leaderEntranceDate < ?", DateTimeUtils.deal(end));
			} catch (ParseException e) {
				System.err.println("时间格式不正确！");
			}
		}
		if (StringUtils.isNotEmpty(content)) {
			params.put(
					"(leaderName like :content or leaderNumber like :content or leaderEmail like :content or leaderPhone like :content or leaderAddr like :content or leaderId like :content or leaderRemark like :content)",
					"%" + content + "%");
		}
		Map<String, String> orderOrGroupBy = new HashMap<>();// 排序参数
		orderOrGroupBy.put("order by", "id desc");
		Page<Leader> pageBean = new Page<>();
		pageBean = leaderService.getPageList(params, orderOrGroupBy);
		out.print(pageBean.toJqGridString());
		out.flush();
		out.close();
	}

	/**
	 * @author XieXing
	 * @createDate 2019年4月12日 下午3:49:48
	 * @description 编辑领导信息
	 * @param leaderVo
	 * @param leaderEntranceDate
	 * @param leaderBirth
	 * @param leaderTitle
	 * @return
	 */
	@RequestMapping("/edit")
	public String edit(LeaderVo leaderVo, String leaderEntranceDate, String leaderBirth, Integer leaderTitle) {
		leaderService.edit(leaderVo, leaderEntranceDate, leaderBirth, leaderTitle);
		return "redirect:/leader/toList";
	}
}
