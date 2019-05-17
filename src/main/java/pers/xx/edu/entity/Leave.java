package pers.xx.edu.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author XieXing
 * @createDate 2019年4月18日 上午9:54:55
 * @description 请假实体
 */
@Entity
@Table(name = "leave_info")
public class Leave implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "leaveInfo_days", columnDefinition = "int(11) COMMENT '请假天数'")
	private Integer leaveInfoDays;// 请假天数

	@Column(name = "leaveInfo_type", columnDefinition = "varchar(255) COMMENT '请假类型'")
	private String leaveInfoType;// 请假类型
	
	@ManyToOne
	@JoinColumn(name = "leaveInfo_stu", columnDefinition = "int(11) COMMENT '请假学生'")
	private Student leaveInfoStu;//请假学生
	
	@Column(name = "leaveInfo_state", columnDefinition = "varchar(255) COMMENT '审批状态'")
	private String leaveInfoState;//未提交 审核中 审核通过 审核未通过

	@Column(name = "leaveInfo_reason", columnDefinition = "varchar(255) COMMENT '请假原因'")
	private String leaveInfoReason;// 请假原因

	@JSONField(format = "yyyy年MM月dd日")
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@Column(name = "leaveInfo_time", columnDefinition = "datetime COMMENT '请假时间'")
	private Date leaveInfoTime;// 请假时间
	
	@Column(name = "leaveInfo_processInstanceId", columnDefinition = "varchar(255) COMMENT '流程实例id'")
	private String processInstanceId; // 流程实例Id

	@Column(name = "leaveInfo_remark", columnDefinition = "varchar(255) COMMENT '备注'")
	private String leaveInfoRemark;// 备注

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLeaveInfoDays() {
		return leaveInfoDays;
	}

	public void setLeaveInfoDays(Integer leaveInfoDays) {
		this.leaveInfoDays = leaveInfoDays;
	}

	public String getLeaveInfoType() {
		return leaveInfoType;
	}

	public void setLeaveInfoType(String leaveInfoType) {
		this.leaveInfoType = leaveInfoType;
	}

	public String getLeaveInfoReason() {
		return leaveInfoReason;
	}

	public void setLeaveInfoReason(String leaveInfoReason) {
		this.leaveInfoReason = leaveInfoReason;
	}

	public Date getLeaveInfoTime() {
		return leaveInfoTime;
	}

	public void setLeaveInfoTime(Date leaveInfoTime) {
		this.leaveInfoTime = leaveInfoTime;
	}

	public String getLeaveInfoRemark() {
		return leaveInfoRemark;
	}

	public void setLeaveInfoRemark(String leaveInfoRemark) {
		this.leaveInfoRemark = leaveInfoRemark;
	}

	public Student getLeaveInfoStu() {
		return leaveInfoStu;
	}

	public void setLeaveInfoStu(Student leaveInfoStu) {
		this.leaveInfoStu = leaveInfoStu;
	}

	public String getLeaveInfoState() {
		return leaveInfoState;
	}

	public void setLeaveInfoState(String leaveInfoState) {
		this.leaveInfoState = leaveInfoState;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

}
