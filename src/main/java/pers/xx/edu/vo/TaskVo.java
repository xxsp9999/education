package pers.xx.edu.vo;

/**
 * @author xiexing
 * @description 任务提示vo类
 * @create 2018年10月24日 下午2:20:37
 */
public class TaskVo {
	private String message;//提示信息
	private String taskUrl;//跳转路径
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTaskUrl() {
		return taskUrl;
	}
	public void setTaskUrl(String taskUrl) {
		this.taskUrl = taskUrl;
	}
	
}
