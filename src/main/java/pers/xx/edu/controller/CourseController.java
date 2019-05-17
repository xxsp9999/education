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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pers.xx.edu.entity.Course;
import pers.xx.edu.entity.StuCourse;
import pers.xx.edu.entity.Student;
import pers.xx.edu.entity.TeaCourse;
import pers.xx.edu.entity.Teacher;
import pers.xx.edu.service.CourseService;
import pers.xx.edu.service.StuCourseService;
import pers.xx.edu.service.TeaCourseService;
import pers.xx.edu.service.TeacherService;
import pers.xx.edu.utils.DateTimeUtils;
import pers.xx.edu.utils.Page;
import pers.xx.edu.utils.ResponseInfo;
import pers.xx.edu.utils.StringUtils;
import pers.xx.edu.utils.TermUtils;
import pers.xx.edu.vo.SelectedCourseVo;

/**
 * @author XieXing
 * @createDate 2019年5月2日 上午10:10:17
 * @description 课程信息Controller
 */
@Controller
@RequestMapping("/course")
public class CourseController {

	@Autowired
	private CourseService courseService;

	@Autowired
	private StuCourseService stuCourseService;

	@Autowired
	private TeaCourseService teaCourseService;

	@Autowired
	private TeacherService teacherService;

	/**
	 * @author XieXing
	 * @createDate 2019年5月2日 上午10:11:56
	 * @description 跳转到课程信息列表页面
	 * @return
	 */
	@RequestMapping("/toList")
	public String toList() {
		return "course/list";
	}

	/**
	 * @author XieXing
	 * @createDate 2019年5月2日 上午10:39:51
	 * @description 跳转到课程新增页面
	 * @param id
	 * @param map
	 * @param operate
	 * @return
	 */
	@RequestMapping("/toAdd")
	public String toAdd(Integer id, Map<String, Object> map, String operate) {
		if (id != null) {
			Course course = courseService.getById(id);
			map.put("course", course);
		}
		map.put("operate", operate);
		return "course/add";
	}

	/**
	 * @author XieXing
	 * @createDate 2019年5月2日 下午1:56:39
	 * @description 课程编辑
	 * @param course
	 * @return
	 */
	@RequestMapping("/edit")
	public String edit(Course course) {
		courseService.saveOrUpdate(course);
		return "redirect:/course/toList";
	}

	/**
	 * @author XieXing
	 * @createDate 2019年5月2日 下午2:01:42
	 * @description 获取课程信息
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
	public void getCourseList(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int rows,
			String content, String start, String end, Integer gradeId, Integer teaId, HttpServletRequest request,
			PrintWriter out) {
		Map<String, Object> params = new LinkedHashMap<>();// 值参数
		params.put("page", page);
		params.put("rows", rows);
		if (StringUtils.isNotEmpty(content)) {
			params.put("(cNo like :content or cName like :content or cScore like :content or cRemark like :content)",
					"%" + content + "%");
		}
		Map<String, String> orderOrGroupBy = new HashMap<>();// 排序参数
		orderOrGroupBy.put("order by", "id desc");
		Page<Course> pageBean = new Page<>();
		pageBean = courseService.getPageList(params, orderOrGroupBy);
		out.print(pageBean.toJqGridString());
		out.flush();
		out.close();
	}

	/**
	 * @author XieXing
	 * @createDate 2019年5月13日 下午5:27:32
	 * @description 跳转到学生学课页面
	 * @return
	 */
	@RequestMapping("/toStuChoose")
	public String toStuChoose() {
		return "course/stuchoose";
	}

