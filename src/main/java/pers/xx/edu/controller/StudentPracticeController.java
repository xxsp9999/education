package pers.xx.edu.controller;

import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import pers.xx.edu.entity.Student;
import pers.xx.edu.entity.StudentPractice;
import pers.xx.edu.service.StudentPracticeService;
import pers.xx.edu.utils.DateTimeUtils;
import pers.xx.edu.utils.JsonUtils;
import pers.xx.edu.utils.Page;
import pers.xx.edu.utils.StringUtils;
import pers.xx.edu.utils.UploadUtils;
import pers.xx.edu.vo.StudentPracticeVo;

/**
 * @author XieXing
 * @createDate 2019年5月25日 上午9:50:11
 * @description 学生活动Controller
 */
@Controller
@RequestMapping("/studentpractice")
public class StudentPracticeController {

	@Autowired
	private StudentPracticeService studentPracticeService;

	/**
	 * @author XieXing
	 * @createDate 2019年5月25日 上午9:57:16
	 * @description 跳转到学生活动列表
	 * @return
	 */
	@RequestMapping("/toList")
	public String toList() {
		return "studentpractice/list";
	}
	
	
	/**
	 * @author XieXing
	 * @createDate 2019年5月25日 上午9:57:47
	 * @description 跳转到学生活动列表页面
	 * @param id
	 * @param map
	 * @param operate
	 * @return
	 */
	@RequestMapping("/toAdd")
	public String toAdd(Integer id, Map<String, Object> map, String operate) {
		if (id != null) {
			StudentPractice studentPractice = studentPracticeService.getById(id);
			map.put("studentPractice", studentPractice);
		}
		map.put("operate", operate);
		return "studentpractice/add";
	}

	/**
	 * @author XieXing
	 * @createDate 2019年5月25日 上午9:58:35
	 * @description 保存或修改学生活动信息
	 * @return
	 */
	@RequestMapping("/edit")
	public String edit(StudentPracticeVo suPracticeVo, String date,
			@RequestParam(value = "file", required = false) CommonsMultipartFile file,HttpSession session) {
		Integer id = suPracticeVo.getId();
		StudentPractice studentPractice = null;
		if (id != null) {
			studentPractice = studentPracticeService.getById(id);
		} else {
			studentPractice = new StudentPractice();
			Student student = (Student) session.getAttribute("student");
			studentPractice.setPracticeStudent(student);
		}
		BeanUtils.copyProperties(suPracticeVo, studentPractice);
		if (date != null) {
			try {
				Date tmp = DateTimeUtils.deal(date);
				studentPractice.setPraceticeDate(tmp);
			} catch (ParseException e) {
				System.err.println("时间格式非法");
			}

		}
		if (file != null) {
			String path = UploadUtils.saveFile(file, session, id+"");
			studentPractice.setCertificatePath(path);
		}
		studentPracticeService.saveOrUpdate(studentPractice);
		return "redirect:/studentpractice/toList";
	}

	@RequestMapping("/getList")
	public void getstudentpracticeList(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int rows, String content, String start, String end, Integer gradeId,
			Integer teaId, HttpServletRequest request, PrintWriter out) {
		Map<String, Object> params = new LinkedHashMap<>();// 值参数
		params.put("page", page);
		params.put("rows", rows);
		if (StringUtils.isNotEmpty(content)) {
			params.put(
					"(itemName like :content or prizeSituation like :content or practiceScore like :content or checkState like :content or practiceRemark like :content)",
					"%" + content + "%");
		}
		Map<String, String> orderOrGroupBy = new HashMap<>();// 排序参数
		orderOrGroupBy.put("order by", "id desc");
		Page<StudentPractice> pageBean = new Page<>();
		pageBean = studentPracticeService.getPageList(params, orderOrGroupBy);
		out.print(pageBean.toJqGridString());
		out.flush();
		out.close();
	}
	
	/**
	 * @author XieXing
	 * @createDate 2019年5月29日 上午10:22:41
	 * @description 获取实践信息
	 * @param stuId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getPractice",produces = "application/json;charset=utf-8")
	public String getPractice(Integer stuId) {
		Map<String, Object> params = new HashMap<>();
		params.put("practiceStudent.id = ?",stuId);
		List<StudentPractice> list = studentPracticeService.getList(params, null);
		List<StudentPracticeVo> studentPracticeVos = new ArrayList<>();
		for(StudentPractice studentPractice:list) {
			StudentPracticeVo practiceVo = new StudentPracticeVo();
			BeanUtils.copyProperties(studentPractice, practiceVo);
			studentPracticeVos.add(practiceVo);
		}
		return JsonUtils.toJson(studentPracticeVos);
	}
}
