package pers.xx.edu.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pers.xx.edu.entity.Grade;
import pers.xx.edu.service.GradeService;
import pers.xx.edu.utils.Page;
import pers.xx.edu.utils.StringUtils;

/**
 * @author XieXing
 * @createDate 2019年5月2日 上午10:20:45
 * @description 年级Controller
 */
@Controller
@RequestMapping("/grade")
public class GradeController {
	@Autowired
	private GradeService gradeService;

	/**
	 * @author XieXing
	 * @createDate 2019年5月2日 上午10:22:05
	 * @description 年级列表
	 * @return
	 */
	@RequestMapping("/toList")
	public String toList() {
		return "grade/list";
	}

	/**
	 * @author XieXing
	 * @createDate 2019年5月2日 上午10:32:46
	 * @description 跳转到年级新增页面
	 * @param id
	 * @param map
	 * @param operate
	 * @return
	 */
	@RequestMapping("/toAdd")
	public String toAdd(Integer id, Map<String, Object> map, String operate) {
		if (id != null) {
			Grade grade = gradeService.getById(id);
			map.put("grade", grade);
		}
		map.put("operate", operate);
		return "grade/add";
	}

	/**
	 * @author XieXing
	 * @createDate 2019年5月2日 下午2:03:56
	 * @description 编辑年级信息
	 * @param grade
	 * @return
	 */
	@RequestMapping("/edit")
	public String edit(Grade grade) {
		gradeService.saveOrUpdate(grade);
		return "redirect:/grade/toList";
	}

	/**
	 * @author XieXing
	 * @createDate 2019年5月2日 下午2:05:26
	 * @description 获取年级信息
	 * @param page
	 * @param rows
	 * @param content
	 * @param start
	 * @param end
	 * @param gradeId
	 * @param teaId
	 * @param request
	 * @param out
	 */
	@RequestMapping("/getList")
	public void getCpList(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int rows,
			String content, String start, String end, Integer gradeId, Integer teaId, HttpServletRequest request,
			PrintWriter out) {
		Map<String, Object> params = new LinkedHashMap<>();// 值参数
		params.put("page", page);
		params.put("rows", rows);
		if (StringUtils.isNotEmpty(content)) {
			params.put("(gradeName like :content or gradeRemark like :content)", "%" + content + "%");
		}
		Map<String, String> orderOrGroupBy = new HashMap<>();// 排序参数
		orderOrGroupBy.put("order by", "id desc");
		Page<Grade> pageBean = new Page<>();
		pageBean = gradeService.getPageList(params, orderOrGroupBy);
		out.print(pageBean.toJqGridString());
		out.flush();
		out.close();
	}
	
	/**
	 * @author XieXing
	 * @createDate 2019年5月2日 下午3:25:16
	 * @description 获取所有的年级
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getAllGrades",produces="application/json;charset=utf-8")
	public List<Grade> getAllGrades(){
		return gradeService.getAll();
	}
}