	/**
	 * @author XieXing
	 * @createDate 2019年5月14日 下午3:02:21
	 * @description 获取选课列表
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
	@RequestMapping("/getChooseCourseList")
	public void getChooseCourseList(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int rows, String content, String start, String end, Integer gradeId,
			Integer teaId, HttpServletRequest request, PrintWriter out) {
		Map<String, Object> params = new LinkedHashMap<>();// 值参数
		params.put("page", page);
		params.put("rows", rows);
		if (StringUtils.isNotEmpty(content)) {
			params.put(
					"(tcTeacher.teaName like :content or tcCourse.cNo like :content or  tcCourse.cName like :content or tcCourse.cEnglishName like :content)",
					"%" + content + "%");
		}
		Map<String, String> orderOrGroupBy = new HashMap<>();// 排序参数
		orderOrGroupBy.put("order by", "id desc");
		Page<TeaCourse> pageBean = new Page<>();
		pageBean = teaCourseService.getPageList(params, orderOrGroupBy);
		out.print(pageBean.toJqGridString());
		out.flush();
		out.close();
	}

	/**
	 * @author XieXing
	 * @createDate 2019年5月14日 下午3:04:26
	 * @description 获取学生已选课程列表
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
	@RequestMapping("/getStuCourseList")
	public void getStuCourseList(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int rows, String content, String start, String end, Integer gradeId,
			Integer teaId, HttpServletRequest request, PrintWriter out) {
		Map<String, Object> params = new LinkedHashMap<>();// 值参数
		params.put("page", page);
		params.put("rows", rows);
		// 学生登陆，只能看见自己选择的课程
		if (StringUtils.isNotEmpty(start)) {
			try {
				params.put("scDate >= ?", DateTimeUtils.deal(start));
			} catch (ParseException e) {
				System.err.println("时间格式不正确");
			}
		}
		if (StringUtils.isNotEmpty(end)) {
			try {
				params.put("scDate < ?", DateTimeUtils.deal(end));
			} catch (ParseException e) {
				System.err.println("时间格式不正确");
			}
		}
		Student student = (Student) request.getSession().getAttribute("student");
		if (student != null) {
			params.put("scStudent.id = ?", student.getId());
		}
		if (StringUtils.isNotEmpty(content)) {
			params.put(
					"(scStudent.stuNumber like :content or scStudent.stuName like :content or scScore like :content or  scState like :content or scRemark like :content)",
					"%" + content + "%");
		}
		Map<String, String> orderOrGroupBy = new HashMap<>();// 排序参数
		orderOrGroupBy.put("order by", "id desc");
		Page<StuCourse> pageBean = new Page<>();
		pageBean = stuCourseService.getPageList(params, orderOrGroupBy);
		out.print(pageBean.toJqGridString());
		out.flush();
		out.close();
	}

	/**
	 * @author XieXing
	 * @createDate 2019年5月13日 下午5:29:12
	 * @description 跳转到学生的课程页面
	 * @return
	 */
	@RequestMapping("/toStuCourses")
	public String stuCourses() {
		return "course/stucourses";
	}

	/**
	 * @author XieXing
	 * @createDate 2019年5月13日 下午5:30:58
	 * @description 跳转到老师课程页面
	 * @return
	 */
	@RequestMapping("/teacourses")
	public String teaCourses() {
		return "course/teacourses";
	}

	/**
	 * @author XieXing
	 * @createDate 2019年5月14日 下午3:53:08
	 * @description 教师排课
	 * @return
	 */
	@RequestMapping("/courseschedule")
	public String courseSchedule() {
		return "course/teachoose";
	}

