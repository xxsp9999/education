package pers.xx.edu.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author xiexing
 * @description 首页需要提示的任务信息，当完成后删除
 * @create 2018年10月22日 下午5:56:37
 */
@Entity
@Table(name = "task")
public class Task implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "dept_name",columnDefinition="varchar(255) COMMENT '部门号'")
	private String deptName;
	
	@Column(name = "company_name",columnDefinition="varchar(255) COMMENT '公司名称'")
	private String companyName;
	
	@Column(name = "message",columnDefinition="varchar(255) COMMENT '提示信息'")
	private String message;
	
	@Column(name = "task_url",columnDefinition="varchar(255) COMMENT '任务页面路径'")
	private String taskUrl;
	
	//0->需要提示 ,1->提示关闭
	@Column(name = "status",columnDefinition="varchar(255) COMMENT '状态'")
	private Integer status;


	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public String getDeptName() {
		return deptName;
	}


	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}


	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getTaskUrl() {
		return taskUrl;
	}


	public void setTaskUrl(String taskUrl) {
		this.taskUrl = taskUrl;
	}
	
}
