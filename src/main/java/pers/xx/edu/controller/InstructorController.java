package pers.xx.edu.controller;

import java.io.PrintWriter;
import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import pers.xx.edu.entity.Instructor;
import pers.xx.edu.service.InstructorService;
import pers.xx.edu.utils.DateTimeUtils;
import pers.xx.edu.utils.Page;
import pers.xx.edu.utils.StringUtils;
import pers.xx.edu.vo.InstructorVo;

/**
 * @author XieXing
 * @description 学生Controller
 * @create 2019年3月17日 下午5:53:26
 */
@Controller
@RequestMapping("/instructor")
public class InstructorController {
	@Autowired
	private InstructorService instructorService;

//	@Autowired
//	private GradeService gradeService;
//	
//	@Autowired 
//	private InsGradeService insGradeService;
	/**
	 * @author XieXing
	 * @create 2019年3月17日
	 * @description 跳转到导员信息新增页面
	 * @return
	 */
	@RequestMapping("/toAdd")
	public String toAdd(Integer id, String operate, Map<String, Object> map) {
		if (id != null) {
			Instructor instructor = instructorService.getById(id);
			map.put("instructor", instructor);
		}
		map.put("operate", operate);
		return "instructor/add";
	}

	/**
	 * @author XieXing
	 * @create 2019年3月17日
	 * @description 跳转到学生信息列表页面
	 * @return
	 */
	@RequestMapping("/toList")
	public String toList() {
		return "instructor/list";
	}

	/**
	 * @author XieXing
	 * @createDate 2019年4月12日 上午11:11:31
	 * @description 获取导员信息
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
			String content, String start, String end, Integer facId, Integer gradeId, HttpServletRequest request,
			PrintWriter out) {
		Map<String, Object> params = new LinkedHashMap<>();// 值参数
		params.put("page", page);
		params.put("rows", rows);
		if (StringUtils.isNotEmpty(start)) {
			try {
				params.put("instructorEntranceDate >= ?", pers.xx.edu.utils.DateTimeUtils.deal(start));
			} catch (ParseException e) {
				System.err.println("时间格式不正确！");
			}
		}
		if (StringUtils.isNotEmpty(end)) {
			try {
				params.put("instructorEntranceDate < ?", DateTimeUtils.deal(end));
			} catch (ParseException e) {
				System.err.println("时间格式不正确！");
			}
		}
		if (facId != null) {
			params.put("instructorFaculty.id = ?", facId);
		}
		/*if(gradeId!=null) {
			params.put("instructorFaculty.id = ?", facId);
		}*/
		if (StringUtils.isNotEmpty(content)) {
			params.put(
					"(instructorName like :content or instructorNumber like :content or instructorId like :content or instructorAddr like :content or instructorPhone like :content or instructorEmail like :content or instructorRemark like :content)",
					"%" + content + "%");
		}
		Map<String, String> orderOrGroupBy = new HashMap<>();// 排序参数
		orderOrGroupBy.put("order by", "id desc");
		Page<Instructor> pageBean = new Page<>();
		pageBean = instructorService.getPageListNew(params, orderOrGroupBy);
		out.print(pageBean.toJqGridString());
		out.flush();
		out.close();
	}

	/**
	 * @author XieXing
	 * @createDate 2019年4月12日 上午11:18:49
	 * @description 导员信息编辑
	 * @param instructorVo
	 * @param instructorEntranceDate
	 * @param instructorBirth
	 * @return
	 */
	@RequestMapping("/edit")
	public String edit(InstructorVo instructorVo, Integer facultyId, String instructorEntranceDate,
			String instructorBirth, @RequestParam(value = "img", required = false) CommonsMultipartFile img,
			HttpSession session) {
		instructorService.edit(instructorVo, facultyId, instructorEntranceDate, instructorBirth, img, session);
		return "redirect:/instructor/toList";
	}

	/**
	 * @author XieXing
	 * @createDate 2019年5月7日 上午8:11:06
	 * @description 导员-班级插入测试
	 * @return
	 */
//	@ResponseBody
//	@RequestMapping("/insertInsGradeTest")
//	public ResponseInfo saveInsGrade() {
//		Instructor instructor = instructorService.getById(1);
//		Grade grade = gradeService.getById(1);
//		InsGrade insGrade = new InsGrade();
//		insGrade.setIgInstructor(instructor);
//		insGrade.setIgGrade(grade);
//		insGradeService.save(insGrade);
//		return new ResponseInfo(true,"插入成功！");
//	}
}