	/**
	 * @author XieXing
	 * @createDate 2019年5月15日 上午10:00:54
	 * @description 排课编辑
	 * @param courseId  课程id
	 * @param teacherId 教师id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/courseScheduleEdit")
	public Map<String, Object> courseScheduleEdit(Integer courseId, Integer teacherId, Integer capacity,
			String season) {
		Map<String, Object> map = new HashMap<>();
		TeaCourse teaCourse = teaCourseService.isScheduled(courseId, teacherId, season);// 根据课程id,老师id,季节，查看该老师是否已被安排该课程
		if (teaCourse != null) {
			map.put("msg", "该老师已被安排该课程，请到老师课程查看！");
		} else {
			teaCourse = new TeaCourse();
			Course course = courseService.getById(courseId);
			teaCourse.setTcCourse(course);
			Teacher teacher = teacherService.getById(teacherId);
			teaCourse.setTcTeacher(teacher);
			teaCourse.setTcCapacity(capacity);
			teaCourse.setTcAllowance(capacity);
			teaCourse.setTcSelected(0);
			teaCourse.setTcDate(new Date());
			teaCourse.setTcSeason(season);
			teaCourseService.save(teaCourse);
			map.put("msg", "排课成功，请到老师课程查看！");
		}
		return map;
	}

	/**
	 * @author XieXing
	 * @createDate 2019年5月15日 下午3:49:06
	 * @description 修改教师选课
	 * @param courseId
	 * @param teacherId
	 * @param capacity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/courseScheduleUpdate")
	public Map<String, Object> courseScheduleUpdate(Integer id, Integer teacherId, Integer capacity) {
		Map<String, Object> map = new HashMap<>();
		TeaCourse teaCourse = teaCourseService.getById(id);
		if (teacherId != null) {
			Teacher teacher = teacherService.getById(teacherId);
			teaCourse.setTcTeacher(teacher);
			teaCourse.setTcCapacity(capacity);
			if (capacity < teaCourse.getTcSelected()) {
				map.put("msg", "课程容量小于已选学生人数，请重新填写课程容量");
			} else {
				teaCourse.setTcAllowance(capacity - teaCourse.getTcSelected());
				map.put("msg", "修改成功");
			}
		} else {
			map.put("msg", "请选择老师");
		}
		teaCourseService.update(teaCourse);
		return map;
	}

	/**
	 * @author XieXing
	 * @createDate 2019年5月15日 下午3:34:11
	 * @description 根据id获取教师课程信息
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getTeaCourseById")
	public TeaCourse getTeaCourseById(Integer id) {
		TeaCourse teaCourse = teaCourseService.getById(id);
		return teaCourse;
	}

	/**
	 * @author XieXing
	 * @createDate 2019年5月15日 下午3:46:06
	 * @description 删除教师的选课
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteTeaCourseById")
	public ResponseInfo deleteTeaCourseById(String id) {
		Integer[] ids = StringUtils.getListInteger(id);
		// 先删除学生-课程信息
		for (Integer tmp : ids) {
			stuCourseService.deleteByTeaCourseIds(tmp);
		}
		teaCourseService.deleteByIds(ids);
		return new ResponseInfo(true, "删除成功！");
	}

	/**
	 * @author XieXing
	 * @createDate 2019年5月15日 下午5:24:40
	 * @description 学生选课
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/stuChoose")
	public Map<String, Object> stuChoose(String id, HttpSession session) {
		Integer[] ids = StringUtils.getListInteger(id);
		Student student = (Student) session.getAttribute("student");
		Map<String, Object> map = new HashMap<>();
		for (Integer tmp : ids) {
			StuCourse tmpStuCourse = stuCourseService.getByStuIdAndTeaCourseId(student.getId(), tmp);
			if (tmpStuCourse == null) {
				TeaCourse teaCourse = teaCourseService.getById(tmp);
				if (teaCourse.getTcAllowance() > 0) {
					teaCourse.setTcAllowance(teaCourse.getTcAllowance() - 1);
					teaCourse.setTcSelected(teaCourse.getTcSelected() + 1);
					teaCourseService.update(teaCourse);
					StuCourse stuCourse = new StuCourse();
					stuCourse.setScStudent(student);
					stuCourse.setScTeaCourse(teaCourse);
					List<String> list = TermUtils.getStuTermInfo(student.getStuEntranceDate());
					stuCourse.setScSeason(list.get(0));
					stuCourse.setScYear(list.get(1));
					stuCourse.setScTerm(list.get(2));
					stuCourse.setScDate(new Date());
					stuCourse.setScState("选中");
					stuCourseService.save(stuCourse);
				} else {
					if (map.get("msg") != null) {
						map.put("msg", map.get("msg") + "\n" + "对不起，课程" + teaCourse.getTcCourse().getcName() + "容量不足！");
					} else {
						map.put("msg", "对不起，课程" + teaCourse.getTcCourse().getcName() + "容量不足！");
					}

				}
			} else {
				map.put("msg", "你已经选择该课程，请到我的选课里查看");
			}
		}
		return map;
	}

	/**
	 * @author XieXing
	 * @createDate 2019年5月15日 下午9:18:54
	 * @description 确认此课程
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/stuChooseSure")
	public Map<String, Object> stuChooseSure(String id) {
		Map<String, Object> map = new HashMap<>();
		Integer[] ids = StringUtils.getListInteger(id);
		for (Integer tmp : ids) {
			StuCourse stuCourse = stuCourseService.getById(tmp);
			if (stuCourse.getScState().equals("选中")) {
				stuCourse.setScState("已确认");
			} else {
				if (map.get("msg") != null) {
					map.put("msg", map.get("msg") + "\n" + "课程" + stuCourse.getScTeaCourse().getTcCourse().getcName()
							+ "已确认!");
				} else {
					map.put("msg", "课程" + stuCourse.getScTeaCourse().getTcCourse().getcName() + "已确认!");
				}
			}
		}
		return map;
	}

	/**
	 * @author XieXing
	 * @createDate 2019年5月15日 下午9:32:51
	 * @description 取消已选择的课程
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/stuChooseDelete")
	public Map<String, Object> stuChooseDelete(String id) {
		Map<String, Object> map = new HashMap<>();
		Integer[] ids = StringUtils.getListInteger(id);
		for (Integer tmp : ids) {
			StuCourse stuCourse = stuCourseService.getById(tmp);
			if (!stuCourse.getScState().equals("选中")) {
				if (map.get("msg") != null) {
					map.put("msg", map.get("msg") + "\n" + "课程" + stuCourse.getScTeaCourse().getTcCourse().getcName()
							+ "已确认!不能取消！");
				} else {
					map.put("msg", "课程" + stuCourse.getScTeaCourse().getTcCourse().getcName() + "已确认!不能取消！");
				}

			} else {
				TeaCourse teaCourse = stuCourse.getScTeaCourse();
				teaCourse.setTcAllowance(teaCourse.getTcAllowance() + 1);
				teaCourse.setTcSelected(teaCourse.getTcSelected() - 1);
				stuCourseService.delete(stuCourse);
			}
		}
		return map;
	}

	/**
	 * @author XieXing
	 * @createDate 2019年5月16日 下午2:58:25
	 * @description 跳转到老师自己的课程列表
	 * @return
	 */
	@RequestMapping("/toTeacherSelfCourses")
	public String toTeacherSelfCourses() {
		return "course/teaSelfCourses";
	}

