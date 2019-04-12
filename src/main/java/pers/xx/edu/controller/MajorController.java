package pers.xx.edu.controller;

import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pers.xx.edu.entity.Faculty;
import pers.xx.edu.entity.Major;
import pers.xx.edu.service.FacultyService;
import pers.xx.edu.service.MajorService;
import pers.xx.edu.utils.DateTimeUtils;
import pers.xx.edu.utils.Page;
import pers.xx.edu.utils.StringUtils;

/**
 * @author XieXing
 * @createDate 2019年4月9日 上午9:57:23
 * @description 专业Controller
 */
@Controller
@RequestMapping("/major")
public class MajorController {

	@Autowired
	private MajorService majorService;

	@Autowired
	private FacultyService facultyService;

	/**
	 * @author XieXing
	 * @createDate 2019年4月9日 下午2:22:22
	 * @description 跳转到专业列表页面
	 */
	@RequestMapping("/toList")
	public String toList() {
		return "major/list";
	}

	/**
	 * @author XieXing
	 * @createDate 2019年4月10日 下午1:58:51
	 * @description 跳转到专业添加或修改或查询页面
	 */
	@RequestMapping("/toAdd")
	public String toAdd(Integer id, Integer facId, Map<String, Object> map, String operate) {
		if (facId != null) {
			Faculty faculty = facultyService.getById(facId);
			map.put("faculty", faculty);// 添加学院信息
		}
		if (id != null) {
			Major major = majorService.getById(id);
			map.put("major", major);// 添加专业信息
		}
		map.put("operate", operate);// 添加操作类型
		return "major/add";
	}

	/**
	 * @author XieXing
	 * @createDate 2019年4月10日 下午3:12:33
	 * @description 专业信息编辑
	 */
	@RequestMapping("/edit")
	public String edit(Major major, Integer facId) {
		if (facId != null) {// 设置学院
			Faculty faculty = facultyService.getById(facId);
			major.setMajorOfFaculty(faculty);
		}
		if (major.getId() == null) {// 新增
			major.setMajAddTime(new Date());// 设置添加时间，不可修改
			majorService.save(major);
		} else {// 修改
			Major major2 = majorService.getById(major.getId());
			Date date = major2.getMajAddTime();
			BeanUtils.copyProperties(major, major2);
			major2.setMajAddTime(date);
		}
		return "redirect:/college/toList";
	}

	/**
	 * @author XieXing
	 * @createDate 2019年4月9日 下午3:46:38
	 * @description 获取专业信息
	 */
	@RequestMapping("/getList")
	public void getCpList(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int rows,
			String content, String start, String end, HttpServletRequest request, PrintWriter out, Integer facId) {
		Map<String, Object> params = new LinkedHashMap<>();// 值参数
		params.put("page", page);
		params.put("rows", rows);
		params.put("majorOfFaculty.id = ?", facId);
		if (StringUtils.isNotEmpty(start)) {
			try {
				params.put("majAddTime >= ?", DateTimeUtils.deal(start));
			} catch (ParseException e) {
				System.err.println("时间格式不正确！");
			}
		}
		if (StringUtils.isNotEmpty(end)) {
			try {
				params.put("majAddTime < ?", DateTimeUtils.deal(end));
			} catch (ParseException e) {
				System.err.println("时间格式不正确！");
			}
		}
		if (StringUtils.isNotEmpty(content)) {
			params.put("(majorNumber like :content or majorNumber like :content or marjorRemark like :content)",
					"%" + content + "%");
		}
		Map<String, String> orderOrGroupBy = new HashMap<>();// 排序参数
		orderOrGroupBy.put("order by", "id desc");
		Page<Major> pageBean = new Page<>();
		pageBean = majorService.getPageList(params, orderOrGroupBy);
		out.print(pageBean.toJqGridString());
		out.flush();
		out.close();
	}
	
	/**
	 * @author XieXing
	 * @createDate 2019年4月11日 下午2:30:40
	 * @description 根据学院id获取相应专业
	 * @param facId 学院id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getMajorsByFacultyId")
	public List<Major> getFaculties(Integer facId){
		List<Major> majors = majorService.getMajorsByFacultyId(facId);
		return majors;
	}
}
