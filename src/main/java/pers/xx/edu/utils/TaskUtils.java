package pers.xx.edu.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pers.xx.edu.entity.Task;
import pers.xx.edu.service.TaskService;

/**
 * @author xiexing
 * @description 任务信息提示方法类
 * @create 2018年10月24日 下午2:37:27
 */
public class TaskUtils {
	/**
	 * @description 添加任务提示信息
	 * @param deptId
	 * @param companyName
	 * @param message
	 * @param taskUrl
	 * @param taskService
	 */
	public static void addTask(String deptName,String companyName,String message,String taskUrl,TaskService taskService) {
		Task task = new Task();
		task.setDeptName(deptName);
		task.setCompanyName(companyName);
		task.setMessage(message);
		task.setTaskUrl(taskUrl);
		task.setStatus(0);
		taskService.save(task);
	}
	/**
	 * @description 消除任务提示
	 * @param taskUrl
	 * @param taskService
	 */
	public static void cancelTask(String taskUrl,TaskService taskService) {
		Map<String, Object> params = new HashMap<>();
		params.put("taskUrl = ?", taskUrl);
		List<Task> tasks = taskService.getList(params, null);
		for(Task task:tasks) {
			task.setStatus(1);
		}
	}
	
	/**
	 * @description 根据部门和url消除任务提示
	 * @param taskUrl
	 * @param dept
	 * @param taskService
	 */
	public static void cancelTaskByDeptAndUrl(String taskUrl,String dept,TaskService taskService) {
		Map<String, Object> params = new HashMap<>();
		params.put("taskUrl = ?", taskUrl);
		params.put("deptName = ?", dept);
		List<Task> tasks = taskService.getList(params, null);
		for(Task task:tasks) {
			task.setStatus(1);
		}
	}
}
