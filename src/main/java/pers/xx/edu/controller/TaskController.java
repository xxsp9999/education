package pers.xx.edu.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import pers.xx.edu.entity.Instructor;
import pers.xx.edu.entity.Leave;
import pers.xx.edu.entity.Student;
import pers.xx.edu.entity.Teacher;
import pers.xx.edu.service.LeaveService;
import pers.xx.edu.utils.DateJsonValueProcessor;
import pers.xx.edu.utils.LoginInfo;
import pers.xx.edu.utils.MyTask;
import pers.xx.edu.utils.PageInfo;
import pers.xx.edu.utils.ResponseUtils;

/**
 * @author XieXing
 * @createDate 2019年4月23日 上午11:09:42
 * @description 历史流程批注管理
 */
@Controller
@RequestMapping("/task")
public class TaskController {

	// 引入activiti自带的Service接口
	@Autowired
	private TaskService taskService;

	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private FormService formService;

	@Autowired
	private LeaveService leaveService;

	@Autowired
	private HistoryService historyService;

	/**
	 * @author XieXing
	 * @createDate 2019年4月23日 上午11:14:52
	 * @description 查询历史流程批注
	 * @param response
	 * @param processInstanceId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/listHistoryCommentWithProcessInstanceId")
	public String listHistoryCommentWithProcessInstanceId(HttpServletResponse response, String processInstanceId)
			throws Exception {
		if (processInstanceId == null) {
			return null;
		}
		List<Comment> commentList = taskService.getProcessInstanceComments(processInstanceId);
		// 改变顺序，按原顺序的反向顺序返回list
		Collections.reverse(commentList); // 集合元素反转
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class,
				// 时间格式转换
				new DateJsonValueProcessor("yyyy-MM-dd hh:mm:ss"));
		JSONObject result = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(commentList, jsonConfig);
		result.put("rows", jsonArray);
		ResponseUtils.write(response, result);
		return null;
	}

	/**
	 * @author XieXing
	 * @createDate 2019年4月23日 上午11:15:32
	 * @description 重定向审核处理页面
	 * @param taskId
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/redirectPage")
	public String redirectPage(String taskId, HttpServletResponse response) throws Exception {
		TaskFormData formData = formService.getTaskFormData(taskId);
		String url = formData.getFormKey();
		System.out.println("*********************" + url);
		JSONObject result = new JSONObject();
		result.put("url", url);
		ResponseUtils.write(response, result);
		return null;
	}

	/**
	 * @author XieXing
	 * @createDate 2019年4月23日 上午11:15:45
	 * @description 待办流程分页查询
	 * @param response
	 * @param page
	 * @param rows
	 * @param s_name
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/taskPage")
	public String taskPage(HttpServletResponse response, String page, String rows, HttpSession session, String userId)
			throws Exception {
		String assignee = null;

		Instructor instructor = (Instructor) session.getAttribute("instructor");
		Teacher teacher = (Teacher) session.getAttribute("teacher");
		if (instructor != null) {
			assignee = instructor.getInstructorNumber();
		}
		if (teacher != null) {
			assignee = teacher.getTeaNumber();
		}
		PageInfo pageInfo = new PageInfo();
		Integer pageSize = Integer.parseInt(rows);
		pageInfo.setPageSize(pageSize);
		if (page == null || page.equals("")) {
			page = "1";
		}
		pageInfo.setPageIndex((Integer.parseInt(page) - 1) * pageInfo.getPageSize());
		long total = 0;
		List<Task> taskList = null;
		if (assignee != null) {
			// 获取总记录数
			total = taskService.createTaskQuery().taskAssignee(assignee).count(); // 获取总记录数
			taskList = taskService.createTaskQuery().taskAssignee(assignee)
					// 返回带分页的结果集合
					.listPage(pageInfo.getPageIndex(), pageInfo.getPageSize());
		} else {
			total = taskService.createTaskQuery().count(); // 获取总记录数
			taskList = taskService.createTaskQuery()
					// 返回带分页的结果集合
					.listPage(pageInfo.getPageIndex(), pageInfo.getPageSize());
		}
		// 这里需要使用一个工具类来转换一下主要是转成JSON格式
		List<MyTask> MyTaskList = new ArrayList<MyTask>();
		for (Task t : taskList) {
			MyTask myTask = new MyTask();
			myTask.setId(t.getId());
			myTask.setName(t.getName());
			myTask.setCreateTime(t.getCreateTime());
			MyTaskList.add(myTask);
		}
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd hh:mm:ss"));
		JSONObject result = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(MyTaskList, jsonConfig);
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtils.write(response, result);
		return null;
	}

	/**
	 * @author XieXing
	 * @createDate 2019年4月23日 上午11:16:04
	 * @description 查询当前流程图
	 * @param response
	 * @param taskId
	 * @return
	 */
	@RequestMapping("/showCurrentView")
	public String showCurrentView(HttpServletResponse response, String taskId, Map<String, Object> params) {
		// 视图

		Task task = taskService.createTaskQuery() // 创建任务查询
				.taskId(taskId) // 根据任务id查询
				.singleResult();
		// 获取流程定义id
		String processDefinitionId = task.getProcessDefinitionId();
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery() // 创建流程定义查询
				// 根据流程定义id查询
				.processDefinitionId(processDefinitionId).singleResult();
		// 部署id
		params.put("deploymentId", processDefinition.getDeploymentId());
		params.put("diagramResourceName", processDefinition.getDiagramResourceName());
		ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) repositoryService
				.getProcessDefinition(processDefinitionId);
		// 获取流程实例id
		String processInstanceId = task.getProcessInstanceId();
		// 根据流程实例id获取流程实例
		ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId)
				.singleResult();

		// 根据活动id获取活动实例
		ActivityImpl activityImpl = processDefinitionEntity.findActivity(pi.getActivityId());
		// 整理好View视图返回到显示页面
		params.put("x", activityImpl.getX());
		params.put("y", activityImpl.getY());
		params.put("width", activityImpl.getWidth());
		params.put("height", activityImpl.getHeight());
		return "page/currentView";
	}

	/**
	 * @author XieXing
	 * @createDate 2019年4月23日 上午11:16:16
	 * @description 查询历史批注
	 * @param response
	 * @param taskId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/listHistoryComment")
	public String listHistoryComment(HttpServletResponse response, String taskId) throws Exception {
		if (taskId == null) {
			taskId = "";
		}
		HistoricTaskInstance hti = historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
		JsonConfig jsonConfig = new JsonConfig();
		JSONObject result = new JSONObject();
		List<Comment> commentList = null;
		if (hti != null) {
			commentList = taskService.getProcessInstanceComments(hti.getProcessInstanceId());
			// 集合元素反转
			Collections.reverse(commentList);

			// 日期格式转换
			jsonConfig.registerJsonValueProcessor(java.util.Date.class,
					new DateJsonValueProcessor("yyyy-MM-dd hh:mm:ss"));
		}
		JSONArray jsonArray = JSONArray.fromObject(commentList, jsonConfig);
		result.put("rows", jsonArray);
		ResponseUtils.write(response, result);
		return null;
	}

	/**
	 * 审批
	 * 
	 * @param taskId    任务id
	 * @param leaveDays 请假天数
	 * @param comment   批注信息
	 * @param state     审核状态 1 通过 2 驳回
	 * @param response
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/audit_bz")
	public String audit_bz(String taskId, Integer leaveDays, String comment, Integer state,
			HttpServletResponse response, HttpSession session) throws Exception {
		// 首先根据ID查询任务
		Task task = taskService.createTaskQuery() // 创建任务查询
				.taskId(taskId) // 根据任务id查询
				.singleResult();
		Map<String, Object> variables = new HashMap<String, Object>();
		// 获取流程实例id
		String processInstanceId = task.getProcessInstanceId();
		// 根据流程实例获请假单
		Leave leave = leaveService.getByProcessInstanceId(processInstanceId);
		if (state == 1) {
			variables.put("msg", "通过");
		} else {
			leave.setLeaveInfoState("审核未通过");
			variables.put("msg", "未通过");
		}
		LoginInfo loginInfo = (LoginInfo) session.getAttribute("loginInfo");
		if (loginInfo.getUserRole().getId() == 3) {// 领导审批
			Teacher teacher = (Teacher) session.getAttribute("teacher");
			Authentication.setAuthenticatedUserId(teacher.getTeaName() + "[系领导审批]");
		}
		if (loginInfo.getUserRole().getId() == 4) {// 导员审批
			Instructor instructor = (Instructor) session.getAttribute("instructor");
			Authentication.setAuthenticatedUserId(instructor.getInstructorName() + "[辅导员审批]");
			Student student = leave.getLeaveInfoStu();
			session.setAttribute("facultyId", student.getStuFaculty().getId());
			session.setAttribute("majorId", student.getStuMajor().getId());

		}
		variables.put("days", leaveDays);// 设置流程变量
		// 添加批注信息
		taskService.addComment(taskId, processInstanceId, comment);
		// 完成任务
		taskService.complete(taskId, variables);
		// 判断流程是否结束,设置请假状态
		ProcessInstance pi = runtimeService.createProcessInstanceQuery()//
				.processInstanceId(processInstanceId)// 使用流程实例ID查询
				.singleResult();
		if (pi == null) {
			if (!"审核未通过".equals(leave.getLeaveInfoState())) {
				leave.setLeaveInfoState("审核通过");
			}
		}
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtils.write(response, result);
		return null;
	}

	/**
	 * @author XieXing
	 * @createDate 2019年4月23日 上午11:16:34
	 * @description 查詢流程正常走完的历史流程表 : act_hi_actinst
	 * @param response
	 * @param rows
	 * @param page
	 * @param s_name
	 * @param groupId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/finishedList")
	public String finishedList(HttpServletResponse response, String rows, String page, String s_name, String groupId)
			throws Exception {
		// 为什么要这样呢？因为程序首次运行进入后台时，
		// s_name必定是等于null的，如果直接这样填写进查询语句中就会出现 % null %这样就会导致查询结果有误
		if (s_name == null) {
			s_name = "";
		}
		PageInfo pageInfo = new PageInfo();
		Integer pageSize = Integer.parseInt(rows);
		pageInfo.setPageSize(pageSize);
		if (page == null || page.equals("")) {
			page = "1";
		}
		pageInfo.setPageIndex((Integer.parseInt(page) - 1) * pageSize);
		// 创建流程历史实例查询
		List<HistoricTaskInstance> histList = historyService.createHistoricTaskInstanceQuery()
				.taskCandidateGroup(groupId)// 根据角色名称查询
				.taskNameLike("%" + s_name + "%").listPage(pageInfo.getPageIndex(), pageInfo.getPageSize());

		long histCount = historyService.createHistoricTaskInstanceQuery().taskCandidateGroup(groupId)
				.taskNameLike("%" + s_name + "%").count();
		List<MyTask> taskList = new ArrayList<MyTask>();
		// 这里递出没有用的字段，免得给前端页面做成加载压力
		for (HistoricTaskInstance hti : histList) {
			MyTask myTask = new MyTask();
			myTask.setId(hti.getId());
			myTask.setName(hti.getName());
			myTask.setCreateTime(hti.getCreateTime());
			myTask.setEndTime(hti.getEndTime());
			taskList.add(myTask);
		}
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd hh:mm:ss"));
		JSONObject result = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(taskList, jsonConfig);
		result.put("rows", jsonArray);
		result.put("total", histCount);
		ResponseUtils.write(response, result);
		return null;
	}

	/**
	 * @author XieXing
	 * @createDate 2019年4月23日 上午11:16:48
	 * @description 根据任务id查询流程实例的具体执行过程
	 * @param taskId
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/listAction")
	public String listAction(String taskId, HttpServletResponse response) throws Exception {
		HistoricTaskInstance hti = historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
		String processInstanceId = hti.getProcessInstanceId(); // 获取流程实例id
		List<HistoricActivityInstance> haiList = historyService.createHistoricActivityInstanceQuery()
				.processInstanceId(processInstanceId).list();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd hh:mm:ss"));
		JSONObject result = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(haiList, jsonConfig);
		result.put("rows", jsonArray);
		ResponseUtils.write(response, result);
		return null;
	}
}
