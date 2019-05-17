package pers.xx.edu.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import pers.xx.edu.entity.Leave;
import pers.xx.edu.entity.Student;
import pers.xx.edu.service.LeaveService;
import pers.xx.edu.utils.DateJsonValueProcessor;
import pers.xx.edu.utils.ResponseInfo;
import pers.xx.edu.utils.ResponseUtils;
import pers.xx.edu.vo.LeaveInfoVo;

/**
 * @author XieXing
 * @createDate 2019年4月18日 上午10:13:44
 * @description 请假Controller
 */
@Controller
@RequestMapping("/leave")
public class LeaveController {
	
	@Autowired
	private LeaveService leaveService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private TaskService taskService;
	
	/**
	 * @author XieXing
	 * @createDate 2019年4月18日 上午10:15:38
	 * @description 跳转到请假申请列表页面
	 * @return
	 */
	@RequestMapping("/applyList")
	public String applyList() {
		return "leave/applyList";
	}

	/**
	 * @author XieXing
	 * @createDate 2019年4月18日 上午10:16:46
	 * @description 跳转到请假审核列表页面
	 * @return
	 */
	@RequestMapping("/checkList")
	public String checkList() {
		return "leave/checkList";
	}
	
	/**
	 * @author XieXing
	 * @createDate 2019年4月21日 下午3:22:45
	 * @description 保存请假信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/save",produces="application/json;charset=utf-8")
	public ResponseInfo save(LeaveInfoVo leaveInfoVo,HttpSession session) {
		Leave leave = null;
		Integer id = leaveInfoVo.getId();
		if(id!=null) {
			leave = leaveService.getById(id);
			
		}else {
			leave = new Leave();
			Student student = (Student) session.getAttribute("student");
			leave.setLeaveInfoTime(new Date());
			leave.setLeaveInfoStu(student);
		}
		BeanUtils.copyProperties(leaveInfoVo, leave);
		leaveService.saveOrUpdate(leave);
		return new ResponseInfo(true,"保存成功！");
	}

	/**
	 * @author XieXing
	 * @createDate 2019年4月21日 下午3:40:31
	 * @description 提交请假申请
	 * @param response
	 * @param leaveId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/startApply",produces="application/json;charset=utf-8")
	public ResponseInfo startApply(HttpServletResponse response,Integer leaveId){
		//定义流程变量
		Map<String,Object> variables=new HashMap<String,Object>();
		Leave leave = leaveService.getById(leaveId);
		//String key = leave.getClass().getSimpleName();//获取流程启动key（前提，流程定义的key要和业务实体名称对应）
		variables.put("studentId", leave.getLeaveInfoStu().getId());//用来获取业务实例id
		variables.put("leaveId", leaveId);//用来获取业务实例id
		// 启动流程
		ProcessInstance pi= runtimeService.startProcessInstanceByKey("leave",variables); 
		// 根据流程实例Id查询任务
		Task task=taskService.createTaskQuery().processInstanceId(pi.getProcessInstanceId()).singleResult(); 
		 // 完成 学生填写请假单任务		
		taskService.complete(task.getId()); 
		//修改状态
		leave.setLeaveInfoState("审核中");
		leave.setProcessInstanceId(pi.getProcessInstanceId());
		 // 修改请假单状态
		leaveService.update(leave);
		return new ResponseInfo(true,"提交成功！");
	}
	
	
	/**
	 * @author XieXing
	 * @createDate 2019年4月21日 下午3:52:38
	 * @description
	 * @param response
	 * @param page
	 * @param rows
	 * @param userId
	 * @return 分页获取请假数据
	 * @throws Exception
	 */
	@RequestMapping("/leavePage")
	public String leavePage(HttpServletResponse response, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int rows,HttpSession session) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("page", page);
		params.put("rows", rows);
		params.put("1 = ?",1);//去除代码问题
		Student student = (Student) session.getAttribute("student");
		if(student !=null) {
			params.put("leaveInfoStu.id = ?",student.getId());
		}
		List<Leave> leaveList = leaveService.getList(params, null);
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd hh:mm:ss"));
		JSONObject result=new JSONObject();
		JSONArray jsonArray=JSONArray.fromObject(leaveList,jsonConfig);
		result.put("rows", jsonArray);
		result.put("total", leaveList.size());
		ResponseUtils.write(response, result);
		return null;
	}
	
	/**
	 * @author XieXing
	 * @createDate 2019年5月11日 上午9:23:26
	 * @description 跳转到学生请假统计页面
	 * @return
	 */
	@RequestMapping("/toStudentLeaveData")
	public String toStudentLeaveData() {
		return "datashow/leave";
	}
}
