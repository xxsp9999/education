package pers.xx.edu.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import pers.xx.edu.entity.Student;
import pers.xx.edu.service.StudentService;
import pers.xx.edu.utils.Page;
import pers.xx.edu.utils.StringUtils;
import pers.xx.edu.vo.StudentVo;

/**
 * @author XieXing
 * @description 学生Controller
 * @create 2019年3月17日 下午5:53:26
 */
@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	/**
	 * @author XieXing
	 * @create 2019年3月17日
	 * @description 跳转到学生信息新增页面
	 * @return
	 */
	@RequestMapping("/toAdd")
	public String toAdd(Integer id, String operate, Map<String, Object> map) {
		if (id != null) {
			Student student = studentService.getById(id);
			map.put("student", student);
		}
		map.put("operate", operate);
		return "student/add";
	}

	/**
	 * @author XieXing
	 * @create 2019年3月17日
	 * @description 跳转到学生信息列表页面
	 * @return
	 */
	@RequestMapping("/toList")
	public String toList() {
		return "student/list";
	}

	/**
	 * @author XieXing
	 * @create 2019年4月8日
	 * @description 获取学生信息
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
			String content, String start, String end,Integer facultyId,Integer majorId,Integer classId,Integer gradeId,String nationalId,String sex, HttpServletRequest request, PrintWriter out) {
		Map<String, Object> params = new LinkedHashMap<>();// 值参数
		params.put("page", page);
		params.put("rows", rows);
		//时间查询
		/*if (StringUtils.isNotEmpty(start)) {
			try {
				params.put("stuEntranceDate >= ?", pers.xx.edu.utils.DateTimeUtils.deal(start));
			} catch (ParseException e) {
				System.err.println("时间格式不正确！");
			}
		}
		if (StringUtils.isNotEmpty(end)) {
			try {
				params.put("stuEntranceDate < ?", DateTimeUtils.deal(end));
			} catch (ParseException e) {
				System.err.println("时间格式不正确！");
			}
		}*/
		
		if(facultyId!=null) {
			params.put("stuFaculty.id = ?", facultyId);
		}
		if(classId!=null) {
			params.put("stuClass.id = ?", classId);
		}
		if(gradeId!=null) {
			params.put("stuClass.claGrade.id = ?", gradeId);
		}
		if(StringUtils.isNotEmpty(nationalId)) {
			params.put("stuNationality = ?", nationalId);
		}
		if(StringUtils.isNotEmpty(sex)) {
			params.put("stuSex = ?", sex);
		}
		if (StringUtils.isNotEmpty(content)) {
			params.put(
					"(stuName like :content or stuNumber like :content or stuId like :content or stuPhone like :content or stuEmail like :content or stuAddr like :content or stuRemark like :content)",
					"%" + content + "%");
		}
		Map<String, String> orderOrGroupBy = new HashMap<>();// 排序参数
		orderOrGroupBy.put("order by", "id asc");
		Page<Student> pageBean = new Page<>();
		pageBean = studentService.getPageList(params, orderOrGroupBy);
		out.print(pageBean.toJqGridString());
		out.flush();
		out.close();
	}

	/**
	 * @author XieXing
	 * @createDate 2019年4月11日 下午3:32:42
	 * @description 编辑学生信息
	 * @param studentVo
	 * @param stuEntranceDate
	 * @param stuBirth
	 * @param facultyId
	 * @param majorId
	 * @return
	 */
	@RequestMapping("/edit")
	public String edit(StudentVo studentVo, String stuEntranceDate, String stuBirth, Integer facultyId,
			Integer majorId,Integer stuClassId,Integer provinceId,Integer cityId,Integer countyId,@RequestParam(value="img",required=false)CommonsMultipartFile img,HttpSession session) {
		studentService.edit(studentVo, stuEntranceDate, stuBirth, facultyId, majorId,stuClassId,provinceId,cityId,countyId,img,session);
		return "redirect:/student/toList";
	}
	
	/**
	 * @author XieXing
	 * @createDate 2019年4月30日 上午10:31:35
	 * @description 跳转到学生数据展示页面
	 * @return
	 */
	@RequestMapping("/toStudentDataShow")
	public String toStudentDataShow() {
		return "datashow/student";
	}
	
	/**
	 * @author XieXing
	 * @createDate 2019年4月30日 上午10:35:14
	 * @description 根据条件获取学生数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getStudentAnalyseData",produces="application/json;charset=utf-8")
	public Map<String, Object> getStudentAnalyseData(Integer facId,Integer majId,Integer stuClassId){
		return studentService.getStudentAnalyseData(facId,majId,stuClassId);
	}
	
}
