package pers.xx.edu.controller;

import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pers.xx.edu.entity.EduClass;
import pers.xx.edu.service.EduClassService;
import pers.xx.edu.utils.Page;
import pers.xx.edu.utils.StringUtils;
import pers.xx.edu.vo.EduClassVo;


/**
 * @author XieXing
 * @createDate 2019年4月19日 下午4:13:00
 * @description 班级Controller
 */
@Controller
@RequestMapping("/educlass")
public class EduClassController {
	
	@Autowired
	private EduClassService eduClassService;
	
	/**
	 * @author XieXing
	 * @createDate 2019年4月19日 下午4:21:19
	 * @description 获取所有班级
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getAllClasses",produces="application/json;charset=utf-8")
	public List<EduClass> getAllClasses(Integer facId,Integer majId,Integer gradeId){
		Map<String, Object> params = new LinkedHashMap<>();
		if(gradeId!=null) {
			params.put("claGrade.id = ?",gradeId);
		}
		if(majId!=null) {
			params.put("claMajor.id = ?",majId);
			return eduClassService.getList(params, null);
		}
		if(majId == null && facId!=null) {
			params.put("claFaculty.id = ?",facId);
			return eduClassService.getList(params, null);
		}
		return eduClassService.getList(params, null);
	}
	
	/**
	 * @author XieXing
	 * @createDate 2019年5月2日 上午10:22:36
	 * @description 班级列表
	 * @return
	 */
	@RequestMapping("/toList")
	public String toList() {
		return "educlass/list";
	}
	
	/**
	 * @author XieXing
	 * @createDate 2019年5月2日 上午10:25:20
	 * @description 跳转到信息新增页面
	 * @param id
	 * @param map
	 * @param operate
	 * @return
	 */
	@RequestMapping("/toAdd")
	public String toAdd(Integer id, Map<String, Object> map, String operate) {
		if (id != null) {
			EduClass eduClass = eduClassService.getById(id);
			map.put("eduClass", eduClass);
		}
		map.put("operate", operate);
		return "educlass/add";
	}
	
	/**
	 * @author XieXing
	 * @createDate 2019年5月2日 上午11:28:32
	 * @description 编辑课程信息
	 * @param eduClassVo
	 * @param gradeId 年级id
	 * @param teaId 班主任id
	 * @return
	 */
	@RequestMapping("/edit")
	public String edit(EduClassVo eduClassVo,Integer gradeId,Integer teaId) {
		eduClassService.edit(eduClassVo,gradeId,teaId);
		return "redirect:/educlass/toList";
	}
	/**
	 * @author XieXing
	 * @createDate 2019年5月2日 上午10:41:23
	 * @description 获取班级信息
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
			String content, String start, String end,Integer gradeId,Integer teaId, HttpServletRequest request, PrintWriter out) {
		Map<String, Object> params = new LinkedHashMap<>();// 值参数
		params.put("page", page);
		params.put("rows", rows);
		if(gradeId!=null) {
			params.put("claGrade.id = ?",gradeId);
		}
		if(teaId!=null) {
			params.put("claHeadteacher.id = ?",teaId);
		}
		if (StringUtils.isNotEmpty(content)) {
			params.put(
					"(claName like :content or claNumber like :content or claRemark like :content)",
					"%" + content + "%");
		}
//		Map<String, String> orderOrGroupBy = new HashMap<>();// 排序参数
//		orderOrGroupBy.put("order by", "id desc");
		Page<EduClass> pageBean = new Page<>();
		pageBean = eduClassService.getPageList(params, null);
		out.print(pageBean.toJqGridString());
		out.flush();
		out.close();
	}
}
