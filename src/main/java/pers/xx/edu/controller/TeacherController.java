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

import pers.xx.edu.entity.Teacher;
import pers.xx.edu.service.TeacherService;
import pers.xx.edu.utils.DateTimeUtils;
import pers.xx.edu.utils.Page;
import pers.xx.edu.utils.StringUtils;
import pers.xx.edu.vo.TeacherVo;

/**
 * @author XieXing
 * @description 老师Controller
 * @create 2019年3月17日 下午6:05:01
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {
	@Autowired
	private TeacherService teacherService;
	/**
	 * @author XieXing
	 * @create 2019年3月17日
	 * @description 老师信息新增 
	 * @return
	 */
	@RequestMapping("/toAdd")
	public String toAdd(Integer id,String operate,Map<String, Object> map) {
		if(id!=null) {
			Teacher teacher = teacherService.getById(id);
			map.put("teacher", teacher);
		}
		map.put("operate", operate);
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

	/**
	 * @author XieXing
	 * @createDate 2019年4月12日 上午8:59:12
	 * @description 获取老师信息
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
				params.put("teaEntranceDate >= ?", pers.xx.edu.utils.DateTimeUtils.deal(start));
			} catch (ParseException e) {
				System.err.println("时间格式不正确！");
			}
		}
		if (StringUtils.isNotEmpty(end)) {
			try {
				params.put("teaEntranceDate < ?", DateTimeUtils.deal(end));
			} catch (ParseException e) {
				System.err.println("时间格式不正确！");
			}
		}
		if (StringUtils.isNotEmpty(content)) {
			params.put(
					"(teaName like :content or teaNumber like :content or teaId like :content or teaPhone like :content or teaEmail like :content or teaAddr like :content or teaRemark like :content)",
					"%" + content + "%");
		}
		Map<String, String> orderOrGroupBy = new HashMap<>();// 排序参数
		orderOrGroupBy.put("order by", "id desc");
		Page<Teacher> pageBean = new Page<>();
		pageBean = teacherService.getPageList(params, orderOrGroupBy);
		out.print(pageBean.toJqGridString());
		out.flush();
		out.close();
	}
	
	/**
	 * @author XieXing
	 * @createDate 2019年4月12日 上午9:52:33
	 * @description 编辑教师信息
	 * @param studentVo
	 * @param stuEntranceDate
	 * @param stuBirth
	 * @param facultyId
	 * @param majorId
	 * @return
	 */
	@RequestMapping("/edit")
	public String edit(TeacherVo teacherVo, String teaEntranceDate, String teaBirth, Integer teaTitleId) {
		teacherService.edit(teacherVo, teaEntranceDate, teaBirth, teaTitleId);
		return "redirect:/teacher/toList";
	}
}
