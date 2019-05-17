package pers.xx.edu.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pers.xx.edu.entity.Leave;
import pers.xx.edu.service.InstructorService;
import pers.xx.edu.service.LeaveService;
import pers.xx.edu.service.ManagerService;
import pers.xx.edu.service.StudentService;
import pers.xx.edu.service.TeacherService;
import pers.xx.edu.utils.DateTimeUtils;
import pers.xx.edu.utils.JsonUtils;
import pers.xx.edu.utils.StringUtils;
import pers.xx.edu.vo.EChartsVo;
import pers.xx.edu.vo.EChartsVo2;

/**
 * @author XieXing
 * @createDate 2019年5月7日 下午2:58:21
 * @description 数据分析Controller
 */
@Controller
@RequestMapping("/datashow")
public class DataShowController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private InstructorService instructorService;

	@Autowired
	private ManagerService managerService;

	@Autowired
	private LeaveService leaveService;

	/**
	 * @author XieXing
	 * @createDate 2019年5月7日 下午3:01:02
	 * @description 获取各角色数据分结果
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getAllMemberAnalyseData")
	public Map<String, Object> getAllMembersData() {
		Map<String, Object> map = new HashMap<>();
		String[] roles = { "学生", "老师", "导员", "管理员" };
		Integer stuCount = studentService.getCount(null);
		Integer teaCount = teacherService.getCount(null);
		Integer insCount = instructorService.getCount(null);
		Integer manCount = managerService.getCount(null);
		Integer[] counts = { stuCount, teaCount, insCount, manCount };
		map.put("xData", roles);
		map.put("yData", counts);
		return map;
	}

	/**
	 * @author XieXing
	 * @createDate 2019年5月7日 下午4:40:59
	 * @description 获取学生地图数据，每个地方有多少人
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getStudentMapData")
	public String getStudentMapData() {
		List<EChartsVo2> eChartsVos = studentService.getStudentMapData();
		return JsonUtils.toJson(eChartsVos);
	}

	/**
	 * @author XieXing
	 * @createDate 2019年5月11日 上午10:52:04
	 * @description 获取请假相关数据
	 * @param start         开始时间
	 * @param end           结束时间
	 * @param facId         学院id
	 * @param majId         专业id
	 * @param gradeId       年级id
	 * @param eduClassId    班级id
	 * @param leaveInfoType 请假类型
	 * @param content       其他模糊搜索内容
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getStudentLeaveData")
	public Map<String, Object> getStudentLeaveData(String start, String end, Integer facId, Integer majId,
			Integer gradeId, Integer eduClassId, String leaveInfoType, String content) {
		Map<String, Object> params = new LinkedHashMap<>();
		if (StringUtils.isNotEmpty(start)) {
			try {
				params.put("leaveInfoTime >= ?", DateTimeUtils.deal(start));
			} catch (ParseException e) {
				System.err.println("时间格式不正确");
			}
		}
		if (StringUtils.isNotEmpty(end)) {
			try {
				params.put("leaveInfoTime < ?", DateTimeUtils.deal(end));
			} catch (ParseException e) {
				System.err.println("时间格式不正确");
			}
		}
		if (facId != null) {
			params.put("leaveInfoStu.stuFaculty.id = ?", facId);
		}
		if (majId != null) {
			params.put("leaveInfoStu.stuMajor.id = ?", majId);
		}
		if (gradeId != null) {
			params.put("leaveInfoStu.stuClass.claGrade.id = ?", gradeId);
		}
		if (eduClassId != null) {
			params.put("leaveInfoStu.stuClass.id = ?", eduClassId);
		}
		if (StringUtils.isNotEmpty(leaveInfoType)) {
			params.put("leaveInfoType = ?", leaveInfoType);
		}
		if (StringUtils.isNotEmpty(content)) {
			params.put("(leaveInfoStu.stuName like :content or leaveInfoStu.stuNumber like :content)",
					"%" + content + "%");
		}
		List<Leave> leaves = leaveService.getList(params, null);
		Map<String, Integer> map = new LinkedHashMap<>();
		Map<String, Integer> map2 = new HashMap<>();
		for (Leave leave : leaves) {
			String date = (leave.getLeaveInfoTime() + "").substring(0, 10);
			String type = leave.getLeaveInfoType();
			if (map.get(date) != null) {
				map.put(date, map.get(date) + 1);
			} else {
				map.put(date, 1);
			}
			if (map2.get(type) != null) {
				map2.put(type, map2.get(type) + 1);
			} else {
				map2.put(type, 1);
			}
		}
		List<EChartsVo> eChartsVos = new ArrayList<>();
		for (Map.Entry<String, Integer> entry : map2.entrySet()) {
			EChartsVo eChartsVo = new EChartsVo();
			eChartsVo.name = entry.getKey();
			eChartsVo.value = entry.getValue();
			eChartsVos.add(eChartsVo);
		}
		Map<String, Object> data = new HashMap<>();
		data.put("xData", map.keySet());
		data.put("yData", map.values());
		data.put("name", map2.keySet());
		data.put("data", eChartsVos);
		return data;
	}
}
