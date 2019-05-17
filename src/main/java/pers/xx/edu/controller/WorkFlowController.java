package pers.xx.edu.controller;

import java.util.Map;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pers.xx.edu.entity.Leave;
import pers.xx.edu.service.LeaveService;

/**
 * @author XieXing
 * @createDate 2019年4月21日 上午10:05:35
 * @description 工作流controller
 */
@Controller
@RequestMapping("/workflow")
public class WorkFlowController {
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private LeaveService leaveService;
	/**
	 * @author XieXing
	 * @createDate 2019年4月21日 上午10:08:47
	 * @description 准备表单数据
	 * @return
	 */
	@RequestMapping("/audit")
	public String audit(String taskId,Map<String, Object> map) {
		//根据任务id获取任务
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		//获取流程实例id
		String processInstanceId = task.getProcessInstanceId();
		//根据流程实例获请假单
		Leave leave = leaveService.getByProcessInstanceId(processInstanceId);
		map.put("leave",leave);
		map.put("taskId",taskId);
		return "leave/audit_bz";
	}

}
