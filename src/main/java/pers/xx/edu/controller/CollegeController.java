package pers.xx.edu.controller;

import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pers.xx.edu.entity.Faculty;
import pers.xx.edu.service.FacultyService;
import pers.xx.edu.utils.DateTimeUtils;
import pers.xx.edu.utils.Page;
import pers.xx.edu.utils.StringUtils;

/**
 * @author XieXing
 * @description 学院Controller
 * @create 2019年4月9日 上午9:57:22
 */
@Controller
@RequestMapping("/college")
public class CollegeController {

	@Autowired
	private FacultyService facultyService;

	/**
	 * @author XieXing
	 * @createDate 2019年4月9日 上午11:31:54
	 * @description 跳转到学院列表页面
	 */
	@RequestMapping("/toList")
	public String toList() {
		return "college/list";
	}

	/**
	 * @author XieXing
	 * @createDate 2019年4月9日 下午1:58:51
	 * @description 跳转到学院添加或修改或查询页面
	 */
	@RequestMapping("/toAdd")
	public String toAdd(Integer id, Map<String, Object> map, String operate) {
		if (id != null) {
			Faculty faculty = facultyService.getById(id);
			map.put("faculty", faculty);
		}
		map.put("operate", operate);
		return "college/add";
	}

	/**
	 * @author XieXing
	 * @createDate 2019年4月9日 下午3:12:33
	 * @description 学院信息编辑
	 */
	@RequestMapping("/edit")
	public String edit(Faculty faculty) {
		if (faculty.getId() == null) {
			faculty.setFacAddTime(new Date());//设置添加时间，不可修改
		}
		facultyService.saveOrUpdate(faculty);
		return "redirect:/college/toList";
	}

	/**
	 * @author XieXing
	 * @createDate 2019年4月9日 下午3:46:38
	 * @description 获取专业信息
	 */
	@RequestMapping("/getList")
	public void getCpList(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int rows,
			String content, String start, String end, HttpServletRequest request, PrintWriter out) {
		Map<String, Object> params = new LinkedHashMap<>();// 值参数
		params.put("page", page);
		params.put("rows", rows);
		if (StringUtils.isNotEmpty(start)) {
			try {
				params.put("facAddTime >= ?", DateTimeUtils.deal(start));
			} catch (ParseException e) {
				System.err.println("时间格式不正确！");
			}
		}
		if (StringUtils.isNotEmpty(end)) {
			try {
				params.put("facAddTime < ?", DateTimeUtils.deal(end));
			} catch (ParseException e) {
				System.err.println("时间格式不正确！");
			}
		}
		if (StringUtils.isNotEmpty(content)) {
			params.put(
					"(facNumber like :content or facName like :content or facRemark like :content)",
					"%" + content + "%");
		}
		Map<String, String> orderOrGroupBy = new HashMap<>();// 排序参数
		orderOrGroupBy.put("order by", "id desc");
		Page<Faculty> pageBean = new Page<>();
		pageBean = facultyService.getPageList(params, orderOrGroupBy);
		out.print(pageBean.toJqGridString());
		out.flush();
		out.close();
	}
}