	/**
	 * @author XieXing
	 * @createDate 2019年5月16日 下午3:00:24
	 * @description 获取老师自己的课程列表
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
	@RequestMapping("/getTeacherSelfCoursesList")
	public void getTeacherSelfCoursesList(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int rows, String content, String start, String end, Integer gradeId,
			Integer teaId, HttpServletRequest request, PrintWriter out) {
		Map<String, Object> params = new LinkedHashMap<>();// 值参数
		params.put("page", page);
		params.put("rows", rows);
		Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
		if (teacher != null) {
			params.put("tcTeacher.id = ?", teacher.getId());
		}
		if (StringUtils.isNotEmpty(content)) {
			params.put(
					"(tcTeacher.teaName like :content or tcCourse.cNo like :content or  tcCourse.cName like :content or tcCourse.cEnglishName like :content)",
					"%" + content + "%");
		}
		Map<String, String> orderOrGroupBy = new HashMap<>();// 排序参数
		orderOrGroupBy.put("order by", "id desc");
		Page<TeaCourse> pageBean = new Page<>();
		pageBean = teaCourseService.getPageList(params, orderOrGroupBy);
		out.print(pageBean.toJqGridString());
		out.flush();
		out.close();
	}

	/**
	 * @author XieXing
	 * @createDate 2019年5月16日 下午3:05:46
	 * @description 跳转到选择我（老师）课程的学生页面
	 * @return
	 */
	@RequestMapping("/toStudentsOfMyCourse")
	public String toStudentsOfMyCourses() {
		return "course/studentsOfMyCourse";
	}

