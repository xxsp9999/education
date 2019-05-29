package pers.xx.edu.controller;

import java.io.PrintWriter;
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
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import pers.xx.edu.entity.Attachment;
import pers.xx.edu.entity.Courseware;
import pers.xx.edu.entity.Student;
import pers.xx.edu.entity.TeaCourse;
import pers.xx.edu.entity.Teacher;
import pers.xx.edu.service.AttachmentService;
import pers.xx.edu.service.CoursewareService;
import pers.xx.edu.service.StuCourseService;
import pers.xx.edu.service.TeaCourseService;
import pers.xx.edu.utils.DeleteFileUtil;
import pers.xx.edu.utils.Page;
import pers.xx.edu.utils.StringUtils;
import pers.xx.edu.utils.UploadUtils;

/**
 * @author XieXing
 * @createDate 2019年5月18日 上午9:32:13
 * @description 课件列表控制器
 */
@Controller
@RequestMapping("/courseware")
public class CoursewareController {
	@Autowired
	private CoursewareService coursewareService;

	@Autowired
	private TeaCourseService teaCourseService;
	
	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	private StuCourseService stuCourseService;

	/**
	 * @author XieXing
	 * @createDate 2019年5月18日 上午9:33:21
	 * @description 跳转到课件列表页面
	 * @return
	 */
	@RequestMapping("/toList")
	public String toList() {
		return "courseware/list";
	}

	/**
	 * @author XieXing
	 * @createDate 2019年5月18日 上午9:34:57
	 * @description 跳转到课件编辑页面
	 * @param id
	 * @param map
	 * @param operate
	 * @return
	 */
	@RequestMapping("/toAdd")
	public String toAdd(Integer id, Map<String, Object> map, String operate) {
		if (id != null) {
			Courseware courseware = coursewareService.getById(id);
			map.put("courseware", courseware);
		}
		map.put("operate", operate);
		return "courseware/add";
	}
	
	/**
	 * @author XieXing
	 * @createDate 2019年5月19日 下午6:56:33
	 * @description 跳转到课件下载页面
	 * @param id
	 * @param map
	 * @param operate
	 * @return
	 */
	@RequestMapping("/toDownLoad")
	public String toDownLoad(Integer id, Map<String, Object> map) {
		if (id != null) {
			Courseware courseware = coursewareService.getById(id);
			map.put("courseware", courseware);
		}
		return "courseware/download";
	}

	/**
	 * @author XieXing
	 * @createDate 2019年5月18日 上午9:35:53
	 * @description 课件信息编辑
	 * @param course
	 * @return
	 */
	@RequestMapping("/edit")
	public String edit(Integer id, Integer teaCourseId,
			@RequestParam(value = "attachments", required = false) CommonsMultipartFile[] attachments, String explain,
			String csremark,HttpSession session) {
		/**
		 * 先保存上传课件的相关元素据
		 */
		Courseware courseware = null;
		if (id != null) {
			courseware = coursewareService.getById(id);
		} else {
			courseware = new Courseware();
		}
		if (teaCourseId != null) {
			TeaCourse teaCourse = teaCourseService.getById(teaCourseId);
			courseware.setCsTc(teaCourse);
		}
		courseware.setExplain(explain);
		courseware.setCsremark(csremark);
		coursewareService.saveOrUpdate(courseware);
		/**
		 * 将附件保存起来
		 */
		if (attachments != null) {
			for (int i = 0; i < attachments.length; i++) {
				if(!attachments[i].isEmpty()) {
					Attachment attachment = new Attachment();
					attachment.setCourseware(courseware);
					attachment.setFileName(attachments[i].getOriginalFilename());
					String savePath = UploadUtils.saveFile(attachments[i], session, i+"");
					
					attachment.setPath(savePath);
					attachment.setUploadTime(new Date());
					attachmentService.save(attachment);
				}
			}
		}
		return "redirect:/courseware/toList";
	}

	/**
	 * @author XieXing
	 * @createDate 2019年5月18日 上午9:41:32
	 * @description 获取课件列表信息
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
		Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
		if (teacher != null) {
			Integer[] temp = {-1};
			List<Integer> ids = teaCourseService.getIdsByTeacherId(teacher.getId());
			params.put("csTc.id in (:ids)", ids.size()>0?ids.toArray():temp);
		}
		if (StringUtils.isNotEmpty(content)) {
			params.put("(explain like :content or Csremark like :content)", "%" + content + "%");
		}
		Map<String, String> orderOrGroupBy = new HashMap<>();// 排序参数
		orderOrGroupBy.put("order by", "id desc");
		Page<Courseware> pageBean = new Page<>();
		pageBean = coursewareService.getPageList(params, orderOrGroupBy);
		out.print(pageBean.toJqGridString());
		out.flush();
		out.close();
	}
	
	/**
	 * @author XieXing
	 * @createDate 2019年5月19日 下午7:23:50
	 * @description 获取学生选课课件信息
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
	public void getStuCourseList(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int rows,
			String content, String start, String end, Integer gradeId, Integer teaId, HttpServletRequest request,
			PrintWriter out) {
		Map<String, Object> params = new LinkedHashMap<>();// 值参数
		params.put("page", page);
		params.put("rows", rows);
		Student student = (Student) request.getSession().getAttribute("student");
		if (student != null) {
			Integer[] temp = {-1};
			List<Integer> ids = stuCourseService.getTeaCourseIdsByStudentId(student.getId());
			params.put("csTc.id in (:ids)", ids.size()>0?ids.toArray():temp);
		}
		if (StringUtils.isNotEmpty(content)) {
			params.put("(explain like :content or Csremark like :content)", "%" + content + "%");
		}
		Map<String, String> orderOrGroupBy = new HashMap<>();// 排序参数
		orderOrGroupBy.put("order by", "id desc");
		Page<Courseware> pageBean = new Page<>();
		pageBean = coursewareService.getPageList(params, orderOrGroupBy);
		out.print(pageBean.toJqGridString());
		out.flush();
		out.close();
	}
	
	/**
	 * @author XieXing
	 * @createDate 2019年5月18日 下午4:36:43
	 * @description 根据课件上传元素据id获取附件
	 * @param coursewareId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getAttachments")
	public List<Attachment> getAttachments(Integer coursewareId){
		return attachmentService.getByCoursewareId(coursewareId);
	}
	
	/**
	 * @author XieXing
	 * @createDate 2019年5月18日 下午5:27:16
	 * @description 删除文件
	 * @param id
	 */
	@ResponseBody
	@RequestMapping("/deleteAttachment")
	public void deleteAttachment(Integer id){
		Attachment attachment = attachmentService.getById(id);
		DeleteFileUtil.deleteFile(attachment.getPath());
		attachmentService.delete(attachment);
	}
}