	/**
	 * @author XieXing
	 * @createDate 2019年5月16日 下午3:07:46
	 * @description 获取选我课程的学生数据
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
	@RequestMapping("/getStudentsOfMyCourseList")
	public void getStudentsOfMyCourseList(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int rows, String content, String start, String end, Integer gradeId,
			Integer teaId, HttpServletRequest request, PrintWriter out) {
		Map<String, Object> params = new LinkedHashMap<>();// 值参数
		params.put("page", page);
		params.put("rows", rows);
		if (StringUtils.isNotEmpty(start)) {
			try {
				params.put("scDate >= ?", DateTimeUtils.deal(start));
			} catch (ParseException e) {
				System.err.println("时间格式不正确");
			}
		}
		if (StringUtils.isNotEmpty(end)) {
			try {
				params.put("scDate < ?", DateTimeUtils.deal(end));
			} catch (ParseException e) {
				System.err.println("时间格式不正确");
			}
		}
		Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
		if (teacher != null) {
			params.put("scTeaCourse.tcTeacher.id = ?", teacher.getId());
		}

		if (StringUtils.isNotEmpty(content)) {
			params.put("(scStudent.stuNumber like :content or scStudent.stuName like :content)", "%" + content + "%");
		}
		Map<String, String> orderOrGroupBy = new HashMap<>();// 排序参数
		orderOrGroupBy.put("order by", "scTeaCourse,id desc");
		Page<StuCourse> pageBean = new Page<>();
		pageBean = stuCourseService.getPageList(params, orderOrGroupBy);
		out.print(pageBean.toJqGridString());
		out.flush();
		out.close();
	}

	/**
	 * @author XieXing
	 * @createDate 2019年5月16日 下午4:11:19
	 * @description 根据id获取学生选课
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getStuCourseById")
	public StuCourse getStuCourseById(Integer id) {
		return stuCourseService.getById(id);
	}

	/**
	 * @author XieXing
	 * @createDate 2019年5月16日 下午4:30:19
	 * @description 老师打分
	 * @param id
	 * @param score
	 * @param remark
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/stuCourseScore")
	public Map<String, Object> stuCourseScore(Integer id, Integer score, String remark) {
		Map<String, Object> map = new HashMap<>();
		StuCourse stuCourse = stuCourseService.getById(id);
		stuCourse.setScScore(score + "");
		if (score < 60) {
			stuCourse.setScState("未通过");
		} else {
			stuCourse.setScState("通过");
		}
		stuCourse.setScRemark(remark);
		stuCourseService.update(stuCourse);
		map.put("msg", "打分成功！");
		return map;
	}

	/**
	 * @author XieXing
	 * @createDate 2019年5月14日 下午3:02:21
	 * @description 获取选课列表
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
	@RequestMapping("/getStuChooseCourseList")
	public void getStuChooseCourseList(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int rows, String content, String start, String end, Integer gradeId,
			Integer teaId, HttpServletRequest request, PrintWriter out) {
		Map<String, Object> params = new LinkedHashMap<>();// 值参数
		params.put("page", page);
		params.put("rows", rows);
		params.put("tcSeason = ?", TermUtils.getSeasonInfo());
		if (StringUtils.isNotEmpty(content)) {
			params.put(
					"(tcTeacher.teaName like :content or tcCourse.cNo like :content or  tcCourse.cName like :content or tcCourse.cEnglishName like :content)",
					"%" + content + "%");
		}
		Map<String, String> orderOrGroupBy = new HashMap<>();// 排序参数
		orderOrGroupBy.put("order by", "id desc");
		Page<TeaCourse> pageBean = new Page<>();
		pageBean = teaCourseService.getPageList(params, orderOrGroupBy);
		out.print(pageBean.toJqGridString());
		out.flush();
		out.close();
	}

	/**
	 * @author XieXing
	 * @createDate 2019年5月16日 下午5:31:37
	 * @description 获取当前老师的所有课程
	 */
	@ResponseBody
	@RequestMapping("/getTeacherAllCourses")
	public List<SelectedCourseVo> getTeacherAllCourses(HttpSession session) {
		Teacher teacher = (Teacher) session.getAttribute("teacher");
		if (teacher != null) {
			List<SelectedCourseVo> selectedCourseVos = teaCourseService.getByTeacherId(teacher.getId());
			return selectedCourseVos;
		}
		return null;
	}

	/**
	 * @author XieXing
	 * @createDate 2019年5月16日 下午5:33:03
	 * @description 获取学生选择的所有课程
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getStudentAllCourses")
	public List<SelectedCourseVo> getStudentAllCourses(HttpSession session) {
		Map<String, Object> params = new HashMap<>();
		Student student = (Student) session.getAttribute("student");
		if (student != null) {
			params.put("student.id", student.getId());
			List<StuCourse> courses = stuCourseService.getList(params, null);
			List<SelectedCourseVo> courseVos = new ArrayList<>();
			for (StuCourse course : courses) {
				SelectedCourseVo courseVo = new SelectedCourseVo();
				courseVo.setId(course.getScTeaCourse().getId());
				courseVo.setName(course.getScTeaCourse().getTcCourse().getcName());
				courseVos.add(courseVo);
			}
			return courseVos;
		}
		return null;
	}

}
